package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RoomLogDAO {
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

    public boolean insertRoomLog(RoomLog log) {
        connectDB();
        sql = "insert into room_log values(?, ?, ?, ?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, log.logNo);
            pstmt.setInt(2, log.roomNo);
            pstmt.setDate(3, log.logDate);
            pstmt.setInt(4, log.memberNo);
            pstmt.setInt(5, log.price);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            closeDB();
            return false;
        }
        closeDB();
        return true;
    }

    public RoomLog getRoomLog(int logNo) {
        connectDB();
        sql = "select * from room_log where log_no = ?";
        RoomLog l = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, logNo);
            rs = pstmt.executeQuery();

            rs.next();
            l = new RoomLog();
            l.logNo = rs.getInt("log_no");
            l.roomNo = rs.getInt("room_no");
            l.logDate = rs.getDate("log_date");
            l.memberNo = rs.getInt("member_no");
            l.price = rs.getInt("price");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB();
        return l;
    }

    public ArrayList<RoomLog> getAll() {
        connectDB();
        sql = "select * from room_log";

        // 전체 검색 데이터를 전달하는 ArrayList
        ArrayList<RoomLog> datas = new ArrayList<RoomLog>();

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomLog l = new RoomLog();
                l = new RoomLog();
                l.logNo = rs.getInt("log_no");
                l.roomNo = rs.getInt("room_no");
                l.logDate = rs.getDate("log_date");
                l.memberNo = rs.getInt("member_no");
                l.price = rs.getInt("price");
                datas.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB();
        return datas;
    }

    public ArrayList<RoomLog> getWeek(String date) {
        connectDB();

        sql = "select * from room_log where log_date > ?";

        ArrayList<RoomLog> datas = new ArrayList<RoomLog>();

        Date d = Date.valueOf(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, -7);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        d = Date.valueOf(format.format(cal.getTime()));

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, d);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomLog l = new RoomLog();
                l = new RoomLog();
                l.logNo = rs.getInt("log_no");
                l.roomNo = rs.getInt("room_no");
                l.logDate = rs.getDate("log_date");
                l.memberNo = rs.getInt("member_no");
                l.price = rs.getInt("price");
                datas.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();
        return datas;
    }

    public ArrayList<RoomLog> getMonth(String date) {
        connectDB();

        sql = "select * from room_log where year(log_date) = ? and month(log_date) = ?";

        ArrayList<RoomLog> datas = new ArrayList<RoomLog>();

        Date d = Date.valueOf(date);
        int year = d.getYear() + 1900;
        int month = d.getMonth() + 1;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomLog l = new RoomLog();
                l = new RoomLog();
                l.logNo = rs.getInt("log_no");
                l.roomNo = rs.getInt("room_no");
                l.logDate = rs.getDate("log_date");
                l.memberNo = rs.getInt("member_no");
                l.price = rs.getInt("price");
                datas.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();
        return datas;
    }

    public ArrayList<RoomLog> getYear(String date) {
        connectDB();

        sql = "select * from room_log where year(log_date) = ?";

        ArrayList<RoomLog> datas = new ArrayList<RoomLog>();

        Date d = Date.valueOf(date);
        int year = d.getYear() + 1900;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomLog l = new RoomLog();
                l = new RoomLog();
                l.logNo = rs.getInt("log_no");
                l.roomNo = rs.getInt("room_no");
                l.logDate = rs.getDate("log_date");
                l.memberNo = rs.getInt("member_no");
                l.price = rs.getInt("price");
                datas.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDB();
        return datas;
    }
}
