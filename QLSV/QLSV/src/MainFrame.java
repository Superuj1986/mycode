import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFormattedTextField.*;

public class MainFrame extends JFrame implements ActionListener {
    connect conn = new connect();

    JFrame frame;
    JTextField Masv, Tensv, Malopsv, Khoasv, Nganhsv, Madt, Tendt, Tgth, Tiendo, Dtmagv, Kinhphi, Magv, Tengv, Khoagv, Nganhgv, timkiem;
//    JTextField MaSV, Tensv, Ngsinh,Malop, Khoa, Nganh, Madt, Tendt, TGTH, Tiendo, GVHD, Kinhphi, Tengv, Dategv, Khoagv, Nganhgv;
    JTable tb, tb1;

    JDatePickerImpl ngsinhgv;
    JDatePickerImpl ngsinhsv;

    Vector vttitle = new Vector();
    Vector vtdata = new Vector();
    Vector vttitle1 = new Vector();
    Vector vtdata1 = new Vector();
    String[] tbl = {"Mã SV", "Họ và tên", "Ngày sinh", "Mã Lớp", "Khoa", "Ngành", "Mã số ĐT", "Tên ĐT", "TGTH", "Tiến Độ", "GVHD", "Kinh phí"};
    String[] tbl1 = {"Mã GV", "Họ và tên", "Ngày Sinh", "Khoa", "Ngành"};
    DefaultTableModel defaultTableModel;

    JButton hoten, lop, khoa, nganh,
            tendt, madt, gvhd, tiendo;

    public MainFrame() {
        super("Chương trình quản lý sinh viên");

        //Tạo 1 MenuBar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu mnuFile = new JMenu("File");
        menubar.add(mnuFile);

        JMenuItem mnuFileExit = new JMenuItem("Exit");
        mnuFile.add(mnuFileExit);
        mnuFile.addSeparator();
        mnuFileExit.addActionListener(this);

        //Tạo 1 panel hiển thị thông tin người quản lý

        JPanel in4gv = new JPanel();
        in4gv.setLayout(new GridLayout(1, 1));


        //viền bao quanh thông tin
        JPanel in4 = new JPanel();
        Border borderInf = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorderInf = BorderFactory.createTitledBorder(borderInf, "");
        in4.setBorder(titledBorderInf);
        in4gv.add(in4);
        in4.setLayout(new BoxLayout(in4, BoxLayout.Y_AXIS));


        //panel chứa thông tin giáo viên quản lý
        JPanel ql = new JPanel();
        Border borderql = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorderql = BorderFactory.createTitledBorder(borderql, "Thông tin người quản lý");
        ql.setBorder(titledBorderql);
        in4.add(ql);
        ql.setLayout(new BorderLayout());


        //dòng dữ liệu ng quản lý
        JPanel p01 = new JPanel();
        Border border01 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder01 = BorderFactory.createTitledBorder(border01, "");
        p01.setBorder(titledBorder01);
        ql.add(p01, BorderLayout.WEST);
        p01.setLayout(new GridLayout(5, 1));

        JLabel magv = new JLabel("Mã GV");
        p01.add(magv);
        JLabel tengv = new JLabel("Họ và tên");
        p01.add(tengv);
        JLabel ngaysinhgv = new JLabel("Ngày sinh");
        p01.add(ngaysinhgv);
        JLabel khoagv = new JLabel("Khoa");
        p01.add(khoagv);
        JLabel nganhgv = new JLabel("Ngành");
        p01.add(nganhgv);

        JPanel p02 = new JPanel();
        Border border02 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder02 = BorderFactory.createTitledBorder(border02, "");
        p02.setBorder(titledBorder02);
        ql.add(p02, BorderLayout.CENTER);
        p02.setLayout(new GridLayout(6, 1));

        Magv = new JTextField(20);
        p02.add(Magv);
        Tengv = new JTextField(20);
        p02.add(Tengv);


        SqlDateModel dateModel = new SqlDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, new Properties());
        AbstractFormatter formatter = new SqlDateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        ngsinhgv = new JDatePickerImpl(datePanel, formatter);
        p02.add(ngsinhgv);

        Khoagv = new JTextField(20);
        p02.add(Khoagv);
        Nganhgv = new JTextField(20);
        p02.add(Nganhgv);


