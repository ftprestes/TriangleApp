package com.nada.prestes.helloworld;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by prestes on 04/07/15.
 */
public class MainActivity extends Activity{

    MediaPlayer triangleSound0, triangleSound1, triangleSound2;
    int p = 1;
    private TextView text;
    private SeekBar seekBar;
    private Button btnPlay, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triangleSound1 = MediaPlayer.create(MainActivity.this, R.drawable.music_triangulo);
        triangleSound0 = MediaPlayer.create(MainActivity.this, R.drawable.teste0);
        triangleSound2 = MediaPlayer.create(MainActivity.this, R.drawable.teste2);
        btnPlay = (Button)findViewById(R.id.buttonPlay);
        btnStop = (Button)findViewById(R.id.buttonStop);
        seekBar = (SeekBar)findViewById(R.id.speedChoose);
        text = (TextView) findViewById(R.id.textView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = progress;
                text.setText(Integer.toString(p));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                p = seekBar.getProgress();
            }
        });

        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p == 0) {
                    triangleSound0.start();
                    triangleSound0.setLooping(true);
                }
                if(p == 1){
                    triangleSound1.start();
                    triangleSound1.setLooping(true);
                }
                if(p == 2) {
                    triangleSound2.start();
                    triangleSound2.setLooping(true);
                }
                seekBar.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p == 0) {
                    triangleSound0.pause();
                }
                if(p == 1){
                    triangleSound1.pause();
                }
                if(p == 2) {
                    triangleSound2.pause();
                }
                seekBar.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        triangleSound1.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        triangleSound1.release();
    }

    @Override
   protected void onPause() {
       super.onPause();
       triangleSound1.release();
   }
}
