package com.example.stonepaperscissors;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.stonepaperscissors.databinding.ActivityGamemodulewithcomputerBinding;
import com.example.stonepaperscissors.databinding.ActivityGamemodulewithplayersBinding;
import com.example.stonepaperscissors.databinding.ActivityMainBinding;

import java.util.Random;

import kotlin.random.URandomKt;

public class gamemodulewithcomputer extends AppCompatActivity {
    ActivityGamemodulewithcomputerBinding binding;

    String n1,n2;
    Dialog dialog;

  int playerScore=0;
  int computerScore=0;
  MediaPlayer winMediaPlayer;
  MediaPlayer loseMediaPlayer;
  MediaPlayer tieMediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGamemodulewithcomputerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Context context;
        dialog=new Dialog(this);
        winMediaPlayer = MediaPlayer.create(this, R.raw.win_sound);
        loseMediaPlayer = MediaPlayer.create(this, R.raw.lose_sound);
        tieMediaPlayer = MediaPlayer.create(this,R.raw.tie_sound);

        ImageButton resetButton = findViewById(R.id.reset);

        // Set OnClickListener for the reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset playerScore and computerScore to zero
                playerScore = 0;
                computerScore = 0;

                // Update the TextViews to display the updated scores
                binding.playerScore.setText("Player: " + playerScore);
                binding.computerScore.setText("Computer: " + computerScore);
            }
        });


        binding.Rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human.setImageResource(R.drawable.stoneg);
                n1="Rock";
                display();
                result();

            }
        });
        binding.Paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human.setImageResource(R.drawable.paperg);
                n1="Paper";
                display();
                result();
            }
        });
        binding.Scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human.setImageResource(R.drawable.scissorsg);
                n1="Scissor";
                display();
                result();
            }
        });
    }
    public void display(){

        Random rnd = new Random();
        int n=rnd.nextInt(3);
        if(n==0){
            n2="Rock";
            binding.robot.setImageResource(R.drawable.stoneg);
            binding.robot.setRotation(180);
        }else if (n==1){
            n2="Paper";
            binding.robot.setImageResource(R.drawable.paperg);
            binding.robot.setRotation(180);
        }else if (n==2){
            n2="Scissor";
            binding.robot.setImageResource(R.drawable.scissorsg);
            binding.robot.setRotation(180);
        }
    }
    public void result(){
        if (n1.equals(n2)){
            //Here user and robot is same value so u can tie.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showtiedialog();
                }
            },1000);

        }else if (n1.equals("Rock") && n2.equals("Scissor") || n1.equals("Paper") && n2.equals("Rock") || n1.equals("Scissor") && n2.equals("Paper")){
            playerScore++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showwindialog();
                }
            },1000);
        }else{
            computerScore++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showlossdialog();
                }
            },1000);
        }
        // Update the TextViews to display the updated scores
        binding.playerScore.setText("Player: " + playerScore);
        binding.computerScore.setText("robot: " + computerScore);
    }

    private void showlossdialog() {
        dialog.setContentView(R.layout.loss_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close=dialog.findViewById(R.id.close);
        dialog.show();
        loseMediaPlayer.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Reset();
            }
        });
    }

    private void showwindialog() {
        dialog.setContentView(R.layout.win_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close=dialog.findViewById(R.id.close);
        dialog.show();
        winMediaPlayer.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Reset();
            }
        });
    }

    private void showtiedialog() {
        dialog.setContentView(R.layout.tie_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close=dialog.findViewById(R.id.close);
        dialog.show();
        tieMediaPlayer.start();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Reset();
            }
        });
    }
    private void Reset(){
        binding.human.setImageResource(R.drawable.human);
        binding.robot.setImageResource(R.drawable.robot);
        binding.robot.setRotation(0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        winMediaPlayer.release();
        loseMediaPlayer.release();
    }


}