        //Tạo 1 panel hiển thi thông tin cần quản lý
        JPanel panel1 = new JPanel();
        Border bor = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBor = BorderFactory.createTitledBorder(bor, "");
        panel1.setBorder(titledBor);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        //panel chứa thông tin sinh viên
        JPanel p1 = new JPanel();
        Border border1 = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder(border1, "Thông tin sinh viên");
        p1.setBorder(titledBorder1);
        panel1.add(p1);
        p1.setLayout(new BorderLayout());

        //bảnh hiển thị dng dữ liệu sinh viên
        JPanel p11 = new JPanel();
        Border border11 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder11 = BorderFactory.createTitledBorder(border11, "");
        p11.setBorder(titledBorder11);
        p1.add(p11, BorderLayout.WEST);
        p11.setLayout(new GridLayout(6, 1));

        JLabel lbmasv = new JLabel("Mã SV");
        p11.add(lbmasv);
        JLabel lbtensv = new JLabel("Họ tên");
        p11.add(lbtensv);
        JLabel lbngsinhsv = new JLabel("Ngày sinh");
        p11.add(lbngsinhsv);
        JLabel lbmalop = new JLabel("Mã Lớp");
        p11.add(lbmalop);
        JLabel lbkhoasv = new JLabel("Khoa");
        p11.add(lbkhoasv);
        JLabel lbnganhsv = new JLabel("Ngành");
        p11.add(lbnganhsv);


        //cột nhập dữ liệu sv
        JPanel p12 = new JPanel();
        Border border12 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder12 = BorderFactory.createTitledBorder(border12, "");
        p12.setBorder(titledBorder12);
        p1.add(p12, BorderLayout.CENTER);
        p12.setLayout(new GridLayout(6, 1));

        Masv = new JTextField(20);
        p12.add(Masv);
        Tensv = new JTextField(20);
        p12.add(Tensv);


//        SqlDateModel dateModel1 = new SqlDateModel();
//		JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1,new Properties());
//        AbstractFormatter formatter1 = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));

