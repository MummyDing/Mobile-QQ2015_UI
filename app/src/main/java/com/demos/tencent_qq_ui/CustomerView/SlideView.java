package com.demos.tencent_qq_ui.CustomerView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.demos.tencent_qq_ui.CustomerView.AvatarImageView;
import com.demos.tencent_qq_ui.Aty.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by mummyding on 15-8-12.
 */
public class SlideView extends HorizontalScrollView implements View.OnClickListener{

    private int screenWidth;
    private int menuWidth;
    private int halfMenuWidth;
    private int menuOffsetX;  //菜单的偏移
    private int downX,downY; //按下的坐标
    private boolean isFirst = true;
    private boolean isMenuOpen = false ,isMoveFinish = true;
    private ViewGroup menuView,mainView;
    private AvatarImageView titleAvatar;
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
        titleAvatar = (AvatarImageView) mainView.findViewById(R.id.title_avatar);
        titleAvatar.setOnClickListener(this);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    void openMenu(){
        this.smoothScrollTo(0, 0);
        menuOffsetX = menuWidth;
        isMenuOpen = true;
    }
    void closeMenu(){
        this.smoothScrollTo(menuWidth,0);
        menuOffsetX = 0;
        isMenuOpen = false;
    }
    void moveMenu(int offset){
        if(offset <=0) {
            closeMenu();
        }else if (offset > menuWidth){
            openMenu();
        }else{
            this.smoothScrollTo(menuWidth-offset,0);
            menuOffsetX = offset;
            isMenuOpen = true;
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
        //当前手指横纵坐标
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        //菜单做边缘的横坐标
        int tmpX = (int) (menuOffsetX + x - downX);
        //x轴方向移动距离
        int diffX = (int) Math.abs(downX -x);
        //y轴方向移动距离
        int diffY = (int) Math.abs(downY - y);
        //是否想做滑动
        boolean dirLeft = false;  //标记滑动方向
        if(downX >= x|| isMoveFinish) dirLeft = true;
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                isMoveFinish = false;
                // 如果在主界面向做滑动则处理
               if(downX==0&&downY==0||dirLeft && isMenuOpen == false) {
                   downX = x;
                   downY = y;
                   break;
               }
                //如果是垂直方向滑动则不处理
                if(diffX < diffY)break;
                downX = x;
                downY = y;
                moveMenu(tmpX);
                break;
            case MotionEvent.ACTION_UP:
                isMoveFinish =true;
                if(downX==0&&downY==0||dirLeft && isMenuOpen == false) {
                    downX = x;
                    downY = y;
                    break;
                }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_avatar:
                openMenu();
                break;
        }
    }
}
