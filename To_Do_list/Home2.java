package To_Do_list;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home2 extends JFrame {
    String username;

    public static void main(String[] args) {
        new Home2("").setVisible(true);
    }

    public Home2(String username) {
        super("To-Do Application");
        this.username = username;
        setForeground(Color.CYAN);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/todobg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1580, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(0, 0, 1580, 1000);
        add(NewLabel);

        JLabel l1 = new JLabel("To-Do Application");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 55));
        l1.setBounds(750, 30, 1000, 100);
        NewLabel.add(l1);

        // Navigation bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu m1 = new JMenu("PROFILE");
        m1.setForeground(Color.BLUE);
        menuBar.add(m1);

        JMenuItem mi2 = new JMenuItem("UPDATE DETAIL");
        m1.add(mi2);

        JMenuItem mi3 = new JMenuItem("VIEW DETAILS");
        m1.add(mi3);

        JMenuItem mi4 = new JMenuItem("DELETE DETAILS");
        m1.add(mi4);


        mi2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new UpdateCustomer(username).setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        mi3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new ViewCustomers().setVisible(true);
                }catch(Exception e ){}
            }
	});

            mi4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new DeleteCustomer().setVisible(true);
                }catch(Exception e ){}
            }
	});



        JMenu m6 = new JMenu("UTILITY");
        m6.setForeground(Color.BLUE);
        menuBar.add(m6);

        JMenuItem mi13 = new JMenuItem("NOTEPAD");
        m6.add(mi13);

        JMenuItem mi14 = new JMenuItem("CALCULATOR");
        m6.add(mi14);

        mi13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Runtime.getRuntime().exec("notepad.exe");
                } catch (Exception e) {
                }
            }
        });

        mi14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Runtime.getRuntime().exec("calc.exe");
                } catch (Exception e) {
                }
            }
        });

        JMenu m7 = new JMenu("ABOUT");
        m7.setForeground(Color.BLUE);
        menuBar.add(m7);

        JMenuItem mi15 = new JMenuItem("ABOUT");
        m7.add(mi15);

        mi15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new About().setVisible(true);
            }
        });

		JPanel listPanel = new JPanel();
		final int x = (NewLabel.getWidth() - 300) / 2;  // Increased width to 600
		final int y = (NewLabel.getHeight() - 600) / 2; // Increased height to 800
		listPanel.setBounds(x, y, 600, 600); // Set the increased size
		listPanel.setBackground(Color.WHITE);
		listPanel.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), " Your today's tasks ",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 15), Color.BLACK));
		listPanel.setLayout(new BorderLayout());
		NewLabel.add(listPanel);


		

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setSelectedIndex(-1);
        JScrollPane listScrollPane = new JScrollPane(taskList);

        JButton b1 = new JButton("Add or remove tasks");
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(75, 550, 250, 25);
        b1.setBackground(new Color(92, 219, 92));
        b1.setForeground(Color.WHITE);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.add(b1, BorderLayout.SOUTH);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            ToDoList td = new ToDoList(listModel);
                            td.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error opening ToDoList: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });

        JButton b2 = new JButton("Show Today's Score");
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(7000, 550, 250, 25);
        b1.setBackground(new Color(92, 219, 92));
        b1.setForeground(Color.WHITE);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.add(b1, BorderLayout.SOUTH);

                b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            ToDoList td = new ToDoList(listModel);
                            td.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error opening ToDoList: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
