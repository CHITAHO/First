package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainUIController extends Thread{
    private final MainUI v;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private RoomUsingDAO d;
    private RoomUsing r;
    private boolean flag = false;
    private RefreshData refresh;

    public MainUIController(MainUI v){
        this.v = v;
        this.d = new RoomUsingDAO();
        refresh = new RefreshData(this.v);
        refresh.refreshRoom();
        v.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == v.newMemberButton){
                    new NewMemberUIController(new NewMemberUI());
                }
                else if(obj == v.memberManagerButton){
                    new MemberListUIController(new MemberListUI());
                }
                else if(obj == v.salesManagerButton){
                    new SalesManagerUIController(new SalesManagerUI());
                }
                else if(obj == v.logoutButton){
                    new LoginUIController(new LoginUI());
                    v.dispose();
                }
                else if(obj == v.exitButton){
                    System.exit(0);
                }
                for(int i=0; i<24; i++){
                    if(obj == v.room[i]){
                        ArrayList<RoomUsing> datas = d.searchUsingRoom();
                        if(!datas.isEmpty()){
                            for(RoomUsing r : datas){
                                if((r.getRoomNo()-1) == i){ // 현재 점유중인 방
                                    flag = true;    // true면 update false면 추가
                                    new MoreUIController(new MoreUI(), v, r, v.room[i], flag, i); // ---
                                }
                            }
                        }
                        if(!flag){  // 빈 방
                            new MoreUIController(new MoreUI(), v, null, v.room[i], flag, i); // ---
                        }
                    }
                }
                flag = false;
            }
        });
        this.start();
    }
    @Override
    public void run(){
        while(true){
            try{
                v.realtimeLabel.setText("현재시간 : " + format.format(new Date()));
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
