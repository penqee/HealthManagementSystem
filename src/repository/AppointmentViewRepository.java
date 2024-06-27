package repository;

import model.*;

import java.sql.*;
import utils.*;


public class AppointmentViewRepository {
    public ResultSet select(User user, AccompanyingPerson ap, String appointment_no, String appointment_state) { //根据用户中的信息 和 陪诊师类型 和信息状态 查询申请信息
        StringBuilder sql = new StringBuilder("SELECT * FROM applicationView WHERE 1=1");

        // 根据用户信息构建查询条件
        if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
            sql.append(" AND user_name = ?");
        }
        if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
            sql.append(" AND user_phone_number = ?");
        }

        // 根据陪诊师信息构建查询条件
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

        // 预约信息编号
        if (appointment_no != null && !appointment_no.isEmpty()) {
            sql.append(" AND application_no = ?");
        }
        // 预约信息状态
        if (appointment_state != null && !appointment_state.isEmpty()) {
            sql.append(" AND application_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // 设置用户查询参数
            if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
                stmt.setString(index++, user.getUser_no());
            }
            if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
                stmt.setString(index++, user.getUser_name());
            }
            if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
                stmt.setString(index++, user.getUser_phone_number());
            }

            // 设置陪诊师查询参数
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
            // 设置预约信息编号查询参数
            if (appointment_no != null && !appointment_no.isEmpty()) {
                stmt.setString(index++, appointment_no);
            }
            // 设置预约状态查询参数
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
