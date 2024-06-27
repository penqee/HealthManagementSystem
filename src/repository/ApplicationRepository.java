package repository;

import model.Application;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ApplicationRepository {
    //申请信息表 增删改查
    //增加
    public boolean insert(Application application) { //将application新增到用户表
        String sql = "INSERT INTO application (application_no, user_no, ap_type, application_state) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, application.getApplication_no());
            stmt.setString(2, application.getUser_no());
            stmt.setString(3, application.getAp_type());
            stmt.setString(4, application.getApplication_State());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //删除
    public boolean delete(Application application) { //删除application信息 成功返回true 错误返回false
        StringBuilder sql = new StringBuilder("DELETE FROM application WHERE 1=1");

        if (application.getApplication_no() != null && !application.getApplication_no().isEmpty()) {
            sql.append(" AND application_no = ?");
        }
        if (application.getUser_no() != null && !application.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (application.getAp_type() != null && !application.getAp_type().isEmpty()) {
            sql.append(" AND ap_type = ?");
        }
        if (application.getApplication_State() != null && !application.getApplication_State().isEmpty()) {
            sql.append(" AND application_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            if (application.getApplication_no() != null && !application.getApplication_no().isEmpty()) {
                stmt.setString(index++, application.getApplication_no());
            }
            if (application.getUser_no() != null && !application.getUser_no().isEmpty()) {
                stmt.setString(index++, application.getUser_no());
            }
            if (application.getAp_type() != null && !application.getAp_type().isEmpty()) {
                stmt.setString(index++, application.getAp_type());
            }
            if (application.getApplication_State() != null && !application.getApplication_State().isEmpty()) {
                stmt.setString(index++, application.getApplication_State());
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //更新
    public boolean updateByNo(Application application) { //更新application信息 成功返回true 错误返回false
        String sql = "UPDATE application SET user_no = ?, ap_type = ?, application_state = ? WHERE application_no = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, application.getUser_no());
            stmt.setString(2, application.getAp_type());
            stmt.setString(3, application.getApplication_State());
            stmt.setString(4, application.getApplication_no());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //查询
    public ResultSet select(Application application) { //根据application中的信息进行查询
        StringBuilder sql = new StringBuilder("SELECT * FROM application WHERE 1=1");

        if (application.getApplication_no() != null && !application.getApplication_no().isEmpty()) {
            sql.append(" AND application_no = ?");
        }
        if (application.getUser_no() != null && !application.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (application.getAp_type() != null && !application.getAp_type().isEmpty()) {
            sql.append(" AND ap_type = ?");
        }
        if (application.getApplication_State() != null && !application.getApplication_State().isEmpty()) {
            sql.append(" AND application_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            if (application.getApplication_no() != null && !application.getApplication_no().isEmpty()) {
                stmt.setString(index++, application.getApplication_no());
            }
            if (application.getUser_no() != null && !application.getUser_no().isEmpty()) {
                stmt.setString(index++, application.getUser_no());
            }
            if (application.getAp_type() != null && !application.getAp_type().isEmpty()) {
                stmt.setString(index++, application.getAp_type());
            }
            if (application.getApplication_State() != null && !application.getApplication_State().isEmpty()) {
                stmt.setString(index++, application.getApplication_State());
            }

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
