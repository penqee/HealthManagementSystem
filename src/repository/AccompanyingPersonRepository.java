package repository;

import model.AccompanyingPerson;
import model.Application;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccompanyingPersonRepository {
    //陪诊师表 增删改查
    //增加
    public boolean insert(AccompanyingPerson accompanyingPerson) { //将ap新增到用户表
        String sql = "INSERT INTO accompanyingPerson (ap_no, ap_password, ap_name, ap_phone_number, ap_type, ap_state) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accompanyingPerson.getAp_no());
            stmt.setString(2, accompanyingPerson.getAp_password());
            stmt.setString(3, accompanyingPerson.getAp_name());
            stmt.setString(4, accompanyingPerson.getAp_phone_number());
            stmt.setString(5, accompanyingPerson.getAp_type());
            stmt.setString(6, accompanyingPerson.getAp_state());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //删除
    public boolean delete(AccompanyingPerson accompanyingPerson) { //删除ap信息 成功返回true 错误返回false
        StringBuilder sql = new StringBuilder("DELETE FROM accompanyingPerson WHERE 1=1");

        if (accompanyingPerson.getAp_no() != null && !accompanyingPerson.getAp_no().isEmpty()) {
            sql.append(" AND ap_no = ?");
        }
        if (accompanyingPerson.getAp_password() != null && !accompanyingPerson.getAp_password().isEmpty()) {
            sql.append(" AND ap_password = ?");
        }
        if (accompanyingPerson.getAp_name() != null && !accompanyingPerson.getAp_name().isEmpty()) {
            sql.append(" AND ap_name = ?");
        }
        if (accompanyingPerson.getAp_phone_number() != null && !accompanyingPerson.getAp_phone_number().isEmpty()) {
            sql.append(" AND ap_phone_number = ?");
        }
        if (accompanyingPerson.getAp_type() != null && !accompanyingPerson.getAp_type().isEmpty()) {
            sql.append(" AND ap_type = ?");
        }
        if (accompanyingPerson.getAp_state() != null && !accompanyingPerson.getAp_state().isEmpty()) {
            sql.append(" AND ap_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            if (accompanyingPerson.getAp_no() != null && !accompanyingPerson.getAp_no().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_no());
            }
            if (accompanyingPerson.getAp_password() != null && !accompanyingPerson.getAp_password().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_password());
            }
            if (accompanyingPerson.getAp_name() != null && !accompanyingPerson.getAp_name().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_name());
            }
            if (accompanyingPerson.getAp_phone_number() != null && !accompanyingPerson.getAp_phone_number().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_phone_number());
            }
            if (accompanyingPerson.getAp_type() != null && !accompanyingPerson.getAp_type().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_type());
            }
            if (accompanyingPerson.getAp_state() != null && !accompanyingPerson.getAp_state().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_state());
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //更新
    public boolean updateByNo(AccompanyingPerson accompanyingPerson) { //更新ap信息 成功返回true 错误返回false
        String sql = "UPDATE accompanyingPerson SET ap_password = ?, ap_name = ?, ap_phone_number = ?, ap_type = ?, ap_state = ? WHERE ap_no = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accompanyingPerson.getAp_password());
            stmt.setString(2, accompanyingPerson.getAp_name());
            stmt.setString(3, accompanyingPerson.getAp_phone_number());
            stmt.setString(4, accompanyingPerson.getAp_type());
            stmt.setString(5, accompanyingPerson.getAp_state());
            stmt.setString(6, accompanyingPerson.getAp_no());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //查询
    public List<AccompanyingPerson> select(AccompanyingPerson accompanyingPerson) { //根据ap中的信息进行查询
        List<AccompanyingPerson> dataList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM accompanyingPerson WHERE 1=1");

        if (accompanyingPerson.getAp_no() != null && !accompanyingPerson.getAp_no().isEmpty()) {
            sql.append(" AND ap_no = ?");
        }
        if (accompanyingPerson.getAp_password() != null && !accompanyingPerson.getAp_password().isEmpty()) {
            sql.append(" AND ap_password = ?");
        }
        if (accompanyingPerson.getAp_name() != null && !accompanyingPerson.getAp_name().isEmpty()) {
            sql.append(" AND ap_name = ?");
        }
        if (accompanyingPerson.getAp_phone_number() != null && !accompanyingPerson.getAp_phone_number().isEmpty()) {
            sql.append(" AND ap_phone_number = ?");
        }
        if (accompanyingPerson.getAp_type() != null && !accompanyingPerson.getAp_type().isEmpty()) {
            sql.append(" AND ap_type = ?");
        }
        if (accompanyingPerson.getAp_state() != null && !accompanyingPerson.getAp_state().isEmpty()) {
            sql.append(" AND ap_state = ?");
        }

        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;

            if (accompanyingPerson.getAp_no() != null && !accompanyingPerson.getAp_no().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_no());
            }
            if (accompanyingPerson.getAp_password() != null && !accompanyingPerson.getAp_password().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_password());
            }
            if (accompanyingPerson.getAp_name() != null && !accompanyingPerson.getAp_name().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_name());
            }
            if (accompanyingPerson.getAp_phone_number() != null && !accompanyingPerson.getAp_phone_number().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_phone_number());
            }
            if (accompanyingPerson.getAp_type() != null && !accompanyingPerson.getAp_type().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_type());
            }
            if (accompanyingPerson.getAp_state() != null && !accompanyingPerson.getAp_state().isEmpty()) {
                stmt.setString(index++, accompanyingPerson.getAp_state());
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // 将每一行数据读取到 ap 对象中

                    AccompanyingPerson fetchedap = new AccompanyingPerson(null,null,null,null,null,null);
                    fetchedap.setAp_no(rs.getString("ap_no"));
                    fetchedap.setAp_password(rs.getString("ap_password"));
                    fetchedap.setAp_name(rs.getString("ap_name"));
                    fetchedap.setAp_phone_number(rs.getString("ap_phone_number"));
                    fetchedap.setAp_type(rs.getString("ap_type"));
                    fetchedap.setAp_state(rs.getString("ap_state"));
                    // 将 ap 对象添加到 dataList 中
                    dataList.add(fetchedap);
                }
            }

            return dataList.isEmpty() ? null : dataList;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


}
