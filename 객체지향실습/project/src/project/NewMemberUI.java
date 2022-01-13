package project;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewMemberUI extends JFrame{

    private JPanel textPanel;
    private JPanel buttonPanel;

    private JLabel nameLabel;
    private JLabel birthLabel;
    private JLabel telNoLabel;
    private JLabel emailLabel;

    protected JTextField nameInput;
    protected JTextField birthInput;
    protected JTextField telNoInput;
    protected JTextField emailInput;

    protected JButton exitButton;
    protected JButton addNewMemberButton;


    public NewMemberUI(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("회원가입");

        textPanel = new JPanel();
        buttonPanel = new JPanel();

        nameLabel = new JLabel("이름");
        birthLabel = new JLabel("생년월일");
        telNoLabel = new JLabel("전화번호");
        emailLabel = new JLabel("E-Mail");

        nameInput = new JTextField(10);
        birthInput = new JTextField(10);
        telNoInput = new JTextField(10);
        emailInput = new JTextField(10);

        textPanel.setLayout(null);
        textPanel.add(nameLabel);
        textPanel.add(nameInput);
        textPanel.add(birthLabel);
        textPanel.add(birthInput);
        textPanel.add(telNoLabel);
        textPanel.add(telNoInput);
        textPanel.add(emailLabel);
        textPanel.add(emailInput);

        nameLabel.setBounds(140, 80, 100, 30);
        nameInput.setBounds(240, 80, 120, 30);

        birthLabel.setBounds(140, 120, 100, 30);
        birthInput.setBounds(240, 120, 120, 30);

        telNoLabel.setBounds(140, 160, 100, 30);
        telNoInput.setBounds(240, 160, 120, 30);

        emailLabel.setBounds(140, 200, 100, 30);
        emailInput.setBounds(240, 200, 120, 30);

        ImageIcon icon = new ImageIcon("images/회원가입.jpg");
        ImageIcon pressedicon = new ImageIcon("images/pressed회원가입.jpg");
        addNewMemberButton = new JButton("",icon);
        addNewMemberButton.setPressedIcon(pressedicon);
        addNewMemberButton.setBorderPainted(false);
        addNewMemberButton.setFocusPainted(false);
        addNewMemberButton.setContentAreaFilled(false);

        ImageIcon icon1 = new ImageIcon("images/종료.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed종료.jpg");
        exitButton = new JButton("",icon1);
        exitButton.setPressedIcon(pressedicon1);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);


        buttonPanel.add(addNewMemberButton);
        buttonPanel.add(exitButton);

        this.setLayout(new BorderLayout());
        this.add(textPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setResizable(false);
        this.setSize(500, 400);
        this.setVisible(true);
    }
    public void addButtonActionListener(ActionListener listener){
        addNewMemberButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }

}
