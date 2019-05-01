import java.awt.GridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Interface
{
	private Depot depot1, depot2;
	
	public static void main(String[] args) {
		Interface intFace = new Interface();
		intFace.run();
	}
	
	public void run(){
		displayMenu();
	}
	
	
	public void displayMenu() { // menu system
		//
		JFrame frame = new JFrame("Alcolworths Supermarkets");
		
		//**************************************************************************************************************************************************************
		// Add depot button
		JButton addDepotB = new JButton("Add depot");
		addDepotB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
	    		String depotSet = null;
		    	if (depot1 == null) {
		    		depotSet = JOptionPane.showInputDialog(frame, "Please enter name for depot 1", "Depot 1 Name", JOptionPane.PLAIN_MESSAGE); // Request input name for depot 1
		    		if (depotSet != null && depotSet.length()>0) { // check if something was entered
		    			depot1 = new Depot(); // create depot1
		    			depot1.setName(depotSet); // name depot1
		    			JOptionPane.showMessageDialog(frame, "Depot "+depot1.getName()+" has been successfully added", "Success", JOptionPane.PLAIN_MESSAGE); // inform that depot was successfully created
		    		}
		    		else if (depotSet != null) // if nothing is entered, inform user
		    			JOptionPane.showMessageDialog(frame, "Nothing entered, depot has not been added", "Error", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	else if (depot1 != null && depot2 == null) { // check if depot1 already exists
		    		depotSet = JOptionPane.showInputDialog(frame, "Please enter name for depot 2", "Depot 2 name", JOptionPane.PLAIN_MESSAGE); // Request input for depot 2
		    		while (depotSet.equalsIgnoreCase(depot1.getName())) {
		    			depotSet = JOptionPane.showInputDialog(frame, "Name already exists, please enter a different name", "Depot 2 Name", JOptionPane.PLAIN_MESSAGE);
		    			if (depotSet == null)
		    				break;
		    				
		    		}
		    		if (depotSet != null && depotSet.length()>0) {
		    			depot2 = new Depot(); // create depot2
		    			depot2.setName(depotSet);
		    			JOptionPane.showMessageDialog(frame, "Depot "+depot2.getName()+" has been successfully added", "Success", JOptionPane.PLAIN_MESSAGE);
		    		}
		    		else if (depotSet != null)
		    			JOptionPane.showMessageDialog(frame, "Nothing entered, depot has not been added", "Error", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	else if (depot1 != null && depot2 != null)
		    		JOptionPane.showMessageDialog(frame, "2 depots already exist", "Error", JOptionPane.PLAIN_MESSAGE);
		    		
		    		
		    	/*if (depot1.getName() == null || depot1.getName().isEmpty()) { // if depot1 is empty, create depot
		    		depot1.setName(JOptionPane.showInputDialog(frame, "Please enter name for depot 1", "Depot 1 Name", JOptionPane.PLAIN_MESSAGE));
		    		if (depot1.getName() != null && !depot1.getName().isEmpty())
		    			JOptionPane.showMessageDialog(frame, "Depot "+depot1.getName()+" has been successfully added", "Success", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	else if (depot2.getName() == null || depot2.getName().isEmpty()) { // if depot2 is empty, create depot
		    		depot2.setName(JOptionPane.showInputDialog(frame, "Please enter name for depot 2", "Depot 2 Name", JOptionPane.PLAIN_MESSAGE));
		    		if (depot2.getName() != null && !depot2.getName().isEmpty()) {
		    			while (depot2.getName().equalsIgnoreCase(depot1.getName())) {
		    				if (depot2.getName() != null && !depot2.getName().isEmpty())// if depot2 name chosen is same as depot1 name, request name again
		    					depot2.setName(JOptionPane.showInputDialog(frame, "Name already exists, please enter a different name", "Depot 2 Name", JOptionPane.PLAIN_MESSAGE));
		    			}
		    		}
		    		if (depot2.getName() != null && !depot2.getName().isEmpty())
		    		JOptionPane.showMessageDialog(frame, "Depot "+depot2.getName()+" has been successfully added", "Success", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	else // else if no spots left, give error
		    		JOptionPane.showMessageDialog(frame, "2 depots already exist", "ERROR", JOptionPane.PLAIN_MESSAGE);*/
		    }
		});
		
		//**************************************************************************************************************************************************************
		// Remove depot
		JButton removeDepotB = new JButton("Remove depot");
		removeDepotB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String removeDepot = null;
		    	if (depot1 != null || depot2 != null) {
		    		removeDepot = JOptionPane.showInputDialog(frame, "Please enter name of depot to remove", "Remove depot", JOptionPane.PLAIN_MESSAGE);
			    	if (depot1 != null && removeDepot != null && removeDepot.length()>0 && removeDepot.equalsIgnoreCase(depot1.getName())) {
			    		JOptionPane.showMessageDialog(frame, "Depot "+depot1.getName()+" has been removed", "DEPOT REMOVED", JOptionPane.PLAIN_MESSAGE);
			    		depot1 = null;
			    		}
				    else if (depot2 != null && removeDepot != null && removeDepot.length()>0 && removeDepot.equalsIgnoreCase(depot2.getName())) {
			    			JOptionPane.showMessageDialog(frame, "Depot "+depot2.getName()+" has been removed", "DEPOT REMOVED", JOptionPane.PLAIN_MESSAGE);
			    			depot2 = null;
			    		}
				    else if (removeDepot != null)
				    		JOptionPane.showMessageDialog(frame, "Depot with that name does not exist", "Error", JOptionPane.PLAIN_MESSAGE);
			    	}
			    	else
			    		JOptionPane.showMessageDialog(frame, "No depots exist", "Error", JOptionPane.PLAIN_MESSAGE);
		    }
		});
		
		//**************************************************************************************************************************************************************
		// Request list of depots
		JButton listOfDepotB = new JButton("Request list of depots");
		listOfDepotB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (depot1 == null && depot2 == null)
		    		JOptionPane.showMessageDialog(frame, "No depots", "List of depots", JOptionPane.PLAIN_MESSAGE);
		    	else if (depot2 == null)
		    		JOptionPane.showMessageDialog(frame, "Depot 1: "+depot1.getName()+" has "+depot1.getProduct()+" products", "List of depots", JOptionPane.PLAIN_MESSAGE);
		    	else if (depot1 == null)
		    		JOptionPane.showMessageDialog(frame, "Depot 2: "+depot2.getName()+" has "+depot2.getProduct()+" products", "List of depots", JOptionPane.PLAIN_MESSAGE);
		    	else if (depot1 != null && depot2 != null) 
		    		JOptionPane.showMessageDialog(frame, "Depot 1: "+depot1.getName()+" has "+depot1.getProduct()+" products\nDepot 2: "+depot2.getName()+" has "+depot2.getProduct()+" products", "List of depots", JOptionPane.PLAIN_MESSAGE);

		    	
		    	
		    	/*if ((depot1.getName() == null || depot1.getName().isEmpty()) && (depot2.getName() == null || depot2.getName().isEmpty()))
		    	JOptionPane.showMessageDialog(frame, "No depots", "List of depots", JOptionPane.PLAIN_MESSAGE);
		    	else if (depot2.getName() == null || depot2.getName().isEmpty())
		    		JOptionPane.showMessageDialog(frame, "1 depot exists\nDepot 1: "+depot1.getName(), "List of depots", JOptionPane.PLAIN_MESSAGE);
		    	else if (depot1.getName() == null || depot1.getName().isEmpty())
		    		JOptionPane.showMessageDialog(frame, "1 depot exists\nDepot 2: "+depot2.getName(), "List of depots", JOptionPane.PLAIN_MESSAGE);
		    	else
		    		JOptionPane.showMessageDialog(frame, "Depot 1: "+depot1.getName()+"\nDepot 2: "+depot2.getName(), "List of depots", JOptionPane.PLAIN_MESSAGE);*/
		    }
		});
		
		//**************************************************************************************************************************************************************
		// Add product
		JButton addProductB = new JButton("Add product");
		addProductB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String nameEntered = null;
		    	if (depot1 != null || depot2 != null) {
		    		nameEntered = JOptionPane.showInputDialog(frame, "Please enter name of product to add", "Add product: Name", JOptionPane.PLAIN_MESSAGE);
		    		if (nameEntered == product1.getName())
		    	}
		    	else
		    		JOptionPane.showMessageDialog(frame, "No depots exist", "Error", JOptionPane.PLAIN_MESSAGE);
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	/*NumberFormat priceFormat = NumberFormat.getInstance();
		    	NumberFormat weightFormat = NumberFormat.getNumberInstance();
		    	NumberFormat quantityFormat = NumberFormat.getNumberInstance();
		    	
		    	JTextField namef = new JTextField();
		    	JFormattedTextField pricef = new JFormattedTextField(priceFormat);
		    	pricef.setValue(null);
		    	JFormattedTextField weightf = new JFormattedTextField(weightFormat);
		    	weightf.setValue(null);
		    	JFormattedTextField quantityf = new JFormattedTextField(quantityFormat);
		    	quantityf.setValue(null);
		    	Object[] form = {
		    	    "Name:", namef,
		    	    "Price: $", pricef,
		    	    "Weight (g): ", weightf,
		    	    "Quantity: ", quantityf
		    	};

		    	int option = JOptionPane.showConfirmDialog(null, form, "Add Product", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		    	if (option == JOptionPane.OK_OPTION) {
		    		
		    		
		    	    
		    	    if (namef.getText().length()<=0 || pricef.getText().length()<=0 || weightf.getText().length()<=0 || quantityf.getText().length()<=0) {
		    	    	JOptionPane.showMessageDialog(frame, "Fill in all fields", "Error", JOptionPane.PLAIN_MESSAGE);
		    	    }
		    	    else if (namef.getText().length()>0 || pricef.getText().length()>0 && Integer.parseInt(pricef.getText())>0 || weightf.getText().length()>0 || quantityf.getText().length()>0) {
		    	    	String nameEntered = namef.getText();
		    	    	float priceEntered = Float.parseFloat(pricef.getText());
		    	    	int weightEntered = Integer.parseInt(weightf.getText());
		    	    	int quantityEntered = Integer.parseInt(quantityf.getText());
		    	    	
		    	    	if (priceEntered < 0 || weightEntered < 0 || quantityEntered < 0)
		    	    		JOptionPane.showMessageDialog(frame, "Values cannot be negative", "Error", JOptionPane.PLAIN_MESSAGE);
		    	    }
		    	}
		    	
		    	 else {
		    	    System.out.println("Login canceled");
		    	 }*/
		    }
		    
		});
		
		//**************************************************************************************************************************************************************
		// Request list of products in depot
		JButton listOfProductsB = new JButton("Request list of products in depot");
		listOfProductsB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("List of products");
		    }
		});
		
		//**************************************************************************************************************************************************************
		// Request product location
		JButton productLocationB = new JButton("Request product location");
		productLocationB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Product location");
		    }
		});
		
		//**************************************************************************************************************************************************************
		// Request total product value
		JButton requestTotalB = new JButton("Request total product value");
		requestTotalB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Request total");
		    }
		});
		
		//**************************************************************************************************************************************************************
		// Quit
		JButton quitB = new JButton("QUIT");
		quitB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new GridLayout(0, 1, 10, 10));
	    frame.add(addDepotB);
	    frame.add(removeDepotB);
	    frame.add(listOfDepotB);
	    frame.add(addProductB);
	    frame.add(listOfProductsB);
	    frame.add(productLocationB);
	    frame.add(requestTotalB);
	    frame.add(quitB);
	    frame.pack();
	    frame.setSize(400,500);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}