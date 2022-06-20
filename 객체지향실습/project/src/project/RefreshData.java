package project;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// refreshroom 등 데이터 화면 다시 보여주는 기능
public class RefreshData {
    public static ArrayList<ThreadTimer> timers = new ArrayList<ThreadTimer>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private RoomUsingDAO d;
    private MainUI v;
    private MemberListUI l;
    private ArrayList<Member> members;
    private MemberDAO memberD;
    /*
    public RefreshData(){
        d = new RoomUsingDAO();
    }
    */
    public RefreshData(MainUI v){
        this.v = v;
        d = new RoomUsingDAO();
    }
    public RefreshData(MemberListUI l){
        this.l = l;
    }

    // 방관리 열었을때, 메인화면 보여줄때 이거 사용한다.
    public void refreshRoom(){
        thredStop();
        ArrayList<RoomUsing> datas = d.searchUsingRoom();
        ImageIcon icon;
        if(!datas.isEmpty()){
            for(RoomUsing r : datas){
                if(r.getMemberNo() == 0){   // 비회원이라면
                    icon = new ImageIcon("images/on.png");
                }
                else{
                    icon = new ImageIcon("images/member_on.png");
                }

                v.room[r.getRoomNo()-1].setIcon(icon);
                v.room[r.getRoomNo()-1].setForeground(Color.red);
                timers.add(new ThreadTimer(format.format(new Date()), r.getEndTime(), v.room[r.getRoomNo()-1], r));
            }
        }
    }

    // 강제종료...
    public void exitRoom(int roomNo){
        if(!timers.isEmpty()){
            for(ThreadTimer t : timers){
                if(t.roomInfo.getRoomNo() == roomNo){
                    t.changeFlag();
                    ImageIcon icon = new ImageIcon("images/off.png");
                    t.btn.setIcon(icon);
                    t.btn.setForeground(Color.blue);
                    t.btn.setText("room" + t.roomInfo.getRoomNo());
                    d.deleteUsingRoom(t.roomInfo);
                }
            }
        }
    }

    public void refreshMemberData() {
        l.memberText.setText("");
        memberD = new MemberDAO();
        members = new ArrayList<Member>();
        members = memberD.getAll();
        l.memberText.append("회원번호\t이름\t생년월일\t전화번호\t마일리지\tE-mail\t\n");

        if(!members.isEmpty()){
            for(Member m : members){
                l.memberText.append(m.getMemberNo() + "\t" + m.getMemberName() + "\t" + m.getMemberBirth()
                        + "\t" + m.getMemberTelNo() + "\t" + m.getMemberMileage() + "\t"+m.getMemberEmail() + "\n");
            }
        }
    }

    // 지금까지의 모든 스레드 종료. (안전하게 종료)
    public void thredStop(){
        if(!timers.isEmpty()){
            for(ThreadTimer t : timers){
                t.changeFlag();
            }
        }
        timers = new ArrayList<ThreadTimer>();
    }

    class ThreadTimer extends Thread{
        private long count;
        private JButton btn;
        private RoomUsing roomInfo;
        private boolean flag = true;

        public ThreadTimer(String start, String end, JButton btn, RoomUsing roomInfo){
            this.btn = btn;
            this.roomInfo = roomInfo;
            try{
                Date a = format.parse(start);
                Date b = format.parse(end);
                long diff = b.getTime() - a.getTime();
                count = diff/1000;
            }catch(Exception e){
                e.printStackTrace();
            }
            this.start();
        }
        public void changeFlag(){
            flag = false;
        }   // 중요 로직. 삭제 금지.
        @Override
        public void run(){
            int h, m, s;
            long time = count;
            for(int i=0; i<count; i++) {
                try {
                    if(flag){
                        h = (int)time/3600;
                        m = (int)(time%3600)/60;
                        s = (int)(time%3600)%60;
                        btn.setText(h+":"+m+":"+s);
                        time--;
                        Thread.sleep(1000);
                    }
                    else{
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(flag){
                ImageIcon icon = new ImageIcon("images/off.png");
                btn.setIcon(icon);
                btn.setForeground(Color.blue);
                btn.setText("room" + roomInfo.getRoomNo());
                d.deleteUsingRoom(roomInfo);
            }
        }
    }
}
