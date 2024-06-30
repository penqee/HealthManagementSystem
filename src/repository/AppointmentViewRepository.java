package repository;

import model.AccompanyingPerson;
import model.Appointment;
import model.AppointmentView;
import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AppointmentViewRepository {
    public List<AppointmentView> select(User user, AccompanyingPerson ap, String appointment_no, String appointment_state) { //�����û��е���Ϣ �� ����ʦ���� ����Ϣ״̬ ��ѯ������Ϣ
        List<AppointmentView> dataList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM appointmentView WHERE 1=1");

        // �����û���Ϣ������ѯ����
        if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
            sql.append(" AND user_name = ?");
        }
        if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
            sql.append(" AND user_phone_number = ?");
        }

        // ��������ʦ��Ϣ������ѯ����
        if (ap.getAp_no() != null && !ap.getAp_no().isEmpty()) {
            sql.append(" AND ap_no = ?");
        }
        if (ap.getAp_name() != null && !ap.getAp_name().isEmpty()) {
            sql.append(" AND ap_name = ?");
        }
        if (ap.getAp_phone_number() != null && !ap.getAp_phone_number().isEmpty()) {
            sql.append(" AND ap_phone_number = ?");
        }
        if (ap.getAp_type() != null && !ap.getAp_type().isEmpty()) {
            sql.append(" AND ap_type = ?");
        }

        // ԤԼ��Ϣ���
        if (appointment_no != null && !appointment_no.isEmpty()) {
            sql.append(" AND appointment_no = ?");
        }
        // ԤԼ��Ϣ״̬
        if (appointment_state != null && !appointment_state.isEmpty()) {
            sql.append(" AND appointment_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // �����û���ѯ����
            if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
                stmt.setString(index++, user.getUser_no());
            }
            if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
                stmt.setString(index++, user.getUser_name());
            }
            if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
                stmt.setString(index++, user.getUser_phone_number());
            }

            // ��������ʦ��ѯ����
            if (ap.getAp_no() != null && !ap.getAp_no().isEmpty()) {
                stmt.setString(index++, ap.getAp_no());
            }
            if (ap.getAp_name() != null && !ap.getAp_name().isEmpty()) {
                stmt.setString(index++, ap.getAp_name());
            }
            if (ap.getAp_phone_number() != null && !ap.getAp_phone_number().isEmpty()) {
                stmt.setString(index++, ap.getAp_phone_number());
            }
            if (ap.getAp_type() != null && !ap.getAp_type().isEmpty()) {
                stmt.setString(index++, ap.getAp_type());
            }
            // ����ԤԼ��Ϣ��Ų�ѯ����
            if (appointment_no != null && !appointment_no.isEmpty()) {
                stmt.setString(index++, appointment_no);
            }
            // ����ԤԼ״̬��ѯ����
            if (appointment_state != null && !appointment_state.isEmpty()) {
                stmt.setString(index++, appointment_state);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // 将每一行数据读取到 av 对象中
                    AppointmentView fetchedAppointmentView = new AppointmentView(null, null, null, null,null,null,null,null,null);
                    fetchedAppointmentView.setAppointment_no(rs.getString("appointment_no"));
                    fetchedAppointmentView.setUser_no(rs.getString("user_no"));
                    fetchedAppointmentView.setUser_name(rs.getString("user_name"));
                    fetchedAppointmentView.setUser_phone_number(rs.getString("user_phone_number"));
                    fetchedAppointmentView.setAp_no(rs.getString("ap_no"));
                    fetchedAppointmentView.setAp_name(rs.getString("ap_name"));
                    fetchedAppointmentView.setAp_phone_number(rs.getString("ap_phone_number"));
                    fetchedAppointmentView.setAp_type(rs.getString("ap_type"));
                    fetchedAppointmentView.setAppointment_state(rs.getString("appointment_state"));

                    // 将 av 对象添加到 dataList 中
                    dataList.add(fetchedAppointmentView);
                }
            }

            return dataList.isEmpty() ? null : dataList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
