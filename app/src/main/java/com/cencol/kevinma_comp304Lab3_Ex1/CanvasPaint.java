package com.cencol.kevinma_comp304Lab3_Ex1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab3.R;

public class CanvasPaint extends AppCompatActivity {

    private ImageView _imageViewForDrawing;
    private TextView _xPosTextView;
    private TextView _yPosTextView;

    private Canvas _canvas;
    private Paint _paint;
    private Bitmap _bitmap;

    // position of last drawn item
    private int _curXPos;
    private int _curYPos;
    private int _xIncrement;
    private int _yIncrement;
    private int _noIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_paint);

        this._imageViewForDrawing = findViewById(R.id.imageViewForDrawing);

        // configs display env
        this._init();
    }

    //Activate the DPAD on emulator:
    //change the settings in config.ini file in .android folder
    //hw.dPad=yes
    //hw.mainKeys=yes
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:

                if (this._curYPos + this._yIncrement > this._bitmap.getHeight()) {
                    Toast.makeText(this, "You are at the bottom edge of the screen already! Cannot go further down!", Toast.LENGTH_SHORT).show();
                } else {
                    this._drawLine(this._canvas, this._noIncrement, this._yIncrement);
                }
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (this._curXPos - this._xIncrement < 0) {
                    Toast.makeText(this, "You are at the left edge of the screen already! Cannot go further left!", Toast.LENGTH_SHORT).show();
                } else {
                    this._drawLine(this._canvas, -this._xIncrement, this._noIncrement);
                }
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                if (this._curYPos - this._yIncrement < 0) {
                    Toast.makeText(this, "You are at the top edge of the screen already! Cannot go further up!", Toast.LENGTH_SHORT).show();
                } else {
                    this._drawLine(this._canvas, this._noIncrement, -this._yIncrement);
                }
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (this._curXPos + this._xIncrement > this._bitmap.getWidth()) {
                    Toast.makeText(this, "You are at the right edge of the screen already! Cannot go further right!", Toast.LENGTH_SHORT).show();
                } else {
                    this._drawLine(this._canvas, this._xIncrement, this._noIncrement);
                }
                return true;
        }
        return false;
    }

    // helper methods
    private void _init() {
        // set up Paint config
        this._paint = new Paint();
        this._paint.setColor(Color.RED);
        this._paint.setStrokeWidth(getResources().getInteger(R.integer.ex1_paint_stroke_width));

        // creating a bitmap as content view for the image
        this._bitmap = Bitmap.createBitmap(getWindowManager()
                .getDefaultDisplay().getWidth(), getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);

        this._canvas = new Canvas(this._bitmap);
        // background color
        this._canvas.drawColor(Color.WHITE);

        this._imageViewForDrawing = findViewById(R.id.imageViewForDrawing);
        this._imageViewForDrawing.setImageBitmap(this._bitmap);
        this._imageViewForDrawing.setVisibility(View.VISIBLE);

        this._xPosTextView = findViewById(R.id.xPosTextView);
        this._yPosTextView = findViewById(R.id.yPosTextView);

        this._curXPos = this._curYPos = 0;
        this._xIncrement = getResources().getInteger(R.integer.ex1_x_pos_increment);
        this._yIncrement = getResources().getInteger(R.integer.ex1_y_pos_increment);
        this._noIncrement = getResources().getInteger(R.integer.ex1_no_pos_increment);
        this._xPosTextView.setText("X: " + this._curXPos);
        this._yPosTextView.setText("Y: " + this._curYPos);
    }

    private void _startDrawing(View v) {
        this._canvas.drawPoint(0, 0, this._paint);
    }

    private void _drawLine(Canvas canvas, int xIncrement, int yIncrement) {
        this._xPosTextView.setText("X: " + this._curXPos);
        this._yPosTextView.setText("Y: " + this._curYPos);
        this._canvas.drawLine(_curXPos, _curYPos, _curXPos += xIncrement, _curYPos += yIncrement, this._paint);
    }

}