        SqlDateModel dateModel1 = new SqlDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1, new Properties());
        AbstractFormatter formatter1 = new SqlDateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        ngsinhsv = new JDatePickerImpl(datePanel1, formatter1);
        p12.add(ngsinhsv);

        Malopsv = new JTextField(20);
        p12.add(Malopsv);
        Khoasv = new JTextField(20);
        p12.add(Khoasv);
        Nganhsv = new JTextField(20);
        p12.add(Nganhsv);

        //panel chứa thông tin đề tài của sinh viên
        JPanel p2 = new JPanel();
        Border border2 = BorderFactory.createLineBorder(Color.blue);
        TitledBorder titledBorder2 = BorderFactory.createTitledBorder(border2, "Thông tin đề tài");
        p2.setBorder(titledBorder2);
        panel1.add(p2);
        p2.setLayout(new BorderLayout());

        //tên coojt dữ liệu đề tài
        JPanel p21 = new JPanel();
        Border border21 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder21 = BorderFactory.createTitledBorder(border21, "");
        p21.setBorder(titledBorder21);
        p2.add(p21, BorderLayout.WEST);
        p21.setLayout(new GridLayout(6, 1));

        JLabel lbmadt = new JLabel("Mã Số ĐT");
        p21.add(lbmadt);
        JLabel lbtendt = new JLabel("Tên ĐT");
        p21.add(lbtendt);
        JLabel lbtgth = new JLabel("TGTH (tháng)");
        p21.add(lbtgth);
        JLabel lbtiendo = new JLabel("Tiến Độ");
        p21.add(lbtiendo);
        JLabel lbmagv = new JLabel("Mã GV");
        p21.add(lbmagv);
        JLabel lbkinhphi = new JLabel("Kinh phí (VNĐ)");
        p21.add(lbkinhphi);

        //vùng nhập dữ liệu đề tài
        JPanel p22 = new JPanel();
        Border border22 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder22 = BorderFactory.createTitledBorder(border22, "");
        p22.setBorder(titledBorder22);
        p2.add(p22, BorderLayout.CENTER);
        p22.setLayout(new GridLayout(6, 1));

        Madt = new JTextField(20);
        p22.add(Madt);
        Tendt = new JTextField(20);
        p22.add(Tendt);
        Tgth = new JTextField(20);
        p22.add(Tgth);
        Tiendo = new JTextField(20);
        p22.add(Tiendo);
        Dtmagv = new JTextField(20);
        p22.add(Dtmagv);
        Kinhphi = new JTextField(20);
        p22.add(Kinhphi);

        //panel chứa thanh công cụ
        JPanel p3 = new JPanel();
        Border border3 = BorderFactory.createLineBorder(Color.red);
        TitledBorder titledBorder3 = BorderFactory.createTitledBorder(border3, "Công cụ");
        p3.setBorder(titledBorder3);
        panel1.add(p3);
        p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));

        JButton Add = new JButton("Add", new ImageIcon("add.png"));
        p3.add(Add);
        JButton Reset = new JButton("Reset", new ImageIcon("reset.png"));
        p3.add(Reset);
        JButton Update = new JButton("Update", new ImageIcon("update.png"));
        p3.add(Update);
        JButton Delete = new JButton("Delete", new ImageIcon("delete.png"));
        p3.add(Delete);
        JButton Sort = new JButton("Sort", new ImageIcon("sort.png"));
        p3.add(Sort);

        Add.addActionListener(this);
        Reset.addActionListener(this);
        Delete.addActionListener(this);
        Update.addActionListener(this);
        Sort.addActionListener(this);

        JPanel p31 = new JPanel();
        Border border31 = BorderFactory.createLineBorder(Color.GREEN);
        TitledBorder titledBorder31 = BorderFactory.createTitledBorder(border31, "Tìm kiếm (Sử dụng mã sinh viên để tìm kiếm)");
        p31.setBorder(titledBorder31);
        p3.add(p31, BorderLayout.NORTH);
        p31.setLayout(new BoxLayout(p31, BoxLayout.X_AXIS));

        timkiem = new JTextField(20);
        p31.add(timkiem);
        JButton Search = new JButton("Search", new ImageIcon("search.png"));
        p31.add(Search);
        Search.addActionListener(this);

        JPanel p4 = new JPanel();
        panel1.add(p4, BorderLayout.NORTH);
        p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));


        //tạo bảng hiển thị dữ liệu
        //bảng sinh viên
        JPanel ptb1 = new JPanel();
        Border bordertbl1 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBordertbl1 = BorderFactory.createTitledBorder(bordertbl1, "Danh sách");
        ptb1.setBorder(titledBordertbl1);
        p4.add(ptb1);
        ptb1.setLayout(new BorderLayout());
        defaultTableModel = new DefaultTableModel(vtdata, vttitle);
        load();
        tb = new JTable(defaultTableModel);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        JScrollPane sc1 = new JScrollPane(tb);
        ptb1.add(sc1);
        //bảng giáo viên
        JPanel ptb2 = new JPanel();
        Border bordertbl2 = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBordertbl2 = BorderFactory.createTitledBorder(bordertbl2, "Danh sách");
        ptb2.setBorder(titledBordertbl2);
        in4.add(ptb2);
        ptb2.setLayout(new BorderLayout());
        defaultTableModel = new DefaultTableModel(vtdata1, vttitle1);
        load1();
        tb1 = new JTable(defaultTableModel);
        JScrollPane sc2 = new JScrollPane();


        JButton Statistical = new JButton("Statistical", new ImageIcon("Statistical.png"));
        panel1.add(Statistical, BorderLayout.PAGE_END);
        Statistical.addActionListener(this);

        JTabbedPane myTabled = new JTabbedPane();
        myTabled.add(in4, "Thông tin người quản lý");
        myTabled.add(panel1, "Hệ thống quản lý");
