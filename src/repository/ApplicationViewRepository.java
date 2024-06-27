package repository;

import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationViewRepository { //������Ϣ��ͼ
    public ResultSet select(User user,String ap_type, String application_no, String application_state) { //�����û��е���Ϣ �� ����ʦ���� ����Ϣ״̬ ��ѯ������Ϣ
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

        if (ap_type != null && !ap_type.isEmpty()) {
            sql.append(" AND ap_type = ?");
        }

        if (application_no != null && !application_no.isEmpty()) {
            sql.append(" AND application_no = ?");
        }

        if (application_state != null && !application_state.isEmpty()) {
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

            // ��������ʦ���Ͳ�ѯ����
            if (ap_type != null && !ap_type.isEmpty()) {
                stmt.setString(index++, ap_type);
            }

            // ����������Ϣ��Ų�ѯ����
            if (application_no != null && !application_no.isEmpty()) {
                stmt.setString(index++, application_no);
            }

            // ����״̬��ѯ����
            if (application_state != null && !application_state.isEmpty()) {
                stmt.setString(index++, application_state);
            }

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

