import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;


public class Authenticate {
    private JPanel auth_panel;
    private JPasswordField passwordField;
    private JTextField textField1;
    private JButton button;
    String username;
    char[] password;
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    final String db_path = "jdbc:sqlite:./auth";
    Connection c = DriverManager.getConnection(db_path);
    JFrame frame = new JFrame("Authenticate");

    Authenticate(int mode) throws NoSuchAlgorithmException, SQLException {
        button.addActionListener(e -> {
            username = textField1.getText();
            password = passwordField.getPassword();
            byte[] hashed_pw = digest.digest(new String(password).getBytes());

            StringBuilder hashed_pw_hex = new StringBuilder(hashed_pw.length * 2);

            for (byte b : hashed_pw) {
                hashed_pw_hex.append(String.format("%02x", b));
            }

            try {
                Statement st = c.createStatement();
                ResultSet rst = st.executeQuery("SELECT * FROM creds WHERE username = '" + username + "';");

                // password sha256 hash is validated to prevent attacks
                if (Objects.equals(rst.getString("hashed_pw"), hashed_pw_hex.toString())) {
                    if (rst.getInt("user_class") != mode) {
                        System.out.println("Wrong Login Panel, please login with your allotted user class!");
                    } else {
                        System.out.println("[+] Logged in with " + username + "!");
                        HomePane hp = new HomePane(mode, username, c);
                        frame.setVisible(false);
                    }
                } else {
                    System.out.println("[-] Wrong credentials.");
                    // System.out.println("Hashed password is " + hashed_pw_hex.toString());
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

        frame.setContentPane(this.auth_panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
