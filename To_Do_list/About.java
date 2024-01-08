package To_Do_list;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Font;
import javax.swing.JFrame;

public class About extends JFrame implements ActionListener {
		
		    JButton b1;
		    JLabel l1;
		    Font f, f1, f2;
		    TextArea t1;
		    String s;

		    public About() {

		        setLayout(null);
		        JButton b1 = new JButton("Exit");
		        add(b1);
		        b1.setBounds(180, 430, 120, 20);
		        b1.addActionListener(this);

		        Font f = new Font("RALEWAY", Font.BOLD, 180);
		        setFont(f);

		         s = "                                     *********       \n"
		            +"1) User Authentication: Implemented a robust user authentication system allowing users to sign up securely, log in, and protect their information with a password.\n"

					+"2) User Interface Design: Created an intuitive and visually appealing user interface using Java Swing, with well-organized panels, input fields, buttons, and labels.\n"

					+"3) Account Creation: Provided a user-friendly signup process enabling users to register by entering their desired username, name, password, security question, and answer.\n"

					+"4) Database Integration: Integrated a MySQL database to store user account details securely, utilizing JDBC for database connectivity and prepared statements for secure data manipulation.\n"

     				+"5) Data Validation: Implemented validation checks to ensure that the entered data is valid and meets specified criteria before storing it in the database, preventing potential errors.\n"

					+"6) Task Management: Enabled users to create, modify, and delete tasks in their to-do list, allowing for effective task management.\n"

					+"7) Responsive UI Elements: Ensured that UI components, such as buttons and input fields, are responsive to user interactions and provide appropriate feedback.\n"

					+"8) Error Handling: Implemented error handling mechanisms to catch and appropriately handle exceptions that may occur during database operations or user interactions, providing users with meaningful error messages.\n"

					+"9) Image Handling: Allowed users to associate or personalize their accounts with images, handling image resources appropriately and displaying them in the application's UI.\n"

					+"10) User Experience: Strived to enhance the overall user experience by focusing on usability, responsiveness, and providing features that facilitate easy task management and navigation within the application.\n"
		                ;

		        TextArea t1 = new TextArea(s, 10, 40, Scrollbar.VERTICAL);
		        t1.setEditable(false);
		        t1.setBounds(20, 100, 450, 300);

		        add(t1);

		        Font f1 = new Font("RALEWAY", Font.BOLD, 16);
		        t1.setFont(f1);

		        this.getContentPane();
		        t1 = new TextArea();

		        JLabel l1 = new JLabel("About Project");
		        add(l1);
		        l1.setBounds(170, 10, 180, 80);
		        l1.setForeground(Color.red);

		        Font f2 = new Font("RALEWAY", Font.BOLD, 20);
		        l1.setFont(f2);

		        setBounds(700, 220, 500, 550);

		        setLayout(null);
		        setVisible(true);
		        
		    }

		    public void actionPerformed(ActionEvent e) {
		        dispose();
		    }

		    public static void main(String args[]) {
		        new About().setVisible(true);
		    }

	}
