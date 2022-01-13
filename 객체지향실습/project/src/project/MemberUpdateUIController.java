package project;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MemberUpdateUIController {

    private MemberDAO dao;
    private final MemberUpdateUI w;
    private Member member;
    private ArrayList<Member> members;
    private RefreshData refresh;

    public MemberUpdateUIController(MemberUpdateUI w, MemberListUI v){
        this.w = w;
        dao= new MemberDAO();
        refresh = new RefreshData(v);
        refresh.refreshMemberData();
        members = dao.getAll();

        w.addButtonActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == w.searchButton) {
                    if(w.updateTelInput.getText() == null) return;
                    if(dao.getMember(w.updateTelInput.getText())!=null) {
                        member = dao.getMember(w.updateTelInput.getText());
                        JOptionPane.showMessageDialog(null, "회원 정보가 존재합니다.");
                        // flag=1;
                        w.nameInput.setEditable(true);
                        w.birthInput.setEditable(true);
                        w.telNoInput.setEditable(true);
                        w.emailInput.setEditable(true);
                        w.updateButton.setEnabled(true);

                        w.nameInput.setText(member.getMemberName());
                        w.birthInput.setText(member.getMemberBirth());
                        w.telNoInput.setText(member.getMemberTelNo());
                        w.emailInput.setText(member.getMemberEmail());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "회원 정보가 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(obj == w.updateButton) {

                    if(w.nameInput==null||w.birthInput==null||w.telNoInput==null||w.emailInput==null) return;

                    //생년월일 오류체크
                    String[] strings = (w.birthInput.getText()).split("-");
                    if(strings.length<3) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 생년월일입니다.\n0000-00-00형식으로 입력해주세요", "Message", JOptionPane.ERROR_MESSAGE);
                        w.birthInput.setText("");
                        return;
                    }

                    if (!w.telNoInput.getText().matches("^010[-]{1}(\\d{4})[-]{1}(\\d{4})")) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 전화번호 형식입니다.\n 010-xxxx-xxxx형식으로 입력해주세요", "Message",
                                JOptionPane.ERROR_MESSAGE);
                        w.telNoInput.setText("");
                        return;
                    }

                    if (!w.emailInput.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 이메일 형식입니다.\n string@string.string형식으로 입력해주세요.",
                                "Message", JOptionPane.ERROR_MESSAGE);
                        w.emailInput.setText("");
                        return;
                    }

                    if(strings[1].equals("00")||strings[2].equals("00")) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 생년월일입니다.", "Message", JOptionPane.ERROR_MESSAGE);
                        w.birthInput.setText("");
                        return;
                    }

                    if(strings.length<3) {
                        JOptionPane.showMessageDialog(null, "올바르지 않은 생년월일입니다.\n0000-00-00형식으로 입력해주세요", "Message", JOptionPane.ERROR_MESSAGE);
                        w.birthInput.setText("");
                        return;
                    }


                    dao.updateMember(w.nameInput.getText(), w.emailInput.getText(), w.telNoInput.getText(), w.birthInput.getText(), w.updateTelInput.getText());
                    JOptionPane.showMessageDialog(null, "회원 정보가 수정되었습니다.");
                    refresh.refreshMemberData();
                    w.dispose();

                }
            }
        });
    }
}

