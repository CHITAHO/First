package project;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MemberListUIController {

    private Member member;
    private MemberDAO dao;
    private final MemberListUI v;
    private RefreshData refresh;

    public MemberListUIController(MemberListUI v){

        this.v = v;
        refresh = new RefreshData(this.v);
        refresh.refreshMemberData();
        dao = new MemberDAO();

        v.addButtonActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                refresh.refreshMemberData();

                Object obj = e.getSource();
                if(obj == v.memberSearchButton){//회원검색
                    String str = JOptionPane.showInputDialog("검색할 회원의 전화번호는?");
                    if(str == null) return;
                    member = dao.getMember(str);
                    if(member!=null) {
                        v.memberText.setText("");
                        v. memberText.append("회원번호\t이름\t생년월일\t전화번호\t마일리지\tE-mail\t\n");
                        v.memberText.append(String.valueOf(member.getMemberNo())+"\t"+member.getMemberName()+"\t"+member.getMemberBirth()
                                +"\t"+member.getMemberTelNo()+"\t"+String.valueOf(member.getMemberMileage())+"\t"+member.getMemberEmail()+"\n");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "회원 정보가 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                }

                else if(obj == v.memberUpdateButton){//회원정보 수정
                    new MemberUpdateUIController(new MemberUpdateUI(), v);
                }

                else if(obj == v.memberDeleteButton){//회원탈퇴
                    dao = new MemberDAO();
                    String str = JOptionPane.showInputDialog("삭제할 회원 전화번호는?");
                    if(str == null) return;

                    if(dao.getMember(str)!=null){
                        dao.delMember(str);
                        JOptionPane.showMessageDialog(null,"삭제되었습니다.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "회원 정보가 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                    refresh.refreshMemberData();
                }

                else if(obj == v.exitButton){v.dispose();}
            }
        });

    }
}