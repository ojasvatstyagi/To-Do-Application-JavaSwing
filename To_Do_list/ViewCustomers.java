package To_Do_list;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewCustomers extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo";
    private static final String USER = "root";
    private static final String PASSWORD = "Aarav1857@17";

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewCustomers frame = new ViewCustomers();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public ViewCustomers() throws SQLException {
        setBounds(580, 220, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = {"Username", "ID Type", "Number", "Name", "Gender", "Country", "Address", "Phone", "Email"};
        Object[][] data = fetchCustomerData();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 800, 450);
        contentPane.add(scrollPane);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> setVisible(false));
        btnExit.setBounds(350, 480, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }

    private Object[][] fetchCustomerData() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columns = metaData.getColumnCount();
            Object[][] data = new Object[getRowCount(resultSet)][columns];

            int row = 0;
            resultSet.beforeFirst();
            while (resultSet.next()) {
                for (int col = 1; col <= columns; col++) {
                    data[row][col - 1] = resultSet.getObject(col);
                }
                row++;
            }

            return data;
        }
    }


    private int getRowCount(ResultSet resultSet) throws SQLException {
        int rowCount;
        int currentRow = resultSet.getRow(); // Get current row position
        resultSet.last(); // Move to last row
        rowCount = resultSet.getRow(); // Get row count
        if (currentRow == 0) // If current row is at the beginning, move back to it
            resultSet.beforeFirst();
        else
            resultSet.absolute(currentRow); // Move back to the original row
        return rowCount;
    }
}
