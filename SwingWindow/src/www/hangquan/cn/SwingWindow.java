package www.hangquan.cn;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
public class SwingWindow extends JFrame {
	private JLabel myLabel;
	public SwingWindow(){
		super();
		this.setSize(400,300);
		this.getContentPane().setLayout(null);
		this.setTitle("My First Swing Windows");
		this.add(JLabelConstr());
		this.add(getJTextField());
		this.add(getJButton());
		this.add(getCheckBox());
		this.add(getJMenuBarConstr());
//		this.add();
	}
	
	private JLabel JLabelConstr() {
		
		
		if(myLabel==null) {
			myLabel=new JLabel();
			myLabel.setBounds(5, 10, 250, 30);
			myLabel.setText("Hello World");
			myLabel.setForeground(Color.blue);
			myLabel.setSize(100,30);
		}
		
		return myLabel;
	}
	private JTextField myTextField;
	private JTextField getJTextField() {
		myTextField=new JTextField();
		myTextField.setBounds(5,45,200,30);
		myTextField.setText("Shi Yan Lou");
		return myTextField;
	}
	private JCheckBox myCheckBox;
	private JCheckBox getCheckBox() {
		myCheckBox=new JCheckBox();
		myCheckBox.setBounds(5,130,150,20);
		myCheckBox.setText("This is the TextBox");
		return myCheckBox;
	}
	
	
	private JButton getJButton() {
		JButton myButton=new JButton();
		myButton.setBounds(5,80,100,40);
		myButton.setText("click me");

		myButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				myLabel.setForeground(Color.RED);
				myTextField.setBackground(Color.BLUE);
			}
		});
		return myButton;
	}
	
	private JMenuBar myMenuBar;
	private JMenuBar getJMenuBarConstr() {
		myMenuBar=new JMenuBar();
		myMenuBar.setBounds(5, 150, 100, 20);
        JMenu x = new JMenu("Menu"); 
        JMenu x1 = new JMenu("submenu");
        JMenuItem m1 = new JMenuItem("MenuItem1"); 
        JMenuItem m2 = new JMenuItem("MenuItem2"); 
        JMenuItem m3 = new JMenuItem("MenuItem3"); 
        JMenuItem s1 = new JMenuItem("SubMenuItem1"); 
        JMenuItem s2 = new JMenuItem("SubMenuItem2"); 

        
        
        // add menu items to menu 
        x.add(m1); 
        x.add(m2); 
        x.add(m3); 
        x1.add(s1); 
        x1.add(s2); 
        // add submenu 
        x.add(x1); 
  
        // add menu to menu bar 
        myMenuBar.add(x); 
  
		return myMenuBar;
	}
	
	public static void main(String[] args) {
		
		SwingWindow s1=new SwingWindow();
		s1.setVisible(true);
	}
}
