package controller;

import model.*;
import service.*;
import java.sql.*;

public class AdminController {
    UserService userService;
    ApplicationService applicationService;
    AppointmentService appointmentService;
    AccompanyingPersonService accompanyingPersonService;
    ApplicationViewService applicationViewService;
    AppointmentViewService appointmentViewService;

    public AdminController() {
        this.userService = new UserService();
        this.applicationService = new ApplicationService();
        this.appointmentService = new AppointmentService();
        this.accompanyingPersonService = new AccompanyingPersonService();
        this.applicationViewService = new ApplicationViewService();
        this.appointmentViewService = new AppointmentViewService();
    }

    //用户模块 对用户表增删改查
        //将信息增加到用户表中
    public boolean insertUser(String user_no, String user_password, String user_name, String User_phone_number) {
        return false;
    }
        //将相关的信息从用户表中删除
    public boolean deleteUser(String user_no, String user_password, String user_name, String User_phone_number) {
        return false;
    }
        //更新用户相关信息通过No
    public boolean updateUserByNo(String user_no, String user_password, String user_name, String User_phone_number) {
        return false;
    }
        //查询用户表信息
    public ResultSet selectUser(String user_no, String user_password, String user_name, String User_phone_number) {
        return null;
    }

    //陪诊师模块 对陪诊师表增删改查
        //将信息增加到陪诊师表中
    public  boolean insertAP(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        return false;
    }
        //将信息从陪诊师中删除
    public  boolean deleteAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        return false;
    }
        //更新陪诊师相关信息 通过No
    public  boolean updateAPByNo(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {

        return false;
    }
        //查询陪诊师信息
    public  ResultSet selectAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        return null;
    }

    //预约关系模块 对预约关系增删改查 查询时使用视图查询
        //增加信息到预约关系表中
    public boolean insertAppointment(String appointment_no, String user_no, String ap_no, String appointment_state) {
        return false;
    }
        //将信息从预约关系表中删除
    public boolean deleteAppointment(String appointment_no, String user_no, String ap_no, String appointment_state) {
        return false;
    }
        //更新预约信息 通过No
    public boolean updateAppointmentByNo(String appointment_no, String user_no, String ap_no, String appointment_state) {
        return false;
    }
        //查询预约信息 从视图中查询
    public ResultSet selectAppointmentView(String user_no, String user_name, String user_phone_number, String ap_no, String ap_name, String ap_phone_number, String ap_type, String ap_state,String appointment_no, String appointment_state) {
        return null;
    }

    //申请关系模块 对申请关系增删改查 查询时使用视图查询
        //增加申请信息
    public boolean insertApplication(String application_no, String user_no, String ap_type, String application_state) {
        return false;
    }
        //删除申请信息
    public boolean deleteApplication(String application_no, String user_no, String ap_type, String application_state) {
        return false;
    }
        //更新申请信息 通过No
    public boolean updateApplicationByNo(String application_no, String user_no, String ap_type, String application_state) {
        return false;
    }
        //查询申请信息 从视图中查询
    public ResultSet selectApplicationView(String user_no, String user_name, String user_phone_number, String ap_type, String application_no, String application_state) {
        return null;
    }
}
