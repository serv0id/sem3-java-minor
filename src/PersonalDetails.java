import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDetails {
    private JLabel pd;
    private JPanel panel;
    private JLabel Name;
    private JLabel number;
    private JLabel misc;
    JFrame frame = new JFrame("Personal Details");

    PersonalDetails(ResultSet rst) throws SQLException {
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);

        Name.setText("Name: " + rst.getString("First Name") + " " + rst.getString("Last Name"));
        number.setText("Phone Number: " + rst.getString("Phone Number"));
        misc.setText("Address: ");
        frame.setSize(400, 300);
        frame.setVisible(true);

    }
}
