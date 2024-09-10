package com.example.databaseemployee;
import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText ee1, ee2, ee3, ee4;
    Button bb1, bb2;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ee1 = findViewById(R.id.e1);
        ee2 = findViewById(R.id.e2);
        ee3 = findViewById(R.id.e3);
        ee4 = findViewById(R.id.e4);
        bb1 = findViewById(R.id.b1);
        bb2 = findViewById(R.id.b2);

        builder = new AlertDialog.Builder(this);

        db = openOrCreateDatabase("db1", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee (name VARCHAR(50), id INTEGER, department VARCHAR(50), blood_group VARCHAR(10))");

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ee1.getText().toString();
                int id = Integer.parseInt(ee2.getText().toString());
                String department = ee3.getText().toString();
                String bloodGroup = ee4.getText().toString();

                db.execSQL("INSERT INTO employee (name, id, department, blood_group) VALUES ('" + name + "', " + id + ", '" + department + "', '" + bloodGroup + "')");
                builder.setMessage("Record inserted");
                builder.show();
            }
        });

        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                Cursor c = db.rawQuery("SELECT * FROM employee", null);
                while (c.moveToNext()) {
                    sb.append("Employee Name: " + c.getString(c.getColumnIndex("name")) + "\n");
                    sb.append("Employee ID: " + c.getInt(c.getColumnIndex("id")) + "\n");
                    sb.append("Department: " + c.getString(c.getColumnIndex("department")) + "\n");
                    sb.append("Blood Group: " + c.getString(c.getColumnIndex("blood_group")) + "\n\n");
                }
                builder.setMessage(sb.toString());
                builder.show();
            }
        });
    }
}