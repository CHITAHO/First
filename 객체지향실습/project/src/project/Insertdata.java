package project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
난수를 이용해 ROOMLOG에 데이터를 넣음.
 */

public class Insertdata {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RoomLogDAO dao = new RoomLogDAO();

        for (int i = 0; i < 1000; i++) {
            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.add(Calendar.DATE, -(int) (Math.random() * 365));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String randomDate = format.format(cal.getTime());

            RoomLog log = new RoomLog();
            log.roomNo = (int) (Math.random() * 23) + 1;
            log.memberNo = (int) (Math.random() * 1000) + 1;
            log.price = (int) (Math.random() * 5) * 10000 + (int) (Math.random() * 9) * 1000;
            log.logDate = java.sql.Date.valueOf(randomDate);

            System.out.println(log.roomNo + ", " + log.memberNo + ", " + log.logDate + ", " + log.price);

            dao.insertRoomLog(log);
        }
    }

}
