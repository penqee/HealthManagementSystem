package gui;

import controller.AdminController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanel extends JPanel {
    private MainFrame mainFrame;
    private AdminController adminController;

    public AdminPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        adminController = new AdminController();

        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // 创建用户管理面板
        JPanel userManagementPanel = createUserManagementPanel();
        tabbedPane.addTab("用户管理", userManagementPanel);

        // 创建陪诊师管理面板
        JPanel apManagementPanel = createAPManagementPanel();
        tabbedPane.addTab("陪诊师管理", apManagementPanel);

        // 创建预约信息管理面板
        JPanel appointmentManagementPanel = createAppointmentManagementPanel();
        tabbedPane.addTab("预约信息管理", appointmentManagementPanel);

        //创建申请信息管理面板
        JPanel applicationManagementPanel = createApplicationManagementPanel();
        tabbedPane.addTab("申请信息管理", applicationManagementPanel);

        add(tabbedPane, BorderLayout.CENTER);

    }

    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 顶部输入面板
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // 左侧标签靠右对齐
        inputPanel.add(new JLabel("用户编号"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // 右侧文本框靠左对齐
        gbc.weightx = 1.0; // 增加文本框宽度
        JTextField userNoField = new JTextField(15);
        inputPanel.add(userNoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户密码"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userPasswordField = new JTextField(15);
        inputPanel.add(userPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户姓名"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userNameField = new JTextField(15);
        inputPanel.add(userNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户手机号"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userPhoneField = new JTextField(15);
        inputPanel.add(userPhoneField, gbc);

        // 表格
        String[] columnNames = {"用户编号", "用户密码", "用户姓名", "用户手机号"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 底部按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton searchButton = new JButton("查询用户信息");
        JButton addButton = new JButton("增加用户信息");
        JButton updateButton = new JButton("修改用户信息");
        JButton deleteButton = new JButton("删除用户信息");


        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);


        // 事件处理
        // 查询按钮事件处理
        searchButton.addActionListener(new ActionListener() { //查询用户
            @Override
            public void actionPerformed(ActionEvent e) {
                // 模拟查询用户信息并显示在表格中，这里可以根据实际情况从数据库获取数据
                String userNo = userNoField.getText();
                String userPassword = userPasswordField.getText();
                String userName = userNameField.getText();
                String userPhone = userPhoneField.getText();
                try {
                    loadUsers(userNo,userPassword,userName,userPhone,tableModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addButton.addActionListener(new ActionListener() { //添加用户
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("增加用户信息");
                addFrame.setSize(300, 200);
                addFrame.setLocationRelativeTo(null);

                JPanel addPanel = new JPanel(new GridBagLayout());
                GridBagConstraints addGbc = new GridBagConstraints();
                addGbc.insets = new Insets(5, 5, 5, 5);
                addGbc.fill = GridBagConstraints.HORIZONTAL;

                addGbc.gridx = 0;
                addGbc.gridy = 0;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("用户编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 0;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newUserNoField = new JTextField(15);
                addPanel.add(newUserNoField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 1;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("用户密码"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 1;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newUserPasswordField = new JTextField(15);
                addPanel.add(newUserPasswordField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 2;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("用户姓名"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 2;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newUserNameField = new JTextField(15);
                addPanel.add(newUserNameField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 3;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("用户手机号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 3;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newUserPhoneField = new JTextField(15);
                addPanel.add(newUserPhoneField, addGbc);

                // 添加用于保存按钮的面板
                addGbc.gridx = 0;
                addGbc.gridy = 4;
                addGbc.gridwidth = 2;
                addGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                addGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                JButton saveButton = new JButton("保存");
                addPanel.add(saveButton, addGbc);

                addFrame.add(addPanel);
                addFrame.setVisible(true);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String userPassword = newUserPasswordField.getText();
                        String userName = newUserNameField.getText();
                        String userPhone = newUserPhoneField.getText();

                        if (adminController.insertUser(userPassword,userName,userPhone)) {
                            JOptionPane.showMessageDialog(addFrame,"添加成功");
                        } else {
                            JOptionPane.showMessageDialog(addFrame,"添加失败");
                        }

                        //更新表格
                        try {
                            loadUsers(null,null,null,null,tableModel);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        addFrame.dispose();
                    }
                });
            }
        });

        updateButton.addActionListener(new ActionListener() { //更新用户
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    JFrame updateFrame = new JFrame("修改用户信息");
                    updateFrame.setSize(300, 200);
                    updateFrame.setLocationRelativeTo(null);

                    JPanel updatePanel = new JPanel(new GridBagLayout());
                    GridBagConstraints updateGbc = new GridBagConstraints();
                    updateGbc.insets = new Insets(5, 5, 5, 5);
                    updateGbc.fill = GridBagConstraints.HORIZONTAL;

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 0;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("用户编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 0;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateUserNoField = new JTextField(table.getValueAt(selectedRow, 0).toString());
                    updateUserNoField.setEditable(false);
                    updatePanel.add(updateUserNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 1;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("用户密码"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 1;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateUserPasswordField = new JTextField(table.getValueAt(selectedRow, 1).toString());
                    updatePanel.add(updateUserPasswordField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 2;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("用户姓名"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 2;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateUserNameField = new JTextField(table.getValueAt(selectedRow, 2).toString());

                    updatePanel.add(updateUserNameField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 3;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("用户手机号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 3;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateUserPhoneField = new JTextField(table.getValueAt(selectedRow, 3).toString());

                    updatePanel.add(updateUserPhoneField, updateGbc);

                    // 添加用于保存按钮的面板
                    updateGbc.gridx = 0;
                    updateGbc.gridy = 4;
                    updateGbc.gridwidth = 2;
                    updateGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                    updateGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                    JButton saveUpdateButton = new JButton("保存");
                    updatePanel.add(saveUpdateButton, updateGbc);

                    updateFrame.add(updatePanel);
                    updateFrame.setVisible(true);

                    saveUpdateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String userNo = updateUserNoField.getText();
                            String userPassword = updateUserPasswordField.getText();
                            String userName = updateUserNameField.getText();
                            String userPhone = updateUserPhoneField.getText();
                            if (adminController.updateUserByNo(userNo, userPassword,userName,userPhone)) {
                                JOptionPane.showMessageDialog(updateFrame,"更新成功");
                            } else {
                                JOptionPane.showMessageDialog(updateFrame,"更新失败");
                            }

                            //更新表格
                            try {
                                loadUsers(null,null,null,null,tableModel);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            updateFrame.dispose();

                        }
                    });
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() { //删除用户
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String userNo =  tableModel.getValueAt(selectedRow,0).toString();
                    /*
                    String userPassword =  tableModel.getValueAt(selectedRow,1).toString();
                    String userName =  tableModel.getValueAt(selectedRow,2).toString();
                    String userPhone =  tableModel.getValueAt(selectedRow,3).toString();
                    */
                    if (adminController.deleteUser(userNo,null,null,null)) {
                        JOptionPane.showMessageDialog(mainFrame,"删除成功");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,"删除失败");
                    }

                    //更新表格
                    try {
                        loadUsers(null,null,null,null,tableModel);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadUsers(String userNo,String userPassword, String userName, String userPhone,DefaultTableModel tableModel) throws SQLException {
        ResultSet rs = adminController.selectUser(userNo,userPassword,userName,userPhone);
        tableModel.setRowCount(0); // 清空表格数据
        while (rs.next()) {
            String user_no = rs.getString("user_no");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            String user_phone = rs.getString("user_phone_number");
            tableModel.addRow(new Object[]{user_no, user_password, user_name, user_phone});
        }
    }




    private JPanel createAPManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 顶部输入面板
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // 左侧标签靠右对齐
        inputPanel.add(new JLabel("陪诊师编号"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // 右侧文本框靠左对齐
        gbc.weightx = 1.0; // 增加文本框宽度
        JTextField apNoField = new JTextField(15);
        inputPanel.add(apNoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师密码"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField apPasswordField = new JTextField(15);
        inputPanel.add(apPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师姓名"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField apNameField = new JTextField(15);
        inputPanel.add(apNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师电话号"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField apPhoneField = new JTextField(15);
        inputPanel.add(apPhoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师服务类型"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        String[] serviceTypes = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家", "所有"};
        JComboBox<String> apServiceTypeComboBox = new JComboBox<>(serviceTypes);
        inputPanel.add(apServiceTypeComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师状态"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        String[] apStates = {"忙碌", "空闲", "所有"};
        JComboBox<String> apStatesComboBox = new JComboBox<>(apStates);
        inputPanel.add(apStatesComboBox, gbc);


        // 表格
        String[] columnNames = {"陪诊师编号", "陪诊师密码", "陪诊师姓名", "陪诊师电话号", "陪诊师服务类型", "陪诊师状态"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 底部按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton searchButton = new JButton("查询陪诊师信息");
        JButton addButton = new JButton("增加陪诊师信息");
        JButton updateButton = new JButton("修改陪诊师信息");
        JButton deleteButton = new JButton("删除陪诊师信息");

        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // 事件处理
        searchButton.addActionListener(new ActionListener() { //查询
            @Override
            public void actionPerformed(ActionEvent e) {

                String apNo = apNoField.getText();
                String apPassword = apPasswordField.getText();
                String apName = apNameField.getText();
                String apPhone = apPhoneField.getText();
                String apType = apServiceTypeComboBox.getSelectedItem().toString();
                if ("所有".equals(apType)) apType = null;

                String apStatus = apServiceTypeComboBox.getSelectedItem().toString();
                if ("所有".equals(apStatus)) apStatus = null;

                try {
                    loadAPs(apNo,apPassword,apName,apPhone,apType,apStatus,tableModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("增加陪诊师信息");
                addFrame.setSize(300, 300);
                addFrame.setLocationRelativeTo(null);

                JPanel addPanel = new JPanel(new GridBagLayout());
                GridBagConstraints addGbc = new GridBagConstraints();
                addGbc.insets = new Insets(5, 5, 5, 5);
                addGbc.fill = GridBagConstraints.HORIZONTAL;

                addGbc.gridx = 0;
                addGbc.gridy = 0;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 0;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApNoField = new JTextField(15);
                addPanel.add(newApNoField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 1;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师密码"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 1;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApPasswordField = new JTextField(15);
                addPanel.add(newApPasswordField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 2;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师姓名"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 2;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApNameField = new JTextField(15);
                addPanel.add(newApNameField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 3;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师电话号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 3;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApPhoneField = new JTextField(15);
                addPanel.add(newApPhoneField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 4;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师服务类型"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 4;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                String[] serviceType = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家"};
                JComboBox<String> newAPServiceTypeComboBox = new JComboBox<>(serviceType);
                addPanel.add(newAPServiceTypeComboBox, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 5;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师状态"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 5;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                String[] apState = {"忙碌", "空闲"};
                JComboBox<String> newAPStatesComboBox = new JComboBox<>(apState);
                addPanel.add(newAPStatesComboBox, addGbc);

                // 添加用于保存按钮的面板
                addGbc.gridx = 0;
                addGbc.gridy = 6;
                addGbc.gridwidth = 2;
                addGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                addGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                JButton saveButton = new JButton("保存");
                addPanel.add(saveButton, addGbc);

                addFrame.add(addPanel);
                addFrame.setVisible(true);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String apPassword = newApPasswordField.getText();
                        String apName = newApNameField.getText();
                        String apPhone = newApPhoneField.getText();
                        String apType = newAPServiceTypeComboBox.getSelectedItem().toString();
                        String apState = newAPStatesComboBox.getSelectedItem().toString();

                        if (adminController.insertAP(apPassword,apName,apPhone,apType,apState)) {
                            JOptionPane.showMessageDialog(addFrame,"添加成功");
                        } else {
                            JOptionPane.showMessageDialog(addFrame,"添加失败");
                        }

                        //更新表格
                        try {
                            loadAPs(null,null,null,null,null,null,tableModel);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        addFrame.dispose();

                    }
                });
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    JFrame updateFrame = new JFrame("修改陪诊师信息");
                    updateFrame.setSize(300, 300);
                    updateFrame.setLocationRelativeTo(null);

                    JPanel updatePanel = new JPanel(new GridBagLayout());
                    GridBagConstraints updateGbc = new GridBagConstraints();
                    updateGbc.insets = new Insets(5, 5, 5, 5);
                    updateGbc.fill = GridBagConstraints.HORIZONTAL;

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 0;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 0;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApNoField = new JTextField(table.getValueAt(selectedRow, 0).toString());
                    updateApNoField.setEditable(false);
                    updatePanel.add(updateApNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 1;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师密码"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 1;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApPasswordField = new JTextField(table.getValueAt(selectedRow, 1).toString());
                    updatePanel.add(updateApPasswordField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 2;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师姓名"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 2;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApNameField = new JTextField(table.getValueAt(selectedRow, 2).toString());
                    updatePanel.add(updateApNameField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 3;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师电话号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 3;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApPhoneField = new JTextField(table.getValueAt(selectedRow, 3).toString());
                    updatePanel.add(updateApPhoneField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 4;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师服务类型"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 4;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    String[] serviceType = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家"};
                    JComboBox<String> updateAPServiceTypeComboBox = new JComboBox<>(serviceType);
                    updateAPServiceTypeComboBox.setSelectedItem(table.getValueAt(selectedRow, 4).toString());
                    updatePanel.add(updateAPServiceTypeComboBox, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 5;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师状态"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 5;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    String[] apState = {"忙碌", "空闲"};
                    JComboBox<String> updateAPStatesComboBox = new JComboBox<>(apState);
                    updateAPStatesComboBox.setSelectedItem(table.getValueAt(selectedRow, 5).toString());
                    updatePanel.add(updateAPStatesComboBox, updateGbc);

                    // 添加用于保存按钮的面板
                    updateGbc.gridx = 0;
                    updateGbc.gridy = 6;
                    updateGbc.gridwidth = 2;
                    updateGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                    updateGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                    JButton saveUpdateButton = new JButton("保存");
                    updatePanel.add(saveUpdateButton, updateGbc);

                    updateFrame.add(updatePanel);
                    updateFrame.setVisible(true);

                    saveUpdateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String apNo = updateApNoField.getText();
                            String apPassword = updateApPasswordField.getText();
                            String apName = updateApNameField.getText();
                            String apPhone = updateApPhoneField.getText();
                            String apType = updateAPServiceTypeComboBox.getSelectedItem().toString();
                            String apState = updateAPStatesComboBox.getSelectedItem().toString();

                            if (adminController.updateAPByNo(apNo,apPassword,apName,apPhone,apType,apState) ) {
                                JOptionPane.showMessageDialog(updateFrame,"更新成功");
                            } else {
                                JOptionPane.showMessageDialog(updateFrame,"更新失败");
                            }

                            //更新表格
                            try {
                                loadAPs(null,null,null,null,null,null,tableModel);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            updateFrame.dispose();

                        }
                    });
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String apNo = tableModel.getValueAt(selectedRow,0).toString();

                    if (adminController.deleteAp(apNo,null,null,null,null,null) ) {
                        JOptionPane.showMessageDialog(mainFrame,"删除成功");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,"删除失败");
                    }

                    //更新表格
                    try {
                        loadAPs(null,null,null,null,null,null,tableModel);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private void loadAPs(String apNo,String apPassword, String apName, String apPhone, String apType, String apState, DefaultTableModel tableModel) throws SQLException {
        ResultSet rs = adminController.selectAp(apNo,apPassword,apName,apPhone,apType,apState);
        tableModel.setRowCount(0); // 清空表格数据
        while (rs.next()) {
            String ap_no = rs.getString("ap_no");
            String ap_password = rs.getString("ap_password");
            String ap_name = rs.getString("ap_name");
            String ap_phone = rs.getString("ap_phone_number");
            String ap_type = rs.getString("ap_type");
            String ap_State = rs.getString("ap_state");
            tableModel.addRow(new Object[]{ap_no, ap_password, ap_name, ap_phone, ap_type, ap_State});
        }
    }



    private JPanel createAppointmentManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 顶部输入面板
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // 左侧标签靠右对齐
        inputPanel.add(new JLabel("预约信息编号"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // 右侧文本框靠左对齐
        gbc.weightx = 1.0; // 增加文本框宽度
        JTextField apptNoField = new JTextField(15);
        inputPanel.add(apptNoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户编号"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userNoField = new JTextField(15);
        inputPanel.add(userNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户姓名"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userNameField = new JTextField(15);
        inputPanel.add(userNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户手机号"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userPhoneField = new JTextField(15);
        inputPanel.add(userPhoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师编号"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField apNoField = new JTextField(15);
        inputPanel.add(apNoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师姓名"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField apNameField = new JTextField(15);
        inputPanel.add(apNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师手机号"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField apPhoneField = new JTextField(15);
        inputPanel.add(apPhoneField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师服务类型"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        String[] serviceTypes = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家", "所有"};
        JComboBox<String> apServiceTypeComboBox = new JComboBox<>(serviceTypes);
        inputPanel.add(apServiceTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("预约信息状态"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        String[] statusTypes = {"正在进行", "已结束", "所有"};
        JComboBox<String> apptStatesComboBox = new JComboBox<>(statusTypes);
        inputPanel.add(apptStatesComboBox, gbc);

        // 表格
        String[] columnNames = {
                "预约信息编号", "用户编号", "用户姓名", "用户手机号",
                "陪诊师编号", "陪诊师姓名", "陪诊师手机号", "陪诊师服务类型", "预约信息状态"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 底部按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton searchButton = new JButton("查询预约信息");
        JButton addButton = new JButton("增加预约信息");
        JButton updateButton = new JButton("修改预约信息");
        JButton deleteButton = new JButton("删除预约信息");

        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // 事件处理
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apptNo = apptNoField.getText();
                String userNo = userNoField.getText();
                String userName = userNameField.getText();
                String userPhone = userPhoneField.getText();
                String apNo = apNoField.getText();
                String apName = apNameField.getText();
                String apPhone = apPhoneField.getText();
                String apType = apServiceTypeComboBox.getSelectedItem().toString();
                if ("所有".equals(apType)) apType = null;

                String apptStates = apptStatesComboBox.getSelectedItem().toString();
                if ("所有".equals(apptStates)) apptStates = null;

                try {
                    loadAppointments(apptNo,userNo,userName,userPhone,apNo,apName,apPhone,apType,apptStates,tableModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("增加预约信息");
                addFrame.setSize(300, 300);
                addFrame.setLocationRelativeTo(null);

                JPanel addPanel = new JPanel(new GridBagLayout());
                GridBagConstraints addGbc = new GridBagConstraints();
                addGbc.insets = new Insets(5, 5, 5, 5);
                addGbc.fill = GridBagConstraints.HORIZONTAL;
/*
                addGbc.gridx = 0;
                addGbc.gridy = 0;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("预约信息编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 0;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApptNoField = new JTextField(15);
                addPanel.add(newApptNoField, addGbc);
*/
                addGbc.gridx = 0;
                addGbc.gridy = 1;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("用户编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 1;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newUserNoField = new JTextField(15);
                addPanel.add(newUserNoField, addGbc);


                addGbc.gridx = 0;
                addGbc.gridy = 2;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 2;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApNoField = new JTextField(15);
                addPanel.add(newApNoField, addGbc);
/*
                addGbc.gridx = 0;
                addGbc.gridy = 3;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("预约信息状态"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 3;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                String[] statusType = {"正在进行", "已结束"};
                JComboBox<String> newApptStatesComboBox = new JComboBox<>(statusType);
                addPanel.add(newApptStatesComboBox, addGbc);


*/
                // 添加用于保存按钮的面板
                addGbc.gridx = 0;
                addGbc.gridy = 4;
                addGbc.gridwidth = 2;
                addGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                addGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                JButton saveButton = new JButton("保存");
                addPanel.add(saveButton, addGbc);

                addFrame.add(addPanel);
                addFrame.setVisible(true);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //String apptNo = newApptNoField.getText();
                        String userNo = newUserNoField.getText();
                        String apNo = newApNoField.getText();
                        //String apptStates = newApptStatesComboBox.getSelectedItem().toString();

                        if (adminController.insertAppointment(userNo,apNo) ) {
                            JOptionPane.showMessageDialog(addFrame,"添加成功");
                        } else {
                            JOptionPane.showMessageDialog(addFrame,"添加失败");
                        }

                        //更新表格
                        try {
                            loadAppointments(null,null,null,null,null,null,null,null,null,tableModel);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        addFrame.dispose();
                    }
                });
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    JFrame updateFrame = new JFrame("修改预约信息");
                    updateFrame.setSize(300, 300);
                    updateFrame.setLocationRelativeTo(null);

                    JPanel updatePanel = new JPanel(new GridBagLayout());
                    GridBagConstraints updateGbc = new GridBagConstraints();
                    updateGbc.insets = new Insets(5, 5, 5, 5);
                    updateGbc.fill = GridBagConstraints.HORIZONTAL;

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 0;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("预约信息编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 0;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApptNoField = new JTextField(table.getValueAt(selectedRow, 0).toString());
                    updateApptNoField.setEditable(false);
                    updatePanel.add(updateApptNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 1;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("用户编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 1;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateUserNoField = new JTextField(table.getValueAt(selectedRow, 1).toString());
                    updatePanel.add(updateUserNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 2;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 2;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApNoField = new JTextField(table.getValueAt(selectedRow, 4).toString());
                    updatePanel.add(updateApNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 3;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("预约信息状态"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 3;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    String[] statusType = {"正在进行", "已结束"};
                    JComboBox<String> updateApptStatesComboBox = new JComboBox<>(statusType);
                    updateApptStatesComboBox.setSelectedItem(table.getValueAt(selectedRow, 8).toString());
                    updatePanel.add(updateApptStatesComboBox, updateGbc);

                    // 添加用于保存按钮的面板
                    updateGbc.gridx = 0;
                    updateGbc.gridy = 4;
                    updateGbc.gridwidth = 2;
                    updateGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                    updateGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                    JButton saveUpdateButton = new JButton("保存");
                    updatePanel.add(saveUpdateButton, updateGbc);

                    updateFrame.add(updatePanel);
                    updateFrame.setVisible(true);

                    saveUpdateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String apptNo = updateApptNoField.getText();
                            String userNo = updateUserNoField.getText();
                            String apNo = updateApNoField.getText();
                            String apptStates = updateApptStatesComboBox.getSelectedItem().toString();

                            if (adminController.updateAppointmentByNo(apptNo,userNo,apptNo,apptStates)) {
                                JOptionPane.showMessageDialog(updateFrame,"更新成功");
                            } else {
                                JOptionPane.showMessageDialog(updateFrame,"更新失败");
                            }


                            //更新表格
                            try {
                                loadAppointments(null,null,null,null,null,null,null,null,null,tableModel);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            updateFrame.dispose();
                        }
                    });
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String apptNo = tableModel.getValueAt(selectedRow,0).toString();

                    if (adminController.deleteAppointment(apptNo,null,null,null)) {
                        JOptionPane.showMessageDialog(mainFrame,"删除成功");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,"删除失败");
                    }

                    //更新表格
                    try {
                        loadAppointments(null,null,null,null,null,null,null,null,null,tableModel);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadAppointments(String appointmentNo, String userNo,String userName,String userPhone, String apNo,String apName,String apPhone,String apType, String appointmentState, DefaultTableModel tableModel) throws SQLException {
        ResultSet rs = adminController.selectAppointmentView(appointmentNo,userNo,userName,userPhone,appointmentNo,apName,apPhone,apType,appointmentState);
        tableModel.setRowCount(0); // 清空表格数据
        while (rs.next()) {
            String appointment_no = rs.getString("appointment_no");
            String user_no = rs.getString("user_no");
            String user_name = rs.getString("user_name");
            String user_phone = rs.getString("user_phone_number");
            String ap_no = rs.getString("ap_no");
            String ap_name = rs.getString("ap_name");
            String ap_phone = rs.getString("ap_phone_number");
            String ap_type = rs.getString("ap_type");
            String appointment_state = rs.getString("appointment_state");
            tableModel.addRow(new Object[]{appointment_no, user_no, user_name, user_phone, ap_no, ap_name, ap_phone, ap_type, appointment_state});
        }
    }



    private JPanel createApplicationManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 顶部输入面板
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // 左侧标签靠右对齐
        inputPanel.add(new JLabel("申请信息编号"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // 右侧文本框靠左对齐
        gbc.weightx = 1.0; // 增加文本框宽度
        JTextField applicationNoField = new JTextField(15);
        inputPanel.add(applicationNoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户编号"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userNoField = new JTextField(15);
        inputPanel.add(userNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户姓名"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userNameField = new JTextField(15);
        inputPanel.add(userNameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("用户手机号"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField userPhoneField = new JTextField(15);
        inputPanel.add(userPhoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("陪诊师服务类型"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        String[] serviceTypes = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家", "所有"};
        JComboBox<String> serviceTypeComboBox = new JComboBox<>(serviceTypes);
        inputPanel.add(serviceTypeComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("申请信息状态"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        String[] statusTypes = {"待审核", "同意", "拒绝", "所有"};
        JComboBox<String> applicationStatesComboBox = new JComboBox<>(statusTypes);
        inputPanel.add(applicationStatesComboBox, gbc);

        // 表格
        String[] columnNames = {
                "申请信息编号", "用户编号", "用户姓名", "用户手机号",
                "陪诊师服务类型", "申请信息状态"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 底部按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton searchButton = new JButton("查询申请信息");
        JButton addButton = new JButton("增加申请信息");
        JButton updateButton = new JButton("修改申请信息");
        JButton deleteButton = new JButton("删除申请信息");

        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // 事件处理
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String applicationNo = applicationNoField.getText();
                String userNo = userNoField.getText();
                String userName = userNameField.getText();
                String userPhone = userPhoneField.getText();
                String serviceType =  serviceTypeComboBox.getSelectedItem().toString();
                if ("所有".equals(serviceType)) serviceType = null;

                String applicationStates =  applicationStatesComboBox.getSelectedItem().toString();
                if ("所有".equals(applicationStates)) applicationStates = null;

                try {
                    loadApplications(applicationNo,userNo,userName,userPhone,serviceType,applicationStates,tableModel);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("增加申请信息");
                addFrame.setSize(300, 300);
                addFrame.setLocationRelativeTo(null);

                JPanel addPanel = new JPanel(new GridBagLayout());
                GridBagConstraints addGbc = new GridBagConstraints();
                addGbc.insets = new Insets(5, 5, 5, 5);
                addGbc.fill = GridBagConstraints.HORIZONTAL;

                /*
                addGbc.gridx = 0;
                addGbc.gridy = 0;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("申请信息编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 0;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newApplicationNoField = new JTextField(15);
                addPanel.add(newApplicationNoField, addGbc);
                 */


                addGbc.gridx = 0;
                addGbc.gridy = 1;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("用户编号"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 1;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                JTextField newUserNoField = new JTextField(15);
                addPanel.add(newUserNoField, addGbc);

                addGbc.gridx = 0;
                addGbc.gridy = 2;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("陪诊师服务类型"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 2;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                String[] serviceType = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家"};
                JComboBox<String> newServiceTypeComboBox = new JComboBox<>(serviceType);
                addPanel.add(newServiceTypeComboBox, addGbc);

                /*
                addGbc.gridx = 0;
                addGbc.gridy = 3;
                addGbc.anchor = GridBagConstraints.EAST;
                addPanel.add(new JLabel("申请信息状态"), addGbc);
                addGbc.gridx = 1;
                addGbc.gridy = 3;
                addGbc.weightx = 1.0;
                addGbc.anchor = GridBagConstraints.WEST;
                String[] statusType = {"待审核", "同意", "拒绝"};
                JComboBox<String> newApplicationStatusComboBox = new JComboBox<>(statusType);
                addPanel.add(newApplicationStatusComboBox, addGbc);

                 */


                // 添加用于保存按钮的面板
                addGbc.gridx = 0;
                addGbc.gridy = 4;
                addGbc.gridwidth = 2;
                addGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                addGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                JButton saveButton = new JButton("保存");
                addPanel.add(saveButton, addGbc);

                addFrame.add(addPanel);
                addFrame.setVisible(true);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String userNo = newUserNoField.getText();
                        String serviceType = (String) newServiceTypeComboBox.getSelectedItem().toString();
                        //String applicationStatus = (String) newApplicationStatusComboBox.getSelectedItem();

                        if (adminController.insertApplication(userNo,serviceType) ) {
                            JOptionPane.showMessageDialog(addFrame,"添加成功");
                        } else {
                            JOptionPane.showMessageDialog(addFrame,"添加失败");
                        }

                        //更新表格
                        try {
                            loadApplications(null,null,null,null,null,null,tableModel);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        addFrame.dispose();
                    }
                });
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    JFrame updateFrame = new JFrame("修改申请信息");
                    updateFrame.setSize(300, 300);
                    updateFrame.setLocationRelativeTo(null);

                    JPanel updatePanel = new JPanel(new GridBagLayout());
                    GridBagConstraints updateGbc = new GridBagConstraints();
                    updateGbc.insets = new Insets(5, 5, 5, 5);
                    updateGbc.fill = GridBagConstraints.HORIZONTAL;

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 0;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("申请信息编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 0;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateApplicationNoField = new JTextField(table.getValueAt(selectedRow, 0).toString());
                    updateApplicationNoField.setEditable(false);
                    updatePanel.add(updateApplicationNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 1;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("用户编号"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 1;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    JTextField updateUserNoField = new JTextField(table.getValueAt(selectedRow, 1).toString());
                    updatePanel.add(updateUserNoField, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 2;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("陪诊师服务类型"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 2;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    String[] serviceType = {"陪同就医", "提前挂号", "代办问诊", "代取结果", "代办跑腿", "病案到家"};
                    JComboBox<String> updateServiceTypeComboBox = new JComboBox<>(serviceType);
                    updateServiceTypeComboBox.setSelectedItem(table.getValueAt(selectedRow, 4).toString());
                    updatePanel.add(updateServiceTypeComboBox, updateGbc);

                    updateGbc.gridx = 0;
                    updateGbc.gridy = 3;
                    updateGbc.anchor = GridBagConstraints.EAST;
                    updatePanel.add(new JLabel("申请信息状态"), updateGbc);
                    updateGbc.gridx = 1;
                    updateGbc.gridy = 3;
                    updateGbc.weightx = 1.0;
                    updateGbc.anchor = GridBagConstraints.WEST;
                    String[] statusType = {"待审核", "同意", "拒绝"};
                    JComboBox<String> updateApplicationStatesComboBox = new JComboBox<>(statusType);
                    updateApplicationStatesComboBox.setSelectedItem(table.getValueAt(selectedRow, 5).toString());
                    updatePanel.add(updateApplicationStatesComboBox, updateGbc);

                    // 添加用于保存按钮的面板
                    updateGbc.gridx = 0;
                    updateGbc.gridy = 4;
                    updateGbc.gridwidth = 2;
                    updateGbc.anchor = GridBagConstraints.CENTER; // 居中保存按钮
                    updateGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                    JButton saveUpdateButton = new JButton("保存");
                    updatePanel.add(saveUpdateButton, updateGbc);

                    updateFrame.add(updatePanel);
                    updateFrame.setVisible(true);

                    saveUpdateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String applicationNo = updateApplicationNoField.getText();
                            //String userNo = updateUserNoField.getText();
                            String apTyep = updateServiceTypeComboBox.getSelectedItem().toString();
                            String applcationStates = updateApplicationStatesComboBox.getSelectedItem().toString();

                            if (adminController.updateApplicationByNo(applicationNo,apTyep,applcationStates) ) {
                                JOptionPane.showMessageDialog(updateFrame,"更新成功");
                            } else {
                                JOptionPane.showMessageDialog(updateFrame,"更新失败");
                            }

                            //更新表格
                            try {
                                loadApplications(null,null,null,null,null,null,tableModel);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            updateFrame.dispose();
                        }
                    });
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String applicationNo = tableModel.getValueAt(selectedRow,0).toString();

                    if (adminController.deleteApplication(applicationNo,null,null,null) ) {
                        JOptionPane.showMessageDialog(mainFrame,"删除成功");
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,"删除失败");
                    }

                    //更新表格
                    try {
                        loadApplications(null,null,null,null,null,null,tableModel);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadApplications(String applicationNo, String userNo,String userName,String userPhone,String apType, String applicationState, DefaultTableModel tableModel) throws SQLException {
        ResultSet rs = adminController.selectApplicationView(applicationNo,userNo,userName,userPhone,apType,applicationState);
        tableModel.setRowCount(0); // 清空表格数据
        while (rs.next()) {
            String application_no = rs.getString("application_no");
            String user_no = rs.getString("user_no");
            String user_name = rs.getString("user_name");
            String user_phone= rs.getString("user_phone_number");
            String ap_type = rs.getString("ap_type");
            String application_state = rs.getString("application_state");
            tableModel.addRow(new Object[]{application_no, user_no, user_name, user_phone, ap_type, application_state});
        }
    }

}
