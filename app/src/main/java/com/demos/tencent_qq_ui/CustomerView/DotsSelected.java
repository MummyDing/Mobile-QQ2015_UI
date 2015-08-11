package com.demos.tencent_qq_ui.CustomerView;

/**
 * Created by mummyding on 15-8-11.
 */
public class DotsSelected {
    private int dots[];
    private int pos;
    private final int DOTS_NUM =9;

    public DotsSelected() {
        dots= new int[DOTS_NUM];
        pos = 0;
    }
    public void clear(){
        pos = 0;
    }
    public void add(int dotNum){
        if(pos < DOTS_NUM)
        dots[pos++] =dotNum;
    }
    public int getCount(){
        return pos;
    }
    public int getDotByOrder(int order){
        if( order < DOTS_NUM){
            return dots[order];
        }
        return -1;
    }
}
