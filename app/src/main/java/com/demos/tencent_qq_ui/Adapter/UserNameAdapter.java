package com.demos.tencent_qq_ui.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.demos.tencent_qq_ui.Aty.LoginAciivity;
import com.demos.tencent_qq_ui.Aty.R;
import com.demos.tencent_qq_ui.Item.UserNameItem;

import java.util.List;

/**
 * Created by mummyding on 15-8-7.
 */
public class UserNameAdapter extends ArrayAdapter<UserNameItem> implements View.OnClickListener{

    List<UserNameItem> list ;
    public static int itemPos;
    public static String username;
    public UserNameAdapter(Context context, int resource, List<UserNameItem> objects) {
        super(context, resource, objects);
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserNameItem userNameItem = getItem(position);
        View view;
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.username_item, null);
            viewHolder.userName_tv = (TextView) view.findViewById(R.id.usernameText);
            //显示账号
            username = userNameItem.getUserName();
            viewHolder.userName_tv.setText(username);
            //显示头像
            viewHolder.avatar_img = (ImageView) view.findViewById(R.id.avatar_img);
            viewHolder.avatar_img.setImageDrawable(ContextCompat.getDrawable(getContext(),userNameItem.getAvatarImage()));
            //添加删除按钮 click 监听事件
            viewHolder.del_btn = (ImageButton) view.findViewById(R.id.del_btn);
            viewHolder.del_btn.setTag(position);
            viewHolder.del_btn.setOnClickListener(this);

            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
            //显示账号
            username = userNameItem.getUserName();
            viewHolder.userName_tv.setText(username);
            viewHolder.avatar_img.setImageDrawable(ContextCompat.getDrawable(getContext(),userNameItem.getAvatarImage()));
            //显示头像
            viewHolder.avatar_img.setImageDrawable(ContextCompat.getDrawable(getContext(),userNameItem.getAvatarImage()));
            //添加删除按钮 click 监听事件
            viewHolder.del_btn = (ImageButton) view.findViewById(R.id.del_btn);
            viewHolder.del_btn.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //删除当前Item
            case R.id.del_btn:
                itemPos = (int) v.getTag();
                Log.d("pos_del",v.getTag()+"");
                list.remove(itemPos);
                UserNameAdapter.this.notifyDataSetChanged();
                break;
        }
    }

    class ViewHolder{
        TextView userName_tv;
        ImageView avatar_img;
        ImageButton del_btn;
    }

}
