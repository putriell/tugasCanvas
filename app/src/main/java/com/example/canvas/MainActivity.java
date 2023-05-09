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
    private int mColorCircle;
    private int mColorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi color
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRetangle = ResourcesCompat.getColor(getResources(), R.color.colorRetangle, null);
        mColorCircle = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mColorText = ResourcesCompat.getColor(getResources(), R.color.black, null);

        //mewarnai paint
        mPaint.setColor(mColorBackground);
        mPaintText.setColor(mColorText);
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.my_image_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view);
            }
        });


    }

    //fungsi untuk menggambar
    public void drawSomething (View view){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();

        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;


        //klik pertama
        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);

            mCanvas.drawText(getString(R.string.keep_tapping),
                    100, 100, mPaintText);
            mOffset += OFFSET;
        }
        //klik kedua
        else{
            //batasan jumlah rectangle
            if (mOffset < halfWidth && mOffset < halfHeight) {
                //menggambar rectangle berkali kali tapi ukurannya berbeda beda
                mPaint.setColor(mColorRetangle - MULTIPLIER*mOffset);
                mRect.set(mOffset, mOffset, vWidth-mOffset, vHeight-mOffset);
                mCanvas.drawRect(mRect, mPaint);
                //ofset ditambah tambah terus agar ukuran makin kecil
                mOffset += OFFSET;
            }

            else{
                //jika jumlah rectangle lebih maka akan digambar lingkaran
                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                //menggambar lingkaran dan set teks
                mCanvas.drawCircle( halfWidth, halfHeight, halfHeight/3, mPaint);
                String text = getString(R.string.done);
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);
                mOffset += OFFSET;


                //menambahkan segitiga
                mPaint.setColor(mColorBackground - MULTIPLIER*mOffset);
                Point a = new Point(halfWidth-50, halfHeight-50);
                Point b = new Point(halfWidth+50, halfHeight-50);
                Point c = new Point(halfWidth, halfHeight+250);

                Path path= new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(a.x, a.y);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);
                path.close();

                mCanvas.drawPath(path, mPaint);
                mOffset += OFFSET;

            }

        }

        view.invalidate();
    }
}