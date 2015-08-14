package com.demos.tencent_qq_ui.CustomerView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.demos.tencent_qq_ui.Aty.LoginAciivity;
import com.demos.tencent_qq_ui.Aty.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by mummyding on 15-8-12.
 */
public class SlideView extends HorizontalScrollView{

    private int screenWidth;
    private int menuWidth;
    private int halfMenuWidth;
    private int menuOffsetX;  //菜单的偏移
    private int downX; //按下的横坐标
    private boolean isFirst = true;
    private ViewGroup menuView,mainView;
    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(isFirst){
            LinearLayout mainLayout = (LinearLayout) getChildAt(0);
            menuView = (ViewGroup) mainLayout.getChildAt(0);
            mainView = (ViewGroup) mainLayout.getChildAt(1);
            DisplayMetrics dm =getResources().getDisplayMetrics();
            screenWidth = dm.widthPixels;  //屏幕宽度
            menuWidth = screenWidth / 4 *3; //菜单宽度
            halfMenuWidth = menuWidth /2;

            menuView.getLayoutParams().width = menuWidth;
            mainView.getLayoutParams().width = screenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    void openMenu(){
        this.smoothScrollTo(0, 0);
        menuOffsetX = menuWidth;
    }
    void closeMenu(){
        this.smoothScrollTo(menuWidth,0);
        menuOffsetX = 0;
    }
    void moveMenu(int offset){
        if(offset < 0) {
            closeMenu();
        }else if (offset > menuWidth){
            openMenu();
        }else{
            this.smoothScrollTo(menuWidth-offset,0);
            menuOffsetX = offset;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            isFirst = false;
            closeMenu();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int tmpX = (int) (menuOffsetX + ev.getX() - downX);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                downX = (int) ev.getX();
                moveMenu(tmpX);
                break;
            case MotionEvent.ACTION_UP:
                if(tmpX < halfMenuWidth) {
                    downX = 0;
                    closeMenu();
                }
                else {
                    downX = menuWidth;
                    openMenu();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt); super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / menuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        ViewHelper.setScaleX(menuView, leftScale);
        ViewHelper.setScaleY(menuView, leftScale);
        ViewHelper.setAlpha(menuView, 0.6f + 0.4f * (1 - scale));
        ViewHelper.setTranslationX(menuView, menuWidth * scale * 0.6f);

        ViewHelper.setPivotX(mainView, 0);
        ViewHelper.setPivotY(mainView, mainView.getHeight() / 2);
        ViewHelper.setScaleX(mainView, rightScale);
        ViewHelper.setScaleY(mainView, rightScale);

        ViewHelper.setAlpha(mainView.findViewById(R.id.title_avatar), scale);
        ViewHelper.setTranslationX(mainView.findViewById(R.id.title_avatar), 1-scale);

    }
}
