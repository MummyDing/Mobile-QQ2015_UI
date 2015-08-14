package com.demos.tencent_qq_ui.Item;

/**
 * Created by mummyding on 15-8-14.
 */
public class MsgListItem {
    public MsgListItem(int avatar_img, String nickname, String msg_info, String time, String msg_number) {
        this.avatar_img = avatar_img;
        this.nickname = nickname;
        this.msg_info = msg_info;
        this.time = time;
        this.msg_number = msg_number;
    }

    public int getAvatar_img() {
        return avatar_img;
    }

    public void setAvatar_img(int avatar_img) {
        this.avatar_img = avatar_img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getMsg_info() {
        return msg_info;
    }

    public void setMsg_info(String msg_info) {
        this.msg_info = msg_info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg_number() {
        return msg_number;
    }

    public void setMsg_number(String msg_number) {
        this.msg_number = msg_number;
    }

    private int avatar_img;
    private String nickname;
    private String msg_info;
    private String time;
    private String msg_number;
}
