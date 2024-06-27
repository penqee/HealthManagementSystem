package controller;

import model.*;
import service.*;
import java.sql.*;

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
        return false;
    }

    public User getAP(String user_no, String user_password) { //获取登录的账号的AP信息

        return null;
    }

    //更新自己工作状态
    public void updateSelfState(AccompanyingPerson ap) { // 只要自己的预约记录有空时才能进行修改

    }

    //查询与自己有关的预约记录
    public ResultSet selectSelfAppointment(AccompanyingPerson ap) {
        return appointmentViewService.select(null,ap,null);
    }

}
