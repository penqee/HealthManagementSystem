package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class APPanel extends JPanel {
    private MainFrame mainFrame;

    private JTable table;
    private DefaultTableModel tableModel;

    public APPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 创建表格模型
        String[] columnNames = {"预约信息编号", "用户编号", "用户名", "用户手机号", "陪诊师编号", "陪诊师姓名", "陪诊师手机号", "陪诊师服务类型", "预约信息状态"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        // 设置列宽
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // 添加查询按钮
        JButton queryButton = new JButton("查询信息");
        JButton updateButton = new JButton("更新状态");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(queryButton);
        buttonPanel.add(updateButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(buttonPanel, gbc);

        // 查询按钮动作监听
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loadAppointments();
            }
        });

        // 更新按钮动作监听
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String appointmentNo = (String) tableModel.getValueAt(selectedRow, 0);
                    //updateAppointmentStatus(appointmentNo);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "请先选择一个预约记录");
                }
            }
        });
    }
/*
    private void loadAppointments() {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM appointment_view";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                tableModel.setRowCount(0); // 清空表格数据
                while (rs.next()) {
                    String appointmentNo = rs.getString("appointment_no");
                    String userNo = rs.getString("user_no");
                    String userName = rs.getString("user_name");
                    String userPhoneNumber = rs.getString("user_phone_number");
                    String apNo = rs.getString("ap_no");
                    String apName = rs.getString("ap_name");
                    String apPhoneNumber = rs.getString("ap_phone_number");
                    String apType = rs.getString("ap_type");
                    String appointmentState = rs.getString("appointment_state");
                    tableModel.addRow(new Object[]{appointmentNo, userNo, userName, userPhoneNumber, apNo, apName, apPhoneNumber, apType, appointmentState});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAppointmentStatus(String appointmentNo) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE appointment SET appointment_state = '已处理' WHERE appointment_no = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, appointmentNo);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(mainFrame, "预约状态已更新");
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "更新失败，请重试");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 */
}
