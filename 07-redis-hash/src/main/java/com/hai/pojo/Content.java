package com.hai.pojo;

/**
 * @description:
 * @author: xuke
 * @time: 2021/6/9 21:16
 */
public class Content {
    // 发送的用户
    private Integer id;
    // 发送的用户
    private Integer userId;
    // 用户昵称
    private String nickname;
    // 用户头像
    private String avatar;
    // 发送内容
    private String content;
    // 发送时间
    private Long sendtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSendtime() {
        return sendtime;
    }

    public void setSendtime(Long sendtime) {
        this.sendtime = sendtime;
    }
}
