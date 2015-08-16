package com.demos.tencent_qq_ui.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.demos.tencent_qq_ui.Adapter.MsgListAdapter;
import com.demos.tencent_qq_ui.Aty.R;
import com.demos.tencent_qq_ui.Item.MsgListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mummyding on 15-8-14.
 */
public class MsgFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private View view;
    private List<MsgListItem> list;
    private MsgListAdapter msgListAdapter;
    private ListView listView;
    private SwipeRefreshLayout swipeContainer;//下拉刷新
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_msg_fragment,container,false);
        setTitle();
        initData();

        return view;
    }
    void setTitle(){
        TextView title = (TextView) view.findViewById(R.id.title_tv);
        title.setText("消息");
        Button button = (Button) view.findViewById(R.id.header_imgbtn);
        button.setBackgroundResource(R.drawable.header_btn_more);
        button.setText("");
    }
    void initData(){

        list = new ArrayList<MsgListItem>();
        listView = (ListView) view.findViewById(R.id.msg_list);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        msgListAdapter = new MsgListAdapter(getActivity(),R.id.msg_list,list);
        MsgListItem msgListItem = new MsgListItem(R.drawable.avatar_01,"马化腾","小子有前途啊!","21:32","3");
        list.add(msgListItem);

        msgListItem = new MsgListItem(R.drawable.avatar_03,"ACS 萌萌哒","不错哈","20:23","99+");
        list.add(msgListItem);

        msgListItem = new MsgListItem(R.drawable.avatar_04,"ACS 老人群","明天再看吧","20:03","24");
        list.add(msgListItem);

        listView.setAdapter(msgListAdapter);
    }
    void addItems(){
        MsgListItem msgListItem =  new MsgListItem(R.drawable.avatar,"MummyDing","Tencent_QQ_UI 2015 ","18:58","2");
        list.add(msgListItem);
        msgListItem = new MsgListItem(R.drawable.avatar_02,"抠脚大叔","你过来下!","15:42","2");
        list.add(msgListItem);
        msgListItem = new MsgListItem(R.drawable.avatar_05,"美女1号","我要飞得更高~","15:02","1");
        list.add(msgListItem);
        msgListItem = new MsgListItem(R.drawable.avatar_06,"小马哥","这有点帅,不好意思了","10:48","23");
        list.add(msgListItem);
        msgListItem = new MsgListItem(R.drawable.avatar_07,"老大","你给我小心点!","昨天","8");
        list.add(msgListItem);
        msgListItem = new MsgListItem(R.drawable.avatar_05,"棒子家的","我真没有整过容","前天","21");
        list.add(msgListItem);


    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addItems();
                msgListAdapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        }, 1000);
    }
}
