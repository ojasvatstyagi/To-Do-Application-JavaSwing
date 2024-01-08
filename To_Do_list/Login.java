package To_Do_list;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;

public class Login extends JFrame implements ActionListener {
	private JTextField t1;
	private Component passwordField;
	private JButton b1,b2,b3;
	
	Login(){
		
		setTitle("Login Page");
		this.setLayout(null);
		this.setVisible(true);
		setSize(750,450);
		getContentPane().setBackground(new Color(255, 255, 204));
		setLocationRelativeTo(null);
        setResizable(false);

				
	JPanel p1 = new JPanel();
	p1.setBackground(new Color(255, 229, 180));
	p1.setBounds(50, 50, 640, 330);
	p1.setLayout(null);
	add(p1);
	
	JLabel l1 = new JLabel("Username : ");
	l1.setBounds(50, 50, 150, 25);
	l1.setFont(new Font("Arial", Font.BOLD, 18));
	p1.add(l1);

	JLabel l2 = new JLabel("Password : ");
	l2.setBounds(50, 100, 150, 25);
	l2.setFont(new Font("Arial", Font.BOLD, 18));
	p1.add(l2);

	t1 = new JTextField();
	t1.setBounds(170, 50, 200, 25);
	p1.add(t1);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(170, 100, 200, 25);
	p1.add(passwordField);
	

    // Create a border for the panel
	p1.setForeground(new Color(34, 139, 34));
	p1.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "   Get In Fast !  ",
	TitledBorder.CENTER, TitledBorder.TOP, null, new Color(34, 139, 34)));
	p1.setLayout(null);
	
	b1 = new JButton("Login");
	b1.addActionListener(this);                
	b1.setForeground(Color.WHITE);
	b1.setBackground(new Color(92, 219, 92));
	b1.setBounds(70, 170, 100, 30);
	p1.add(b1);
	
	
    b2 = new JButton("SignUp");
	b2.addActionListener(this);	
	b2.setForeground(Color.WHITE);
	b2.setBackground(new Color(92, 219, 92));
	b2.setBounds(260, 170, 100, 30);
	p1.add(b2);

	
	b3 = new JButton("Forgot Password");
	b3.addActionListener(this);	
    b3.setForeground(Color.WHITE);
	b3.setBackground(new Color(92, 219, 92));
	b3.setBounds(140, 230, 150, 30);
	p1.add(b3);
	
    JLabel l3 = new JLabel(" *Your information is safe with us,we will not share it with any body !");
	l3.setBounds(20, 290, 400, 25);
	p1.add(l3);
	
	
	//adding image  to frame	
    ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
    Image i1 = c1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
    ImageIcon i2 = new ImageIcon(i1);

    JLabel l4 = new JLabel(i2);
    l4.setBounds(450, 85, 150, 150);
    p1.add(l4);
    
	}
	
	public void actionPerformed(ActionEvent ae) {
	    if (ae.getSource() == b1) {
	        try {System.out.println("Got it");
	            Conn con = new Conn();
	            String sql = "SELECT * FROM signup WHERE username=? AND password=?";
	            PreparedStatement st = con.c.prepareStatement(sql);

	            st.setString(1, t1.getText());
	            st.setString(2, new String(((JPasswordField) passwordField).getPassword()));

	            ResultSet rs = st.executeQuery();
	            if (rs.next()) {
	                this.setVisible(false);
	                new Loading(t1.getText()).setVisible(true);
	            } else {
	                JOptionPane.showMessageDialog(null, "Invalid Login or Password!");
	            }

	            con.c.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else if (ae.getSource() == b2) {
	        setVisible(false);
	        Signup su = new Signup();
	        su.setVisible(true);
	    } else if (ae.getSource() == b3) {
	        setVisible(false);
	        ForgotPassword forgot = new ForgotPassword();
	        forgot.setVisible(true);
	    }
	}

	public static void main(String[] args){
	    SwingUtilities.invokeLater(() -> {
	        new Login().setVisible(true); 
	    });
	}
}