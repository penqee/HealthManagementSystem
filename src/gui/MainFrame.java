package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 添加各个面板
        mainPanel.add(new SelectPanel(this),"SelectPanel");
        mainPanel.add(new UserLogin(this),"UserLogin");
        mainPanel.add(new AdminLogin(this),"AdminLogin");
        mainPanel.add(new APLogin(this),"APLogin");
        mainPanel.add(new UserPanel(this),"UserPanel");
        mainPanel.add(new APPanel(this),"APPanel");
        mainPanel.add(new AdminPanel(this),"AdminPanel");

        add(mainPanel);
        setTitle("健康管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public void showPanel(String panelName) { //切换panel
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}