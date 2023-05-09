
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
public class Login extends JFrame implements ActionListener {
    JPanel panel,panel1,panel2;
    JLabel user_label , password_label;
    JTextField username_text;
    JPasswordField password_text;
    JButton submit;
    public Login(){
        super("Login");
        setLayout(new GridLayout(3,1));
        panel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border,"");
        panel.setBorder(titledBorder);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        ImageIcon user = new ImageIcon("user.png");
        user_label = new JLabel("User Name: ");
        user_label.setIcon(user);
        username_text = new JTextField("");
        panel.add(user_label);
        panel.add(username_text);
        panel1 = new JPanel();
        Border border1 = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder(border1, "");
        panel1.setBorder(titledBorder1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        ImageIcon pass = new ImageIcon("pass.png");
        password_label = new JLabel (" Password:  ");
        password_label.setIcon(pass);
        password_text = new JPasswordField("");
        panel1.add(password_label);
        panel1.add(password_text);

        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        submit = new JButton("Login", new ImageIcon("login.png"));
        panel2.add(submit, BorderLayout.AFTER_LINE_ENDS);

        add(panel);
        add(panel1);
        add(panel2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submit.addActionListener(this);
        setSize(350,150);
        setVisible(true);
    }

    public static void main(String[] args){ new Login();}

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = username_text.getText();
        String password = password_text.getText();
        if ((username.equals("admin")) && (password.equals("123"))){
            this.setVisible(true);
            new MainFrame();
        }else{
            JOptionPane.showMessageDialog(Login.this,"Xin lỗi, tài khoản hoặc mật khẩu sai!",
                                                                            "Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
            username_text.setText("");
            password_text.setText("");
        }
    }
}
