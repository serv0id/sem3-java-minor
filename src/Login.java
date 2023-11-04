import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login {
    private JPanel panel;
    private JLabel top_label;
    private JButton studentLoginButton;
    private JButton facultyLoginButton;
    private JButton staffLoginButton;
    JFrame frame = new JFrame("Login");
    public Login() {
        studentLoginButton.addActionListener(e -> {
            System.out.println("[!] Student Login called");
            try {
                Authenticate auth = new Authenticate(1);
            } catch (NoSuchAlgorithmException | SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setVisible(false);
        });

        facultyLoginButton.addActionListener(e -> {
            System.out.println("[!] Faculty Login called");
            try {
                Authenticate auth = new Authenticate(2);
            } catch (NoSuchAlgorithmException | SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setVisible(false);
        });
        staffLoginButton.addActionListener(e -> {
            System.out.println("[!] Staff Login called");
            try {
                Authenticate auth = new Authenticate(3);
            } catch (NoSuchAlgorithmException | SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.setVisible(false);
        });

        frame.setContentPane(this.panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
