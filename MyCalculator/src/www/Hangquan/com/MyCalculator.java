package www.Hangquan.com;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
//import java.awt.*;


import javax.swing.*;
import java.awt.event.*; 

import java.util.ArrayList;
import java.util.List;
import java.io.*; 
import java.lang.*; 
import java.util.*; 
import java.lang.Object.*;
import java.util.stream.Collectors;

public class MyCalculator extends JFrame {
	private JTextField entry=new JTextField("0",20);
	private JButton clear_button=new JButton("clear");
	private JButton num1=new JButton("1");private JButton num2=new JButton("2");
	private JButton num3=new JButton("3");private JButton num4=new JButton("4");
	private JButton num5=new JButton("5");private JButton num6=new JButton("6");
	private JButton num7=new JButton("7");private JButton num8=new JButton("8");
	private JButton num9=new JButton("9");private JButton num0=new JButton("0");
	private JButton opt_mul=new JButton("*");private JButton opt_div=new JButton("/");
	private JButton opt_add=new JButton("+");private JButton opt_minus=new JButton("-");
	private JButton opt_equ=new JButton("=");private JButton opt_dot=new JButton(".");
	private JPanel enter_panel=new JPanel(); private JPanel number_panel=new JPanel();
	
	public MyCalculator(){
		//UI Part--------------------------------------//
		this.setLocation(300, 200);

		this.setResizable(false);
		this.setTitle("Calculator");
		this.setLayout(new BorderLayout());
//		this.getContentPane().setLayout(new BorderLayout());
		
		this.add(getNumberPanel(),BorderLayout.CENTER);
		this.add(getEnterPanel(),BorderLayout.NORTH);
		//--------------------------------------------------------------//
		//----action listener part
		EquListener equ_list=new EquListener();opt_equ.addActionListener(equ_list);
		ClrListener clr_list=new ClrListener();clear_button.addActionListener(clr_list); 
		NumListener num_list=new NumListener();num1.addActionListener(num_list);num2.addActionListener(num_list);
		num3.addActionListener(num_list);num4.addActionListener(num_list);num5.addActionListener(num_list);
		num6.addActionListener(num_list);num7.addActionListener(num_list);num8.addActionListener(num_list);
		num9.addActionListener(num_list);num0.addActionListener(num_list);opt_dot.addActionListener(num_list);
		OptListener opt=new OptListener();opt_mul.addActionListener(opt); opt_add.addActionListener(opt);
		opt_minus.addActionListener(opt);opt_div.addActionListener(opt);
		

		
	}
	// Panel part----------------------------------------------
	public JPanel getNumberPanel() {
		number_panel.setLayout(new GridLayout(4,4,5,5));
		number_panel.setForeground(Color.blue);
		number_panel.add(num7);number_panel.add(num8);number_panel.add(num9);number_panel.add(opt_div);
		number_panel.add(num4);number_panel.add(num5);number_panel.add(num6);number_panel.add(opt_mul);
		number_panel.add(num3);number_panel.add(num2);number_panel.add(num1);number_panel.add(opt_minus);
		number_panel.add(num0);number_panel.add(opt_dot);number_panel.add(opt_equ);number_panel.add(opt_add);
		return number_panel;
	}
	public JPanel getEnterPanel() {
		enter_panel.setLayout(new BorderLayout());
		enter_panel.add(entry,BorderLayout.WEST);enter_panel.add(clear_button,BorderLayout.EAST);
		return enter_panel;
	}
	// Action Listener part--------------------------------------------------
	
	// Variable setting------------------
//	String str1="0";String str2="0";
	List<String> enter_num=new ArrayList<String>(); String cur_str=new String("");
	String total_str=new String("");
	List<String> opts=new ArrayList<String>(); 
	int key_clear=0;
	String result="";JButton store;
	public void init() {
		enter_num.clear();cur_str="";total_str=""; opts.clear(); result="";
	}
	class NumListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	String ss=((JButton)e.getSource()).getText();
	    	store=(JButton)e.getSource();
	    	cur_str+=ss;
	    	total_str+=ss;
	    	entry.setText(total_str);
	    }
	}
	class OptListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	String ss=((JButton)e.getSource()).getText();
	    	if(cur_str.length()==0) { 
	    		opts.add(ss);total_str+=ss;
		    	entry.setText(total_str);return;
	    	}
	    	enter_num.add(cur_str);opts.add(ss);cur_str="";
	    	total_str+=ss;
	    	entry.setText(total_str);
	    }
	}
	
	class ClrListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	init();entry.setText("0");
	    }
	}
	

	// when only enter the opts, then it will printout "Error", then clear
	// when enter the opts but there is no number keeping on, then the opt size ==num size clear and return
	// there is no probability of entering to many numbers
	// 
	
	class EquListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	double res=0;
	    	if(cur_str.length()!=0)
	    		enter_num.add(cur_str);
	    	
			if(enter_num.size()==0&&opts.size()==0) {init();return;} 
			if(opts.size()>=enter_num.size()) {init();entry.setText("Enter the opts Wrongly"); return ;}
			List<Double> l1=new ArrayList<Double>();
			try {
				for(int i=0;i<enter_num.size();i++) {
					double val=Double.valueOf(enter_num.get(i));l1.add(val);
				}
			} catch(Exception err){
				System.out.println(err.getMessage()); entry.setText("Enter the number wrongly");init();return;
			}
    		//-- handle */
	    	for(int i=0;i<opts.size();i++) {
	    		if(opts.get(i).equals("*")) {
	    			double val=l1.get(i)*l1.get(i+1);
	    			enter_num.set(i,String.valueOf(val) );enter_num.remove(i+1); opts.remove(i);
	    		} else 	if(opts.get(i).equals("/")) {
	    			if(l1.get(i+1)==0) {init();entry.setText("infinite");}
	    			double val=l1.get(i)/l1.get(i+1);
	    			enter_num.set(i,String.valueOf(val) );enter_num.remove(i+1); opts.remove(i);
	    		}
	    	}
	    	l1.clear();
			for(int i=0;i<enter_num.size();i++) {
				double val=Double.valueOf(enter_num.get(i));l1.add(val);
			}
	    	res+=l1.get(0);
	    	for(int i=0;i<opts.size();i++) {
	    		if(opts.get(i).equals("+")) {
	    			res+=l1.get(i+1);
	    		} else if(opts.get(i).equals("-")) {
	    			res-=l1.get(i+1);
	    		}
	    	}
	    	entry.setText(String.valueOf(res));
	    	init();
	    }
	}
	
	
	public static void main(String[] args) {
		MyCalculator c1=new MyCalculator();
		c1.pack();
		c1.setVisible(true);
		
	}
	
}
