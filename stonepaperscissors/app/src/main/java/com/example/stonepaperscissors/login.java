package com.example.stonepaperscissors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class login extends AppCompatActivity {
    EditText uname, pass;
    Button bb1 , bb3 ;
    boolean passVisibility;
    DBHelper DB;
    String u1;
    String p1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = (EditText) findViewById(R.id.e1);
        pass = (EditText) findViewById(R.id.e2);
        bb1 = (Button)findViewById(R.id.b1) ;
        bb3 = (Button)findViewById(R.id.b3) ;

        DB = new DBHelper(this);

        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= pass.getRight() - pass.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = pass.getSelectionEnd();
                        if (passVisibility) {
                            //set drawable image here
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                            //for hide pass
                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisibility = false;
                        } else {
                            //set drawable image here
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);
                            //for show pass
                            pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisibility = true;

                        }
                        pass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        int maxLength = 20;
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxLength);
        uname.setFilters(filters);




        //Database connect
        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u1 = uname.getText().toString();
                String p1 = pass.getText().toString();
                if(u1.equals("")||p1.equals(""))
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkusername(u1);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(u1,p1);
                        if (insert == true) {
                            Toast.makeText(login.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), gamemodulewithcomputer.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        //uname.setError("The user aldready exists");
                        Toast.makeText(login.this, "User name already exits", Toast.LENGTH_SHORT).show();
                    }
                }
                Boolean checkpass = DB.checkusernamepassword(u1,p1);
                if (checkpass==false){
                    Toast.makeText(login.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(login.this, "You have logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), gamemodulewithcomputer.class);
                    startActivity(intent);
                }
            }
        });
        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u1 = uname.getText().toString();
                String p1 = pass.getText().toString();
                if(u1.equals("")||p1.equals(""))
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkusername(u1);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(u1,p1);
                        if (insert == true) {
                            Toast.makeText(login.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), getplayersname.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        //uname.setError("The user aldready exists");
                        Toast.makeText(login.this, "User name already exits", Toast.LENGTH_SHORT).show();
                    }
                }
                Boolean checkpass = DB.checkusernamepassword(u1,p1);
                if (checkpass==false){
                    Toast.makeText(login.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(login.this, "You have logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), getplayersname.class);
                    startActivity(intent);
                }
            }
        });




    }
}

//int maxLength = 25;
//        InputFilter[] filters = new InputFilter[1];
//        filters[0] = new InputFilter.LengthFilter(maxLength);
//        uname.setFilters(filters);
//
//        String password = pass.getText().toString();
//        String password_pattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
//        if (password.isEmpty() || password.length() < 6) {
//            pass.setError("Password cannot be less than 6 characters!");
//        }else if(password != password_pattern){
//            pass.setError("The passwword muct have atleat one of the following");
//
//        }
//        else {
//            pass.setError(null);
//            pass.setError("The password field can't be left empty");
//        }
//
//
//    }
//
//    public static boolean validatePassword(final String pass) {
//
//        Pattern pattern;
//        Matcher matcher;
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(pass);
//
//        return matcher.matches();
//    }
//
//}
