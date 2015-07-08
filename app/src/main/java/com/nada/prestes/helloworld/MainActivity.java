package com.nada.prestes.helloworld;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by prestes on 04/07/15.
 */
public class MainActivity extends Activity {

    public static final float MIN_RANGE = 0.8f;
    public static final float MAX_RANGE = 1.5f;
    public static final int NUMBER_OF_STEPS = 20;
    public static final int DEFAULT_RANGE = 10;

    private TextView text;
    private SeekBar seekBar;
    private Button btnPlay, btnStop;
    private SoundPool triangleSound;
    private float j = 1.0f;
    private int soundId, streamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.buttonPlay);
        btnStop = (Button) findViewById(R.id.buttonStop);
        seekBar = (SeekBar) findViewById(R.id.speedChoose);
        seekBar.setVisibility(View.INVISIBLE);
        text = (TextView) findViewById(R.id.textView);
        triangleSound = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

        soundId = triangleSound.load(MainActivity.this, R.drawable.teste_triangulo, 1);

        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                streamId = triangleSound.play(soundId, 1, 1, 1, -1, 1.0f);

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        changeRate(progress);
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
                triangleSound.stop(streamId);

                changeRate(DEFAULT_RANGE);

                seekBar.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void changeRate(int progress) {
        float rate = (((MAX_RANGE - MIN_RANGE) / NUMBER_OF_STEPS) * progress) + MIN_RANGE;
        text.setText(Integer.toString(progress * (100 / NUMBER_OF_STEPS)) + "%");
        triangleSound.setRate(streamId, rate);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
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
