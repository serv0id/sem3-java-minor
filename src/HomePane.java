import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePane {
    private JPanel HomePanel;
    private JLabel WelcomeLabel;
    private JToolBar ToolBar;
    private JButton deleteAUserButton;
    private JButton viewPersonalDetailsButton;
    private JButton viewCoursesButton;
    private JButton viewAttendanceButton;
    private JButton addAUserButton;
    private JButton viewLectureNotesButton;
    private JButton viewTeachersButton;
    private JButton addLectureNotesButton;
    private JButton contactSupportButton;
    JFrame frame = new JFrame("Student Management System");

    HomePane(int mode, String username, Connection sql_c) throws SQLException {
        frame.setContentPane(HomePanel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (mode == 3) {
            addAUserButton.setEnabled(true);
            deleteAUserButton.setEnabled(true);
        }
        if (mode == 2) {
            addLectureNotesButton.setEnabled(true);
        }
        frame.setVisible(true);

        // retrieve personal details
        Statement st = sql_c.createStatement();
        ResultSet rst = st.executeQuery("SELECT * FROM personal_details where username = '" + username + "'");
        WelcomeLabel.setText("Welcome, " + rst.getString("First Name") + "!");


        viewPersonalDetailsButton.addActionListener(e -> {
            try {
                PersonalDetails pd = new PersonalDetails(rst);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        addAUserButton.addActionListener(e -> {
                    try {
                        AddUser au = new AddUser(sql_c);
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
    }
}
