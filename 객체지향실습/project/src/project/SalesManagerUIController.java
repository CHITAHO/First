package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SalesManagerUIController {

    private SalesManagerUI ui;
    private RoomLogDAO dao;
    private ArrayList<RoomLog> roomLogList;
    private SalesButtonActionListener listener;

    private SimpleDateFormat sdf;
    String date;

    SalesManagerUIController(SalesManagerUI ui) {
        this.ui = ui;

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 시연을 위한 수정
        // date = sdf.format(new Date());
        date = "2020-12-31";

        dao = new RoomLogDAO();

        listener = new SalesButtonActionListener();
        ui.addButtonActionListener(listener);

        ui.weekButton.doClick();
    }

    public int[] addWeeklySales() {
        int[] sum = new int[7];

        int temp;
        for (RoomLog l : roomLogList) {
            Date now = new Date();
            Calendar c1 = new GregorianCalendar();
            Calendar c2 = new GregorianCalendar();

            // 시연을 위한 수정
            c1.add(Calendar.DATE, -4);
            if (l.logDate.after(c1.getTime())) {
                continue;
            }

            // 시연을 위한 수정
            // c1.setTime(now);
            c2.setTime(l.logDate);

            long diffSec = (c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24 * 60 * 60);

            temp = (int) diffDays;
            sum[6 - temp] += l.price;
        }
        return sum;
    }

    public int[] addMonthlySales() {
        int[] sum = new int[31];

        for (RoomLog l : roomLogList) {
            sum[l.logDate.getDate() - 1] += l.price;
        }
        return sum;
    }

    public int[] addAnnualSales() {
        int[] sum = new int[12];

        for (RoomLog l : roomLogList) {
            sum[l.logDate.getMonth()] += l.price;
        }
        return sum;
    }

    class SalesButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == ui.weekButton) {
                roomLogList = dao.getWeek(date);
                ui.weekGraph.setSales(addWeeklySales());
                ui.weekGraph.repaint();
                ui.cardLayout.show(ui.tab, "week");

            } else if (obj == ui.monthButton) {
                roomLogList = dao.getMonth(date);
                ui.monthGraph.setSales(addMonthlySales());
                ui.monthGraph.repaint();
                ui.cardLayout.show(ui.tab, "month");

            } else if (obj == ui.yearButton) {
                roomLogList = dao.getYear(date);
                ui.yearGraph.setSales(addAnnualSales());
                ui.yearGraph.repaint();
                ui.cardLayout.show(ui.tab, "year");
            }
        }
    }
}