package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewMemberUIController {
    private final NewMemberUI v;
    private MemberDAO m;
    private ArrayList<Member> members;

    public NewMemberUIController(NewMemberUI v){
        this.v = v;
        m = new MemberDAO();
        members = m.getAll();

        v.addButtonActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == v.addNewMemberButton){
                    String[] strings = (v.birthInput.getText()).split("-");


                    if (!v.telNoInput.getText().matches("^010[-]{1}(\\d{4})[-]{1}(\\d{4})")) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 전화번호 형식입니다.\n 010-xxxx-xxxx형식으로 입력해주세요", "Message",
                                JOptionPane.ERROR_MESSAGE);
                        v.telNoInput.setText("");
                        return;
                    }

                    if (!v.emailInput.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 이메일 형식입니다.\n string@string.string형식으로 입력해주세요.",
                                "Message", JOptionPane.ERROR_MESSAGE);
                        v.emailInput.setText("");
                        return;
                    }

                    if(strings[1].equals("00")||strings[2].equals("00")) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 생년월일입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        v.birthInput.setText("");
                        return;
                    }

                    if(strings.length<3) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 생년월일입니다.\n0000-00-00형식으로 입력해주세요", "Message", JOptionPane.ERROR_MESSAGE);
                        v.birthInput.setText("");
                        return;
                    }

                    if(!members.isEmpty()){
                        for(Member m : members){
                            if(m.getMemberTelNo().equals(v.telNoInput.getText())){
                                JOptionPane.showMessageDialog(null, "중복된 전화번호입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                                v.telNoInput.setText("");
                                return;
                            }
                        }
                    }

                    if(m.registUser(v.nameInput.getText(), v.emailInput.getText(), v.telNoInput.getText(), v.birthInput.getText())) {
                        JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다..");
                        v.dispose();
                        new MemberListUIController(new MemberListUI());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                    v.nameInput.setText("");
                    v.emailInput.setText("");
                    v.telNoInput.setText("");
                    v.birthInput.setText("");
                }
                else if(obj == v.exitButton){
                    v.dispose();
                }
            }

        });

    }
}