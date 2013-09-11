package koti.todo.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import koti.todo.db.beans.Todo;
import koti.todo.db.tables.TodoManager;

public class GUI extends JFrame implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	public JTextArea mainTextArea = new JTextArea();
	public JTextArea nameTextArea = new JTextArea();
	public JPanel buttons = new JPanel();
	
	
	public GUI() {
		
		super("Todo SQL Test");
		setLayout(new FlowLayout());
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
		
		this.createButton("Show all entries", "all");
		this.createButton("Show sorted list", "singleAll");
		this.createButton("Clear the list", "clear");
		this.createButton("Insert an entry", "insert");
		this.createButton("Update an entry", "update");
		
		
		JPanel output = new JPanel();
		mainTextArea.setLineWrap(true);
		mainTextArea.setRows(30);
		mainTextArea.setWrapStyleWord(true);
		output.add(mainTextArea);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
		
		nameTextArea.setLineWrap(true);
		nameTextArea.setRows(1);
		nameTextArea.setWrapStyleWord(true);
		
		JLabel label = new JLabel("Additional info:");
	    label.setFont(new Font("Serif", Font.PLAIN, 16));
	    labelPanel.add(label);
	    labelPanel.add(nameTextArea);
	    
		add(buttons);

	    add(labelPanel);
		add(output);
		
		
	    
	}

	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getActionCommand().equals("all")){
			String temp = TodoManager.allRows();
			mainTextArea.setText(temp);
		}
		else if (e.getActionCommand().equals("singleAll")){
			String name = nameTextArea.getText();
			String temp = TodoManager.allRows(name);
			mainTextArea.setText(temp);
		}
		else if (e.getActionCommand().equals("clear")){
			String temp = "";
			mainTextArea.setText(temp);
		}
		else if (e.getActionCommand().equals("insert")){
			String temp = nameTextArea.getText();
			String[] temps = temp.split(" ");
			Todo bean = new Todo();
			bean.setName(temps[0]);
			bean.setThing(temps[1]);
			try {
				if (!TodoManager.getBean(bean)) {
					TodoManager.insert(bean);
					mainTextArea.setText("Entry inserted successfully");
				} else {
					mainTextArea.setText("Entry failed.");
				}
				
			} catch (SQLException e1) {
				System.err.println(e1);
			}
		}
		else if (e.getActionCommand().equals("update")){
			String temp = nameTextArea.getText();
			String[] temps = temp.split(" ");
			Todo bean = new Todo();
			bean.setName(temps[0]);
			bean.setThing(temps[1]);
			bean.setDone(Integer.parseInt(temps[2]));
			try {
				if (TodoManager.getBean(bean)) {
					TodoManager.update(bean);
					mainTextArea.setText("Entry updated successfully");
				} else {
					mainTextArea.setText("Update failed.");
				}
				
			} catch (SQLException e1) {
				System.err.println(e1);
			}
		}
		
	}
	
	public void createButton(String text, String action) {
		JButton btn = new JButton(text);
		btn.addActionListener(this);
		btn.setActionCommand(action);
		buttons.add(btn);
		buttons.add(Box.createRigidArea(new Dimension(0,15)));
	}
}
