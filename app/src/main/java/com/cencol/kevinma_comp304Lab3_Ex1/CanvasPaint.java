package com.cencol.kevinma_comp304Lab3_Ex1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
                this._drawLineDown();
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                this._drawLineLeft();
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                this._drawLineUp();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                this._drawLineRight();
                return true;
        }
        return false;
    }

    // helper methods
    private void _drawLineLeft() {
        if (this._curXPos - this._xIncrement < 0) {
            Toast.makeText(this, getResources().getString(R.string.ex1_draw_left_msg), Toast.LENGTH_SHORT).show();
        } else {
            this._drawLine(this._canvas, -this._xIncrement, this._noIncrement);
            this._imageViewForDrawing.invalidate();
        }
    }

    private void _drawLineUp() {
        if (this._curYPos - this._yIncrement < 0) {
            Toast.makeText(this, getResources().getString(R.string.ex1_draw_up_msg), Toast.LENGTH_SHORT).show();
        } else {
            this._drawLine(this._canvas, this._noIncrement, -this._yIncrement);
            this._imageViewForDrawing.invalidate();
        }
    }

    private void _drawLineRight() {
        if (this._curXPos + this._xIncrement > this._bitmap.getWidth()) {
            Toast.makeText(this, getResources().getString(R.string.ex1_draw_right_msg), Toast.LENGTH_SHORT).show();
        } else {
            this._drawLine(this._canvas, this._xIncrement, this._noIncrement);
            // force redraw/update canvas
            this._imageViewForDrawing.invalidate();
        }
    }

    private void _drawLineDown() {
        if (this._curYPos + this._yIncrement > this._bitmap.getHeight()) {
            Toast.makeText(this, getResources().getString(R.string.ex1_draw_down_msg), Toast.LENGTH_SHORT).show();
        } else {
            this._drawLine(this._canvas, this._noIncrement, this._yIncrement);
            this._imageViewForDrawing.invalidate();
        }
    }

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

        this._imageViewForDrawing = findViewById(R.id.imageViewForDrawing);
        this._imageViewForDrawing.setImageBitmap(this._bitmap);
        this._imageViewForDrawing.setVisibility(View.VISIBLE);

        this._xPosTextView = findViewById(R.id.xPosTextView);
        this._yPosTextView = findViewById(R.id.yPosTextView);

        this._xIncrement = getResources().getInteger(R.integer.ex1_x_pos_increment);
        this._yIncrement = getResources().getInteger(R.integer.ex1_y_pos_increment);
        this._noIncrement = getResources().getInteger(R.integer.ex1_no_pos_increment);

        this._clearCanvas();

        // attach event handlers
        findViewById(R.id.rightArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CanvasPaint.this._drawLineRight();
                return true;
            }
        });
        findViewById(R.id.upArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CanvasPaint.this._drawLineUp();
                return true;
            }
        });
        findViewById(R.id.leftArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CanvasPaint.this._drawLineLeft();
                return true;
            }
        });
        findViewById(R.id.downArrowKeyBtn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CanvasPaint.this._drawLineDown();
                return true;
            }
        });

        findViewById(R.id.clearCanvasBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CanvasPaint.this._clearCanvas();
            }
        });
    }

    private void _startDrawing() {
        this._canvas.drawPoint(0, 0, this._paint);
    }

    private void _clearCanvas(){
        this._canvas.drawColor(Color.WHITE);
        this._curXPos = this._curYPos = 0;
        this._startDrawing();
        this._xPosTextView.setText("X: " + this._curXPos);
        this._yPosTextView.setText("Y: " + this._curYPos);
    }

    private void _drawLine(Canvas canvas, int xIncrement, int yIncrement) {
        this._xPosTextView.setText("X: " + this._curXPos);
        this._yPosTextView.setText("Y: " + this._curYPos);
        this._canvas.drawLine(_curXPos, _curYPos, _curXPos += xIncrement, _curYPos += yIncrement, this._paint);
    }

}
