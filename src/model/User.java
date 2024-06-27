package model;

public class User { //用户类 实现用户相关功能 用户增删改查
    private String user_no; //用户编号
    private String user_Password; //用户密码
    private String user_name; //用户姓名
    private String user_phone_number; //用户手机号

    public User(String user_no, String user_Password, String user_name, String user_phone_number) {
        this.user_no = user_no;
        this.user_Password = user_Password;
        this.user_name = user_name;
        this.user_phone_number = user_phone_number;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }
}
