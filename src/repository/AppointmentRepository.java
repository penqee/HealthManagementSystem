package repository;

import model.Appointment;
import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    //预约信息表 增删改查
    //增加
    public boolean insert (Appointment appointment) { //将appointment新增到用户表
        String sql = "INSERT INTO appointment (appointment_no, user_no, ap_no, appointment_state) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, appointment.getAppointment_no());
            stmt.setString(2, appointment.getUser_no());
            stmt.setString(3, appointment.getAp_no());
            stmt.setString(4, appointment.getAppointment_state());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
    //删除
    public boolean delete(Appointment appointment) { //删除appointment信息 成功返回true 错误返回false
        StringBuilder sql = new StringBuilder("DELETE FROM appointment WHERE 1=1");

        if (appointment.getAppointment_no() != null && !appointment.getAppointment_no().isEmpty()) {
            sql.append(" AND appointment_no = ?");
        }
        if (appointment.getUser_no() != null && !appointment.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (appointment.getAp_no() != null && !appointment.getAp_no().isEmpty()) {
            sql.append(" AND ap_no = ?");
        }
        if (appointment.getAppointment_state() != null && !appointment.getAppointment_state().isEmpty()) {
            sql.append(" AND appointment_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            if (appointment.getAppointment_no() != null && !appointment.getAppointment_no().isEmpty()) {
                stmt.setString(index++, appointment.getAppointment_no());
            }
            if (appointment.getUser_no() != null && !appointment.getUser_no().isEmpty()) {
                stmt.setString(index++, appointment.getUser_no());
            }
            if (appointment.getAp_no() != null && !appointment.getAp_no().isEmpty()) {
                stmt.setString(index++, appointment.getAp_no());
            }
            if (appointment.getAppointment_state() != null && !appointment.getAppointment_state().isEmpty()) {
                stmt.setString(index++, appointment.getAppointment_state());
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //更新
    public boolean updateByNo(Appointment appointment) { //更新appointment信息 成功返回true 错误返回false
        String sql = "UPDATE appointment SET user_no = ?, ap_no = ?, appointment_state = ? WHERE appointment_no = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, appointment.getUser_no());
            stmt.setString(2, appointment.getAp_no());
            stmt.setString(3, appointment.getAppointment_state());
            stmt.setString(4, appointment.getAppointment_no());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //查询
    public List<Appointment> select(Appointment appointment) { //根据appointment中的信息进行查询
        List<Appointment> dataList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM appointment WHERE 1=1");

        if (appointment.getAppointment_no() != null && !appointment.getAppointment_no().isEmpty()) {
            sql.append(" AND appointment_no = ?");
        }
        if (appointment.getUser_no() != null && !appointment.getUser_no().isEmpty()) {
            sql.append(" AND user_no = ?");
        }
        if (appointment.getAp_no() != null && !appointment.getAp_no().isEmpty()) {
            sql.append(" AND ap_no = ?");
        }
        if (appointment.getAppointment_state() != null && !appointment.getAppointment_state().isEmpty()) {
            sql.append(" AND appointment_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            if (appointment.getAppointment_no() != null && !appointment.getAppointment_no().isEmpty()) {
                stmt.setString(index++, appointment.getAppointment_no());
            }
            if (appointment.getUser_no() != null && !appointment.getUser_no().isEmpty()) {
                stmt.setString(index++, appointment.getUser_no());
            }
            if (appointment.getAp_no() != null && !appointment.getAp_no().isEmpty()) {
                stmt.setString(index++, appointment.getAp_no());
            }
            if (appointment.getAppointment_state() != null && !appointment.getAppointment_state().isEmpty()) {
                stmt.setString(index++, appointment.getAppointment_state());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // 将每一行数据读取到 Appointment 对象中
                    Appointment fetchedAppointment = new Appointment(null,null,null,null);
                    fetchedAppointment.setAppointment_no(rs.getString("Appointment_no"));
                    fetchedAppointment.setUser_no(rs.getString("user_no"));
                    fetchedAppointment.setAp_no(rs.getString("ap_no"));
                    fetchedAppointment.setAppointment_state(rs.getString("appointment_state"));

                    // 将 Appointment 对象添加到 dataList 中
                    dataList.add(fetchedAppointment);
                }
            }

            return dataList.isEmpty() ? null : dataList;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
