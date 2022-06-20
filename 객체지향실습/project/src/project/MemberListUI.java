package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberListUI extends JFrame{

    protected JTextArea memberText;

    private JScrollPane scroll;
    private JPanel buttonPanel;

    protected JButton memberSearchButton;
    protected JButton memberUpdateButton;
    protected JButton memberDeleteButton;
    protected JButton exitButton;

    public MemberListUI(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("회원관리");

        memberText = new JTextArea(10, 45);
        memberText.setEnabled(false);
        scroll = new JScrollPane(memberText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        memberText.append("회원번호\t이름\t생년월일\t전화번호\t마일리지\tE-Mail\t\n");

        buttonPanel = new JPanel();

        ImageIcon icon1 = new ImageIcon("images/회원검색.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed회원검색.jpg");
        memberSearchButton = new JButton("",icon1);
        memberSearchButton.setPressedIcon(pressedicon1);
        memberSearchButton.setBorderPainted(false);
        memberSearchButton.setFocusPainted(false);
        memberSearchButton.setContentAreaFilled(false);

        ImageIcon icon2 = new ImageIcon("images/정보수정.jpg");
        ImageIcon pressedicon2 = new ImageIcon("images/pressed정보수정.jpg");
        memberUpdateButton = new JButton("",icon2);
        memberUpdateButton.setPressedIcon(pressedicon2);
        memberUpdateButton.setBorderPainted(false);
        memberUpdateButton.setFocusPainted(false);
        memberUpdateButton.setContentAreaFilled(false);

        ImageIcon icon3 = new ImageIcon("images/회원탈퇴.jpg");
        ImageIcon pressedicon3 = new ImageIcon("images/pressed회원탈퇴.jpg");
        memberDeleteButton = new JButton("",icon3);
        memberDeleteButton.setPressedIcon(pressedicon3);
        memberDeleteButton.setBorderPainted(false);
        memberDeleteButton.setFocusPainted(false);
        memberDeleteButton.setContentAreaFilled(false);

        ImageIcon icon4 = new ImageIcon("images/종료.jpg");
        ImageIcon pressedicon4 = new ImageIcon("images/pressed종료.jpg");
        exitButton = new JButton("",icon4);
        exitButton.setPressedIcon(pressedicon4);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);


        buttonPanel.setLayout(null);

        buttonPanel.add(memberSearchButton);
        buttonPanel.add(memberUpdateButton);
        buttonPanel.add(memberDeleteButton);
        buttonPanel.add(exitButton);

        buttonPanel.setPreferredSize(new Dimension(120, this.getHeight()));
        memberSearchButton.setBounds(0, 10, 115, 50);
        memberUpdateButton.setBounds(0, 250, 115, 50);
        memberDeleteButton.setBounds(0, 310, 115, 50);
        exitButton.setBounds(0, 370, 115, 50);

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.EAST);
        this.add(scroll, BorderLayout.WEST);

        this.setResizable(false);
        this.setSize(960, 540);
        this.setVisible(true);
    }

    public void addButtonActionListener(ActionListener listener){
        memberSearchButton.addActionListener(listener);
        memberUpdateButton.addActionListener(listener);
        memberDeleteButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}
