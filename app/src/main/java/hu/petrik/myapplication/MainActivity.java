package hu.petrik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button rogzitogomb;
    private TextView kiiroview;
    DBHelper adatbazis;
    private Button olvasogomb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        kiiroview.setMovementMethod(new ScrollingMovementMethod());
        rogzitogomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, rogzitoActivity.class));
                finish();
            }

        });

        olvasogomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = adatbazis.listaz();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nincs az adatbázisvan bejegyzés", Toast.LENGTH_SHORT).show();

                } else {
                    StringBuilder bobTheBuilder = new StringBuilder();
                    while (adatok.moveToNext()) {
                        bobTheBuilder.append("ID: ").append(adatok.getInt(0));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Vezetéknév: ").append(adatok.getString(1));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("Keresztnév: ").append(adatok.getString(2));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append("JEGY: ").append(adatok.getInt(3));
                        bobTheBuilder.append(System.lineSeparator());
                        bobTheBuilder.append(System.lineSeparator());
                    }
                    kiiroview.setText(bobTheBuilder.toString());
                }

            }
        });


    }


    private void init() {
        rogzitogomb = findViewById(R.id.rogzitogomb);
        kiiroview = findViewById(R.id.kiiroview);
        adatbazis = new DBHelper(MainActivity.this);
        olvasogomb = findViewById(R.id.olvasogomb);
    }
}