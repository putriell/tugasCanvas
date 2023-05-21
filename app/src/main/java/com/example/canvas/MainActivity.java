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
    private int mColorWhite;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi color
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRetangle = ResourcesCompat.getColor(getResources(), R.color.colorRetangle, null);
        mColorFace = ResourcesCompat.getColor(getResources(), R.color.colorFace, null);
        mColorText = ResourcesCompat.getColor(getResources(), R.color.black, null);
        mColorWhite = ResourcesCompat.getColor(getResources(), R.color.white, null);
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
            //face
            mPaint.setColor(mColorFace);
            mCanvas.drawCircle(halfWidth, halfHeight-100, halfHeight/3, mPaint);
            int x = halfWidth - mBounds.centerX();
            int y = halfHeight - mBounds.centerY();

        }

        else if (count == 3) {

            //left ear
            mPaint.setColor(mColorFace );
            Point a = new Point(halfWidth-290, halfHeight-250);
            Point b = new Point(halfWidth-90, halfHeight-410);
            Point c = new Point( halfWidth -230, halfHeight-550);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.lineTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();

            mCanvas.drawPath(path, mPaint);

            mPaint.setColor(mColorWhite);
            Point g = new Point(halfWidth-250, halfHeight-300);
            Point h = new Point(halfWidth-110, halfHeight-410);
            Point i = new Point( halfWidth-210, halfHeight-500);

            Path path1 = new Path();
            path1.setFillType(Path.FillType.EVEN_ODD);
            path1.lineTo(g.x, g.y);
            path1.lineTo(h.x, h.y);
            path1.lineTo(i.x, i.y);
            path1.lineTo(g.x, g.y);
            path1.close();

            mCanvas.drawPath(path1, mPaint);

            //right ear
            mPaint.setColor(mColorFace);
            Point d = new Point(halfWidth + 290, halfHeight - 250);
            Point e = new Point(halfWidth + 90, halfHeight - 410);
            Point f = new Point(halfWidth +230, halfHeight-550);

            Path path2 = new Path();
            path2.lineTo(d.x, d.y);
            path2.lineTo(e.x, e.y);
            path2.lineTo(f.x, f.y);
            path2.lineTo(d.x, d.y);
            path2.close();

            mCanvas.drawPath(path2, mPaint);

            mPaint.setColor(mColorWhite );
            Point j = new Point(halfWidth+250, halfHeight-300);
            Point k = new Point(halfWidth+110, halfHeight-410);
            Point l = new Point( halfWidth+210, halfHeight-500);

            Path path3 = new Path();
            path3.setFillType(Path.FillType.EVEN_ODD);
            path3.lineTo(j.x, j.y);
            path3.lineTo(k.x, k.y);
            path3.lineTo(l.x, l.y);
            path3.lineTo(j.x, j.y);
            path3.close();

            mCanvas.drawPath(path3, mPaint);
        }

        else if (count == 4) {
            //eye
            mPaint.setColor(mColorWhite);
            mCanvas.drawCircle(halfWidth-120, halfHeight-250, halfHeight/16, mPaint);
            mPaint.setColor(mColorWhite);
            mCanvas.drawCircle(halfWidth+120, halfHeight-250, halfHeight/16, mPaint);
            mPaint.setColor(mColorText);
            mCanvas.drawCircle(halfWidth-120, halfHeight-250, halfHeight/23, mPaint);
            mPaint.setColor(mColorText);
            mCanvas.drawCircle(halfWidth+120, halfHeight-250, halfHeight/23, mPaint);
        }

        else if (count == 5) {

            //mouth
            mPaint.setColor(mColorText);
            mCanvas.drawCircle(halfWidth, halfHeight+90, halfHeight/12, mPaint);
            mPaint.setColor(mColorFace);
            mCanvas.drawCircle(halfWidth, halfHeight+70, halfHeight/12, mPaint);


        }

        else if (count == 6) {
            //nose
            mPaint.setColor(mColorText);
            mCanvas.drawCircle(halfWidth, halfHeight-50, halfHeight/13, mPaint);

        }

        view.invalidate();
    }
}