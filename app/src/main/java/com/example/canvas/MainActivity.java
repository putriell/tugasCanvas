package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    //yang kita gambar dan tempel
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    //batasan, gambarnya dimana, warnanya apa dll
    private static final int OFFSET = 50;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackground;
    private int mColorRetangle;
    private int mColorFace;
    private int mColorText;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi color
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRetangle = ResourcesCompat.getColor(getResources(), R.color.colorRetangle, null);
        mColorFace = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mColorText = ResourcesCompat.getColor(getResources(), R.color.black, null);

        //mewarnai paint
        mPaint.setColor(mColorBackground);
        mPaintText.setColor(mColorText);
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.my_image_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view, count);
                count += 1;
            }
        });


    }

    //fungsi untuk menggambar
    public void drawSomething(View view, int count){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();

        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;


        //klik pertama
        if (this.count == 0) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);

//            mCanvas.drawText(getString(R.string.keep_tapping),
//                    100, 100, mPaintText);
//            mOffset += OFFSET;
        }
        //klik kedua
        else if (this.count == 2) {
            mPaint.setColor(mColorFace);
            mCanvas.drawCircle(halfWidth, halfHeight-100, halfHeight/3, mPaint);
            int x = halfWidth - mBounds.centerX();
            int y = halfHeight - mBounds.centerY();

        }

        else if (count == 3) {
            mPaint.setColor(mColorFace - MULTIPLIER *mOffset);
            Point a = new Point(halfWidth-410, halfHeight-310);
            Point b = new Point(halfWidth-150, halfHeight-398);
            Point c = new Point( halfWidth - 330, halfHeight+50);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.lineTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();

            mCanvas.drawPath(path, mPaint);


        }

        view.invalidate();
    }
}