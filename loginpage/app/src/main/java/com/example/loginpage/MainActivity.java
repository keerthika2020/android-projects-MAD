package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText uname, pass;
    boolean passVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (EditText) findViewById(R.id.e1);
        pass = (EditText) findViewById(R.id.e2);
        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= pass.getRight() - pass.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = pass.getSelectionEnd();
                        if (passVisibility) {
                            //set drawable image here
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off_black_24dp, 0);
                            //for hide pass
                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisibility = false;
                        } else {
                            //set drawable image here
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_black_24dp, 0);
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