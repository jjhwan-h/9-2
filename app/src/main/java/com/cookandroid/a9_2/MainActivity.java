package com.cookandroid.a9_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    static float scaleX=1, scaleY=1;
    static float angle=0;
    static float color=1;
    static float satur =1;
    ImageButton ibZoomin, ibZoomout, ibRotate, ibBright,ibDark, ibGray;
    MyGraphicView graphicView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);

    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);}

        @SuppressLint("DrawAllocation")
        @Override
            protected void onDraw(Canvas canvas){
                super.onDraw(canvas);

                clickIcons();

                Paint paint = new Paint();
                float[] array = {color,0,0,0,0,
                                 0,color,0,0,0,
                                 0,0,color,0,0,
                                 0,0,0,1,0};
                ColorMatrix cm = new ColorMatrix(array);
                if(satur==0)cm.setSaturation(satur);
                paint.setColorFilter(new ColorMatrixColorFilter(cm));

                Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.tux);

                int picX = (this.getWidth() - picture.getWidth())/2;
                int picY = (this.getHeight() - picture.getHeight())/2;
                int CenX = this.getWidth()/2;
                int CenY = this.getHeight()/2;
                canvas.scale(scaleX,scaleY,CenX,CenY);
                canvas.rotate(angle,CenX,CenY);

                canvas.drawBitmap(picture, picX, picY, paint);

                picture.recycle();
            }
        }

    private void clickIcons(){
        ibZoomin = (ImageButton) findViewById(R.id.ibZoomin);
        ibZoomout = (ImageButton) findViewById(R.id.ibZoomout);
        ibRotate = (ImageButton) findViewById(R.id.ibSpin);
        ibBright = (ImageButton) findViewById(R.id.ibBright);
        ibDark = (ImageButton) findViewById(R.id.ibDark);
        ibGray = (ImageButton) findViewById(R.id.ibGray);

        ibZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
            }
        });
        ibZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
            }
        });
        ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle = angle+20;
                graphicView.invalidate();
            }
        });
        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = color+0.2f;
                graphicView.invalidate();
            }
        });
        ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = color-0.2f;
                graphicView.invalidate();
            }
        });
        ibGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(satur ==0) satur =1;
                else satur=0;
                graphicView.invalidate();
            }
        });

    }


}
