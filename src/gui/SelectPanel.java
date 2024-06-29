package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SelectPanel extends JPanel{ //选择模块 用户 管理员 陪诊师
    private MainFrame mainFrame;

    public SelectPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // 设置组件之间的间距

        Font buttonFont = new Font("Serif", Font.PLAIN, 18);

        // 添加用户登录按钮
        JButton userLoginButton = new JButton("用户登录");
        userLoginButton.setFont(buttonFont);
        userLoginButton.setPreferredSize(new Dimension(200, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLoginButton, gbc);

        // 添加管理员登录按钮
        JButton adminLoginButton = new JButton("管理员登录");
        adminLoginButton.setFont(buttonFont);
        adminLoginButton.setPreferredSize(new Dimension(200, 60));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(adminLoginButton, gbc);

        // 添加陪诊师登录按钮
        JButton apLoginButton = new JButton("陪诊师登录");
        apLoginButton.setFont(buttonFont);
        apLoginButton.setPreferredSize(new Dimension(200, 60));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(apLoginButton, gbc);

        // 为按钮添加动作监听器
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("UserLogin");
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("AdminLogin");
            }
        });

        apLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("APLogin");
            }
        });
    }
}

