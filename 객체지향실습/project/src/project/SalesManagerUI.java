package project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SalesManagerUI extends JFrame {
    private JPanel graphPanel;
    private JPanel buttonPanel;

    protected Container tab;
    protected CardLayout cardLayout;
    protected YearGraph yearGraph;
    protected MonthGraph monthGraph;
    protected WeekGraph weekGraph;

    protected JButton weekButton;
    protected JButton monthButton;
    protected JButton yearButton;

    public SalesManagerUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("매출조회");

        graphPanel = new JPanel();
        buttonPanel = new JPanel();
        yearGraph = new YearGraph();
        monthGraph = new MonthGraph();
        weekGraph = new WeekGraph();

        ImageIcon icon1 = new ImageIcon("images/주.jpg");
        ImageIcon pressedicon1 = new ImageIcon("images/pressed주.jpg");
        weekButton = new JButton("", icon1);
        weekButton.setPressedIcon(pressedicon1);
        weekButton.setBorderPainted(false);
        weekButton.setFocusPainted(false);
        weekButton.setContentAreaFilled(false);

        ImageIcon icon2 = new ImageIcon("images/월.jpg");
        ImageIcon pressedicon2 = new ImageIcon("images/pressed월.jpg");
        monthButton = new JButton("", icon2);
        monthButton.setPressedIcon(pressedicon2);
        monthButton.setBorderPainted(false);
        monthButton.setFocusPainted(false);
        monthButton.setContentAreaFilled(false);

        ImageIcon icon3 = new ImageIcon("images/년.jpg");
        ImageIcon pressedicon3 = new ImageIcon("images/pressed년.jpg");
        yearButton = new JButton("", icon3);
        yearButton.setPressedIcon(pressedicon3);
        yearButton.setBorderPainted(false);
        yearButton.setFocusPainted(false);
        yearButton.setContentAreaFilled(false);

        buttonPanel.add(weekButton);
        buttonPanel.add(monthButton);
        buttonPanel.add(yearButton);

        tab = new JPanel();
        cardLayout = new CardLayout();
        tab.setLayout(cardLayout);
        tab.add(weekGraph, "week");
        tab.add(monthGraph, "month");
        tab.add(yearGraph, "year");
        tab.setBounds(50, 50, 550, 350);

        graphPanel.setLayout(null);
        graphPanel.add(tab);

        buttonPanel.setLayout(null);
        buttonPanel.setPreferredSize(new Dimension(200, this.getHeight()));
        weekButton.setBounds(0, 10, 50, 50);
        monthButton.setBounds(60, 10, 50, 50);
        yearButton.setBounds(120, 10, 50, 50);

        this.setLayout(new BorderLayout());
        this.add(graphPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);

        this.setResizable(false);
        this.setSize(960, 540);
        this.setVisible(true);
    }

    public void addButtonActionListener(ActionListener l) {
        weekButton.addActionListener(l);
        monthButton.addActionListener(l);
        yearButton.addActionListener(l);
    }

    class YearGraph extends JPanel {
        int sales[];
        double nomalized[];
        int maxYAxis = 0;
        double ratio;

        public YearGraph() {
            sales = new int[12];
            nomalized = new double[12];

            setBackground(Color.white);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            nomalization();
            g.drawString("매출(만 원)", 0, 10);
            g.drawString("월", 535, 345);
            for (int i = 0; i < 11; i++) {
                g.drawString(String.format("%.1f", maxYAxis / 10.0 * i), 0, 335 - (30 * i));
                g.drawLine(50, 330 - (30 * i), 450, 330 - (30 * i));
            }
            for (int i = 0; i < 12; i++) {
                g.drawString(i + 1 + "", 67 + (30 * i), 350);
                g.fillRect(70 + (30 * i), (int) (330 - Math.floor(nomalized[i])), 10, (int) nomalized[i]);
            }
        }

        public void nomalization() {
            for (int i = 0; i < 12; i++) {
                sales[i] /= 10000;
            }
            for (int s : sales) {
                maxYAxis = s > maxYAxis ? s : maxYAxis;
            }
            ratio = (double) 300 / maxYAxis;
            for (int i = 0; i < 12; i++) {
                nomalized[i] = sales[i] * ratio;
            }
        }

        public void setSales(int sales[]) {
            this.sales = sales;
        }

    }

    class MonthGraph extends JPanel {
        int sales[];
        double nomalized[];
        int maxYAxis = 0;
        double ratio;

        public MonthGraph() {
            sales = new int[31];
            nomalized = new double[31];

            setBackground(Color.white);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            nomalization();

            g.drawString("매출(원)", 0, 10);
            g.drawString("일", 535, 345);
            for (int i = 0; i < 11; i++) {
                g.drawString(maxYAxis / 10 * i + "", 0, 335 - (30 * i));
                g.drawLine(40, 330 - (30 * i), 510, 330 - (30 * i));
            }
            for (int i = 0; i < 31; i++) {
                g.drawString(i + 1 + "", 47 + (15 * i), 350);
                if (i < 9) {
                    g.fillRect(50 + (15 * i), (int) (330 - Math.floor(nomalized[i])), 3, (int) nomalized[i]);
                } else {
                    g.fillRect(50 + (15 * i) + 3, (int) (330 - Math.floor(nomalized[i])), 3, (int) nomalized[i]);
                }

            }
        }

        public void nomalization() {
            for (int s : sales) {
                maxYAxis = s > maxYAxis ? s : maxYAxis;
            }
            ratio = (double) 300 / maxYAxis;
            for (int i = 0; i < 31; i++) {
                nomalized[i] = sales[i] * ratio;
            }
        }

        public void setSales(int sales[]) {
            this.sales = sales;
        }

    }

    class WeekGraph extends JPanel {
        int sales[];
        double nomalized[];
        int maxYAxis = 0;
        double ratio;

        private SimpleDateFormat sdf;
        private Calendar c;
        String date[] = { "1", "2", "3", "4", "5", "6", "7" };

        public WeekGraph() {
            sales = new int[7];
            nomalized = new double[7];

            c = new GregorianCalendar();
            sdf = new SimpleDateFormat("MM/dd");

            // 시연을 위한 수정
            c.add(Calendar.DATE, -4);

            for (int i = 0; i < 7; i++) {
                date[6 - i] = sdf.format(c.getTime());
                c.add(Calendar.DATE, -1);
            }

            setBackground(Color.white);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            nomalization();

            g.drawString("매출(원)", 0, 10);
            g.drawString("날짜", 525, 345);
            for (int i = 0; i < 11; i++) {
                g.drawString(maxYAxis / 10 * i + "", 0, 335 - (30 * i));
                g.drawLine(50, 330 - (30 * i), 450, 330 - (30 * i));
            }
            for (int i = 0; i < 7; i++) {
                g.drawString(date[i], 90 + (50 * i), 350);
                g.fillRect(100 + (50 * i), (int) (330 - Math.floor(nomalized[i])), 10, (int) nomalized[i]);
            }
        }

        public void nomalization() {
            for (int s : sales) {
                maxYAxis = s > maxYAxis ? s : maxYAxis;
            }
            ratio = (double) 300 / maxYAxis;
            for (int i = 0; i < 7; i++) {
                nomalized[i] = sales[i] * ratio;
            }
        }

        public void setSales(int sales[]) {
            this.sales = sales;
        }
    }
}
