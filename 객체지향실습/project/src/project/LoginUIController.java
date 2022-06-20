package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUIController {
    private final LoginUI v;
    public LoginUIController(LoginUI v){
        this.v = v;
        v.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == v.loginButton){
                    if((v.idInput.getText().equals(v.loginID)) && (String.valueOf(v.passwordInput.getPassword()).equals(v.passward))){
                        new MainUIController(new MainUI());
                        v.dispose();  // JFrame 닫기
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        v.idInput.setText("");
                        v.passwordInput.setText("");
                    }
                }
                else if(obj == v.exitButton){
                    System.exit(0);
                }
            }
        });
    }
}
