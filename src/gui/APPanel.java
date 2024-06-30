package gui;

import controller.AccompanyingPersonController;
import model.AppointmentView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class APPanel extends JPanel {
    private MainFrame mainFrame;
    private AccompanyingPersonController apc;
    private JTable table;
    private DefaultTableModel tableModel;

    public APPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        apc = new AccompanyingPersonController();
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
                try {
                    loadAppointments();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 更新按钮动作监听
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (apc.updateSelfState()) {
                        JOptionPane.showMessageDialog(mainFrame, "更新成功，当前状态为:" + apc.getAP().getAp_state());
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "更新失败，请确保自己没有预约记录");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void loadAppointments() throws SQLException {
        List<AppointmentView> appointmentViewList = apc.selectSelfAppointment();
        tableModel.setRowCount(0); // 清空表格数据
        if (appointmentViewList != null) {
            for (AppointmentView appointmentView : appointmentViewList) {
                String appointment_no = appointmentView.getAppointment_no();
                String user_no = appointmentView.getUser_no();
                String user_name = appointmentView.getUser_name();
                String user_phone = appointmentView.getUser_phone_number();
                String ap_no = appointmentView.getAp_no();
                String ap_name = appointmentView.getAp_name();
                String ap_phone = appointmentView.getAp_phone_number();
                String ap_type = appointmentView.getAp_type();
                String appointment_state = appointmentView.getAppointment_state();

                tableModel.addRow(new Object[]{appointment_no, user_no, user_name, user_phone, ap_no, ap_name, ap_phone, ap_type, appointment_state});
            }
        }
    }


}



