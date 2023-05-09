import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class thongke extends JFrame {
    connect conn = new connect();

    JTable tb, tb2;

    Vector vttitlebv = new Vector();
    Vector vtdatabv = new Vector();
    String[] tbl1 = {"Họ và tên", "Mã SV", "Ngày sinh", "Mã Lớp", "Khoa", "Ngành", "Tên ĐT", "Mã số ĐT", "TGTH", "Tiến Độ", "GVHD", "Kinh phí"};

    Vector vttitlecbv = new Vector();
    Vector vtdatacbv = new Vector();
    String[] tbl2 = {"Họ và tên", "Mã SV", "Ngày sinh", "Mã Lớp", "Khoa", "Ngành", "Tên ĐT", "Mã số ĐT", "TGTH", "Tiến Độ", "GVHD", "Kinh phí"};

    DefaultTableModel model, model2;

    JButton Exit1;

    public thongke() {
    	JFrame frame = new JFrame("Thống kê");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        //tạo bảng những sinh viên đã được bảo vệ
        JPanel p1 = new JPanel();
        Border border1 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBordertbl1 = BorderFactory.createTitledBorder(border1, "Danh sách sinh viên được bảo vệ");
        p1.setBorder(titledBordertbl1);
        p1.setLayout(new BorderLayout());
        model = new DefaultTableModel(vtdatabv, vttitlebv);
        load();
        tb = new JTable(model);
        //set độ rộng cột
        tb.getColumnModel().getColumn(0).setPreferredWidth(150);
        tb.getColumnModel().getColumn(1).setPreferredWidth(50);
        tb.getColumnModel().getColumn(2).setPreferredWidth(50);
        tb.getColumnModel().getColumn(3).setPreferredWidth(130);
        tb.getColumnModel().getColumn(4).setPreferredWidth(120);
        tb.getColumnModel().getColumn(5).setPreferredWidth(120);
        tb.getColumnModel().getColumn(6).setPreferredWidth(50);
        tb.getColumnModel().getColumn(7).setPreferredWidth(50);
        tb.getColumnModel().getColumn(8).setPreferredWidth(50);
        tb.getColumnModel().getColumn(9).setPreferredWidth(80);
        tb.getColumnModel().getColumn(10).setPreferredWidth(50);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane sc = new JScrollPane(tb);
        p1.add(sc);
        panel.add(p1);

        //tạo bảng những sinh viên chưa được bảo vệ
        JPanel p2 = new JPanel();
        Border border2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBordertbl2 = BorderFactory.createTitledBorder(border2, "Danh sách sinh viên chưa bảo vệ");
        p2.setBorder(titledBordertbl2);
        p2.setLayout(new BorderLayout());
        model2 = new DefaultTableModel(vtdatacbv, vttitlecbv);
        load2();
        tb2 = new JTable(model2);
        //set độ rộng cột
        tb2.getColumnModel().getColumn(0).setPreferredWidth(150);
        tb2.getColumnModel().getColumn(1).setPreferredWidth(50);
        tb2.getColumnModel().getColumn(2).setPreferredWidth(50);
        tb2.getColumnModel().getColumn(3).setPreferredWidth(130);
        tb2.getColumnModel().getColumn(4).setPreferredWidth(120);
        tb2.getColumnModel().getColumn(5).setPreferredWidth(120);
        tb2.getColumnModel().getColumn(6).setPreferredWidth(50);
        tb2.getColumnModel().getColumn(7).setPreferredWidth(50);
        tb2.getColumnModel().getColumn(8).setPreferredWidth(50);
        tb2.getColumnModel().getColumn(9).setPreferredWidth(80);
        tb2.getColumnModel().getColumn(10).setPreferredWidth(50);
        tb2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane sc2 = new JScrollPane(tb2);
        p2.add(sc2);
        panel.add(p2);

        Exit1 = new JButton("Exit");    panel.add(Exit1);
        Exit1.addActionListener(event -> {
            if (event.getActionCommand().equals("Exit"))
                frame.setVisible(false);
        });
        frame.add(Exit1, BorderLayout.PAGE_END);

        frame.add(panel);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void load() {
        try {
            String sql = ("SELECT * FROM SinhVien join DeTai on SinhVien.MaSV = DeTai.MaSV WHERE TienDo = '100%' ");
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            vttitlebv.clear();
            vtdatabv.clear();
            int num_column = tbl1.length;
            for (int i = 0; i < num_column; i++) {
                vttitlebv.add(tbl1[i]);
            }
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= num_column; i++) {
                    row.addElement(rs.getString(i));
                }
                vtdatabv.add(row);
            }
            model.setDataVector(vtdatabv, vttitlebv);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //cho dữ liệu vào danh sách chưa bảo vệ
    public void load2() {
        try {
            String sql = ("SELECT * FROM SinhVien join DeTai on SinhVien.MaSV = DeTai.MaSV WHERE TienDo <> '100%' ");
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            vttitlecbv.clear();
            vtdatacbv.clear();
            int num_column = tbl2.length;
            for (int i = 0; i < num_column; i++) {
                vttitlecbv.add(tbl2[i]);
            }
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= num_column; i++) {
                    row.addElement(rs.getString(i));
                }
                vtdatacbv.add(row);
            }
            model2.setDataVector(vtdatacbv, vttitlecbv);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
