package repository;

import model.Admin;
import model.Appointment;
import model.User;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    //查询
    public List<Admin> select(Admin admin) { //根据user中的信息进行查询
        List<Admin> dataList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM admin WHERE 1=1");

        // 根据用户信息构建查询条件
        if (admin.getAdmin_no() != null && !admin.getAdmin_no().isEmpty()) {
            sql.append(" AND admin_no = ?");
        }
        if (admin.getAdmin_password() != null && !admin.getAdmin_password().isEmpty()) {
            sql.append(" AND admin_password = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            // 设置查询参数
            if (admin.getAdmin_no() != null && !admin.getAdmin_no().isEmpty()) {
                stmt.setString(index++, admin.getAdmin_no());
            }
            if (admin.getAdmin_password() != null && !admin.getAdmin_password().isEmpty()) {
                stmt.setString(index++, admin.getAdmin_password());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // 将每一行数据读取到 admin 对象中
                    Admin fetchedAdmin = new Admin(null,null);
                    fetchedAdmin.setAdmin_no(rs.getString("admin_no"));
                    fetchedAdmin.setAdmin_password(rs.getString("admin_password"));

                    // 将 admin 对象添加到 dataList 中
                    dataList.add(fetchedAdmin);
                }
            }

            return dataList.isEmpty() ? null : dataList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
