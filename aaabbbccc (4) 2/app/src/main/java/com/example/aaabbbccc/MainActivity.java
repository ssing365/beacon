package com.example.aaabbbccc;

import static android.icu.number.Precision.integer;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ResourceManagerInternal;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.service.RunningAverageRssiFilter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

// 비콘이 쓰이는 클래스는 BeaconConsumer 인터페이스를 구현해야한다.
public class MainActivity extends AppCompatActivity implements BeaconConsumer {
    private BeaconManager beaconManager;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static Region mregion = new Region("region1",
            Identifier.parse("22222222-2222-2222-2222-222222222222"),null, null);
    // 감지된 비콘들을 임시로 담을 리스트
    String firebaselocation;

   class difference{
        int location=0;
        double euclideanDistance=0;
        int x;
        int y;

        difference() {
            this.location = location;
            this.euclideanDistance= euclideanDistance;
            this.x = x;
            this.y = y;
        }
        void changelocation(int newValue) { location = newValue; }
        void changerssi(double newValue) { euclideanDistance = newValue; }
        void changex(int newValue) { x = newValue; }
        void changey(int newValue) { y = newValue; }
    }
    class knnDifference {
        int location = 0;
        double diff = 0;

        knnDifference() {
            this.location = location;
            this.diff = diff;
        }
    }

    int px=0, py=0;
    double temp = 0;
    int floor;
    difference[] diff = new difference[19];
    knnDifference[] knndiff= new knnDifference[3];

