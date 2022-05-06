package com.example.aaabbbccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class mapActivity extends AppCompatActivity {

    Timer timerCall=null;
    TimerTask task=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        DatabaseReference mDatabase ;
        mDatabase = FirebaseDatabase.getInstance().getReference();


        timerCall = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                mDatabase.child("location").limitToLast(1).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();
                            for (DataSnapshot result : snapshot.getChildren()) {
                                int locationscan = result.getValue(Integer.class);
                                ImageView imageView = findViewById(R.id.user1);
                                ImageView imageView2 = findViewById(R.id.user2);
                                ImageView imageView3 = findViewById(R.id.user3);
                                ImageView imageView4 = findViewById(R.id.user4);
                                ImageView imageView5 = findViewById(R.id.user5);
                                ImageView imageView6 = findViewById(R.id.user6);
                                ImageView imageView7 = findViewById(R.id.user7);
                                ImageView imageView8 = findViewById(R.id.user8);
                                ImageView imageView9 = findViewById(R.id.user9);
                                ImageView imageView10 = findViewById(R.id.user10);
                                ImageView imageView11 = findViewById(R.id.user11);
                                ImageView imageView12 = findViewById(R.id.user12);
                                ImageView imageView13 = findViewById(R.id.user13);
                                ImageView imageView14 = findViewById(R.id.user14);
                                ImageView imageView15 = findViewById(R.id.user15);
                                ImageView imageView16 = findViewById(R.id.user16);
                                ImageView imageView17 = findViewById(R.id.user17);
                                ImageView imageView18 = findViewById(R.id.user18);
                                ImageView imageView19 = findViewById(R.id.user19);
                                switch (locationscan){
                                    case 1:
                                        imageView.setVisibility(View.VISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 2:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.VISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 3:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.VISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 4:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.VISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 5:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.VISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 6:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.VISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 7:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.VISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 8:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.VISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 9:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.VISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 10:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.VISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 11:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.VISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 12:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.VISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 13:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.VISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 14:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.VISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 15:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.VISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 16:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.VISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 17:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.VISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 18:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.VISIBLE);
                                        imageView19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 19:
                                        imageView.setVisibility(View.INVISIBLE);
                                        imageView2.setVisibility(View.INVISIBLE);
                                        imageView3.setVisibility(View.INVISIBLE);
                                        imageView4.setVisibility(View.INVISIBLE);
                                        imageView5.setVisibility(View.INVISIBLE);
                                        imageView6.setVisibility(View.INVISIBLE);
                                        imageView7.setVisibility(View.INVISIBLE);
                                        imageView8.setVisibility(View.INVISIBLE);
                                        imageView9.setVisibility(View.INVISIBLE);
                                        imageView10.setVisibility(View.INVISIBLE);
                                        imageView11.setVisibility(View.INVISIBLE);
                                        imageView12.setVisibility(View.INVISIBLE);
                                        imageView13.setVisibility(View.INVISIBLE);
                                        imageView14.setVisibility(View.INVISIBLE);
                                        imageView15.setVisibility(View.INVISIBLE);
                                        imageView16.setVisibility(View.INVISIBLE);
                                        imageView17.setVisibility(View.INVISIBLE);
                                        imageView18.setVisibility(View.INVISIBLE);
                                        imageView19.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        } else {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                    }
                });
            }
        };
        timerCall.schedule(task,1000,1000);

        timerCall = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                mDatabase.child("state").limitToLast(1).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();
                            ImageView imageView = findViewById(R.id.fire);
                            for (DataSnapshot result : snapshot.getChildren()) {
                                int firescan = result.getValue(Integer.class);
                                if (firescan == 0) {
                                    imageView.setVisibility(View.VISIBLE);
                                } else {
                                    imageView.setVisibility(View.INVISIBLE);
                                }
                            }
                        } else {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                    }
                });
            }
        };
        timerCall.schedule(task,1000,1000);

    }
}
