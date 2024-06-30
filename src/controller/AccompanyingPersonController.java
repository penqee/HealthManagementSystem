package controller;

import model.*;
import service.*;
import utils.ThreadLocalUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
    public boolean checkAP(String ap_no, String ap_password) throws SQLException { //检查登录

        if (ap_no==null || ap_password==null) {
            return false;
        }

        AccompanyingPerson check_ap = new AccompanyingPerson(ap_no, ap_password,null,null,null,null);
        ResultSet rs = accompanyingPersonService.select(check_ap);
        if (rs.next()){

            HashMap<String,Object> map = new HashMap();
            map.put("ap_no",rs.getString("ap_no"));
            map.put("ap_name",rs.getString("ap_name"));

            //保存登录陪诊师用户信息
            ThreadLocalUtil.set(map);

            return true;
        }
        return false;
    }

    public AccompanyingPerson getAP() { //获取登录的账号的AP信息
        HashMap<String, Object> map = ThreadLocalUtil.get();
        String ap_no = (String) map.get("ap_no");
        String ap_password = (String) map.get("ap_password");
        AccompanyingPerson ap_info = new AccompanyingPerson(ap_no,ap_password,null, null,null,null);
        ResultSet rs = accompanyingPersonService.select(ap_info);
        return accompanyingPersonService.ConvertToAP(rs);
    }

    // 更新自己的状态
    public boolean updateSelfState() throws SQLException {
        ResultSet rs = selectSelfAppointment();
        if (rs.next()) {
            return false;
        } else {
            HashMap<String, Object> map = ThreadLocalUtil.get();
            String ap_no = (String) map.get("ap_no");
            ResultSet r = accompanyingPersonService.select(new AccompanyingPerson(ap_no,null,null,null,null,null));

            AccompanyingPerson ap = accompanyingPersonService.ConvertToAP(rs);
            if ("忙碌".equals(ap.getAp_state())) {
                ap.setAp_state("空闲");
            } else if ("空闲".equals(ap.getAp_state())) {
                ap.setAp_state("忙碌");
            }
            return accompanyingPersonService.updateByNo(ap);
        }
    }

    //查询与自己有关的预约记录
    public ResultSet selectSelfAppointment() {
        HashMap<String, Object> map = ThreadLocalUtil.get();
        String ap_no = (String) map.get("ap_no");
        ResultSet rs = accompanyingPersonService.select(new AccompanyingPerson(ap_no,null,null,null,null,null));

        AccompanyingPerson ap = accompanyingPersonService.ConvertToAP(rs);
        return appointmentViewService.select(null,ap,null,null);
    }

}
