package controller;

import model.*;
import service.*;
import java.sql.ResultSet;

public class AccompanyingPersonController {
    UserService userService;
    ApplicationService applicationService;
    AppointmentService appointmentService;
    AccompanyingPersonService accompanyingPersonService;
    ApplicationViewService applicationViewService;
    AppointmentViewService appointmentViewService;

    public AccompanyingPersonController() {
        this.userService = new UserService();
        this.applicationService = new ApplicationService();
        this.appointmentService = new AppointmentService();
        this.accompanyingPersonService = new AccompanyingPersonService();
        this.applicationViewService = new ApplicationViewService();
        this.appointmentViewService = new AppointmentViewService();
    }

    //登录相关
    public boolean checkAP(String user_no, String user_password) { //检查登录
        AccompanyingPerson ap = new AccompanyingPerson(user_no,user_password,null,null,null,null);
        try {
            ResultSet rs = accompanyingPersonService.select(ap);
            if (rs != null && rs.next()) {
                // 检查密码是否匹配
                String storedPassword = rs.getString("ap_password");
                return user_password.equals(storedPassword);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public AccompanyingPerson getAP(String user_no, String user_password) { //获取登录的账号的AP信息
        AccompanyingPerson ap = new AccompanyingPerson(user_no,user_password,null,null,null,null);
        ap.setAp_no(user_no);
        ap.setAp_password(user_password);
        ResultSet rs = accompanyingPersonService.select(ap);

        try {
            if (rs != null && rs.next()) {
                AccompanyingPerson accompanyingPerson = new AccompanyingPerson(null,null,null,null,null,null);
                accompanyingPerson.setAp_no(rs.getString("ap_no"));
                accompanyingPerson.setAp_password(rs.getString("ap_password"));
                accompanyingPerson.setAp_name(rs.getString("ap_name"));
                accompanyingPerson.setAp_phone_number(rs.getString("ap_phone_number"));
                accompanyingPerson.setAp_type(rs.getString("ap_type"));
                accompanyingPerson.setAp_state(rs.getString("ap_state"));
                return accompanyingPerson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 查看是否空闲
    public boolean isStateFree(String ap_no) {
        return accompanyingPersonService.isStateFree(ap_no);
    }

    //查询与自己有关的预约记录
    public ResultSet selectSelfAppointment(AccompanyingPerson ap) {
        return appointmentViewService.select(null,ap,null,null);
    }

}
