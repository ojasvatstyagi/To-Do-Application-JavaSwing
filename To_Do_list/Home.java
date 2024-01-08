package To_Do_list;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Home extends JFrame{
    String username;
    public static void main(String[] args) {
        new Home("").setVisible(true);
    }
   
    public Home(String username) {
          super("To-Do-Application");
          setForeground(Color.CYAN);
          setLayout(null); 

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/todobg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1580, 1000,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); 
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(0, 0, 1580, 1000); 
        add(NewLabel);
        
        JLabel l1 = new JLabel("To Do Application");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 55));
        l1.setBounds(500, 60, 1000, 100);
        NewLabel.add(l1);
        		
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
		
        JMenu m1 = new JMenu("PROFILE");
        m1.setForeground(Color.BLUE);
        menuBar.add(m1);
		
        JMenuItem mi1 = new JMenuItem("Login");
        m1.add(mi1);
        
        JMenuItem mi2 = new JMenuItem("Signup");
        m1.add(mi2);
        
        mi1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new Login().setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        mi2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new Signup().setVisible(true);
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
        
        mi13.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    Runtime.getRuntime().exec("notepad.exe");
                }catch(Exception e){ }
            }
        });
        
        
        mi14.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
                try{
                    Runtime.getRuntime().exec("calc.exe");
                }catch(Exception e){ }
            }
        });
        
        JMenu m7 = new JMenu("ABOUT");
        m7.setForeground(Color.BLUE);
        menuBar.add(m7);
        
        JMenuItem mi15 = new JMenuItem("ABOUT");
        m7.add(mi15);
        
        mi15.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new About().setVisible(true);
            }
	});
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }
}