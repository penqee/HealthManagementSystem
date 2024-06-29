package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class APLogin extends JPanel {
    private MainFrame mainFrame;
    public APLogin(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 设置字体
        Font labelFont = new Font("Serif", Font.BOLD, 18);
        Font textFont = new Font("Serif", Font.PLAIN, 18);
        Font buttonFont = new Font("Serif", Font.PLAIN, 18);

        // 添加标题标签
        JLabel titleLabel = new JLabel("陪诊师登录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // 添加账号标签和文本框
        JLabel userLabel = new JLabel("账号:");
        userLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 30, 10, 10); // 调整左边距
        add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        userText.setFont(textFont);
        userText.setPreferredSize(new Dimension(200, 30)); // 设置文本框大小
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 30); // 调整左边距
        add(userText, gbc);

        // 添加密码标签和文本框
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 30, 10, 10); // 调整左边距
        add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setFont(textFont);
        passwordText.setPreferredSize(new Dimension(200, 30)); // 设置文本框大小
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 30); // 调整左边距
        add(passwordText, gbc);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 15, 5, 15); // 设置按钮之间的间距

        // 添加登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setFont(buttonFont);
        loginButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(loginButton, buttonGbc);

        // 添加返回按钮
        JButton backButton = new JButton("返回");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 1;
        buttonGbc.gridy = 0;
        buttonPanel.add(backButton, buttonGbc);

        // 添加退出按钮
        JButton exitButton = new JButton("退出");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 2;
        buttonGbc.gridy = 0;
        buttonPanel.add(exitButton, buttonGbc);

        // 添加按钮面板到主布局
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // 为按钮添加动作监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加登录逻辑
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                System.out.println("账号: " + username);
                System.out.println("密码: " + password);
                //添加控制
                mainFrame.showPanel("APPanel");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加返回逻辑
                System.out.println("点击了返回按钮");
                mainFrame.showPanel("SelectPanel");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加退出逻辑
                System.exit(0);
            }
        });
    }
}
