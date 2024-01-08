package To_Do_list;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ForgotPassword extends JFrame implements ActionListener {

	
    private JTextField t1,t2,t3,t4,t5;
    private JButton b1,b2,b3;
    
    
    public ForgotPassword() {
    	setTitle("Change Password ");
    	setSize(850,450);
    	getContentPane().setBackground(new Color(255, 255, 204));
    	setLocationRelativeTo(null);
    	
        // Set the frame to be non-resizable
        setResizable(false);
    	setLayout(null);
 
    	// panel to write on
    	JPanel p1 = new JPanel();
    	p1.setBackground(new Color(255, 229, 180));
    	p1.setBounds(40,40,760, 340);
    	
        // Create a border for the panel
    	p1.setForeground(new Color(34, 139, 34));
    	p1.setBorder(new TitledBorder(new LineBorder(Color.black, 2), " Change Your Password ",
    	TitledBorder.CENTER, TitledBorder.TOP, null, new Color(34, 139, 34)));
    	p1.setLayout(null);

    	add(p1);
    	
    	// adding texts
    	JLabel l1 = new JLabel("Username :");
    	l1.setFont(new Font("Tahoma", Font.BOLD, 18));
    	l1.setBounds(50, 50, 150, 25);
    	p1.add(l1);

    	JLabel l2 = new JLabel("Name :");
    	l2.setFont(new Font("Tahoma", Font.BOLD, 18));
    	l2.setBounds(50, 90, 150, 25);
    	p1.add(l2);

    	JLabel l3 = new JLabel("Security Question :");
    	l3.setFont(new Font("Tahoma", Font.BOLD, 18));
    	l3.setBounds(50, 130, 200, 25);
    	p1.add(l3);

    	JLabel l4 = new JLabel("Answer :");
    	l4.setFont(new Font("Tahoma", Font.BOLD, 18));
    	l4.setBounds(50, 170, 150, 25);
    	p1.add(l4);

    	JLabel l5 = new JLabel("Password :");
    	l5.setFont(new Font("Tahoma", Font.BOLD, 18));
    	l5.setBounds(50, 210, 150, 25);
    	p1.add(l5);
    	
    	// image on the panel
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icon/forgot-password.png"));
        Image i1 = c1.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        JLabel l6 = new JLabel(i2);
        l6.setBounds(570, 85, 150, 150);
        p1.add(l6);
        
        t1 = new JTextField();
    	t1.setFont(new Font("Tahoma", Font.BOLD, 13));
    	t1.setForeground(new Color(105, 105, 105));
    	t1.setBounds(240, 50, 150, 25);
    	p1.add(t1);
    	t1.setColumns(10);

    	t2 = new JTextField();
    	t2.setEditable(false);
    	t2.setFont(new Font("Tahoma", Font.BOLD, 13));
    	t2.setForeground(new Color(165, 42, 42));
    	t2.setColumns(10);
    	t2.setBounds(240, 90, 150, 25);
    	p1.add(t2);

    	t3 = new JTextField();
    	t3.setEditable(false);
    	t3.setFont(new Font("Tahoma", Font.BOLD, 12));
    	t3.setForeground(new Color(72, 61, 139));
    	t3.setColumns(10);
    	t3.setBounds(240, 130, 150, 25);
    	p1.add(t3);

    	t4 = new JTextField();
    	t4.setFont(new Font("Tahoma", Font.BOLD, 13));
    	t4.setForeground(Color.gray);
    	t4.setColumns(10);
    	t4.setBounds(240, 170, 150, 25);
    	p1.add(t4);

    	t5 = new JTextField();
    	t5.setEditable(false);
    	t5.setFont(new Font("Tahoma", Font.BOLD, 13));
    	t5.setForeground(new Color(50, 205, 50));
    	t5.setColumns(10);
    	t5.setBounds(240, 210, 150, 25);
    	p1.add(t5);
    	
    	b1 = new JButton("Search");
    	b1.addActionListener(this);
    	b1.setFont(new Font("Tahoma", Font.BOLD, 12));
    	b1.setBounds(420, 50, 90, 25);
    	b1.setBackground(new Color(92, 219, 92));
        b1.setForeground(Color.WHITE);
        p1.add(b1);

    	b2 = new JButton("Retrieve");
    	b2.addActionListener(this);
    	b2.setFont(new Font("Tahoma", Font.BOLD, 12));
    	b2.setBounds(420, 170, 90, 25);
    	b2.setBackground(new Color(92, 219, 92));
        b2.setForeground(Color.WHITE);
    	p1.add(b2);

    	b3 = new JButton("Back");
    	b3.addActionListener(this);
    	b3.setFont(new Font("Tahoma", Font.BOLD, 13));
    	b3.setBounds(233, 270, 90, 25);
        b3.setBackground(new Color(92, 219, 92));
        b3.setForeground(Color.WHITE);
    	p1.add(b3);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            Conn con = new Conn();
            if(ae.getSource() == b1){
                String sql = "select * from signup where username=?";
                PreparedStatement st = con.c.prepareStatement(sql);

		st.setString(1, t1.getText());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
                    t2.setText(rs.getString("name"));
                    t3.setText(rs.getString("question"));
		}

            }
            if(ae.getSource() == b2){
                String sql = "select * from signup where answer=?";
		PreparedStatement st = con.c.prepareStatement(sql);

		st.setString(1, t4.getText());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
                   t5.setText(rs.getString("password"));
		}

            }
            if(ae.getSource() == b3){
              this.setVisible(false);
		      new Login().setVisible(true);
			
            }
        }catch(Exception e){
            
        }
    }
    
    public static void main(String[] args) {
    	new ForgotPassword().setVisible(true);
        }
}