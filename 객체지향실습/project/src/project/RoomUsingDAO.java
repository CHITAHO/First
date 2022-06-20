package project;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;


public class RoomUsingDAO {
    protected final static int PAY = 0;
    protected final static int SERVICE = 1;

    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String jdbcUrl = "jdbc:mysql://localhost:3306/project?&&serverTimezone=UTC";
    private Connection conn;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar calendar;

    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;
    public void connectDB(){
        try {
            //Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, "root", "root");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void closeDB() {
        try {
            pstmt.close();
            if(rs != null){
                rs.close();
            }
            conn.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // 사용중인 방 데이터 검색
    public ArrayList<RoomUsing> searchUsingRoom(){
        ArrayList<RoomUsing> datas = new ArrayList<RoomUsing>();
        sql = "SELECT * FROM ROOM_USING";
        connectDB();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                RoomUsing r = new RoomUsing();
                r.setRoomNo(rs.getInt("ROOM_NO"));
                r.setMemberNo(rs.getInt("MEMBER_NO"));
                r.setStartTime(rs.getString("START_TIME"));
                r.setEndTime(rs.getString("END_TIME"));
                r.setPrice(rs.getInt("PRICE"));
                datas.add(r);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        closeDB();
        return datas;
    }

    public void addUsingRoom(int roomNo, int memberNo, int minute){
        connectDB();
        sql = "INSERT INTO ROOM_USING SET ROOM_NO = ?, MEMBER_NO = ?, START_TIME = ?, END_TIME = ?, PRICE = ?";
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minute);

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomNo);
            pstmt.setInt(2, memberNo);
            pstmt.setString(3, format.format(new Date()));
            pstmt.setString(4, format.format(calendar.getTime()));
            pstmt.setInt(5, minute*100);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        closeDB();
    }

    // 사용중인 방에 시간을 추가.
    public void updateUsingRoom(RoomUsing r, int minute, int type){
        connectDB();
        int price;
        sql = "UPDATE ROOM_USING SET END_TIME = ?, PRICE = ? WHERE ROOM_NO = ?";
        try{
            if(type == PAY){
                price = r.getPrice() + (minute*100);
            }
            else{
                price = r.getPrice();
            }

            Date date = format.parse(r.getEndTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, minute);

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, format.format(calendar.getTime()));
            pstmt.setInt(2, price);
            pstmt.setInt(3, r.getRoomNo());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        closeDB();
    }

    // 시간 완료되면 삭제한다.
    synchronized public void deleteUsingRoom(RoomUsing r){
        connectDB();
        sql = "DELETE FROM ROOM_USING WHERE ROOM_NO = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, r.getRoomNo());
            pstmt.executeUpdate();
            addRoomLog(r);
        }catch(Exception e){
            e.printStackTrace();
        }
        closeDB();
    }

    // RoomUsing에서 삭제된 데이터를 Log로 내림.
    public void addRoomLog(RoomUsing r){
        connectDB();
        sql = "INSERT INTO ROOM_LOG SET ROOM_NO = ?, LOG_DATE = ?, MEMBER_NO = ?, PRICE = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, r.getRoomNo());
            pstmt.setString(2, format.format(new Date()));
            pstmt.setInt(3, r.getMemberNo());
            pstmt.setInt(4, r.getPrice());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        closeDB();
    }
}
