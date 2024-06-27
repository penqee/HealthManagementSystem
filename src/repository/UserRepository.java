package repository;

import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    //用户表 增删改查
    //增加
    public boolean insert(User user) { //将user新增到用户表
        String sql = "INSERT INTO user (user_no, user_password, user_name, user_phone_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUser_no());
            stmt.setString(2, user.getUser_Password());
            stmt.setString(3, user.getUser_name());
            stmt.setString(4, user.getUser_phone_number());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //删除
    public boolean delete(User user) { //删除user信息 成功返回true 错误返回false
        StringBuilder sql = new StringBuilder("DELETE FROM user WHERE 1=1");

        // 根据用户信息构建删除条件
        if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (user.getUser_Password() != null && !user.getUser_Password().isEmpty()) {
            sql.append(" AND user_password = ?");
        }
        if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
            sql.append(" AND user_name = ?");
        }
        if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
            sql.append(" AND user_phone_number = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            // 设置查询参数
            if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
                stmt.setString(index++, user.getUser_no());
            }
            if (user.getUser_Password() != null && !user.getUser_Password().isEmpty()) {
                stmt.setString(index++, user.getUser_Password());
            }
            if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
                stmt.setString(index++, user.getUser_name());
            }
            if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
                stmt.setString(index++, user.getUser_phone_number());
            }
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //更新
    public boolean updateByNo(User user) { //更新user信息 成功返回true 错误返回false
        String sql = "UPDATE user SET user_password = ?, user_name = ?, user_phone_number = ? WHERE user_no = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUser_Password());
            stmt.setString(2, user.getUser_name());
            stmt.setString(3, user.getUser_phone_number());
            stmt.setString(4, user.getUser_no());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //查询
    public ResultSet select(User user) { //根据user中的信息进行查询
        StringBuilder sql = new StringBuilder("SELECT * FROM user WHERE 1=1");

        // 根据用户信息构建查询条件
        if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (user.getUser_Password() != null && !user.getUser_Password().isEmpty()) {
            sql.append(" AND user_password = ?");
        }
        if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
            sql.append(" AND user_name = ?");
        }
        if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
            sql.append(" AND user_phone_number = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // 设置查询参数
            if (user.getUser_no() != null && !user.getUser_no().isEmpty()) {
                stmt.setString(index++, user.getUser_no());
            }
            if (user.getUser_Password() != null && !user.getUser_Password().isEmpty()) {
                stmt.setString(index++, user.getUser_Password());
            }
            if (user.getUser_name() != null && !user.getUser_name().isEmpty()) {
                stmt.setString(index++, user.getUser_name());
            }
            if (user.getUser_phone_number() != null && !user.getUser_phone_number().isEmpty()) {
                stmt.setString(index++, user.getUser_phone_number());
            }

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

