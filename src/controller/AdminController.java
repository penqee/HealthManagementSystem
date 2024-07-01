package controller;

import model.*;
import service.*;
import utils.SnowFlakeUtil;
import utils.ThreadLocalUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean checkAdmin(String admin_no, String admin_password) throws SQLException { //检查登录

        if (admin_no==null || admin_password==null) {
            return false;
        }

        Admin admin = new Admin(admin_no, admin_password);
        List<Admin> adminList = adminService.select(admin);
        if (adminList != null && !adminList.isEmpty()){
            return true;
        }
        return false;
    }

    //新增用户
    public boolean insertUser(String user_password, String user_name, String user_phone_number) {
       if(user_password != null && user_password.length() <= 15 &&
               user_name != null && user_name.length() <= 15 &&
               user_phone_number != null && user_phone_number.length() == 11) {
           User user = new User(SnowFlakeUtil.getSnowFlakeId().toString(), user_password, user_name, user_phone_number);
           return userService.insert(user);
       }
        return false;
    }

    //更新用户
    public boolean updateUserByNo(String user_no, String user_password, String user_name, String user_phone_number) {
        if(user_no==null){
            return false;
        }
        User user = new User(user_no, user_password, user_name, user_phone_number);
        return userService.updateByNo(user);
    }

    //删除用户
    public boolean deleteUser(String user_no, String user_password, String user_name, String user_phone_number) {
        if(user_no==null) {
            return false;
        }
        User user = new User(user_no, user_password, user_name, user_phone_number);
        return userService.delete(user);
    }

    //查询用户
    public List<User> selectUser(String user_no, String user_password, String user_name, String user_phone_number) {
        User user = new User(user_no, user_password, user_name, user_phone_number);
        return userService.select(user);
    }

    public void userToAp(User user,String ap_type) {
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(user.getUser_no(), user.getUser_Password(), user.getUser_name(),user.getUser_phone_number(), ap_type,"忙碌");
        accompanyingPersonService.insert(accompanyingPerson);
    }
    //新增陪诊师
    public  boolean insertAP(String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        if(ap_password != null && ap_password.length() <= 15 &&
                ap_name != null && ap_name.length() <= 15 &&
                ap_phone_number!= null && ap_phone_number.length() == 11 &&
                ap_type !=null && ap_state!=null){

            AccompanyingPerson accompanyingPerson = new AccompanyingPerson(SnowFlakeUtil.getSnowFlakeId().toString(),ap_password,ap_name,ap_phone_number,ap_type,ap_state);
            return accompanyingPersonService.insert(accompanyingPerson);
        }
        return false;
    }

    //更新陪诊师
    public  boolean updateAPByNo(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        if (ap_no==null){
            return false;
        }
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(ap_no,ap_password,ap_name,ap_phone_number,ap_type,ap_state);
        return accompanyingPersonService.updateByNo(accompanyingPerson);
    }

    //删除陪诊师
    public  boolean deleteAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        if(ap_no==null) {
            return false;
        }
        AccompanyingPerson accompanyingPerson = new AccompanyingPerson(ap_no, ap_password, ap_name, ap_phone_number, ap_type, ap_state);
        return accompanyingPersonService.delete(accompanyingPerson);
    }

    //查询陪诊师
    public  List<AccompanyingPerson> selectAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
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
        Appointment appointment = new Appointment(appointment_no,user_no,ap_no,appointment_state);
        return appointmentService.updateByNo(appointment);
    }

    //查询预约信息
    public List<AppointmentView> selectAppointmentView(String appointment_no, String user_no, String user_name, String user_phone, String ap_no, String ap_name, String ap_phone, String ap_type, String appointment_state) {
        User user = new User(user_no,null,user_name,user_phone);
        AccompanyingPerson ap = new AccompanyingPerson(ap_no,null,ap_name,ap_phone,ap_type,null);
        return appointmentViewService.select(user,ap,appointment_no,appointment_state);
    }

    //新增兼职信息
    public boolean insertApplication( String user_no, String ap_type) {
        if(user_no==null || ap_type==null) {
            return false;
        }
        Application application = new Application(SnowFlakeUtil.getSnowFlakeId().toString(), user_no,ap_type,"待审核");
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


    //更新兼职信息
    public boolean updateApplicationByNo(String application_no, String ap_type, String application_state) {
        if(application_no==null){
            return false;
        }

        List<Application> applicationList = applicationService.select(new Application(application_no,null,null,null));
        Application application = applicationList.get(0);

        application.setAp_type(ap_type);
        application.setApplication_State(application_state);
        return applicationService.updateByNo(application);
    }


    //查询兼职信息
    public List<ApplicationView> selectApplicationView(String application_no, String user_no, String user_name, String user_phone, String ap_type, String application_state) {
       User user = new User(user_no,null,user_name,user_phone);
       return applicationViewService.select(user,ap_type,application_no,application_state);
    }

    public boolean checkMessage(String user_password, String user_name, String user_phone_number) { //检查信息是否合法

        return user_password != null && user_password.length() <= 15 && user_name != null && user_name.length() <= 15
                && user_phone_number != null && user_phone_number.length() == 11;

    }

}
