package repository;

import model.ApplicationView;
import model.AppointmentView;
import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationViewRepository { //������Ϣ��ͼ
    public List<ApplicationView> select(User user, String ap_type, String application_no, String application_state) { //�����û��е���Ϣ �� ����ʦ���� ����Ϣ״̬ ��ѯ������Ϣ
        List<ApplicationView> dataList = new ArrayList<>();

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

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // 将每一行数据读取到 ApplicationView对象中
                    ApplicationView fetchedApplicationView = new ApplicationView(null, null,null,null,null,null);
                    fetchedApplicationView.setApplication_no(rs.getString("application_no"));
                    fetchedApplicationView.setUser_no(rs.getString("user_no"));
                    fetchedApplicationView.setUser_name(rs.getString("user_name"));
                    fetchedApplicationView.setUser_phone_number(rs.getString("user_phone_number"));
                    fetchedApplicationView.setAp_type(rs.getString("ap_type"));
                    fetchedApplicationView.setApplication_state(rs.getString("application_state"));

                    // 将 ApplicationView 对象添加到 dataList 中
                    dataList.add(fetchedApplicationView);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList.isEmpty() ? null : dataList;
    }

}

