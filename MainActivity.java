package com.example.aaabbbccc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ResourceManagerInternal;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.service.RunningAverageRssiFilter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;

// 비콘이 쓰이는 클래스는 BeaconConsumer 인터페이스를 구현해야한다.
public class MainActivity extends AppCompatActivity implements BeaconConsumer {
    protected static final String TAG2=":::RangingActivity:::";
    private BeaconManager beaconManager;
    // 감지된 비콘들을 임시로 담을 리스트


    int x=0 ,y=0 ,z=0, px=0, py=0;
    int floor;
    ArrayList<Integer> location=new ArrayList<>();

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 실제로 비콘을 탐지하기 위한 비콘매니저 객체를 초기화
        beaconManager = BeaconManager.getInstanceForApplication(this);
        textView = findViewById(R.id.Textview);
        BeaconManager.setRssiFilterImplClass(RunningAverageRssiFilter.class);
        RunningAverageRssiFilter.setSampleExpirationMilliseconds(20000);

        // 여기가 중요한데, 기기에 따라서 setBeaconLayout 안의 내용을 바꿔줘야 하는듯 싶다.
        // 필자의 경우에는 아래처럼 하니 잘 동작했음.
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));


        beaconManager.setForegroundBetweenScanPeriod(0);
        beaconManager.setForegroundScanPeriod(100);
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
        
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override

            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {// 비콘이 감지되면 해당 함수가 호출된다. Collection<Beacon> beacons에는 감지된 비콘의 리스트가,
                // region에는 비콘들에 대응하는 Region 객체가 들어온다.
                ArrayList<Beacon> sortedBeacons;
                ArrayList<Beacon> beaconList = new ArrayList<>();

                if (beacons.size() ==3) {
                    sortedBeacons = new ArrayList<Beacon>(beacons);
                    Collections.sort(sortedBeacons, new Comparator<Beacon>() {

                        @Override
                        public int compare(Beacon beacon0, Beacon beacon1) {
                            return new Integer(String.valueOf(beacon0.getId2())).compareTo(new Integer(String.valueOf(beacon1.getId2())));
                        }//Sortedbeacon 엔 beacon major 값을 오름차순으로 정렬
                    });
                    beaconList.addAll(beacons);
                    Collections.sort(beaconList, new Comparator<Beacon>() {

                        @Override
                        public int compare(Beacon beacon0, Beacon beacon1) {
                            return new Integer(String.valueOf(beacon0.getRssi())).compareTo(new Integer(String.valueOf(beacon1.getRssi())));
                        }//beaconlist엔 beacon RSSI값을 오름차순으로 정렬
                    });
                    Collections.reverse(beaconList);//beaconlist값 내림차순
                    floor=new Integer(String.valueOf(beaconList.get(0).getId2()));//beaconlist값 첫번쨰의 메이저값으로 층수구분
                    
                    int a = -30;
                    x=Math.abs(sortedBeacons.get(0).getRssi()+19)+Math.abs(sortedBeacons.get(1).getRssi()+47)+Math.abs(sortedBeacons.get(2).getRssi()+38);
                    y=Math.abs(sortedBeacons.get(0).getRssi()+45)+Math.abs(sortedBeacons.get(1).getRssi()+18)+Math.abs(sortedBeacons.get(2).getRssi()+40);
                    z=Math.abs(sortedBeacons.get(0).getRssi()+42)+Math.abs(sortedBeacons.get(1).getRssi()+50)+Math.abs(sortedBeacons.get(2).getRssi()+22);
                    //사용자가 X,Y,Z위치일떄 각 비콘의 평균 rssi값[ex)1번비콘-19,2번비콘-47,3번비콘-38]을 현재 스마트폰에 측정되는 각비콘 값들의 rssi값에서 뺸후 절대값을 더해
                    //x,y,z변수에 집어넣음
                    location.add(x);location.add(y);location.add(z);// 절대값들의 합을 리스트에 집어넣음
                    Collections.sort(location);// 리스트값을 오름차순으로 정렬 가장 낮은값이 리스트 첫번째 값에 정렬됨
                    

                    //만약 리스트 첫째값이 변수x값과 같다면 현재 스마트폰에 측정되는 rssi값들이 X위치에서 측정되었던 RSSI값과 비슷하다는 의미 이므로
                    //사용자가 X위치에 있다고 판단
                    if (location.get(0) == x ) {
                        px = 2;
                        py = 2;
                    } else if (location.get(0) == y ) {
                        px = 3;
                        py = 3;
                    } else if (location.get(0) == z ){
                        px=1;
                        py=1;


                    }
                    textView.setText("");
                    for (int i = 0; i < sortedBeacons.size(); i++) {
                        textView.append("floor::"+floor);
                        textView.append("px::"+px+"  py::"+py);
                        System.out.println(sortedBeacons.listIterator(i).next().getId1() + " " + sortedBeacons.listIterator(i).next().getRssi());
                        textView.append("\nUUID:::" + sortedBeacons.listIterator(i).next().getId1());
                        textView.append("\nMAJOR:::" + sortedBeacons.listIterator(i).next().getId2());
                        textView.append("\nMINOR:::" + sortedBeacons.listIterator(i).next().getId3());
                        textView.append("\nTXPOWER:::" + sortedBeacons.listIterator(i).next().getTxPower());
                        textView.append("\nRSSI:::" + sortedBeacons.listIterator(i).next().getRssi());
                        textView.append("\ndistance:::" + sortedBeacons.listIterator(i).next().getDistance());
                    }


                }

            }

        });


        try {
            Region region = new Region("region1",
                    null,null,
                    Identifier.parse("1"));
            beaconManager.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {   }

    }

}