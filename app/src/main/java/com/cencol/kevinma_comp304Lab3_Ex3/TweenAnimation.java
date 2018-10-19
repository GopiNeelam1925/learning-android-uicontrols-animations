package com.cencol.kevinma_comp304Lab3_Ex3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab3.R;

public class TweenAnimation extends AppCompatActivity {

    private ImageView _moonImageView;
    private ImageView _earthImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);

        this._earthImageView = findViewById(R.id.earthImgView);
        this._moonImageView = findViewById(R.id.moonImgView);

        // add event handlers
        findViewById(R.id.startAnimBtn).setOnClickListener(v -> this._startAnimation());
        findViewById(R.id.stopAnimBtn).setOnClickListener(v -> this._stopAnimation());
    }

    //helper methods
    private void _startAnimation() {
        Toast.makeText(this, getResources().getString(R.string.starting_anim_txt), Toast.LENGTH_SHORT).show();
        // we will animate the imageview
        this._earthImageView.setImageResource(R.drawable.earth);
        this._moonImageView.setImageResource(R.drawable.moon);

        // Load the appropriate animation
        Animation earthAnim = AnimationUtils.loadAnimation(this, R.anim.earth_spin);
        Animation moonAnim = AnimationUtils.loadAnimation(this, R.anim.moon_anim);

        earthAnim.setRepeatCount(Animation.INFINITE);
        moonAnim.setRepeatMode(Animation.RESTART);
        moonAnim.setRepeatCount(Animation.INFINITE);
        // Register a listener, so we can disable and re-enable buttons
//        an.setAnimationListener(new MyAnimationListener());
        // Start the animation
        this._moonImageView.startAnimation(moonAnim);
        this._earthImageView.startAnimation(earthAnim);
    }

    private void _stopAnimation() {
        Toast.makeText(this, getResources().getString(R.string.stopping_anim_txt), Toast.LENGTH_SHORT).show();
        this._earthImageView.clearAnimation();
        this._moonImageView.clearAnimation();
        // remove image resources
//        this._earthImageView.setImageResource(0);
//        this._moonImageView.setImageResource(0);
    }
}
