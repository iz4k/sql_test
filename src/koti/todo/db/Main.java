package koti.todo.db;

import java.sql.*;

import javax.swing.JFrame;

import koti.todo.db.beans.Todo;
import koti.todo.db.tables.TodoManager;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public static void doStuff() throws SQLException {
		
		System.out.println("\nHere all rows!\n");
		TodoManager.allRows();
		Todo bean  = new Todo();
		/*
		System.out.println("\nHere new entry!\n");
		
		bean.setName(InputHelper.getInput("Enter your name: "));
		bean.setThing(InputHelper.getInput("Enter stuff to do: "));
		bean.setDone(0);
		TodoManager.insert(bean);
		
		System.out.println("\nHere all rows from user: x");
		String inName = InputHelper.getInput("what nameeee: ");
		TodoManager.allRows(inName);
		*/
		System.out.println("\nHere update a row of your choice");
		bean.setName(GetInput.getInput("Enter your name: "));
		bean.setThing(GetInput.getInput("Enter stuff to do: "));
		
		//bean = TodoManager.getBean(bean.getName(), bean.getThing());
		System.out.println("NAME: " + bean.getName() + "  THING: " + bean.getThing() + "  DONE: " + bean.getDone());
		bean.setDone(Integer.parseInt(GetInput.getInput("Enter your done status: (0 or 1)")));
		TodoManager.update(bean);
		
		
	}
}
