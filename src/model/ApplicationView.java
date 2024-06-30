package model;

public class ApplicationView {
    private String application_no;
    private String user_no;
    private String user_name;
    private String user_phone_number;
    private String ap_type;
    private String application_state;

    public ApplicationView(String application_no, String user_no, String user_name, String user_phone_number, String ap_type, String application_state) {
        this.application_no = application_no;
        this.user_no = user_no;
        this.user_name = user_name;
        this.user_phone_number = user_phone_number;
        this.ap_type = ap_type;
        this.application_state = application_state;
    }

    public String getApplication_no() {
        return application_no;
    }

    public void setApplication_no(String application_no) {
        this.application_no = application_no;
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

    public String getAp_type() {
        return ap_type;
    }

    public void setAp_type(String ap_type) {
        this.ap_type = ap_type;
    }

    public String getApplication_state() {
        return application_state;
    }

    public void setApplication_state(String application_state) {
        this.application_state = application_state;
    }
}
