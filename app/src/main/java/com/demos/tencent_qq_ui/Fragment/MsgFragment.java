package com.demos.tencent_qq_ui.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class MsgFragment extends Fragment {
    private View view;
    private List<MsgListItem> list;
    private MsgListAdapter msgListAdapter;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_msg_fragment,null);
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
        msgListAdapter = new MsgListAdapter(getActivity(),R.id.msg_list,list);

        MsgListItem msgListItem = new MsgListItem(R.drawable.avatar_01,"马化腾","小子有前途啊!","11:32","3");
        list.add(msgListItem);

        msgListItem = new MsgListItem(R.drawable.avatar_02,"抠脚大叔","你过来下~","20:42","2");
        list.add(msgListItem);



        listView.setAdapter(msgListAdapter);
        /*  msgListItem = new MsgListItem(R.drawable.avatar_03,"ACS 萌萌哒","不错哈","13:23","99+");
        list.add(msgListItem);

        msgListItem = new MsgListItem(R.drawable.avatar_04,"ACS 老人群","下午再看吧","10:38","24");
        list.add(msgListItem);*/

    }
}
