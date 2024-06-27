package repository;

import model.*;

import java.sql.*;
import utils.*;

public class ApplicationViewRepository { //申请信息视图
    public ResultSet select(User user,String ap_type, String application_no, String application_state) { //根据用户中的信息 和 陪诊师类型 和信息状态 查询申请信息
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

            // 设置陪诊师类型查询参数
            if (ap_type != null && !ap_type.isEmpty()) {
                stmt.setString(index++, ap_type);
            }

            // 设置申请信息编号查询参数
            if (application_no != null && !application_no.isEmpty()) {
                stmt.setString(index++, application_no);
            }

            // 设置状态查询参数
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