    double[][] DB = {
            {63.04, 61.36, 69.99, 77.23, 78.49, 83.43, 69.62, 85.64, 86.76},
            {69.99, 54.01, 66.91, 81.38,  86.86, 85.40, 82.37, 0, 82.44},
            {73.24, 65.68, 57.11, 83.69, 81.04, 76.40, 82.84, 84.35, 69.37},
            {72.85, 73.7, 79.19, 81.29, 86.4, 88.38, 82.73, 0, 95},
            {75.59, 84.05, 90.76, 84.11, 85.66, 93.5, 81.27, 98, 0},

            {78.06,79.24,63.5,85.03,79.8,70.19,68.34,65.62,57.47},
            {70.10, 83.34, 85.04, 68.41, 69.31, 65.78, 69.26, 80.83, 82.42},
            {70, 79.41, 87.25, 68.88, 76.74, 77.82, 66.04, 75.07, 80.5},
            {68.93, 78.35, 85.9, 68.71, 78.80, 79.43, 64.60, 71.56, 75.65},
            {64.60, 77.50, 81.26, 68.40, 78.41, 79.11, 55.78, 65.04, 67.23},

            {79.26, 0, 79.84, 86.55, 88, 81.65, 64.74, 54.95, 59.06 },
            {78.06,79.24,63.5,85.03,79.8,70.19,68.34,65.62,57.47},
            {80.66,77.92,65.08,79.98,79.12,69.79,73.98,71.65,61.98},
            {82.28,80.45,67.08,82.9,73.75,64.95,83.54,79.19,66.35},
            {79.22,78.83,67.38,73.56,68.83,63.47,82.78,87,67.02},

            {81,80.2,66.67,75.06,66.53,52.42,84.9,86.23,68.35},
            {87.08,82.06,67.55,84.96,74.43,67.93,81.79,89,68.23},
            {76.16,70.28,61.89,84.94,80.86,76.03,83.44,90,68.27},
            {83.29,89,83.8,65.74,56.45,65.09,82.55,88.93,82.77}
    };







    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 실제로 비콘을 탐지하기 위한 비콘매니저 객체를 초기화
        Button sendbt = (Button) findViewById(R.id.button);
        Button changepage=(Button) findViewById(R.id.button2);
        sendbt.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                TimerTask task = new TimerTask() {
                    @Override//limitToLast(1) 통신 성공시 값을가져오는데 마지막 1개 값만 가져오겠다는것
                    public void run() {
                        databaseReference.child("location").limitToLast(1).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            }
                            else {
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                               firebaselocation= String.valueOf(task.getResult().getValue());
                            }
                        }
                     });
                    }
                };

                new Timer().scheduleAtFixedRate(task, 0l, 1000);


            }
        });
        changepage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,mapActivity.class);
                startActivity(intent);
            }
        });
        beaconManager = BeaconManager.getInstanceForApplication(this);
        textView = findViewById(R.id.Textview);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));



        // 비콘 탐지를 시작한다. 실제로는 서비스를 시작하는것.
        beaconManager.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {

        BeaconManager.setRssiFilterImplClass(RunningAverageRssiFilter.class);
        RunningAverageRssiFilter.setSampleExpirationMilliseconds(20000);
        beaconManager.setBackgroundBetweenScanPeriod(0);
        beaconManager.setBackgroundScanPeriod(400);
        beaconManager.setForegroundBetweenScanPeriod(0);
        beaconManager.setForegroundScanPeriod(400);
        try {
            beaconManager.updateScanPeriods();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        beaconManager.addRangeNotifier(new RangeNotifier() {

            @Override

            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {// 비콘이 감지되면 해당 함수가 호출된다. Collection<Beacon> beacons에는 감지된 비콘의 리스트가,
                // region에는 비콘들에 대응하는 Region 객체가 들어온다.
                ArrayList<Beacon> sortedBeacons;
                ArrayList<Beacon> beaconList = new ArrayList<>();
                ArrayList<Integer> num3 = new ArrayList<Integer>(Arrays.asList(null,null,null,null,null,null,null,null,null));

                double[][] calculatedValue = new double[19][9];
                for(double a[]:calculatedValue){
                Arrays.fill(a,0);}
                double[] sum = new double[19];
                Arrays.fill(sum, 0);
                int[] N = new int[19];
                Arrays.fill(N, 0);
                for(int i=0;i<19;i++){
                    diff[i]= new difference();
                    diff[i].location=i+1;
                }
                diff[0].x = 0; diff[0].y = 0;
                diff[1].x = 9; diff[1].y = 0;
                diff[2].x = 18; diff[2].y = 0;
                diff[3].x = 0; diff[3].y = 9;
                diff[4].x = 0; diff[4].y = 18;
                diff[5].x = 0; diff[5].y = 27;
                diff[6].x = 0; diff[6].y = 36;
                diff[7].x = 0; diff[7].y = 45;
                diff[8].x = 0; diff[8].y = 54;
                diff[9].x = 0; diff[9].y = 62;
                diff[10].x = 9; diff[10].y = 62;
                diff[11].x = 18; diff[11].y = 62;
                diff[12].x = 18; diff[12].y = 54;
                diff[13].x = 18; diff[13].y = 45;
                diff[14].x = 18; diff[14].y = 36;
                diff[15].x = 18; diff[15].y = 27;
                diff[16].x = 18; diff[16].y = 18;
                diff[17].x = 18; diff[17].y = 9;
                diff[18].x = 9; diff[18].y = 0;


                if (beacons.size() >0 ) {
                    sortedBeacons = new ArrayList<Beacon>(beacons);
                    Collections.sort(sortedBeacons, new Comparator<Beacon>() {

                        @Override
                        public int compare(Beacon beacon0, Beacon beacon1) {
                            return new Integer(String.valueOf(beacon0.getId3())).compareTo(new Integer(String.valueOf(beacon1.getId3())));
                        }//Sortedbeacon 엔 beacon minor 값을 오름차순으로 정렬
                    });//num3에 마이너 순서대로rssi값 집어넣음
                    for(int n=0; n< sortedBeacons.size(); n++) {
                        if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(1))) {
                            num3.set(0, sortedBeacons.get(n).getRssi());
                        } else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(2))) {
                            num3.set(1, sortedBeacons.get(n).getRssi());
                        }else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(3))) {
                            num3.set(2, sortedBeacons.get(n).getRssi());
                        }else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(4))) {
                            num3.set(3, sortedBeacons.get(n).getRssi());
                        }else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(5))) {
                            num3.set(4, sortedBeacons.get(n).getRssi());
                        }
                        else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(6))) {
                            num3.set(5, sortedBeacons.get(n).getRssi());
                        }
                        else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(7))) {
                            num3.set(6, sortedBeacons.get(n).getRssi());
                        }
                        else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(8))) {
                            num3.set(7, sortedBeacons.get(n).getRssi());
                        }
                        else if (sortedBeacons.get(n).getId3().equals(Identifier.fromInt(9))) {
                            num3.set(8, sortedBeacons.get(n).getRssi());
                        }
                    }




                    beaconList.addAll(beacons);//비콘리스트는 floor 구별하기위해 rssi값을 오름차순으로정렬한후 내림차순 즉 가장가까운비콘이 첫번째값으로 들어감
                    Collections.sort(beaconList, new Comparator<Beacon>() {

                        @Override
                        public int compare(Beacon beacon0, Beacon beacon1) {
                            return new Integer(String.valueOf(beacon0.getRssi())).compareTo(new Integer(String.valueOf(beacon1.getRssi())));
                        }//beaconlist엔 beacon RSSI값을 오름차순으로 정렬
                    });
                    Collections.reverse(beaconList);//beaconlist값 내림차순
                    floor=new Integer(String.valueOf(beaconList.get(0).getId2()));//beaconlist값 첫번쨰의 메이저값으로 층수구분


                    //승연코드
                   for(int j=0; j<19; j++) {
                       temp=0;
                        for(int i=0; i<9; i++) {
                            if ((num3.get(i) != null)&&(DB[j][i]!=0)) { //비콘이 인식이 되고, 데이터베이스에 값이 있을 때 연산하겠다는 뜻
                                calculatedValue[j][i] = pow((num3.get(i) + DB[j][i]), 2);
                                N[j] += 1;                       //감지된 비콘 개수
                            }
                        }
                        for (int k = 0; k < 9; k++) {

                            temp = temp + calculatedValue[j][k];
                            sum[j] = sqrt(temp);
                        }

                        diff[j].euclideanDistance = sum[j] / N[j];
                    }
                    Comparator<difference> comparator = new Comparator<difference>() {
                        @Override
                        public int compare(difference a, difference b) {
                            return (int)(a.euclideanDistance - b.euclideanDistance);
                        }
                    };
                    Arrays.sort(diff, comparator);//승연코드
                    double knn3x= 0 ,knn3y=0;
                    knn3x=(diff[0].x + diff[1].x + diff[2].x )/3;
                    knn3y=(diff[0].y + diff[1].y + diff[2].y )/3;

                        for(int i=0 ; i<3 ; i++){
                            knndiff[i] = new knnDifference();
                        }

                    for(int i=0; i<3 ; i++){
                        knndiff[i].location = diff[i].location;
                        knndiff[i].diff = sqrt(pow((diff[i].x - knn3x),2) + pow((diff[i].y - knn3y),2));
                    }
                    Comparator<knnDifference> knncomparator = new Comparator<knnDifference>() {
                        @Override
                        public int compare(knnDifference a, knnDifference b) {
                            return (int)(a.diff - b.diff);
                        }
                    };
                    Arrays.sort(knndiff, knncomparator);
                    //x,y,z변수에 집어넣음
                    // 절대값들의 합을 리스트에 집어넣음
                    // 리스트값을 오름차순으로 정렬 가장 낮은값이 리스트 첫번째 값에 정렬됨
                    //만약 리스트 첫째값이 변수x값과 같다면 현재 스마트폰에 측정되는 rssi값들이 X위치에서 측정되었던 RSSI값과 비슷하다는 의미 이므로
                    //사용자가 X위치에 있다고 판단
