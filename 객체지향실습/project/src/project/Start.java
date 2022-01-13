package project;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Start extends JFrame{
    /*여기있는 이미지를 프레임에 그려줄거임.*/
    private Container cPane;
    private ImageIcon img;
    private JLabel ImgBox;

    public Start() {
        super.setTitle("세종 노래방");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cPane = getContentPane();
        cPane.setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    new LoginUIController(new LoginUI());
                    dispose();
                }
            }
        });

        img = new ImageIcon("images/main.jpg");
        ImgBox = new JLabel(img);
        ImgBox = new JLabel(img);
        ImgBox.setBounds(0,0,400,300);

        cPane.add(ImgBox);
        setSize(400,330);
        setVisible(true);
    }
    public static void main(String[] args){
        new Start();
    }
}