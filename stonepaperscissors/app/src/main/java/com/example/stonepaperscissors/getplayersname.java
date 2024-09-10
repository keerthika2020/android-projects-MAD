package com.example.stonepaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class getplayersname extends AppCompatActivity {
    private EditText editTextPlayer1, editTextPlayer2;
    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getplayersname);
        editTextPlayer1 = findViewById(R.id.editTextPlayer1);
        editTextPlayer2 = findViewById(R.id.editTextPlayer2);
        buttonStartGame = findViewById(R.id.buttonStartGame);

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1Name = editTextPlayer1.getText().toString();
                String player2Name = editTextPlayer2.getText().toString();

                // Pass player names to the game activity
                Intent intent = new Intent(getplayersname.this, gamemodulewithplayers.class);
                intent.putExtra("PLAYER1_NAME", player1Name);
                intent.putExtra("PLAYER2_NAME", player2Name);
                startActivity(intent);
            }
        });
    }
}