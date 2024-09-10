package com.example.dessert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bb1,bb2,bb3;
    TextView tv1,tv2,tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.t1);
        tv2=(TextView)findViewById(R.id.t2);
        tv3=(TextView)findViewById(R.id.t3);
        bb1=(Button)findViewById(R.id.b1);
        bb2=(Button)findViewById(R.id.b2);
        bb3=(Button)findViewById(R.id.b3);
        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),addtocart.class);
                String s = tv1.getText().toString();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                in.putExtra("a","s");
                startActivity(in);
            }
        });
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),addtocart.class);
                String s1 = tv2.getText().toString();
                Toast.makeText(MainActivity.this, s1, Toast.LENGTH_SHORT).show();
                in.putExtra("b","s1");
                startActivity(in);
            }
        });
        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),addtocart.class);
                String s2 = tv3.getText().toString();
                Toast.makeText(MainActivity.this, s2, Toast.LENGTH_SHORT).show();
                in.putExtra("c","s2");
                startActivity(in);
            }
        });

    }
}