/*
                    textView.setText("");
                    textView.append("floor:::"+floor);
                    for (int i = 0; i < sortedBeacons.size(); i++) {

                        textView.append("\nUUID:::" + sortedBeacons.listIterator(i).next().getId1());
                        textView.append("\nMAJOR:::" + sortedBeacons.listIterator(i).next().getId2());
                        textView.append("\nMINOR:::" + sortedBeacons.listIterator(i).next().getId3());
                        textView.append("\nTXPOWER:::" + sortedBeacons.listIterator(i).next().getTxPower());
                        textView.append("\nRSSI:::" + sortedBeacons.listIterator(i).next().getRssi());
                        textView.append("\ndistance:::" + sortedBeacons.listIterator(i).next().getDistance());

                    }
                    for (int n=0; n<num3.size(); n++){
                        System.out.println((n+1)+" "+num3.get(n));
                    }*/
                    //승연코드
                    databaseReference.child("location").push().setValue(diff[0].location);
                    textView.setText("");
                    textView.append("\nfirebaselocation:::" + firebaselocation);
                    textView.append("\nknn3-위치1:::" + knndiff[0].location +"knn1-:::"+diff[0].location);
                    textView.append("\n위치2:::" + knndiff[1].location);
                    textView.append("\n위치3:::" + knndiff[2].location);

                    for (int p = 0; p < beaconList.size(); p++) {
                        System.out.println(beaconList.listIterator(p).next().getId3() + " " +beaconList.listIterator(p).next().getRssi());
                        textView.append("\nMINOR:::" + beaconList.listIterator(p).next().getId3());
                        textView.append("\nRSSI:::" + beaconList.listIterator(p).next().getRssi());
                        textView.append("\n:::::::::::::::::::::::::::::::::::::::::");
                    }//승연코드


                }

            }

        });


        try {
            beaconManager.startRangingBeaconsInRegion(mregion);
        } catch (RemoteException e) {   }

    }

}