//      myTabled.add(panel2,"Đề tài tự chọn");
        Container con = getContentPane();
        con.add(myTabled);

        //Hàm khi nhấn vào 1 Row, thông tin sẽ hiển thị ngược lại lên Textfield
        tb.getSelectionModel().addListSelectionListener(event -> {
            int row = tb.getSelectedRow();
            if (row >= 0) {
                Masv.setText(tb.getModel().getValueAt(row, 0).toString());
                Tensv.setText(tb.getModel().getValueAt(row, 1).toString());
                Malopsv.setText(tb.getModel().getValueAt(row, 3).toString());
                Khoasv.setText(tb.getModel().getValueAt(row, 4).toString());
                Nganhsv.setText(tb.getModel().getValueAt(row, 5).toString());
                Madt.setText(tb.getModel().getValueAt(row, 6).toString());
                Tendt.setText(tb.getModel().getValueAt(row, 7).toString());
                Tgth.setText(tb.getModel().getValueAt(row, 8).toString());
                Tiendo.setText(tb.getModel().getValueAt(row, 9).toString());
                Dtmagv.setText(tb.getModel().getValueAt(row, 10).toString());
                Kinhphi.setText(tb.getModel().getValueAt(row, 11).toString());
                try {
                    String dt = tb.getModel().getValueAt(tb.getSelectedRow(), 2).toString();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = dateFormat.parse(dt);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    dateModel1.setValue(sqlDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void reload() {
        Masv.setText("");
        Tensv.setText("");
        Malopsv.setText("");
        Khoasv.setText("");
        Nganhsv.setText("");
        Madt.setText("");
        Tendt.setText("");
        Tgth.setText("");
        Tiendo.setText("");
        Dtmagv.setText("");
        Kinhphi.setText("");
        Magv.setText("");
        Tengv.setText("");
        Khoagv.setText("");
        Nganhgv.setText("");
        ngsinhgv.getModel().setValue(null);
        ngsinhsv.getModel().setValue(null);
        timkiem.setText("");
    }

    //Hàm cho dữ liệu từ database vào table
    // cho dữ liệu sv từ database vào table
    public void load() {
        try {
            String sql = ("SELECT * FROM SinhVien join DeTai on SinhVien.MaSV = DeTai.MaSV ");
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            vttitle.clear();
            vtdata.clear();
            int num_column = tbl.length;
            for (int i = 0; i < num_column; i++) {
                vttitle.add(tbl[i]);
            }
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= num_column; i++) {
                    row.addElement(rs.getString(i));
                }
                vtdata.add(row);
            }
            defaultTableModel.setDataVector(vtdata, vttitle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //cho dữ liệu giáo viên từ database vào table
    public void load1() {
        try {
            String sql = ("SELECT * FROM GVHD");
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            vttitle1.clear();
            vtdata1.clear();
            int num_column = tbl1.length;
            for (int i = 0; i < num_column; i++) {
                vttitle1.add(tbl1[i]);
            }
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= num_column; i++) {
                    row.addElement(rs.getString(i));
                }
                vtdata1.add(row);
            }
            defaultTableModel.setDataVector(vtdata1, vttitle1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Hàm kiểm tra xem có trùng mã sinh viên khi thêm vào không
    public boolean ktmasv(String MaSV) {
        try {
            String sql = ("SELECT MaSV FROM SinhVien WHERE MaSV = ?");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, Masv.getText());
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Hàm kiểm tra xem có trùng mã số đề tài khi thêm vào không
    public boolean ktmasodt(String MaSoDT) {
        try {
            String sql = ("SELECT MaSoDT FROM DeTai WHERE MaSoDT = ?");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, Madt.getText());
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Thêm thông tin vào table
    public void insert() {
        try {
            String sql1 = ("INSERT INTO SinhVien (MaSV,HoTen,NgaySinh,MaLop,Khoa,Nganh) VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql1);
            String sql2 = ("INSERT INTO DeTai (MaSoDT,TenDT,TGTH,TienDo,MaGV,KinhPhi,MaSV) VALUES (?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps1 = conn.getConnection().prepareStatement(sql2);
            if (checkBlank()) {
                JOptionPane.showMessageDialog(this, "Không được để trống!");
            } else {
                if (ktmasv(Masv.getText())) {
                    JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại rồi !");
                    if (ktmasodt(Madt.getText())) {
                        JOptionPane.showMessageDialog(this, "Mã đề tài đã tồn tại rồi !");
                    }
                } else {
                    ps.setString(1, Masv.getText());
                    ps.setString(2, Tensv.getText());
                    ps.setDate(3, Date.valueOf(ngsinhsv.getModel().getValue().toString()));
                    ps.setString(4, Malopsv.getText());
                    ps.setString(5, Khoasv.getText());
                    ps.setString(6, Nganhsv.getText());
                    ps1.setString(1, Madt.getText());
                    ps1.setString(2, Tendt.getText());
                    ps1.setString(3, Tgth.getText());
                    ps1.setString(4, Tiendo.getText());
                    ps1.setString(5, Dtmagv.getText());
                    ps1.setString(6, Kinhphi.getText());
                    ps1.setString(7, Masv.getText());

                    int check = ps.executeUpdate();
                    int check1 = ps1.executeUpdate();
                    if (check > 0) {
                        if (check1 > 0) {
                            defaultTableModel.setNumRows(0);
                            load();
                            JOptionPane.showMessageDialog(this, "Thêm thông tin thành công");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Cập nhật thông tin theo mã sinh viên
    public void editsv() {
        try {
            String sql1 = ("UPDATE SinhVien SET   MaLop = ?,HoTen = ?,NgaySinh = ? ,Khoa = ?, Nganh = ? WHERE MaSV = ?");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql1);
            String sql2 = ("UPDATE DeTai SET MaSoDT = ?, TenDT = ?,  TGTH= ?, TienDo=?, MaGV = ?, KinhPhi = ? WHERE MaSV = ?");
            PreparedStatement ps1 = conn.getConnection().prepareStatement(sql2);
            ps.setString(6, Masv.getText());
            ps.setString(3, Malopsv.getText());  // ma lop
            ps.setString(4, Khoasv.getText());  //  khoa
            ps.setString(5, Nganhsv.getText());  // nganh
            ps.setDate(2, Date.valueOf(ngsinhsv.getModel().getValue().toString())); // ngay sinh
            ps.setString(1, Tensv.getText());  // ho ten

            ps1.setString(1, Madt.getText()); // ma dt
            ps1.setString(2, Tendt.getText()); // ten dt
            ps1.setString(3, Tgth.getText()); // tgth
            ps1.setString(4, Tiendo.getText()); // tien do
            ps1.setString(5, Dtmagv.getText()); // gvhd
            ps1.setString(6, Kinhphi.getText()); // kinh phi
            ps1.setString(7, Masv.getText());
            int check = ps.executeUpdate();
            int check1 = ps1.executeUpdate();
            if (check > 0) {
                if (check1 > 0) {
                    defaultTableModel.setRowCount(0);
                    load();
                    JOptionPane.showMessageDialog(this, "Sửa thông tin thành công");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Không tồn tại mã sinh viên này!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //xóa thông tin sinh viên
    public void deletesv() {
        try {
            String sql = ("DELETE FROM SinhVien WHERE MaSV = ?");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, Masv.getText());
            deletedtByMaSV();
            int check = ps.executeUpdate();
            if (check > 0) {
                defaultTableModel.setNumRows(0);
                load();
            } else {
                JOptionPane.showMessageDialog(this, "Không có mã sinh viên này!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // Xoá đề tài theo mã sv
    public void deletedtByMaSV() {
        try {
            String sql = "delete from DeTai where MaSV = ?";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, Masv.getText());
            int check = ps.executeUpdate();
            if (check > 0) {
                load();
                reload();
            } else {
                JOptionPane.showMessageDialog(this, "Không có mã số đề tài này!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // Hàm sắp xếp lại thứ tự bảng sinh viên
    public void loadSortSinhVien(TypeTable typeTable) {
        try {
            String sql = ("Select * from SinhVien join DeTai on Sinhvien.MaSV = DeTai.MaSV ORDER BY " + typeTable);
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.getMetaData();
            vttitle.clear();
            vtdata.clear();

            int num_column = tbl.length;
            for (int i = 0; i < num_column; i++) {
                vttitle.add(tbl[i]);
            }
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= num_column; i++) {
                    row.addElement(rs.getString(i));
                }
                vtdata.add(row);
            }
            defaultTableModel.setDataVector(vtdata, vttitle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Hàm tìm kiếm thông tin thông qua mã sinh viên
    public void findByMaSV() {
        try {
            String sql1 = ("SELECT * FROM SinhVien WHERE MaSV = ?");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql1);
            String sql2 = ("SELECT * FROM DeTai WHERE MaSV = ?");
            PreparedStatement ps1 = conn.getConnection().prepareStatement(sql2);
            ps.setString(1, timkiem.getText());
            ps1.setString(1, timkiem.getText());
            ResultSet check = ps.executeQuery();
            ResultSet check1 = ps1.executeQuery();
            if (check.next()) {
                if (check1.next()) {
                    Madt.setText(check.getString("MaSV"));
                    Tendt.setText(check.getString("HoTen"));
                    Malopsv.setText(check.getString("MaLop"));
                    Khoasv.setText(check.getString("Khoa"));
                    Nganhsv.setText(check.getString("Nganh"));
                    Madt.setText(check1.getString("MaSoDT"));
                    Tendt.setText(check1.getString("TenDT"));
                    Tgth.setText(check1.getString("TGTH"));
                    Tiendo.setText(check1.getString("TienDo"));
                    Dtmagv.setText(check1.getString("MaGV"));
                    Kinhphi.setText(check1.getString("KinhPhi"));

                    String dt = check.getString("NgaySinh");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = dateFormat.parse(dt);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    SqlDateModel dateModel1 = new SqlDateModel();
                    dateModel1.setValue(sqlDate);


                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tồn tại mã sinh viên này!");
                timkiem.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                if (!checkBlank()) {
                    insert();
                    load();
                }
                break;

            case "Update":
                if (!Masv.getText().isBlank() && !Madt.getText().isBlank()) {
                    editsv();
                }
                break;

            case "Delete":
                if (!Masv.getText().isBlank()) {
                    deletesv();
                }
                if (!Madt.getText().isBlank()) {
                    deletedtByMaSV();
                }
                load();
                reload();
                JOptionPane.showMessageDialog(this, "Xóa thông tin thành công");
                break;

            case "Search":
                JOptionPane.showMessageDialog(this, "Tìm kiếm thông tin thành công!");
                findByMaSV();
                break;

            case "Reset":
                reload();
                break;

            case "Sort":
                Sort();
                break;

            case "Exit":
                int Exit = JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát ra?",
                        "Exit?", JOptionPane.YES_NO_OPTION);
                if (Exit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                    System.out.println("Thoát ra thành công!");
                }
                break;

            case "Statistical":
                new thongke();
                break;

            default:
                break;
        }
    }

    private boolean checkBlank() {
        return Masv.getText().isBlank() ||
                Tensv.getText().isBlank() ||
                Malopsv.getText().isBlank() ||
                Khoasv.getText().isBlank() ||
                Nganhsv.getText().isBlank() ||
                Madt.getText().isBlank() ||
                Tendt.getText().isBlank() ||
                Tgth.getText().isBlank() ||
                Tiendo.getText().isBlank() ||
                Dtmagv.getText().isBlank() ||
                Kinhphi.getText().isBlank();
    }


    // sắp xếp theo yêu cầu
    private void Sort() {
        JFrame fr = new JFrame("Bạn muốn sắp xếp theo?");
        JPanel pn = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "");
        pn.setBorder(titledBorder);
        pn.setLayout(new GridLayout(2, 4));

        hoten = new JButton("Họ tên");
        pn.add(hoten);
        lop = new JButton("Lớp");
        pn.add(lop);
        khoa = new JButton("Khoa");
        pn.add(khoa);
        nganh = new JButton("Ngành");
        pn.add(nganh);
        tendt = new JButton("Tên ĐT");
        pn.add(tendt);
        madt = new JButton("Mã số ĐT");
        pn.add(madt);
        gvhd = new JButton("GVHD");
        pn.add(gvhd);
        tiendo = new JButton("Tiến Độ");
        pn.add(tiendo);

        hoten.addActionListener(event -> {
            if (event.getActionCommand().equals("Họ tên"))
                loadSortSinhVien(TypeTable.HoTen);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        lop.addActionListener(event -> {
            if (event.getActionCommand().equals("Lớp"))
                loadSortSinhVien(TypeTable.MaLop);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        khoa.addActionListener(event -> {
            if (event.getActionCommand().equals("Khoa"))
                loadSortSinhVien(TypeTable.Khoa);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        nganh.addActionListener(event -> {
            if (event.getActionCommand().equals("Ngành"))
                loadSortSinhVien(TypeTable.Nganh);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        tendt.addActionListener(event -> {
            if (event.getActionCommand().equals("Tên ĐT"))
                loadSortSinhVien(TypeTable.TenDT);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        madt.addActionListener(event -> {
            if (event.getActionCommand().equals("Mã số ĐT"))
                loadSortSinhVien(TypeTable.MaSoDT);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        gvhd.addActionListener(event -> {
            if (event.getActionCommand().equals("GVHD"))
                loadSortSinhVien(TypeTable.GVHD);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });
        tiendo.addActionListener(event -> {
            if (event.getActionCommand().equals("Tiến Độ"))
                loadSortSinhVien(TypeTable.TienDo);
            fr.setVisible(false);
            JOptionPane.showMessageDialog(fr, "Sắp xếp thông tin thành công!");
        });

        fr.add(pn);
        fr.setSize(400, 120);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}

enum TypeTable {
    HoTen,
    MaLop,
    Khoa,
    Nganh,
    TenDT,
    MaSoDT,
    GVHD,
    TienDo
}
