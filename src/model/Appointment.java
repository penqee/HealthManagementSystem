package model;

public class Appointment { //预约信息 实现预约信息相关功能 预约信息增删改查
    private String appointment_no; //预约信息编号
    private String user_no; //用户编号
    private String ap_no; //陪诊师编号
    private String appointment_state; //预约信息状态 进行中/结束

    public Appointment(String appointment_no, String user_no, String ap_no, String appointment_state) {
        this.appointment_no = appointment_no;
        this.user_no = user_no;
        this.ap_no = ap_no;
        this.appointment_state = appointment_state;
    }

    public String getAppointment_no() {
        return appointment_no;
    }

    public void setAppointment_no(String appointment_no) {
        this.appointment_no = appointment_no;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getAp_no() {
        return ap_no;
    }

    public void setAp_no(String ap_no) {
        this.ap_no = ap_no;
    }

    public String getAppointment_state() {
        return appointment_state;
    }

    public void setAppointment_state(String appointment_state) {
        this.appointment_state = appointment_state;
    }
}
