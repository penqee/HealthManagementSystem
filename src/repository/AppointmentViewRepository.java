package repository;

import model.AccompanyingPerson;
import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppointmentViewRepository {
    public ResultSet select(User user, AccompanyingPerson ap, String appointment_no, String appointment_state) { //�����û��е���Ϣ �� ����ʦ���� ����Ϣ״̬ ��ѯ������Ϣ
        StringBuilder sql = new StringBuilder("SELECT * FROM applicationView WHERE 1=1");

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
            sql.append(" AND application_no = ?");
        }
        // ԤԼ��Ϣ״̬
        if (appointment_state != null && !appointment_state.isEmpty()) {
            sql.append(" AND application_state = ?");
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

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
