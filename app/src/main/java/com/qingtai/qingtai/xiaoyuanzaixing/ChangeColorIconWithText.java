package com.qingtai.qingtai.xiaoyuanzaixing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ChangeColorIconWithText extends View {
    private int mColor = 0xFF45c01A;
    private Bitmap mIconBitmap;
    private String mText = "帖子" ;
    private int mTextSize =(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics());

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private int mAlpha;

    private Rect mIconRect;
    private Rect mTextBound;

    private Paint mTextPaint;


    public ChangeColorIconWithText(Context context) {
        this(context,null);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChangeColorIconWithText);
        int n = a.getIndexCount();
        for(int i = 0;i < n;i++){
            int attr = a.getIndexCount();
            switch (attr){
                case R.styleable.ChangeColorIconWithText_icon:
                    BitmapDrawable d = (BitmapDrawable)a.getDrawable(attr);
                    mIconBitmap = d.getBitmap();
                    break;
                case R.styleable.ChangeColorIconWithText_color:
                    mColor = a.getColor(attr,0xFF45c01A);
                    break;
                case R.styleable.ChangeColorIconWithText_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.ChangeColorIconWithText_text_size:
                    mTextSize = (int)a.getDimension(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics()));
                    break;

            }
        }
        a.recycle();
        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff555555);

        mTextPaint.getTextBounds(mText,0,mText.length(),mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth()- getPaddingLeft()-getPaddingRight(),getMeasuredHeight()-getPaddingBottom()-getPaddingTop()-mTextBound.height());
        int left = getMeasuredWidth()/2 - iconWidth /2;
        int top = (getMeasuredHeight()-mTextBound.height())/2 - iconWidth/2;
        mIconRect = new Rect(left,top,left + iconWidth,top+iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap,null,mIconRect,null);

    }
}
