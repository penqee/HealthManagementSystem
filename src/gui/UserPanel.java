package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
public class UserPanel extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> typeComboBox;


    public UserPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // 创建预约陪诊师面板
        JPanel bookApPanel = createBookApPanel();
        tabbedPane.addTab("预约陪诊师", bookApPanel);

        // 创建申请成为兼职陪诊师面板
        JPanel applyApPanel = createApplyApPanel();
        tabbedPane.addTab("申请成为兼职陪诊师", applyApPanel);

        // 创建查看自己的预约记录面板
        JPanel viewAppointmentsPanel = createAppointmentViewPanel();
        tabbedPane.addTab("查看自己的预约记录", viewAppointmentsPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createBookApPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 创建一个额外的容器来包含类型标签、选择框和查询按钮
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.insets = new Insets(5, 5, 5, 5);

        // 添加类型标签
        JLabel typeLabel = new JLabel("类型:");
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(typeLabel, centerGbc);

        // 添加选择框
        typeComboBox = new JComboBox<>(new String[]{"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家"});
        centerGbc.gridx = 1;
        centerGbc.gridy = 0;
        centerPanel.add(typeComboBox, centerGbc);

        // 添加查询按钮
        JButton queryButton = new JButton("查询");
        centerGbc.gridx = 2;
        centerGbc.gridy = 0;
        centerPanel.add(queryButton, centerGbc);

        // 将 centerPanel 添加到主 panel 并居中对齐
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(centerPanel, gbc);

        // 创建表格
        String[] columnNames = {"陪诊师编号", "陪诊师姓名", "陪诊师手机号", "陪诊师类型", "陪诊师状态"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        // 添加预约按钮
        JButton bookButton = new JButton("预约");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(bookButton, gbc);

        // 查询按钮动作监听
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                //loadAccompanyingPersons(selectedType);
            }
        });

        // 预约按钮动作监听
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String apNo = (String) tableModel.getValueAt(selectedRow, 0);
                    bookAccompanyingPerson(apNo);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "请先选择一个陪诊师");
                }
            }
        });

        return panel;
    }

    /*
        private void loadAccompanyingPersons(String type) {
            try (Connection conn = DBUtil.getConnection()) {
                String sql = "SELECT * FROM accompanyingPerson WHERE ap_type = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, type);
                    try (ResultSet rs = stmt.executeQuery()) {
                        tableModel.setRowCount(0); // 清空表格数据
                        while (rs.next()) {
                            String apNo = rs.getString("ap_no");
                            String apName = rs.getString("ap_name");
                            String apPhoneNumber = rs.getString("ap_phone_number");
                            String apType = rs.getString("ap_type");
                            String apState = rs.getString("ap_state");
                            tableModel.addRow(new Object[]{apNo, apName, apPhoneNumber, apType, apState});
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

     */
    private void bookAccompanyingPerson(String apNo) {
        // 这里添加预约逻辑，例如将预约信息插入到数据库
        JOptionPane.showMessageDialog(mainFrame, "已预约陪诊师编号: " + apNo);
    }


    private JPanel createApplyApPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 创建一个额外的容器来包含类型标签、选择框和申请按钮
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.insets = new Insets(5, 5, 5, 5);

        // 添加类型标签
        JLabel typeLabel = new JLabel("申请类型:");
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(typeLabel, centerGbc);

        // 添加选择框
        typeComboBox = new JComboBox<>(new String[]{"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家", "所有"});
        centerGbc.gridx = 1;
        centerGbc.gridy = 0;
        centerPanel.add(typeComboBox, centerGbc);

        // 添加申请按钮
        JButton applyButton = new JButton("申请");
        centerGbc.gridx = 2;
        centerGbc.gridy = 0;
        centerPanel.add(applyButton, centerGbc);

        // 将 centerPanel 添加到主 panel 并居中对齐
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(centerPanel, gbc);

        // 创建表格
        String[] columnNames = {"申请信息编号", "用户编号", "用户姓名", "用户手机号", "陪诊师服务类型", "申请信息状态"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        // 添加查询申请状态按钮
        JButton checkStatusButton = new JButton("查询申请状态");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(checkStatusButton, gbc);

        // 申请按钮动作监听
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // 查询申请状态按钮动作监听
        checkStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loadApplicationStatus();
                String selectedType = (String) typeComboBox.getSelectedItem();
                applyForAccompanyingPerson(selectedType);
            }
        });

        return panel;
    }

    private void applyForAccompanyingPerson(String type) {
        // 这里添加申请逻辑，例如将申请信息插入到数据库
        JOptionPane.showMessageDialog(mainFrame, "已申请陪诊服务类型: " + type);
    }
/*
    private void loadApplicationStatus() {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM application WHERE user_no = ?"; // 假设用当前用户编号查询
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, currentUser.getUser_no()); // 假设 currentUser 是当前登录的用户对象
                try (ResultSet rs = stmt.executeQuery()) {
                    tableModel.setRowCount(0); // 清空表格数据
                    while (rs.next()) {
                        String applicationNo = rs.getString("application_no");
                        String userNo = rs.getString("user_no");
                        String apType = rs.getString("ap_type");
                        String applicationState = rs.getString("application_state");
                        tableModel.addRow(new Object[]{applicationNo, userNo, apType, applicationState});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

 */

    private JPanel createAppointmentViewPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 创建一个额外的容器来包含类型标签、选择框和查询按钮
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.insets = new Insets(5, 5, 5, 5);

        // 添加类型标签
        JLabel typeLabel = new JLabel("类型:");
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(typeLabel, centerGbc);

        // 添加选择框
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家"});
        centerGbc.gridx = 1;
        centerGbc.gridy = 0;
        centerPanel.add(typeComboBox, centerGbc);

        // 添加查询按钮
        JButton queryButton = new JButton("查询");
        centerGbc.gridx = 2;
        centerGbc.gridy = 0;
        centerPanel.add(queryButton, centerGbc);

        // 将 centerPanel 添加到主 panel 并居中对齐
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(centerPanel, gbc);

        // 创建表格
        String[] columnNames = {"预约信息编号", "用户编号", "用户姓名", "用户手机号", "陪诊师编号", "陪诊师姓名", "陪诊师手机号", "陪诊师服务类型", "预约信息状态"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);

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
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        // 添加更新按钮
        JButton updateButton = new JButton("更新状态");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(updateButton, gbc);

        // 查询按钮动作监听
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                //loadAppointments(selectedType, tableModel);
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

        return panel;
    }
/*
    private void loadAppointments(String type, DefaultTableModel tableModel) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM appointment_view WHERE ap_type = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, type);
                try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAppointmentStatus(String appointmentNo) {
        // 这里添加更新逻辑，例如将预约信息状态更新到数据库
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



