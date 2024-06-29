package controller;

import model.*;
import service.*;
import utils.SnowFlakeUtil;
import utils.ThreadLocalUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AdminController {

    UserService userService;
    ApplicationService applicationService;
    AppointmentService appointmentService;
    AccompanyingPersonService accompanyingPersonService;
    ApplicationViewService applicationViewService;
    AppointmentViewService appointmentViewService;
    AdminService adminService;

    public AdminController() {
        this.userService = new UserService();
        this.applicationService = new ApplicationService();
        this.appointmentService = new AppointmentService();
        this.accompanyingPersonService = new AccompanyingPersonService();
        this.applicationViewService = new ApplicationViewService();
        this.appointmentViewService = new AppointmentViewService();
        this.adminService = new AdminService();
    }


    //登录相关
    public boolean checkAdmin(String user_name, String user_password) throws SQLException { //检查登录

        if (user_name==null || user_password==null) {
            return false;
        }

        Admin admin = new Admin(user_name, user_password);
        ResultSet rs = adminService.select(admin);
        if (rs.next()){
            return true;
        }
        return false;
    }

    //新增用户
    public boolean insertUser(String user_no, String user_password, String user_name, String User_phone_number) {
       if(user_no==null||user_name==null||user_password==null||User_phone_number==null){
           return false;
       }
       User user = new User(user_no, user_password, user_name, User_phone_number);
       return userService.insert(user);
    }

    //更新用户
    public boolean updateUserByNo(String user_no, String user_password, String user_name, String User_phone_number) {
        if(user_no==null){
            return false;
        }
        User user = new User(user_no, user_password, user_name, User_phone_number);
        return userService.updateByNo(user);
    }

    //删除用户
    public boolean deleteUser(String user_no, String user_password, String user_name, String User_phone_number) {
        if(user_no==null) {
            return false;
        }
        User user = new User(user_no, user_password, user_name, User_phone_number);
        return userService.delete(user);
    }

    //查询用户
    public ResultSet selectUser(String user_no, String user_password, String user_name, String User_phone_number) {

        User user = new User(user_no, user_password, user_name, User_phone_number);
        return userService.select(user);
    }

    //新增护理师
    public  boolean insertAP(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        if(ap_no==null||ap_password==null||ap_name==null||ap_phone_number==null||ap_type==null||ap_state==null){
            return false;
        }
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(ap_no,ap_password,ap_name,ap_phone_number,ap_type,ap_state);
        return accompanyingPersonService.insert(accompanyingPerson);
    }

    //更新护理师
    public  boolean updateAPByNo(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        if (ap_no==null){
            return false;
        }
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(ap_no,ap_password,ap_name,ap_phone_number,ap_type,ap_state);
        return accompanyingPersonService.updateByNo(accompanyingPerson);
    }

    //删除护理师
    public  boolean deleteAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        if(ap_no==null) {
            return false;
        }
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(ap_no, ap_password, ap_name, ap_phone_number, ap_type, ap_state);
        return accompanyingPersonService.delete(accompanyingPerson);
    }

    //查询护理师
    public  ResultSet selectAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(ap_no, ap_password, ap_name, ap_phone_number, ap_type, ap_state);
        return accompanyingPersonService.select(accompanyingPerson);
    }

    //增加预约信息
    public boolean insertAppointment(String user_no, String ap_no) {
        if(user_no==null || ap_no==null) {
            return false;
        }
        Appointment appointment = new Appointment(SnowFlakeUtil.getSnowFlakeId().toString(),user_no,ap_no,"正在进行");
        return appointmentService.insert(appointment);
    }

    //删除预约信息
    public boolean deleteAppointment(String appointment_no, String user_no, String ap_no, String appointment_state) {
        if(appointment_no==null) {
            return false;
        }
        Appointment appointment = new Appointment(appointment_no,user_no,ap_no,appointment_state);
        return appointmentService.delete(appointment);
    }

    //更新预约信息
    public boolean updateAppointmentByNo(String appointment_no, String user_no, String ap_no, String appointment_state) {
        if(appointment_no==null) {
            return false;
        }
        Appointment appointment = new Appointment(appointment_no,user_no,ap_no,"已完成");
        return appointmentService.updateByNo(appointment);
    }

    //查询预约信息
    public ResultSet selectAppointmentView(String appointment_no, String user_no, String ap_no, String appointment_state) {
       Appointment appointment = new Appointment(appointment_no,user_no,ap_no,appointment_state);
       return appointmentService.select(appointment);
    }

    //新增兼职信息
    public boolean insertApplication(String application_no, String user_no, String ap_type) {
        if(application_no ==null || user_no==null || ap_type==null) {
            return false;
        }
        Application application = new Application(application_no,user_no,ap_type,"待审核");
        return applicationService.insert(application);
    }

    //删除兼职信息
    public boolean deleteApplication(String application_no, String user_no, String ap_type, String application_state) {
        if (application_no==null) {
            return false;
        }
        Application application = new Application(application_no,user_no,ap_type, application_state);
        return applicationService.delete(application);
    }

    //更新兼职信息（同意）
    public boolean updateApplicationByNoAC(String application_no, String user_no, String ap_type) {
        if(application_no==null){
            return false;
        }
        Application application = new Application(application_no,user_no,ap_type, "同意");
        return applicationService.updateByNo(application);
    }

    //更新兼职信息（拒绝）
    public boolean updateApplicationByNoRJ(String application_no, String user_no, String ap_type) {
        if(application_no==null){
            return false;
        }
        Application application = new Application(application_no,user_no,ap_type, "拒绝");
        return applicationService.updateByNo(application);
    }

    //查询兼职信息
    public ResultSet selectApplicationView(String application_no, String user_no, String ap_type, String application_state) {
       Application application =new Application(application_no,user_no,ap_type,application_state);
       return applicationService.select(application);
    }
}
