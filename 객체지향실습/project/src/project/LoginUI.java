package project;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame{

    protected final String loginID = "root";
    protected final String passward = "1234";

    private JPanel textPanel;
    private JPanel buttonPanel;

    private JLabel idLabel;
    private JLabel passwordLabel;

    protected JTextField idInput;
    protected JPasswordField passwordInput;

    protected JButton loginButton;
    protected JButton exitButton;


    public LoginUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("관리자 로그인");

        textPanel = new JPanel();
        buttonPanel = new JPanel();

        idLabel = new JLabel("ID");
        passwordLabel = new JLabel("password");

        idInput = new JTextField(15);
        passwordInput = new JPasswordField(15);

        textPanel.setLayout(null);

        textPanel.add(idLabel);
        textPanel.add(idInput);
        textPanel.add(passwordLabel);
        textPanel.add(passwordInput);

        idLabel.setBounds(100, 50, 100, 30);
        idInput.setBounds(170, 50, 120, 30);
        passwordLabel.setBounds(60, 130, 100, 30);
        passwordInput.setBounds(170, 130, 120, 30);

        ImageIcon icon = new ImageIcon("images/로그인.jpg");
        ImageIcon pressedicon = new ImageIcon("images/pressed로그인.jpg");
        loginButton = new JButton("",icon);
        loginButton.setPressedIcon(pressedicon);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);

        ImageIcon icon1 = new ImageIcon("images/종료.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed종료.jpg");
        exitButton = new JButton("",icon1);
        exitButton.setPressedIcon(pressedicon1);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        this.setLayout(new BorderLayout());
        this.add(textPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);


        this.setResizable(false);
        this.setSize(400, 300);
        this.setVisible(true);
    }
    public void addButtonActionListener(ActionListener listener){
        loginButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}
