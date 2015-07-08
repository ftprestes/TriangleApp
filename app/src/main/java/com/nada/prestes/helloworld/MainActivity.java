package com.nada.prestes.helloworld;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by prestes on 04/07/15.
 */
public class MainActivity extends Activity{

    private TextView text;
    private SeekBar seekBar;
    private Button btnPlay, btnStop;
    private SoundPool triangleSound = null;
    private float j = 1.0f;
    private int sID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button)findViewById(R.id.buttonPlay);
        btnStop = (Button)findViewById(R.id.buttonStop);
        seekBar = (SeekBar)findViewById(R.id.speedChoose);
        seekBar.setVisibility(View.INVISIBLE);
        text = (TextView) findViewById(R.id.textView);
        triangleSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sID = triangleSound.load(MainActivity.this, R.drawable.music_triangulo, 1);
                triangleSound.play(sID, 1, 1, 1, -1, 1);


                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        text.setText(Integer.toString(progress) + "X");
                        if (progress == 0) {

                        }
                        if (progress == 1) {

                        }
                        if (progress == 2) {
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                });
                seekBar.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(1);
                text.setText(Integer.toString(1) + "X");
                seekBar.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
   protected void onPause() {
       super.onPause();
   }
}
