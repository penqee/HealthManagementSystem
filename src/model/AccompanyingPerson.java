package model;

public class AccompanyingPerson { //陪诊师 实现陪诊师相关功能 陪诊师增删改查
    private String ap_no; //陪诊师编号
    private String ap_password; //陪诊师密码
    private String ap_name; //陪诊师姓名
    private String ap_phone_number; //陪诊师手机号
    private String ap_type; //陪诊服务类型 陪同就医、提前挂号、代办问诊、代取结果、代办跑腿、病案到家
    private String ap_state; //陪诊师状态 忙碌/空闲

    public AccompanyingPerson(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        this.ap_no = ap_no;
        this.ap_password = ap_password;
        this.ap_name = ap_name;
        this.ap_phone_number = ap_phone_number;
        this.ap_type = ap_type;
        this.ap_state = ap_state;
    }

    public String getAp_no() {
        return ap_no;
    }

    public void setAp_no(String ap_no) {
        this.ap_no = ap_no;
    }

    public String getAp_password() {
        return ap_password;
    }

    public void setAp_password(String ap_password) {
        this.ap_password = ap_password;
    }

    public String getAp_name() {
        return ap_name;
    }

    public void setAp_name(String ap_name) {
        this.ap_name = ap_name;
    }

    public String getAp_phone_number() {
        return ap_phone_number;
    }

    public void setAp_phone_number(String ap_phone_number) {
        this.ap_phone_number = ap_phone_number;
    }

    public String getAp_type() {
        return ap_type;
    }

    public void setAp_type(String ap_type) {
        this.ap_type = ap_type;
    }

    public String getAp_state() {
        return ap_state;
    }

    public void setAp_state(String ap_state) {
        this.ap_state = ap_state;
    }
}
