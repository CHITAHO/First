package project;
import java.util.*;
import java.sql.*;

public class MemberDAO {
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String jdbcUrl = "jdbc:mysql://localhost:3306/project?&&serverTimezone=UTC";
    private Connection conn;

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

    public ArrayList<Member> getAll(){
        ArrayList<Member> datas = new ArrayList<Member>();
        sql = "select * from member";
        connectDB();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Member m = new Member();
                m.setMemberNo(rs.getInt("MEMBER_NO"));
                m.setMemberName(rs.getString("MEMBER_NAME"));
                m.setMemberEmail(rs.getString("MEMBER_EMAIL"));
                m.setMemberTelNo(rs.getString("MEMBER_TEL_NO"));
                m.setMemberBirth(rs.getString("MEMBER_BIRTHDAY"));
                m.setMemberMileage(rs.getInt("MILEAGE"));
                datas.add(m);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        closeDB();
        return datas;
    }

    public boolean registUser(String uname, String email, String telNo, String birth) {
        int flag=1;
        String sql = "INSERT INTO MEMBER SET MEMBER_NAME = ?, MEMBER_EMAIL = ?, MEMBER_TEL_NO = ?, MEMBER_BIRTHDAY = ?;";
        if(uname.equals("")||email.equals("")||telNo.equals("")||birth.equals("")) {
            return false;
        }
        connectDB();
        try {
            //3단계 Statement 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname);
            pstmt.setString(2, email);
            pstmt.setString(3, telNo);
            pstmt.setString(4, birth);
            //4단계 sql문 전송
            pstmt.executeUpdate();
            flag=1;
        } catch(SQLException e) {
            flag=0;
            //e.printStackTrace();
        }
        closeDB();
        if(flag==1) return true;
        else return false;
    }

    public Member getMember(String MEMBER_TEL_NO) {
        connectDB();
        sql = "select * from member where MEMBER_TEL_NO = ?";
        Member m = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, MEMBER_TEL_NO);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                m = new Member();
                m.setMemberBirth(rs.getString("MEMBER_BIRTHDAY"));
                m.setMemberEmail(rs.getString("MEMBER_EMAIL"));
                m.setMemberMileage(rs.getInt("MILEAGE"));
                m.setMemberName(rs.getString("MEMBER_NAME"));
                m.setMemberNo(rs.getInt("MEMBER_NO"));
                m.setMemberTelNo(rs.getString("MEMBER_TEL_NO"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        closeDB();
        return m;
    }

    // MoreUIController에서 사용됨. 회원번호를 이용해서 검색.
    public Member getMember(int memberNo) {
        connectDB();
        sql = "select * from member where MEMBER_NO = ?";
        Member m = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNo);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                m = new Member();
                m.setMemberBirth(rs.getString("MEMBER_BIRTHDAY"));
                m.setMemberEmail(rs.getString("MEMBER_EMAIL"));
                m.setMemberMileage(rs.getInt("MILEAGE"));
                m.setMemberName(rs.getString("MEMBER_NAME"));
                m.setMemberNo(rs.getInt("MEMBER_NO"));
                m.setMemberTelNo(rs.getString("MEMBER_TEL_NO"));
            }
        }catch(SQLException e){
            //e.printStackTrace();
        }
        closeDB();
        return m;
    }


    public boolean delMember(String MEMBER_TEL_NO) {
        connectDB();
        String sql = "delete from member where MEMBER_TEL_NO = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,  MEMBER_TEL_NO);

            pstmt.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        closeDB();
        return false;

    }

    public boolean updateMember(String uname, String email, String telNo, String birth,String searchtelNo) {
        connectDB();
        sql = "update member set MEMBER_NAME = ?, MEMBER_EMAIL = ?,MEMBER_TEL_NO = ?,MEMBER_BIRTHDAY = ?  where MEMBER_TEL_NO = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname);
            pstmt.setString(2, email);
            pstmt.setString(3,telNo);
            pstmt.setString(4, birth);
            pstmt.setString(5, searchtelNo);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        closeDB();
        return false;
    }

    public void updateMileage(int memberNumber, int mileage){
        connectDB();
        sql = "UPDATE MEMBER SET MILEAGE = ? WHERE MEMBER_NO = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mileage);
            pstmt.setInt(2, memberNumber);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        closeDB();
    }
}
