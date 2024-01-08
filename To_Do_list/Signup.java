package To_Do_list;

 
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;


public class Signup extends JFrame implements ActionListener {

    private JTextField t1, t2,t3,t4;

    private JComboBox<String> comboBox;
    private JButton b1, b2;
    

    public Signup() {
        setTitle("New User SignUp Page");
        setSize(750, 450);
        getContentPane().setBackground(new Color(255, 255, 204));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255, 229, 180));
        p1.setBounds(40, 40, 660, 340);
        p1.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "  Create-Account  ",
                TitledBorder.CENTER, TitledBorder.TOP, null, new Color(34, 139, 34)));
        p1.setLayout(null);
        add(p1);

        JLabel l1 = new JLabel("Username :");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.setBounds(50, 50, 150, 25);
        p1.add(l1);

        JLabel l2 = new JLabel("Name :");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Tahoma", Font.BOLD, 18));
        l2.setBounds(50, 90, 150, 25);
        p1.add(l2);

        JLabel l3 = new JLabel("Password :");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Tahoma", Font.BOLD, 18));
        l3.setBounds(50, 130, 150, 25);
        p1.add(l3);

        JLabel l4 = new JLabel("Answer :");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Tahoma", Font.BOLD, 18));
        l4.setBounds(50, 210, 150, 25);
        p1.add(l4);

        JLabel l5 = new JLabel("Security Question :");
        l5.setForeground(Color.DARK_GRAY);
        l5.setFont(new Font("Tahoma", Font.BOLD, 18));
        l5.setBounds(50, 170, 190, 25);
        p1.add(l5);

        t1 = new JTextField();
        t1.setBounds(250, 50, 150, 25);
        p1.add(t1);
        t1.setColumns(10);

        t2 = new JTextField();
        t2.setColumns(10);
        t2.setBounds(250, 90, 150, 25);
        p1.add(t2);

        t3 = new JPasswordField();
        t3.setBounds(250, 130, 150, 25);
        p1.add(t3);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Your NickName?", "Your Lucky Number?",
                "Your child SuperHero?", "Your childhood Name ?"}));
        comboBox.setBounds(250, 170, 150, 25);
        p1.add(comboBox);

        t4 = new JTextField();
        t4.setColumns(10);
        t4.setBounds(250, 210, 150, 25);
        p1.add(t4);

        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i1 = c1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);

        JLabel l6 = new JLabel(i2);
        l6.setBounds(470, 85, 150, 150);
        p1.add(l6);

        b1 = new JButton("Create");
        b1.addActionListener(this);
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(200, 270, 100, 30);
        b1.setBackground(new Color(92, 219, 92));
        b1.setForeground(Color.WHITE);
        p1.add(b1);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setFont(new Font("Tahoma", Font.BOLD, 13));
        b2.setBounds(350, 270, 100, 30);
        b2.setBackground(new Color(92, 219, 92));
        b2.setForeground(Color.WHITE);
        p1.add(b2);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            Conn con = new Conn();
            
            if(ae.getSource() == b1){
                String sql = "insert into signup(username, name, password, question, answer) values(?, ?, ?, ?, ?)";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText());
                st.setString(2, t2.getText());
                st.setString(3, t3.getText());
                st.setString(4, (String) comboBox.getSelectedItem());
                st.setString(5, t4.getText());

                int i = st.executeUpdate();
                if (i > 0){
                    JOptionPane.showMessageDialog(null, "Account Created Successfully ");
                }

        t1.setText("");
        t2.setText("");
		t3.setText("");
		t4.setText("");
            }
            if(ae.getSource() == b2){
                this.setVisible(false);
                new Login().setVisible(true);			
            }
        }catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
          }
        }
    
    public static void main(String[] args) {
        new Signup().setVisible(true);
    }
    
}
