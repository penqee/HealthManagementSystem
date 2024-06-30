package model;

public class AppointmentView {
    private String appointment_no;
    private String user_no;
    private String user_name;
    private String user_phone_number;
    private String ap_no;
    private String ap_name;
    private String ap_phone_number;
    private String ap_type;
    private String appointment_state;

    public AppointmentView(String appointment_no, String user_no, String user_name, String user_phone_number, String ap_no, String ap_name, String ap_phone_number, String ap_type, String appointment_state) {
        this.appointment_no = appointment_no;
        this.user_no = user_no;
        this.user_name = user_name;
        this.user_phone_number = user_phone_number;
        this.ap_no = ap_no;
        this.ap_name = ap_name;
        this.ap_phone_number = ap_phone_number;
        this.ap_type = ap_type;
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

    public String getAp_no() {
        return ap_no;
    }

    public void setAp_no(String ap_no) {
        this.ap_no = ap_no;
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

    public String getAppointment_state() {
        return appointment_state;
    }

    public void setAppointment_state(String appointment_state) {
        this.appointment_state = appointment_state;
    }
}
