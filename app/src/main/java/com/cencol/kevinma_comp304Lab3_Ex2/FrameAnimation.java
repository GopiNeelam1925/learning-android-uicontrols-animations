package com.cencol.kevinma_comp304Lab3_Ex2;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab3.R;

public class FrameAnimation extends AppCompatActivity {

    private AnimationDrawable _frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        // add event handlers
        findViewById(R.id.startAnimBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameAnimation.this._startAnimation();
            }
        });

        findViewById(R.id.stopAnimBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameAnimation.this._stopAnimation();
            }
        });
    }

    // helper methods
    private void _startAnimation() {
        ImageView imgView = findViewById(R.id.imageViewForAnimation);
        Drawable[] frames = new Drawable[11];
        frames[0] = getDrawable(R.drawable.frame0);
        frames[1] = getDrawable(R.drawable.frame1);
        frames[2] = getDrawable(R.drawable.frame2);
        frames[3] = getDrawable(R.drawable.frame3);
        frames[4] = getDrawable(R.drawable.frame4);
        frames[5] = getDrawable(R.drawable.frame5);
        frames[6] = getDrawable(R.drawable.frame6);
        frames[7] = getDrawable(R.drawable.frame7);
        frames[8] = getDrawable(R.drawable.frame8);
        frames[9] = getDrawable(R.drawable.frame9);
        frames[10] = getDrawable(R.drawable.frame10);

        this._frameAnimation = new AnimationDrawable();
        // infinitely loop
        this._frameAnimation.setOneShot(false);

        //add all frames to animation
        for (Drawable frame : frames) {
            this._frameAnimation.addFrame(frame, getResources().getInteger(R.integer.animation_duration));
        }

        imgView.setBackgroundDrawable(this._frameAnimation);
        this._frameAnimation.start();
        Toast.makeText(this, getResources().getString(R.string.starting_anim_txt), Toast.LENGTH_SHORT).show();
    }

    private void _stopAnimation() {
        this._frameAnimation.stop();
        Toast.makeText(this, getResources().getString(R.string.stopping_anim_txt), Toast.LENGTH_SHORT).show();
    }
}
