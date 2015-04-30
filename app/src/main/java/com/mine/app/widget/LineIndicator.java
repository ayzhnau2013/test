package com.mine.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.mine.app.R;

/**
 * Created by zhu on 15-4-30.
 */
public class LineIndicator extends View implements ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    private int mCurrPage;
    private float mPageOffSet;
    private int mScrollState;

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mActionPointId;
    private float mLastMotionX;

    private int mTouchMin;//最小触动距离
    private boolean mIsDragging;
    public LineIndicator(Context context) {
        super(context);
        init(context,null);
    }

    public LineIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LineIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttr(context, attrs);
        mPaint.setAlpha(0xFF);
        mPaint.setColor(getResources().getColor(R.color.cpb_blue));
        ViewConfiguration config = ViewConfiguration.get(context);
        mTouchMin = ViewConfigurationCompat.getScaledPagingTouchSlop(config);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray attr = getTypedArray(context, attrs, R.styleable.LineIndicator);
        if (attr == null) {
            return;
        }
        try {
            Drawable bkg = attr.getDrawable(R.styleable.LineIndicator_android_background);
            setBackgroundDrawable(bkg);
        } finally {
            attr.recycle();
        }
    }

    private TypedArray getTypedArray(Context context, AttributeSet attrs, int[] lineIndicator) {
        return context.obtainStyledAttributes(attrs,lineIndicator,0,0);
    }

    public void setViewPager(ViewPager vp){
        if(vp == mViewPager){
            return ;
        }

        if(mViewPager != null){
            mViewPager.setOnPageChangeListener(null);
        }

        mViewPager = vp;
        mViewPager.setOnPageChangeListener(this);
        invalidate();
    }

    public void setColor(int color){
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mViewPager == null){
            return;
        }
        int count = mViewPager.getAdapter().getCount();
        if(count == 0){
            return;
        }

        if(mCurrPage >= count){
            setCurrentItem(count - 1);
            return;
        }

        int paddingLeft = getPaddingLeft();
        float pageWidth = (getWidth() - paddingLeft - getPaddingRight()) /(1f * count);
        float left = paddingLeft + pageWidth * (mCurrPage + mPageOffSet);
        float right = left + pageWidth;
        float top = getPaddingTop();
        float bottom = getHeight() - getPaddingBottom();
        canvas.drawRect(left,top,right,bottom,mPaint);
    }

    public void setCurrentItem(int item){
        if(mViewPager == null){
            return ;
        }
        mViewPager.setCurrentItem(item);
        mCurrPage = item;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(super.onTouchEvent(event)){
            return true;
        }
        if(mViewPager == null || mViewPager.getAdapter().getCount() == 0){
            return false;
        }
        int action = event.getAction() & MotionEvent.ACTION_MASK;//过滤多点触控，只余操作码
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mActionPointId = MotionEventCompat.getPointerId(event,0);
                mLastMotionX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int activePointerId = MotionEventCompat.findPointerIndex(event,mActionPointId);
                float x = MotionEventCompat.getX(event,activePointerId);
                float delaX = x - mLastMotionX;
                if(!mIsDragging){
                    if(Math.abs(x) > mTouchMin){
                        mIsDragging = true;
                    }
                }

                if(mIsDragging){
                    mLastMotionX = x;
                    if(mViewPager.isFakeDragging() || mViewPager.beginFakeDrag()){
                        mViewPager.fakeDragBy(delaX);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if(!mIsDragging){
                    int count = mViewPager.getAdapter().getCount();
                    int width = getWidth();
                    float halfWidth = width / 2;
                    float sixWidth = width / 6;
                    if(mCurrPage > 0 && (event.getX() < halfWidth - sixWidth)){
                        if(action == MotionEvent.ACTION_CANCEL){
                            mViewPager.setCurrentItem(mCurrPage - 1);
                        }
                        return true;
                    }else if((mCurrPage < count - 1) && (event.getX() > halfWidth + sixWidth)){
                        if(action == MotionEvent.ACTION_CANCEL){
                             mViewPager.setCurrentItem(mCurrPage + 1);
                        }
                        return true;
                    }
                }
                mIsDragging = false;
                mActionPointId = -1;
                if(mViewPager.isFakeDragging()){
                    mViewPager.endFakeDrag();
                }
                break;
            case MotionEventCompat.ACTION_POINTER_DOWN:
                int index = MotionEventCompat.getActionIndex(event);
                mActionPointId = MotionEventCompat.getPointerId(event,index);
                mLastMotionX = MotionEventCompat.getX(event,index);
                break;
            case MotionEventCompat.ACTION_POINTER_UP:
                int pointIndex = MotionEventCompat.getActionIndex(event);
                int pointId = MotionEventCompat.getPointerId(event,pointIndex);
                if(pointId == mActionPointId){
                    int newPointIndex = (pointIndex == 0 ? 1 : 0);
                    mActionPointId = MotionEventCompat.findPointerIndex(event,newPointIndex);
                }
                mLastMotionX = MotionEventCompat.getX(event,MotionEventCompat.findPointerIndex(event,mActionPointId));
                break;
        }
        return true;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener){
        mListener = listener;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        mCurrPage = i;
        mPageOffSet = v;
        invalidate();

        if(mListener != null){
            mListener.onPageScrolled(i,v,i2);
        }
    }

    @Override
    public void onPageSelected(int i) {
        if(mScrollState == ViewPager.SCROLL_STATE_IDLE){
            mCurrPage = i;
            mPageOffSet = 0;
            invalidate();
        }

        if(mListener != null){
            mListener.onPageSelected(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        mScrollState = i;

        if(mListener != null){
            mListener.onPageScrollStateChanged(i);
        }
    }
}
