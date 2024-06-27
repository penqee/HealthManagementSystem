package model;

public class Application { //申请类 实现用户申请兼职陪诊师 申请信息增删改查

    private String application_no; //申请信息编号
    private String user_no; //用户编号
    private String ap_type; //陪诊师服务类型
    private String application_State; //申请信息状态 待审核/同意/拒绝

    public Application(String application_no, String user_no, String ap_type, String application_State) {
        this.application_no = application_no;
        this.user_no = user_no;
        this.ap_type = ap_type;
        this.application_State = application_State;
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

    public String getAp_type() {
        return ap_type;
    }

    public void setAp_type(String ap_type) {
        this.ap_type = ap_type;
    }

    public String getApplication_State() {
        return application_State;
    }

    public void setApplication_State(String application_State) {
        this.application_State = application_State;
    }
}
