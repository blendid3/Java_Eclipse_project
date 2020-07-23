package Editor;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.*;
import myTest.Main;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.*; 

import java.util.ArrayList;
import java.util.List;
import java.io.*; 
import java.lang.*; 
import java.util.*; 
import java.lang.Object.*;
import java.util.stream.Collectors;
public class myEditor extends JFrame  implements ActionListener, Runnable{
	JPanel catalog_panel=new JPanel();JPanel Text_panel=new JPanel();
	CardLayout mycard=new CardLayout(); JTextArea input_text=new JTextArea(20,50);
	JTextArea compile_text=new JTextArea(20,50); JTextArea docs_text=new JTextArea(20,50);
	JButton code_enter=new JButton("code enter");JButton compile_result=new JButton("compile result");JButton exe_result=new JButton("executing result");
	JTextField file_name=new JTextField("");JButton compile_program=new JButton("compiling program");JTextField class_name=new JTextField(""); 
	JButton run_program=new JButton("running program"); Thread compiler; Thread run;
	myEditor(){
		this.setLayout(new BorderLayout());
		this.add(getCatalogPanel(),BorderLayout.NORTH);
		this.add(getTextPanel(),BorderLayout.CENTER);
		
	}
	
	private JPanel getCatalogPanel() {
		catalog_panel.setLayout(new GridLayout(3,3,10,10));
		catalog_panel.setForeground(Color.gray);
		catalog_panel.add(code_enter);catalog_panel.add(compile_result);catalog_panel.add(exe_result);
		catalog_panel.add(new JLabel("Please enter the file name: [.java] "));catalog_panel.add(file_name);catalog_panel.add(compile_program);
		catalog_panel.add(new JLabel("Please enter the Maiun class name"));catalog_panel.add(class_name);catalog_panel.add(run_program);
		//--- add action
		code_enter.addActionListener(this);compile_result.addActionListener(this);
		exe_result.addActionListener(this);
		compile_program.addActionListener(this);run_program.addActionListener(this);
		
		return catalog_panel;
	}
	
	private JPanel getTextPanel() {
		Text_panel.setLayout(mycard);
		Text_panel.add("input",input_text);Text_panel.add("compile",compile_text);Text_panel.add("docs",docs_text);
		return Text_panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str=((JButton)e.getSource()).getText();
		if(str.equals("code enter")) {
			mycard.show(Text_panel, "input");
		} else if(str.equals("compile result")) {
			mycard.show(Text_panel, "compile");
		} else if(str.equals("executing result")) {
			mycard.show(Text_panel, "docs"); // running result
		} else if(str.equals("compiling program")) {
			if(!compiler.isAlive()) {
				compiler=new Thread(this);
			}
			try{
				compiler.start();
			}
			catch (Exception e2){
				e2.printStackTrace();
			}
			mycard.show(Text_panel, "compile");
			
		} else if(str.equals("running program")) {
			if(!run.isAlive()) {
				run=new Thread(this);
			}
			try{
				compiler.start();
			}
			catch (Exception e2){
				e2.printStackTrace();
			}
			mycard.show(Text_panel, "docs");
		} 
	}
	public void run() {
		
	}
	
    public static void main(String[] args){
        myEditor ed1=new myEditor();
        ed1.pack();
//        ed1.setBounds(200, 180,550,360);
        ed1.setVisible(true);
    }
}
