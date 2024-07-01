package controller;

import model.*;
import service.*;
import utils.ThreadLocalUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
        List<AccompanyingPerson> accompanyingPersonList = accompanyingPersonService.select(check_ap);
        if (accompanyingPersonList != null && !accompanyingPersonList.isEmpty()){

            HashMap<String,Object> map = new HashMap();
            map.put("ap_no",accompanyingPersonList.get(0).getAp_no());
            map.put("ap_password",accompanyingPersonList.get(0).getAp_password());

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
        List<AccompanyingPerson> dataList  = accompanyingPersonService.select(ap_info);
        return dataList.get(0);
    }

    // 更新自己的状态
    public boolean updateSelfState() throws SQLException {
        HashMap<String, Object> map = ThreadLocalUtil.get();
        String ap_no = (String) map.get("ap_no");
        List<AccompanyingPerson> apList = accompanyingPersonService.select(new AccompanyingPerson(ap_no,null,null,null,null,null));

        AccompanyingPerson ap = apList.get(0);

        List<AppointmentView> dataList = appointmentViewService.select(new User(null,null,null,null),ap,null,"正在进行");

        if (dataList != null && !dataList.isEmpty()) {
            return false;
        } else {
            if ("忙碌".equals(ap.getAp_state())) {
                ap.setAp_state("空闲");
            } else if ("空闲".equals(ap.getAp_state())) {
                ap.setAp_state("忙碌");
            }
            return accompanyingPersonService.updateByNo(ap);
        }
    }

    //查询与自己有关的预约记录
    public List<AppointmentView> selectSelfAppointment() {
        HashMap<String, Object> map = ThreadLocalUtil.get();
        String ap_no = (String) map.get("ap_no");
        List<AccompanyingPerson> dataList = accompanyingPersonService.select(new AccompanyingPerson(ap_no,null,null,null,null,null));

        AccompanyingPerson ap = dataList.get(0);
        return appointmentViewService.select(new User(null,null,null,null),ap,null,null);
    }

}
