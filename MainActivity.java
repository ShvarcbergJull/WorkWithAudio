package com.example.workwithaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button play, pause, stop;
    Spinner spinner;
    String prev_song = "";
    ArrayList<MediaPlayer> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        players.add(MediaPlayer.create(this, R.raw.comp1));
        players.add(MediaPlayer.create(this, R.raw.comp2));
        players.add(MediaPlayer.create(this, R.raw.comp3));

        play = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        spinner = findViewById(R.id.compos);
    }

    public  void onClick(View v) {
        if (!spinner.getSelectedItem().toString().equals("NO")) { // проверяем, что есть выбранная запись
            int index = Integer.valueOf(spinner.getSelectedItem().toString().substring(spinner.getSelectedItem().toString().length() - 1)); // так как запси в массиве, нужен индекс её вытащить
            index -= 1;
            if (!prev_song.equals(spinner.getSelectedItem().toString()) && (!prev_song.equals(""))) { // вот этот весь if да и само prev_song, воспроизведение записей не накладывалось
                int temp = Integer.valueOf(prev_song.substring(prev_song.length() - 1)) - 1;
                players.get(temp).pause();
            }
            prev_song = spinner.getSelectedItem().toString();
            if (v.getId() == R.id.start) {
                players.get(index).start();
            }

            if (v.getId() == R.id.pause) {
                players.get(index).pause();
            }

            if (v.getId() == R.id.stop) {
                players.get(index).stop();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "Choose file", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
