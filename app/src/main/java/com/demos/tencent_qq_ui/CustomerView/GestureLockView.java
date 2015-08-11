package com.demos.tencent_qq_ui.CustomerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demos.tencent_qq_ui.Aty.MainActivity;

/**
 * Created by mummyding on 15-8-10.
 */
public class GestureLockView extends View {
    private int gridWidth; //九宫格边长
    private int radius;    //圆点半径
    private int startX , startY ; // 第一个圆点(左上角)的坐标
    private int touchX , touchY ; // 手指坐标
    private int offset;     // 点间距
    private final int edgeSpec = 20;  //九宫格与View边上的空隙
    private final static int widthDots = 3;  //每个边的圆点数量
    private boolean isFinish ;   //标记绘制结束
    private static Dot [] dots = new Dot[widthDots*widthDots];
    private Paint paint;  //绘图画笔
    private DotsSelected dotsSelected = new DotsSelected();
    public GestureLockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawLine(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initData();
    }

    void initData(){
        paint = new Paint();
        isFinish = true;
        int tmpWidth = getMeasuredWidth()> getMeasuredHeight() ? getMeasuredHeight() : getMeasuredWidth();
        //计算参数
        gridWidth = tmpWidth - edgeSpec * 2;
        radius = gridWidth / 8;
        startX = edgeSpec + radius;
        startY = startX;
        offset = radius * 3;
       initDots();
    }
    void initDots(){
        //初始化各点
        for(int i = 0 ; i < widthDots ; i ++)
            for( int j = 0 ; j < widthDots ; j ++){
                dots[i * widthDots + j] = new Dot(i * offset + startX, j * offset + startY , i * widthDots +j);
            }
    }
    void drawCircle(Canvas canvas){
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        if( dots[0].getState() == DOT_STATE.ERROR){
            paint.setColor(Color.RED);
        }else{
            paint.setColor(Color.GRAY);
        }
         //画圆
         for(Dot dot : dots){
             canvas.drawPoint(dot.getX(),dot.getY(),paint);
             canvas.drawCircle(dot.getX(),dot.getY(),radius,paint);
         }
    }
    void drawLine(Canvas canvas){
        if( dotsSelected.getCount() == 0) return ;
        Dot lastDot = dots[dotsSelected.getDotByOrder(0)];
        paint.reset();
        paint.setStrokeWidth(8);
        if(lastDot.getState() == DOT_STATE.ERROR){
            paint.setColor(Color.RED);
        }else {
            paint.setColor(Color.BLUE);
        }
        for(int i = 1 ; i < dotsSelected.getCount() ; i++){
            Dot thisDot = dots[dotsSelected.getDotByOrder(i)];
            canvas.drawLine(lastDot.getX(),lastDot.getY(),thisDot.getX(),thisDot.getY(),paint);
            lastDot =thisDot;
        }
        canvas.drawLine(lastDot.getX(),lastDot.getY(),touchX,touchY,paint);
    }
    private int inWhichDot(int x, int y){
        for(Dot dot: dots){
            if(dot.getState() == DOT_STATE.SELECTED) continue;
            if(Math.abs(x - dot.getX()) <= radius && Math.abs(y - dot.getY()) <= radius){
                return dot.getDotNum();
            }
        }
        return -1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = (int) event.getX();
        touchY = (int) event.getY();
        int dotNum = inWhichDot(touchX,touchY);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isFinish = false;
                if(dotNum == -1 ) break;
                dotsSelected.add(dotNum);
                dots[dotNum].setState(DOT_STATE.SELECTED);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if(isFinish == true) break;
                if(dotNum != -1) {
                    dots[dotNum].setState(DOT_STATE.SELECTED);
                    dotsSelected.add(dotNum);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isFinish = true;
                invalidate();
                // 检查是否 正确～～
                dotsSelected.clear();
                initDots();
                break;
        }

        return true;
    }
}
