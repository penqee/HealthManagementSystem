package controller;

import model.*;
import service.*;
import utils.SnowFlakeUtil;
import utils.ThreadLocalUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
    public boolean checkUser(String user_name, String user_password) throws SQLException { //检查登录

        if (user_name==null || user_password==null) {
            return false;
        }

        User check_user = new User(null, user_password,user_name,null);
        ResultSet rs = userService.select(check_user);
        if (rs.next()){

            HashMap<String,Object> map = new HashMap();
            map.put("user_no",rs.getString("user_no"));
            map.put("user_name",rs.getString("user_name"));

            //保存登录用户信息
            ThreadLocalUtil.set(map);

            return true;
        }
        return false;
    }

    public User getUser(String user_no, String user_password) { //获取登录的账号的user信息
        User user_info = new User(user_no,user_password,null, null);
        ResultSet rs = userService.select(user_info);
        return ConvertToUser(rs);
    }

    private User ConvertToUser(ResultSet rs){
        try {
            if (rs.next()){
                String uid = rs.getString("user_no");
                String password = rs.getString("user_password");
                String username = rs.getString("user_name");
                String user_phone_number = rs.getString("user_phone_number");

                return new User(uid, password, username, user_phone_number);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



    //注册相关
    //checkMessage 用来检验用户信息是否完善，完善返回1，不完善返回0
    public boolean checkMessage(String user_no, String user_password, String user_name, String user_phone_number) { //检查信息是否合法
        return user_no != null && user_password != null && user_name != null && user_phone_number != null;
    }
    public boolean registerUser(String user_password, String user_name, String user_phone_number) { //注册一个账号 将信息增加到用户表中

        if(user_password==null || user_name==null || user_phone_number==null) {
            return false;
        }

        User check_user = new User(SnowFlakeUtil.getSnowFlakeId().toString(), user_password,user_name,user_phone_number);

        return userService.insert(check_user);

    }


    //预约相关
    public ResultSet selectFreeAccompanyingPerson(String ap_type) { //根据类型查询出状态为空闲的陪诊师表
        AccompanyingPerson ap = new AccompanyingPerson(null, null, null, null, ap_type, "空闲");
        return accompanyingPersonService.select(ap);
    }
    public void appointment(String ap_name) { //预约 将预约信息增加到预约关系表中
            if (ap_name == null){
                return;
            }

            HashMap<String, Object> map = ThreadLocalUtil.get();
            String user_no = (String) map.get("user_no");
            String user_name = (String) map.get("user_name");

            AccompanyingPerson ap = new AccompanyingPerson(null, null, ap_name, null, null, null);
            ResultSet rs = accompanyingPersonService.select(ap);

            Appointment apt = new Appointment(SnowFlakeUtil.getSnowFlakeId().toString() ,user_no, ap.getAp_no(), "正在进行");
            appointmentService.insert(apt);

            ap.setAp_state("正在进行");
            accompanyingPersonService.updateByNo(ap);




    }
    public ResultSet selectSelfAppointmentView(User user) { //从预约信息视图中 查询跟user自己有关的预约记录
        return appointmentViewService.select(user,null,null,null);
    }

    public boolean updateAppointmentState(User user,AccompanyingPerson ap) { //只能将与user有关的预约记录状态从 进行中->结束
//        appointmentService.updateByNo()


        return false;
    }


    //申请相关
    public void application(String ap_type) throws SQLException { //申请成为 类型的陪诊师 将信息更新到申请信息表中
        HashMap<String, Object> map = ThreadLocalUtil.get();
        String user_no = (String) map.get("user_no");

        Application ap = new Application(null, user_no, null, null);
        ResultSet rs = applicationService.select(ap);

        if(rs.next()){//不存在才能申请兼职
            return;
        }

        ap.setApplication_no(SnowFlakeUtil.getSnowFlakeId().toString());
        ap.setAp_type(ap_type);
        ap.setApplication_State("待审核");

        applicationService.insert(ap);
    }

    public ResultSet selectSelfApplicationView(User user) { //从申请信息视图 查询跟user自己有关的申请记录状态
        return applicationViewService.select(user,null,null,null);
    }

}
