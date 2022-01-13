package project;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MemberUpdateUI extends JFrame{

    private JPanel textPanel;
    private JPanel buttonPanel;

    private JLabel nameLabel;
    private JLabel birthLabel;
    private JLabel telNoLabel;
    private JLabel emailLabel;
    private JLabel updateTelLabel;

    protected JTextField nameInput;
    protected JTextField birthInput;
    protected JTextField telNoInput;
    protected JTextField emailInput;
    protected JTextField updateTelInput;

    protected JButton searchButton;
    protected JButton updateButton;


    public MemberUpdateUI(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("회원정보 수정");

        textPanel = new JPanel();
        buttonPanel = new JPanel();

        updateTelLabel = new JLabel("수정할 회원의 전화번호");
        nameLabel = new JLabel("이름");
        birthLabel = new JLabel("생년월일");
        telNoLabel = new JLabel("전화번호");
        emailLabel = new JLabel("E-Mail");

        updateTelInput = new JTextField(10);
        nameInput = new JTextField(10);
        birthInput = new JTextField(10);
        telNoInput = new JTextField(10);
        emailInput = new JTextField(10);

        textPanel.setLayout(null);
        textPanel.add(updateTelLabel);
        textPanel.add(updateTelInput);
        textPanel.add(nameLabel);
        textPanel.add(nameInput);
        textPanel.add(birthLabel);
        textPanel.add(birthInput);
        textPanel.add(telNoLabel);
        textPanel.add(telNoInput);
        textPanel.add(emailLabel);
        textPanel.add(emailInput);

        updateTelLabel.setBounds(30, 40, 150, 30);
        updateTelInput.setBounds(200, 40, 200, 30);

        nameLabel.setBounds(80, 110, 100, 30);
        nameInput.setBounds(200, 110, 200, 30);

        birthLabel.setBounds(80, 150, 100, 30);
        birthInput.setBounds(200, 150, 200, 30);

        telNoLabel.setBounds(80, 190, 100, 30);
        telNoInput.setBounds(200, 190, 200, 30);

        emailLabel.setBounds(80, 230, 100, 30);
        emailInput.setBounds(200, 230, 200, 30);

        ImageIcon icon1 = new ImageIcon("images/수정조회.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed수정조회.jpg");
        searchButton = new JButton("",icon1);
        searchButton.setPressedIcon(pressedicon1);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);

        ImageIcon icon2 = new ImageIcon("images/수정하기.jpg");
        ImageIcon pressedicon2 = new ImageIcon("images/pressed수정하기.jpg");
        updateButton = new JButton("",icon2);
        updateButton.setPressedIcon(pressedicon2);
        updateButton.setBorderPainted(false);
        updateButton.setFocusPainted(false);
        updateButton.setContentAreaFilled(false);

        nameInput.setEditable(false);
        birthInput.setEditable(false);
        telNoInput.setEditable(false);
        emailInput.setEditable(false);
        updateButton.setEnabled(false);

        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);

        this.setLayout(new BorderLayout());
        this.add(textPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setResizable(false);
        this.setSize(450,400);
        this.setVisible(true);
    }
    public void addButtonActionListener(ActionListener listener){
        updateButton.addActionListener(listener);
        searchButton.addActionListener(listener);

    }

}