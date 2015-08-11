package com.demos.tencent_qq_ui.CustomerView;

/**
 * Created by mummyding on 15-8-11.
 */
public class Dot {
    private int x;
    private int y;
    private int dotNum;
    private DOT_STATE state;

    public int getDotNum() {
        return dotNum;
    }

    public void setDotNum(int dotNum) {
        this.dotNum = dotNum;
    }


    public Dot(int y, int x,int dotNum) {
        this.y = y;
        this.x = x;
        this.state = DOT_STATE.NORMAL;
        this.dotNum = dotNum;
    }

    public DOT_STATE getState() {
        return state;
    }

    public void setState(DOT_STATE state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
