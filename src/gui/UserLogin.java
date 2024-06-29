package gui;

import controller.UserController;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLogin extends JPanel {
    private MainFrame mainFrame;

    public UserLogin(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 设置字体
        Font labelFont = new Font("Serif", Font.BOLD, 18);
        Font textFont = new Font("Serif", Font.PLAIN, 18);
        Font buttonFont = new Font("Serif", Font.PLAIN, 18);

        // 添加标题标签
        JLabel titleLabel = new JLabel("用户登录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // 添加账号标签和文本框
        JLabel userLabel = new JLabel("账号:");
        userLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        userText.setFont(textFont);
        userText.setPreferredSize(new Dimension(200, 30)); // 设置文本框大小
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(userText, gbc);

        // 添加密码标签和文本框
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setFont(textFont);
        passwordText.setPreferredSize(new Dimension(200, 30)); // 设置文本框大小
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordText, gbc);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 5, 5, 5); // 设置按钮之间的间距

        // 添加登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setFont(buttonFont);
        loginButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(loginButton, buttonGbc);

        // 添加注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setFont(buttonFont);
        registerButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 1;
        buttonGbc.gridy = 0;
        buttonGbc.insets = new Insets(5, 20, 5, 5); // 右侧按钮的额外内边距
        buttonPanel.add(registerButton, buttonGbc);

        // 添加返回按钮
        JButton backButton = new JButton("返回");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 1;
        buttonGbc.insets = new Insets(5, 5, 5, 5); // 重置内边距
        buttonPanel.add(backButton, buttonGbc);

        // 添加退出按钮
        JButton exitButton = new JButton("退出");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小
        buttonGbc.gridx = 1;
        buttonGbc.gridy = 1;
        buttonGbc.insets = new Insets(5, 20, 5, 5); // 右侧按钮的额外内边距
        buttonPanel.add(exitButton, buttonGbc);

        // 添加按钮面板到主布局
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // 为按钮添加动作监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加登录逻辑
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                //添加控制
                mainFrame.showPanel("UserPanel");
                System.out.println("账号: " + username);
                System.out.println("密码: " + password);
                // 例如： mainFrame.showCard("Panel2");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registerFrame = new JFrame("注册");
                registerFrame.setSize(300, 200);
                registerFrame.setLocationRelativeTo(null);

                JPanel registerPanel = new JPanel(new GridBagLayout());
                GridBagConstraints registerGbc = new GridBagConstraints();
                registerGbc.insets = new Insets(5, 5, 5, 5);
                registerGbc.fill = GridBagConstraints.HORIZONTAL;

                registerGbc.gridx = 0;
                registerGbc.gridy = 0;
                registerGbc.anchor = GridBagConstraints.EAST;
                registerPanel.add(new JLabel("密码"), registerGbc);
                registerGbc.gridx = 1;
                registerGbc.gridy = 0;
                registerGbc.weightx = 1.0;
                registerGbc.anchor = GridBagConstraints.WEST;
                JPasswordField passwordField = new JPasswordField(15);
                registerPanel.add(passwordField, registerGbc);

                registerGbc.gridx = 0;
                registerGbc.gridy = 1;
                registerGbc.anchor = GridBagConstraints.EAST;
                registerPanel.add(new JLabel("姓名"), registerGbc);
                registerGbc.gridx = 1;
                registerGbc.gridy = 1;
                registerGbc.weightx = 1.0;
                registerGbc.anchor = GridBagConstraints.WEST;
                JTextField nameField = new JTextField(15);
                registerPanel.add(nameField, registerGbc);

                registerGbc.gridx = 0;
                registerGbc.gridy = 2;
                registerGbc.anchor = GridBagConstraints.EAST;
                registerPanel.add(new JLabel("手机号"), registerGbc);
                registerGbc.gridx = 1;
                registerGbc.gridy = 2;
                registerGbc.weightx = 1.0;
                registerGbc.anchor = GridBagConstraints.WEST;
                JTextField phoneField = new JTextField(15);
                registerPanel.add(phoneField, registerGbc);

                // 添加用于注册按钮的面板
                registerGbc.gridx = 0;
                registerGbc.gridy = 3;
                registerGbc.gridwidth = 2;
                registerGbc.anchor = GridBagConstraints.CENTER; // 居中注册按钮
                registerGbc.fill = GridBagConstraints.NONE; // 防止按钮延伸
                JButton registerButton = new JButton("注册");
                registerPanel.add(registerButton, registerGbc);

                registerFrame.add(registerPanel);
                registerFrame.setVisible(true);

                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 在这里添加注册逻辑
                        String password = new String(passwordField.getPassword());
                        String name = nameField.getText();
                        String phone = phoneField.getText();

                        // 示例打印注册信息，可以替换为实际的注册逻辑
                        System.out.println("密码: " + password);
                        System.out.println("姓名: " + name);
                        System.out.println("手机号: " + phone);

                        registerFrame.dispose();
                    }
                });
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
