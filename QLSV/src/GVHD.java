import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFormattedTextField.*;
public class GVHD extends JFrame implements ActionListener{
    connect conn = new connect();
    JFrame frame;
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtfSearch;
    JTable tb;
    JDatePickerImpl birthday;
    Vector vttitle = new Vector();
    Vector vtdata = new Vector();
    String[] tbl = {"Họ và tên","Mã GV","Ngày sinh","Khoa","Ngành"};
    DefaultTableModel model;
    JButton hoten, khoa;
    public GVHD(){

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
