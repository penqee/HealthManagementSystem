package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JPanel {
    private MainFrame mainFrame;

    public UserLogin(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 添加标题标签
        JLabel titleLabel = new JLabel("用户登录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // 添加账号标签和文本框
        JLabel userLabel = new JLabel("账号:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(userText, gbc);

        // 添加密码标签和文本框
        JLabel passwordLabel = new JLabel("密码:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordText, gbc);

        // 添加登录按钮
        JButton loginButton = new JButton("登录");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);

        // 添加注册按钮
        JButton registerButton = new JButton("注册");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(registerButton, gbc);

        // 添加返回按钮
        JButton backButton = new JButton("返回");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(backButton, gbc);

        // 添加退出按钮
        JButton exitButton = new JButton("退出");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(exitButton, gbc);

        // 为按钮添加动作监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加登录逻辑
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                System.out.println("账号: " + username);
                System.out.println("密码: " + password);
                // 例如： mainFrame.showCard("Panel2");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加注册逻辑
                System.out.println("点击了注册按钮");
                // 例如： mainFrame.showCard("RegisterPanel");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加返回逻辑
                System.out.println("点击了返回按钮");
                // 例如： mainFrame.showCard("PreviousPanel");
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
