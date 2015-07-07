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
        seekBar.setVisibility(View.INVISIBLE);
        text = (TextView) findViewById(R.id.textView);

        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                triangleSound1.start();
                triangleSound1.setLooping(true);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        p = progress;
                        text.setText(Integer.toString(p) + "X");
                        if (p == 0) {
                            if (triangleSound1.isPlaying())
                                triangleSound1.pause();
                            if (triangleSound2.isPlaying())
                                triangleSound2.pause();
                            triangleSound0.start();
                            triangleSound0.setLooping(true);
                        }
                        if (p == 1) {
                            if (triangleSound0.isPlaying())
                                triangleSound0.pause();
                            if (triangleSound2.isPlaying())
                                triangleSound2.pause();
                            triangleSound1.start();
                            triangleSound1.setLooping(true);
                        }
                        if (p == 2) {
                            if (triangleSound1.isPlaying())
                                triangleSound1.pause();
                            if (triangleSound0.isPlaying())
                                triangleSound0.pause();
                            triangleSound2.start();
                            triangleSound2.setLooping(true);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
                seekBar.setProgress(1);
                seekBar.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(Integer.toString(1) + "X");
                if(triangleSound1.isPlaying())
                    triangleSound1.pause();
                if(triangleSound0.isPlaying())
                    triangleSound0.pause();
                if(triangleSound2.isPlaying())
                    triangleSound2.pause();
                seekBar.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        triangleSound1.release();
        triangleSound0.release();
        triangleSound2.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        triangleSound1.release();
        triangleSound0.release();
        triangleSound2.release();
    }

    @Override
   protected void onPause() {
       super.onPause();
        triangleSound1.release();
        triangleSound0.release();
        triangleSound2.release();
   }
}
