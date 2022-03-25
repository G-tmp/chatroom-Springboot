package com.xd.entity;


// only contain nickname here
public class User {

    private String nickname;
//    private String password;
//    private String email;
//    private String avatar;


    public User(){}


    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
