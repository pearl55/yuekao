package com.bw.gaoyongpei.bean;

/**
 * Created by gaoyongpei on 2017/7/28.
 */
public class UserTest {

    private Integer id;
    private String uname;
    private int passwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", passwd=" + passwd +
                '}';
    }
}
