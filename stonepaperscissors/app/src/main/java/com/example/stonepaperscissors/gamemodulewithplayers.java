package com.example.stonepaperscissors;

import static com.example.stonepaperscissors.R.id.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stonepaperscissors.databinding.ActivityGamemodulewithplayersBinding;

import java.util.Random;

public class gamemodulewithplayers extends AppCompatActivity {
    ActivityGamemodulewithplayersBinding binding;

    String n1, n2;
    Dialog dialog;
    private TextView textViewPlayer1, textViewPlayer2;

    int player1Score = 0;
    int player2Score = 0;
    MediaPlayer winMediaPlayer;
    MediaPlayer loseMediaPlayer;
    MediaPlayer tieMediaPlayer;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemodulewithplayers);
        binding = ActivityGamemodulewithplayersBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        dialog = new Dialog(this);
        winMediaPlayer = MediaPlayer.create(this, R.raw.win_sound);
        loseMediaPlayer = MediaPlayer.create(this, R.raw.lose_sound);
        tieMediaPlayer = MediaPlayer.create(this, R.raw.tie_sound);

        textViewPlayer1 = findViewById(R.id.textViewPlayer1);
        textViewPlayer2 = findViewById(R.id.textViewPlayer2);

        // Receive player names from intent
        Intent intent = getIntent();
        String player1Name = intent.getStringExtra("PLAYER1_NAME");
        String player2Name = intent.getStringExtra("PLAYER2_NAME");

        // Set player names in TextViews
        textViewPlayer1.setText("Player1: " + player1Name);
        textViewPlayer2.setText("Player2: " + player2Name);


        binding.Rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human1.setImageResource(R.drawable.stoneg);
                n1 = "Rock";
                // Delay for player 2's turn
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result();
                    }
                }, 1000); // Delay of 1 second
            }
        });

        binding.Paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human1.setImageResource(R.drawable.paperg);
                n1 = "Paper";
                // Delay for player 2's turn
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result();
                    }
                }, 1000); // Delay of 1 second
            }
        });

        binding.Scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human1.setImageResource(R.drawable.scissorsg);
                n1 = "Scissor";
                // Delay for player 2's turn
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        result();
                    }
                }, 1000); // Delay of 1 second
            }
        });

        binding.Rock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human2.setImageResource(R.drawable.stoneg);
                n2 = "Rock";
                result(); // Immediately proceed to result after player 2's choice
            }
        });

        binding.Paper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human2.setImageResource(R.drawable.paperg);
                n2 = "Paper";
                result(); // Immediately proceed to result after player 2's choice
            }
        });

        binding.Scissor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human2.setImageResource(R.drawable.scissorsg);
                n2 = "Scissor";
                result(); // Immediately proceed to result after player 2's choice
            }
        });
    }




        public void result() {
        if (n1 != null && n2 != null) {
            if (n1.equals(n2)) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showtiedialog();
                        Reset();

                    }
                }, 1000);
            } else if ((n1.equals("Rock") && n2.equals("Scissor")) ||
                    (n1.equals("Paper") && n2.equals("Rock")) ||
                    (n1.equals("Scissor") && n2.equals("Paper"))) {
                player1Score++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showwindialog();
                        Reset();

                    }
                }, 1000);
            } else {
                player2Score++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showlossdialog();
                        Reset();

                    }
                }, 1000);
            }
            // Update the TextViews to display the updated scores
            binding.playerScore1.setText("Player1: " + player1Score);
            binding.playerScore2.setText("Player2: " + player2Score);
        } else {
            // Handle the case where n1 or n2 is null
            // You may want to display a message or handle it in some way appropriate for your application
        }
    }


    private void showlossdialog() {
        dialog.setContentView(R.layout.loss_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = dialog.findViewById(R.id.close);
        dialog.show();
        loseMediaPlayer.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Reset(); // Reset images after dismissing the dialog
            }
        });
    }

    private void showwindialog() {
        dialog.setContentView(R.layout.win_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = dialog.findViewById(R.id.close);
        dialog.show();
        winMediaPlayer.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Reset(); // Reset images after dismissing the dialog
            }
        });
    }

    private void showtiedialog() {
        dialog.setContentView(R.layout.tie_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = dialog.findViewById(R.id.close);
        dialog.show();
        tieMediaPlayer.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Reset(); // Reset images after dismissing the dialog
            }
        });
    }



    private void Reset() {
        binding.human1.setImageResource(R.drawable.human);
        binding.human2.setImageResource(R.drawable.human);

    }

}


