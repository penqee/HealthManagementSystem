package controller;

import model.*;
import service.*;
import java.sql.*;
public class UserController { //用户模块需要的功能
    UserService userService;
    ApplicationService applicationService;
    AppointmentService appointmentService;
    AccompanyingPersonService accompanyingPersonService;
    ApplicationViewService applicationViewService;
    AppointmentViewService appointmentViewService;

    public UserController() {
        this.userService = new UserService();
        this.applicationService = new ApplicationService();
        this.appointmentService = new AppointmentService();
        this.accompanyingPersonService = new AccompanyingPersonService();
        this.applicationViewService = new ApplicationViewService();
        this.appointmentViewService = new AppointmentViewService();
    }

    //登录相关
    public boolean checkUser(String user_no, String user_password) { //检查登录
        User check_user = new User(user_no, user_password,null,null);
        ResultSet rs = userService.select(check_user);
        try {
            return rs.next(); // 如果有结果返回 true，否则返回 false
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String user_no, String user_password) { //获取登录的账号的user信息

        return null;
    }

    //注册相关
    public boolean checkMessage(String user_no, String user_password, String user_name, String user_phone_number) { //检查信息是否合法

        return false;
    }
    public void registerUser(String user_no, String user_password, String user_name, String user_phone_number) { //注册一个账号 将信息增加到用户表中

    }


    //预约相关
    public ResultSet selectFreeAccompanyingPerson(String ap_type) { //根据类型查询出状态为空闲的陪诊师表
        return null;
    }
    public void appointment() { //预约 将预约信息增加到预约关系表中

    }
    public ResultSet selectSelfAppointmentView(User user) { //从预约信息视图中 查询跟user自己有关的预约记录
        return appointmentViewService.select(user,null,null);
    }

    public boolean updateAppointmentState(User user,AccompanyingPerson ap) { //只能将与user有关的预约记录状态从 进行中->结束

        return false;
    }


    //申请相关
    public void application(String ap_type) { //申请成为 类型的陪诊师 将信息更新到申请信息表中

    }

    public ResultSet selectSelfApplicationView(User user) { //从申请信息视图 查询跟user自己有关的申请记录状态
        return applicationViewService.select(user,null,null);
    }

}
