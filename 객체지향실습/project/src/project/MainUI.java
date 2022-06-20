package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainUI extends JFrame{
    private JPanel timePanel;
    private JPanel roomPanel;
    private JPanel buttonPanel;
    private JPanel subPanel[];
    protected JLabel realtimeLabel;

    protected JButton newMemberButton;
    protected JButton memberManagerButton;
    protected JButton salesManagerButton;
    protected JButton logoutButton;
    protected JButton exitButton;

    protected JButton room[];
    private JPanel subRoomPanel[];
    private ImageIcon icon = new ImageIcon("images/off.png");

    public MainUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("메인화면");

        timePanel = new JPanel();
        roomPanel = new JPanel();
        buttonPanel = new JPanel();

        realtimeLabel = new JLabel("현재시간 : ");
        timePanel.add(realtimeLabel);

        ImageIcon icon = new ImageIcon("images/회원가입.jpg");
        ImageIcon pressedicon = new ImageIcon("images/pressed회원가입.jpg");
        newMemberButton = new JButton("회원가입",icon);
        newMemberButton.setPressedIcon(pressedicon);
        newMemberButton.setBorderPainted(false);
        newMemberButton.setFocusPainted(false);
        newMemberButton.setContentAreaFilled(false);

        ImageIcon icon1 = new ImageIcon("images/회원관리.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed회원관리.jpg");
        memberManagerButton = new JButton("회원관리",icon1);
        memberManagerButton.setPressedIcon(pressedicon1);
        memberManagerButton.setBorderPainted(false);
        memberManagerButton.setFocusPainted(false);
        memberManagerButton.setContentAreaFilled(false);

        ImageIcon icon2 = new ImageIcon("images/매출조회.jpg");
        ImageIcon pressedicon2 = new ImageIcon("images/pressed매출조회.jpg");
        salesManagerButton = new JButton("매출조회",icon2);
        salesManagerButton.setPressedIcon(pressedicon2);
        salesManagerButton.setBorderPainted(false);
        salesManagerButton.setFocusPainted(false);
        salesManagerButton.setContentAreaFilled(false);

        ImageIcon icon3 = new ImageIcon("images/로그아웃.jpg");
        ImageIcon pressedicon3 = new ImageIcon("images/pressed로그아웃.jpg");
        logoutButton = new JButton("로그아웃",icon3);
        logoutButton.setPressedIcon(pressedicon3);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setContentAreaFilled(false);

        ImageIcon icon4 = new ImageIcon("images/종료.jpg");
        ImageIcon pressedicon4 = new ImageIcon("images/pressed종료.jpg");
        exitButton = new JButton("종료",icon4);
        exitButton.setPressedIcon(pressedicon4);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);

        buttonPanel.add(newMemberButton);
        buttonPanel.add(memberManagerButton);
        buttonPanel.add(salesManagerButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);

        this.setLayout(new BorderLayout());
        this.add(timePanel, BorderLayout.NORTH);
        this.add(roomPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);

        buttonPanel.setLayout(null);
        buttonPanel.setPreferredSize(new Dimension(120, this.getHeight()));
        newMemberButton.setBounds(15, 90, 115, 50);
        memberManagerButton.setBounds(15, 180, 115, 50);
        salesManagerButton.setBounds(15, 270, 115, 50);
        logoutButton.setBounds(15, 360, 115, 50);
        exitButton.setBounds(15, 450, 115, 50);
        roomPanel.setLayout(new GridLayout(0, 3, 50, 0));
        makeRoom();

        this.setResizable(false);
        this.setSize(1280, 720);
        this.setVisible(true);
    }

    public void addButtonActionListener(ActionListener listener){
        newMemberButton.addActionListener(listener);
        memberManagerButton.addActionListener(listener);
        salesManagerButton.addActionListener(listener);
        logoutButton.addActionListener(listener);
        exitButton.addActionListener(listener);
        for(int i=0; i<24; i++){
            room[i].addActionListener(listener);
        }
    }

    public void makeRoom() {
        room = new JButton[24];
        subPanel= new JPanel[3];

        for(int i=0; i<room.length; i++){
            room[i] = new JButton("room"+(i+1),icon);
            room[i].setBorderPainted(false);
            room[i].setFocusPainted(false);
            room[i].setContentAreaFilled(false);
            room[i].setVerticalTextPosition(SwingConstants.TOP);
            room[i].setHorizontalTextPosition(SwingConstants.CENTER);
            room[i].setForeground(Color.blue);
            room[i].setPreferredSize(new Dimension(100,95));
        }

        for(int i=0; i<subPanel.length; i++){
            subPanel[i] = new JPanel();
            subPanel[i].setLayout(new GridLayout(4,2,5,5));
        }

        for(int j=0; j<24; j++){subPanel[j/8].add(room[j]);}

        roomPanel.add(subPanel[0]);
        roomPanel.add(subPanel[1]);
        roomPanel.add(subPanel[2]);
    }
}

