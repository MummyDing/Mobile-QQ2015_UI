package com.demos.tencent_qq_ui.Aty;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.demos.tencent_qq_ui.Adapter.UserNameAdapter;
import com.demos.tencent_qq_ui.Item.UserNameItem;

import java.util.ArrayList;
import java.util.List;

public class LoginAciivity extends Activity implements View.OnClickListener,PopupWindow.OnDismissListener,AdapterView.OnItemClickListener{

    ListView listView;
    List<UserNameItem> list;
    UserNameAdapter userNameAdapter;
    public static PopupWindow popupWindow;
    public static EditText username_inputbox;
    public static EditText password_inputbox;
    ImageButton popupBtn;
    LinearLayout linearLayout;
    View popupwindow_view;
    ImageButton showUser_btn;
    int locationX ,locationY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


    }
    void init(){
        linearLayout = (LinearLayout) findViewById(R.id.userName_layout);
        password_inputbox = (EditText) findViewById(R.id.password_inputbox);
        username_inputbox = (EditText) findViewById(R.id.username_inputbox);
        popupBtn = (ImageButton) findViewById(R.id.showUser_btn);
        popupBtn.setOnClickListener(this);
        showUser_btn = (ImageButton) findViewById(R.id.showUser_btn);
        popupwindow_view = getLayoutInflater().inflate(R.layout.username_listview, null);
        listView = (ListView) popupwindow_view.findViewById(R.id.user_listview);
        list = new ArrayList<UserNameItem>();


        /*
        * 放点数据进去
        * */
        UserNameItem userNameItem = new UserNameItem();
        userNameItem.setUserName("33333333");
        userNameItem.setAvatarImage(R.drawable.avatar);
        list.add(userNameItem);
        userNameItem = new UserNameItem();
        userNameItem.setAvatarImage(R.drawable.avatar);
        userNameItem.setUserName("43333333");
        list.add(userNameItem);
        userNameItem = new UserNameItem();
        userNameItem.setUserName("53333333");
        userNameItem.setAvatarImage(R.drawable.avatar);
        list.add(userNameItem);
        userNameItem = new UserNameItem();
        userNameItem.setUserName("3333333");
        userNameItem.setAvatarImage(R.drawable.avatar);
        list.add(userNameItem);

        userNameAdapter = new UserNameAdapter(this,R.layout.username_item,list);
        listView.setAdapter(userNameAdapter);
        listView.setOnItemClickListener(this);


        userNameAdapter.notifyDataSetChanged();
        initPopupWindow();
    }

    void initPopupWindow(){
        popupWindow = new PopupWindow(popupwindow_view);
        //注意要加这句代码，点击弹出窗口其它区域才会让窗口消失
        popupWindow.setOutsideTouchable(true);
        //获取焦点，响应按键
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());// 响应返回键必须的语句。
        //添加 Dismiss 事件监听
        popupWindow.setOnDismissListener(this);
        //忽略键盘
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int [] location = new int[2];
        password_inputbox.getLocationOnScreen(location);
        locationX = location[0];
        locationY = location[1];
    }

    @Override
    public void onClick(View v) {
        Log.d("Click","点到了 show_btn");
        switch (v.getId()){
            case R.id.showUser_btn:
                if(popupWindow.isShowing() == false)
                {
                    showUser_btn.setBackgroundResource(R.drawable.up_arrow);
                    popupWindow.showAtLocation(linearLayout, Gravity.CENTER | Gravity.TOP, 0, locationY);
                    popupWindow.update(0,locationY, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
                }
                break;
        }
    }

    @Override
    public void onDismiss() {
        showUser_btn.setBackgroundResource(R.drawable.down_arrow);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserNameAdapter.itemPos = position;
        String username = userNameAdapter.getItem(position).getUserName();
        username_inputbox.setText(username);
        username_inputbox.setSelection(username.length());
        popupWindow.dismiss();
    }
}
