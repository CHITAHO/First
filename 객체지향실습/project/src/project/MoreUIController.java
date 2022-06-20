package project;

import javax.swing.*;
import java.awt.event.*;

public class MoreUIController {
    private MoreUI v;
    private RoomUsing r;
    private boolean flag;
    private JButton room;
    private RoomUsingDAO roomDAO;
    private RefreshData refresh;

    private MemberDAO memberDAO;
    private Member memberInfo;

    public MoreUIController(MoreUI v, MainUI mV, RoomUsing roomInfo, JButton selectedRoom, boolean f, int roomNo){
        this.v = v;
        this.r = roomInfo;
        this.room = selectedRoom;
        this.flag = f;
        roomDAO = new RoomUsingDAO();
        refresh = new RefreshData(mV);
        memberDAO = new MemberDAO();

        // 이미 활성화 된 방이라면 서비스 버튼을 활성화 시킴. (true가 활성화 된 방임.)
        if(flag){
            if(r.getMemberNo() != 0){   // 멤버넘버가 0이 아니라면 회원이다.
                v.userCheck.setSelected(true);  // 그러므로 회원체크박스 on
                v.searchButton.setEnabled(false);   // 회원관리 버튼 비활성화
                v.userNum.setEnabled(false);        // 회원번호 비활성화
                v.mileageButton.setEnabled(true);
                v.userNum.setText(Integer.toString(r.getMemberNo()));   // 회원번호 자동 넣기
                memberInfo = memberDAO.getMember(r.getMemberNo());
                v.memberNameLabel.setText("회원명(회원번호) : " + memberInfo.getMemberName() + "(" + memberInfo.getMemberNo() + ")");
                v.memberMileageLabel.setText("마일리지(10점에1분) : " + memberInfo.getMemberMileage());
            }
            v.exitButton.setEnabled(true);
            v.userCheck.setEnabled(false);  // 체크박스 비활성화
            v.serviceButton.setEnabled(true);
            v.startTimeLabel.setText("시작시간 : " + r.getStartTime().substring(r.getStartTime().length()-8, r.getStartTime().length()));
            v.priceLabel.setText("금액 : " + r.getPrice());
        }

        v.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                int time = 0;

                if(obj == v.searchButton){
                    new MemberListUIController(new MemberListUI());
                }
                else if(obj == v.timeaddButton){
                    if(v.timeText.getText().equals("") || v.timeText.getText().matches("[+-]?\\d*(\\.\\d+)?") == false || Integer.parseInt(v.timeText.getText()) <= 0){
                        v.timeText.setText("");
                        JOptionPane.showMessageDialog(null, "추가할 시간을 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        time = Integer.parseInt(v.timeText.getText());
                    }

                    if(flag){
                        if(r.getMemberNo() != 0) {   // 멤버넘버가 0이 아니라면 회원이다.
                            memberDAO.updateMileage(memberInfo.getMemberNo() ,(memberInfo.getMemberMileage() + time));
                        }
                        roomDAO.updateUsingRoom(r, time, roomDAO.PAY);
                        refresh.refreshRoom();
                        v.dispose();
                    }
                    else{
                        int memberNum = 0;
                        if(!v.userNum.getText().equals("")){
                            if(v.userNum.getText().matches("[+-]?\\d*(\\.\\d+)?") == false){
                                v.userNum.setText("");
                                JOptionPane.showMessageDialog(null, "회원번호는 숫자만 입력가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            else{
                                memberNum = Integer.parseInt(v.userNum.getText());
                                memberInfo = memberDAO.getMember(memberNum);
                                if(memberInfo != null){
                                    memberDAO.updateMileage(memberInfo.getMemberNo() ,(memberInfo.getMemberMileage() + time));
                                }
                                else{
                                    v.userNum.setText("");
                                    JOptionPane.showMessageDialog(null, "회원번호에 해당하는 회원이 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }
                        }
                        roomDAO.addUsingRoom(roomNo+1, memberNum, time);
                        refresh.refreshRoom();
                        v.dispose();
                    }
                }
                else if(obj == v.serviceButton){
                    if(v.timeText.getText().equals("") || v.timeText.getText().matches("[+-]?\\d*(\\.\\d+)?") == false || Integer.parseInt(v.timeText.getText()) <= 0){
                        v.timeText.setText("");
                        JOptionPane.showMessageDialog(null, "추가할 시간을 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        time = Integer.parseInt(v.timeText.getText());
                    }
                    roomDAO.updateUsingRoom(r, Integer.parseInt(v.timeText.getText()), roomDAO.SERVICE);
                    refresh.refreshRoom();
                    v.dispose();
                }

                else if(obj == v.mileageButton){
                    if(v.timeText.getText().equals("") || v.timeText.getText().matches("[+-]?\\d*(\\.\\d+)?") == false || Integer.parseInt(v.timeText.getText()) <= 0){
                        v.timeText.setText("");
                        JOptionPane.showMessageDialog(null, "추가할 시간을 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        time = Integer.parseInt(v.timeText.getText());
                    }

                    // 마일리지 10점에 1분
                    if(memberInfo.getMemberMileage() >= 10){
                        if(memberInfo.getMemberMileage() >= (Integer.parseInt(v.timeText.getText())*10)){
                            roomDAO.updateUsingRoom(r, time, roomDAO.SERVICE);  // 마일리지는 매출에 포함되지 않음.
                            memberDAO.updateMileage(memberInfo.getMemberNo() ,(memberInfo.getMemberMileage() - (time*10)));
                            refresh.refreshRoom();
                            v.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "마일리지가 부족합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "마일리지는 10점 이상부터 사용가능.", "Message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if(obj == v.exitButton){
                    refresh.exitRoom(roomNo+1);
                    v.dispose();
                    refresh.refreshRoom();
                }
            }
        });

        v.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    v.searchButton.setEnabled(true);
                    v.userNum.setEnabled(true);
                }
                else {
                    v.searchButton.setEnabled(false);
                    v.userNum.setEnabled(false);
                }
            }
        });

    }
}

