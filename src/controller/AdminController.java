package controller;

import model.*;
import service.*;

import java.sql.ResultSet;

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

    //�û�ģ�� ���û�����ɾ�Ĳ�
        //����Ϣ���ӵ��û�����
    public boolean insertUser(String user_no, String user_password, String user_name, String User_phone_number) {
        return false;
    }
        //����ص���Ϣ���û�����ɾ��
    public boolean deleteUser(String user_no, String user_password, String user_name, String User_phone_number) {
        return false;
    }
        //�����û������Ϣͨ��No
    public boolean updateUserByNo(String user_no, String user_password, String user_name, String User_phone_number) {
        return false;
    }
        //��ѯ�û�����Ϣ
    public ResultSet selectUser(String user_no, String user_password, String user_name, String User_phone_number) {
        return null;
    }

    //����ʦģ�� ������ʦ����ɾ�Ĳ�
        //����Ϣ���ӵ�����ʦ����
    public  boolean insertAP(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        return false;
    }
        //����Ϣ������ʦ��ɾ��
    public  boolean deleteAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        return false;
    }
        //��������ʦ�����Ϣ ͨ��No
    public  boolean updateAPByNo(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {

        return false;
    }
        //��ѯ����ʦ��Ϣ
    public  ResultSet selectAp(String ap_no, String ap_password, String ap_name, String ap_phone_number, String ap_type, String ap_state) {
        return null;
    }

    //ԤԼ��ϵģ�� ��ԤԼ��ϵ��ɾ�Ĳ� ��ѯʱʹ����ͼ��ѯ
        //������Ϣ��ԤԼ��ϵ����
    public boolean insertAppointment(String appointment_no, String user_no, String ap_no, String appointment_state) {
        return false;
    }
        //����Ϣ��ԤԼ��ϵ����ɾ��
    public boolean deleteAppointment(String appointment_no, String user_no, String ap_no, String appointment_state) {
        return false;
    }
        //����ԤԼ��Ϣ ͨ��No
    public boolean updateAppointmentByNo(String appointment_no, String user_no, String ap_no, String appointment_state) {
        return false;
    }
        //��ѯԤԼ��Ϣ ����ͼ�в�ѯ
    public ResultSet selectAppointmentView(String user_no, String user_name, String user_phone_number, String ap_no, String ap_name, String ap_phone_number, String ap_type, String ap_state,String appointment_no, String appointment_state) {
        return null;
    }

    //�����ϵģ�� �������ϵ��ɾ�Ĳ� ��ѯʱʹ����ͼ��ѯ
        //����������Ϣ
    public boolean insertApplication(String application_no, String user_no, String ap_type, String application_state) {
        return false;
    }
        //ɾ��������Ϣ
    public boolean deleteApplication(String application_no, String user_no, String ap_type, String application_state) {
        return false;
    }
        //����������Ϣ ͨ��No
    public boolean updateApplicationByNo(String application_no, String user_no, String ap_type, String application_state) {
        return false;
    }
        //��ѯ������Ϣ ����ͼ�в�ѯ
    public ResultSet selectApplicationView(String user_no, String user_name, String user_phone_number, String ap_type, String application_no, String application_state) {
        return null;
    }
}
