package To_Do_list;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ToDoList extends JFrame {
    private JList<String> list;
    DefaultListModel<String> listModel;

    // JDBC database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo";
    private static final String USER = "root";
    private static final String PASSWORD = "Aarav1857@17";

    public ToDoList(DefaultListModel<String> listModel) {
        super("Enhanced To-Do List");

        this.listModel = listModel;

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(-1);
        JScrollPane listScrollPane = new JScrollPane(list);

        // Load initial data from the database
        loadToDoListFromDatabase();

        JButton addButton = createStyledButton("Add", Color.GREEN);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newItem = showInputDialog("Enter a new item:");
                if (newItem != null && !newItem.isEmpty()) {
                    int itemCount = listModel.getSize() + 1;
                    listModel.addElement(itemCount + ". " + newItem + " (Added on: " + getCurrentTimestamp() + ")");

                    // Save the updated to-do list to the database
                    saveToDoListToDatabase(newItem);
                }
            }

            private String showInputDialog(String message) {
                JTextField textField = new JTextField();
                textField.setColumns(30);  // Set the desired number of columns

                JPanel panel = new JPanel();
                panel.add(new JLabel(message));
                panel.add(textField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Task", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    return textField.getText();
                } else {
                    return null;
                }
            }
        });

        JButton removeButton = createStyledButton("Remove", Color.RED);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index != -1) {
                    String selectedItem = listModel.getElementAt(index);
                    // Extract taskId from the selected item (assuming it is in the format "ID. Task (Added on: Timestamp)")
                    int taskId = Integer.parseInt(selectedItem.split("\\.")[0]);
                    listModel.remove(index);

                    // Save the updated to-do list to the database
                    removeFromDatabase(taskId);
                    JOptionPane.showMessageDialog(null, "Congratulations , You completed the task ");
                    JOptionPane.showMessageDialog(null, " + 10 XP");
                }
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(20));
        buttonPane.add(removeButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(listScrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
        setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        return button;
    }

    private void loadToDoListFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT task, timestamp FROM task");

            while (resultSet.next()) {
                int taskId = resultSet.getInt("id");
                String task = resultSet.getString("task");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                listModel.addElement(taskId + ". " + task + " (Added on: " + timestamp + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveToDoListToDatabase(String newItem) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task (task) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newItem);
            preparedStatement.executeUpdate();

            // Retrieve the generated keys, which include the task ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int taskId = generatedKeys.getInt(1);
                    // You can use the task ID as needed
                    System.out.println("Inserted task with ID: " + taskId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeFromDatabase(int taskId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM task WHERE id = ?")) {
            preparedStatement.setInt(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTimestamp() {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DefaultListModel<String> listModel = new DefaultListModel<>();
                new ToDoList(listModel).setVisible(true);
            }
        });
    }
}
