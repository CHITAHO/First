package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoreUI extends JFrame{
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JPanel checkPanel;

    protected JCheckBox userCheck;

    protected JLabel startTimeLabel;
    protected JLabel memberNameLabel;
    protected JLabel memberMileageLabel;
    protected JLabel priceLabel;

    protected JLabel userNumLabel;
    protected JLabel timeTextLabel;
    protected JTextField userNum;
    protected JTextField timeText;

    protected JButton searchButton;
    protected JButton timeaddButton;
    protected JButton serviceButton;
    protected JButton mileageButton;
    protected JButton exitButton;


    public MoreUI(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("더보기");

        checkPanel = new JPanel();
        labelPanel = new JPanel();
        buttonPanel = new JPanel();

        userCheck = new JCheckBox("회원",false);

        startTimeLabel = new JLabel("시작시간 :");
        memberNameLabel = new JLabel("회원명(회원번호) :");
        memberMileageLabel = new JLabel("마일리지(10점에1분) : ");
        priceLabel = new JLabel("금액 : ");

        userNumLabel = new JLabel("회원 번호:");
        timeTextLabel = new JLabel("추가 시간(분):");

        Font font = new Font("맑은 고딕",Font.BOLD,13);
        timeText=new JTextField(20);
        userNum=new JTextField(20);
        checkPanel.add(userCheck);

        labelPanel.setLayout(null);
        labelPanel.add(startTimeLabel);
        labelPanel.add(memberNameLabel);
        labelPanel.add(memberMileageLabel);
        labelPanel.add(priceLabel);

        labelPanel.add(userNumLabel);
        labelPanel.add(userNum);
        labelPanel.add(timeTextLabel);
        labelPanel.add(timeText);

        startTimeLabel.setFont(font);
        memberNameLabel.setFont(font);
        memberMileageLabel.setFont(font);
        priceLabel.setFont(font);
        userNumLabel.setFont(font);
        timeTextLabel.setFont(font);

        startTimeLabel.setBounds(50, 0, 150, 30);
        memberNameLabel.setBounds(50, 40, 200, 30);
        memberMileageLabel.setBounds(50, 80, 200, 30);
        priceLabel.setBounds(50, 120, 200, 30);

        userNumLabel.setBounds(30, 170, 120, 30);
        userNum.setBounds(135, 170, 120, 30);
        timeTextLabel.setBounds(30, 210, 120, 30);
        timeText.setBounds(135, 210, 120, 30);

        ImageIcon icon1 = new ImageIcon("images/회원관리.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed회원관리.jpg");
        searchButton = new JButton("",icon1);
        searchButton.setPressedIcon(pressedicon1);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);

        ImageIcon icon2 = new ImageIcon("images/시간추가.jpg");
        ImageIcon pressedicon2 = new ImageIcon("images/pressed시간추가.jpg");
        timeaddButton = new JButton("",icon2);
        timeaddButton.setPressedIcon(pressedicon2);
        timeaddButton.setBorderPainted(false);
        timeaddButton.setFocusPainted(false);
        timeaddButton.setContentAreaFilled(false);

        ImageIcon icon3 = new ImageIcon("images/서비스.jpg");
        ImageIcon pressedicon3 = new ImageIcon("images/pressed서비스.jpg");
        serviceButton = new JButton("",icon3);
        serviceButton.setPressedIcon(pressedicon3);
        serviceButton.setBorderPainted(false);
        serviceButton.setFocusPainted(false);
        serviceButton.setContentAreaFilled(false);

        ImageIcon icon4 = new ImageIcon("images/마일리지.jpg");
        ImageIcon pressedicon4 = new ImageIcon("images/pressed마일리지.jpg");
        mileageButton = new JButton("",icon4);
        mileageButton.setPressedIcon(pressedicon4);
        mileageButton.setBorderPainted(false);
        mileageButton.setFocusPainted(false);
        mileageButton.setContentAreaFilled(false);

        // 종료버튼
        ImageIcon icon5 = new ImageIcon("images/사용종료.jpg");
        ImageIcon pressedicon5 = new ImageIcon("images/pressed사용종료.jpg");
        exitButton = new JButton("",icon5);
        exitButton.setPressedIcon(pressedicon5);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);



        searchButton.setPreferredSize(new Dimension(130, 50));
        searchButton.setFont(font);

        timeaddButton.setPreferredSize(new Dimension(80, 40));
        //timeaddButton.setFont(new Font("맑은 고딕",Font.BOLD,10));
        serviceButton.setPreferredSize(new Dimension(80, 40));
        //serviceButton.setFont(new Font("맑은 고딕",Font.BOLD,10));
        mileageButton.setPreferredSize(new Dimension(80, 40));
        //mileageButton.setFont(new Font("맑은 고딕",Font.BOLD,10));
        exitButton.setPreferredSize(new Dimension(80, 40));
        //exitButton.setFont(new Font("맑은 고딕",Font.BOLD,10));

        checkPanel.add(searchButton);

        buttonPanel.add(timeaddButton);
        buttonPanel.add(mileageButton);
        buttonPanel.add(serviceButton);
        buttonPanel.add(exitButton);


        this.setLayout(new BorderLayout());
        this.add(checkPanel, BorderLayout.NORTH);
        this.add(labelPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        searchButton.setEnabled(false);
        userNum.setEnabled(false);
        serviceButton.setEnabled(false);
        mileageButton.setEnabled(false);
        exitButton.setEnabled(false);

        this.setResizable(false);
        this.setSize(400, 400);
        setVisible(true);
    }
    public void addItemListener(ItemListener listener) {
        userCheck.addItemListener(listener);
    }
    public void addButtonActionListener(ActionListener listener){
        searchButton.addActionListener(listener);
        timeaddButton.addActionListener(listener);
        serviceButton.addActionListener(listener);
        mileageButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}