import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class AddUser {
    private JPanel panel;
    private JButton addButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JSpinner spinner1;
    MessageDigest digest = MessageDigest.getInstance("SHA-256");

    JFrame frame = new JFrame();

    AddUser(Connection c_sql) throws NoSuchAlgorithmException {
        frame.setContentPane(panel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        addButton.addActionListener(e -> {
            String username = textField1.getText();
            String password = Arrays.toString(passwordField1.getPassword());

            assert password.equals(textField2.getText());
            byte[] hashed_pw = digest.digest(password.getBytes());

            long epoch = System.currentTimeMillis();
            int secs = (int) (epoch / 1000);

            StringBuilder hashed_pw_hex = new StringBuilder(hashed_pw.length * 2);

            for (byte b : hashed_pw) {
                hashed_pw_hex.append(String.format("%02x", b));
            }

            try {
                Statement st = c_sql.createStatement();
                st.executeQuery("INSERT INTO creds (username, hashed_pw, user_class, added_on, last_login) VALUES ('" + username + "', '" + hashed_pw_hex.toString()
                + "', '" + 1 + "', '" + secs + "', '0'" );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }
}
