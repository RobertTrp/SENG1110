
/*/*************************************************************************************************************
 * Programming assignment 2
 * 
 * 		    Author:	Robert Trpeski
 * Student numbers:	c3244194
 * 			   Lab:	Wednesday 10am-12pm - ES105
 ***************************************************************************************************************/

import java.util.*;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Interface
{
	private static Scanner console = new Scanner(System.in);
	private Depot[] d = new Depot[4];
	private int depotCount = 0;
	
	public static void main(String[] args)
	{
		Interface intFace = new Interface();
		intFace.menu();
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method displays the menu for task selection to the user
	 * option is set to 0 each time the loop starts over
	 * Another while loop is used to wait for user input while option is equal to 0, a check is performed to see if the input is an integer
	 * Main loop will end and program will exit when '9' is entered
	 ***************************************************************************************************************************************************************************/	
	public void menu()
	{
		int option; 																							// Initialise variable for menu selection
		do {
			option = 0;																							// Set option to 0 each time menu runs
			System.out.println("\n********************************************************");
			System.out.println("    ALCOLWORTHS SUPERMARKETS - INVENTORY MANAGEMENT");
			System.out.println("********************************************************\n");
			System.out.println("Add depot					(1)\n");
			System.out.println("Remove depot					(2)\n");
			System.out.println("Request depot list				(3)\n");
			System.out.println("Add product					(4)\n");
			System.out.println("Remove product					(5)\n");
			System.out.println("Request product list				(6)\n");
			System.out.println("Request product location			(7)\n");
			System.out.println("Request total product value			(8)\n");
			System.out.println("Export data to file          			(9)\n");
			System.out.println("Import data from file         	 		(10)\n");
			System.out.println("Exit						(11)\n");
			System.out.println("********************************************************");
			System.out.print("Please enter a number: ");														// Request menu option input from user
			
			while (option == 0)																					// Loop to wait for integer input from user
    		{
    			if(console.hasNextInt())																		// Check if input is an integer
    				option = console.nextInt();																	// If input is an integer, read from console
    	        console.nextLine();																				// Clears any remaining characters from buffer to prevent errors in future reads
    	        System.out.print("\n");
    		}
			
			// Switch for task selection
	        switch(option)
	        {
	            case 1: 
	            	addDepot();
	                break;
	            case 2:
	            	removeDepot();
	            	break;
	            case 3:
	            	listDepot();
	            	break;
	            case 4:
	            	addProduct();
	            	break;
	            case 5:
	            	removeProduct();
	            	break;
	            case 6:
	            	listProduct();
	            	break;
	            case 7:
	            	locateProduct();
	            	break;
	            case 8:
	            	valueProduct();
	            	break;
	            case 9:
	            	exportData();
	            	break;
	            case 10:
	            	importData();
	            	break;
	            case 11: 																						//Exit
	            	System.out.println("Program has terminated");
	            	break;
	            default: System.out.println("Invalid option\n");
	            		 enterContinue();
	        }
	    }
	        while(option!=11);																					// Loop menu until user inputs 9
	}
	
	/*/************************************************************************************************************************************************************************
	 * Add depot
	 * 
	 * A check is performed to see if any depots already exist
	 * 
	 * If the maximum amount of depots are not set
	 * User is requested to input a name for the depot
	 * If a depot with that name already exists, the user is asked to input a different name.
	 ***************************************************************************************************************************************************************************/
	
	public void addDepot()
	{
		String depotEntered = null;
		if (depotCount < 4) {
			System.out.print("Please enter name for depot to be added: ");
			depotEntered = console.nextLine();
			depotEntered = depotEntered.replace(" ", "-");
			System.out.print("\n");
			while (getDepotIndex(depotEntered) >= 0)
			{
				System.out.print("Name already exists. Please enter a different name: ");					// Request the user to input a different depot name
				depotEntered = console.nextLine();																// Read depot name entered from console
				depotEntered = depotEntered.replace(" ", "-");
				System.out.print("\n");
			}
			if (depotEntered != null && depotEntered.length() > 0)
			{
				d[depotCount] = new Depot();
				d[depotCount].setName(depotEntered);
				System.out.println("Depot "+depotEntered+" has been successfully added\n"); 				// Inform the user that depot was successfully created
				depotCount++;
			}
			else if (depotEntered != null && depotEntered.length() == 0)
				System.out.println("Nothing entered. Depot has not been added\n");								// Inform user nothing was entered and a depot has not been created		
		}
		else
			System.out.println("4 depots already exist.\n");
		enterContinue();
	}
	
	public int getDepotIndex(String depotEntered)
	{
		for (int i = 0; i <= depotCount; i++)
		{
			if (d[i] != null && depotEntered.equalsIgnoreCase(d[i].getName()))
				return i;
		}
		return -1;
	}
	
		
	/*/**************************************************************************************************************************************************************
	 * Remove Depot
	 * 
	 * A list of available depots is displayed
	 * 
	 * User is then requested to input the name of the depot they wish to remove.
	 * 
	 * If the name matches an existing depot
	 * 	The method clearProduct is called from the Depot class to clear each product
	 * 	The method clearName is called from the Depot class to clear the depot name
	 * 	The depot is then set to null
	 ****************************************************************************************************************************************************************/
	public void removeDepot()
	{
		if (depotCount > 0)
		{
			String depotEntered = null;
			availableDepots();
			System.out.print("Please enter name for depot to be removed: ");
			depotEntered = console.nextLine();
			depotEntered = depotEntered.replace(" ", "-");
			System.out.print("\n");
			
			
			if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0)
			{
				for (int i = getDepotIndex(depotEntered); i < depotCount-1; i++)
				{
					d[i] = d[i+1];
				}
				System.out.println("Depot "+depotEntered+" has been successfully removed\n"); 				// Inform the user that depot was successfully removed
				d[depotCount-1] = null;
				depotCount--;
			}
			else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) == -1)
				System.out.println("Depot with that name does not exist. Depot has not been removed\n");								// Inform user nothing was entered and a depot has not been removed
			else if (depotEntered != null && depotEntered.length() == 0)
				System.out.println("Nothing entered. Depot has not been removed\n");								// Inform user nothing was entered and a depot has not been removed
		}
		else
			System.out.println("No depots exist");
		enterContinue();		
	}
	
	/*/**************************************************************************************************************************************************************
	 * Request list of depots
	 * 
	 * A check is performed for which depots are null and the result printed
	 ****************************************************************************************************************************************************************/
	public void listDepot()
	{
		if (depotCount > 0)
		{
			for (int i = 0; i < depotCount; i++)
			{																															
				System.out.println("Depot "+d[i].getName()+" has "+d[i].getProduct()+" products");										// Print depot 1 info
			}
		}
		else
			System.out.println("No depots exist");
		System.out.println();
		enterContinue();
	}
		
	/*/**************************************************************************************************************************************************************
	 * Add product
	 * 
	 * A check is performed to see if any depots exist
	 * 
	 * A product name is requested from the user
	 * 
	 * If a product with the same name exists in any depot, the user is
	 * asked which depot they would like to store the product in.
	 * 
	 * If the product already exists in the depot specified, the user
	 * is asked to input a quantity to add to that depot.
	 * 
	 * If the product does not exist in the depot specified, the user
	 * is asked to input a quantity, a new product is then made in the
	 * depot and name, price and weight are copied from the existing
	 * product in the other depot.
	 * 
	 * If a product does not exist in either depot, a new product is
	 * created in that depot and the user is requested to input
	 * quantity, weight and price.
	 * 
	 * If the depot holds 5 products, no more products can be added.
	 ****************************************************************************************************************************************************************/
	public void addProduct()
	{
		String productEntered = null;																						// Initialise variable for product name input
		String depotEntered = null;
		if (depotCount > 0)
		{
			System.out.print("Please enter name of product to add: ");											// Request user to input product name to be added
			productEntered = console.nextLine();																	// Read product name input from console
			productEntered = productEntered.replace(" ", "-");
			System.out.print("\n");
			int[] productLocation = checkProductInDepot(productEntered);
			if (productEntered != null && productEntered.length() > 0 && productLocation[2] > 0)
			{
				System.out.println("Product with that name already exists in "+checkProductInDepot(productEntered)[2]+" depots.\n");
			}
			availableDepots();																						// Display available depots to add to
			System.out.print("Please enter depot name for product to be stored: ");									// Request input for depot name
			depotEntered = console.nextLine();																			// Read depot name from console
			depotEntered = depotEntered.replace(" ", "-");
			System.out.print("\n");
			int i = getDepotIndex(depotEntered);
			if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0)
			{
				int productIndex = -1;
				for (int j = 0; j < d[i].getProduct(); j++)
				{
					
					if (d[i].getProduct() > 0 && d[i].getProductName(j) != null && d[i].getProductName(j).equalsIgnoreCase(productEntered))
					{
						productIndex = j;
					}
				}
				if (productIndex >= 0)
				{
					System.out.println("Current quantity of "+d[i].getProductName(productIndex)+" in depot "+d[i].getName()+": "+d[i].getProductQuantity(productIndex)+"\n");	// Print product info with current quantity
					d[i].setProductQuantity(d[i].getProductQuantity(productIndex)+addQuantity(), productIndex);
					System.out.println("New quantity of "+d[i].getProductName(productIndex)+" in depot "+d[i].getName()+": "+d[i].getProductQuantity(productIndex)+"\n");		// Print product info with new quantity
				}
				else if (d[i].getProduct() < 5 && productLocation[2] > 0)
				{
					int j = productLocation[0]; // depot index location of existing product
					int k = productLocation[1]; // product index location of existing product
					d[i].setProduct(d[j].getProductName(k), addQuantity(), d[j].getProductWeight(k), d[j].getProductPrice(k));
					System.out.println("Product "+d[j].getProductName(k)+" successfully added to depot "+d[i].getName()+"\n");
				}
				else if (d[i].getProduct() < 5 && productLocation[2] == 0)
				{
					d[i].setProduct(productEntered, addQuantity(), productWeight(), productPrice());
					System.out.println("Product "+productEntered+" successfully added to depot "+d[i].getName()+"\n");				// Inform user product has been successfully added
				}
				else
					System.out.println("Depot "+d[i].getName()+" is full. Please remove a product to add another product.\n");	// Inform user depot is full
			}
			else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) == -1)
				System.out.print("A depot with that name does not exist. No product has been added.");
			else if (depotEntered != null && depotEntered.length() == 0)
				System.out.print("Nothing entered. Nothing has been added");	
		}
		else
			System.out.println("No depots exist");
		System.out.println();
		enterContinue();
	}
	
	public int[] checkProductInDepot(String productEntered)
	{
		int[] location = {-1,-1, 0};
		if (depotCount > 0)
		{
			for (int i = 0; i < depotCount; i++)
			{
				for (int j = 0; j < d[i].getProduct(); j++)
				{
					if (d[i].getProduct() > 0 && d[i].getProductName(j) != null && d[i].getProductName(j).equalsIgnoreCase(productEntered))
					{
							location[0] = i;
							location[1] = j;
							location[2]++;
					}
				}
			}
		}
		return location;
	}
	
	
	/*/**************************************************************************************************************************************************************
	 * Remove a product
	 * 
	 * A check is performed to see if any depots exist
	 * 
	 * A product name is requested from the user
	 * 
	 * If a product with the same name already exists in any
	 * available depots, the user is informed of the product info and
	 * which depot the product is stored in, the method
	 * 'removeFromDepot()' is called and the product name,
	 * depotName the product is stored in and the product number are
	 * passed as arguments to the method.
	 ****************************************************************************************************************************************************************/
	public void removeProduct()
	{
		String productEntered = null;																						// Initialise variable for product name input
		String depotEntered = null;
		int removeQuantity = 0;
		if (depotCount > 0)																	// Check if either depot 1 or depot 2 exist
		{
			System.out.print("Please enter name of product to remove: ");											// Request user to input product name to be added
			productEntered = console.nextLine();																	// Read product name input from console
			productEntered = productEntered.replace(" ", "-");
			System.out.print("\n");
			int[] productLocation = checkProductInDepot(productEntered);
			if (productEntered != null && productEntered.length() > 0 && productLocation[2] > 0)
			{			
				System.out.println("Product "+d[productLocation[0]].getProductName(productLocation[1])+" exists in the following depots:");				// Display available depots to add to
				for (int i = 0; i < depotCount; i++)
				{
					for (int j = 0; j < d[i].getProduct(); j++)
					{
						if (d[i].getProduct() > 0 && d[i].getProductName(j) != null && d[i].getProductName(j).equalsIgnoreCase(productEntered))
						{
							System.out.println(d[i].getName()+" with quantity "+d[i].getProductQuantity(j));
						}
					}
				}
				System.out.println();
				System.out.print("Please enter depot name for product to be removed from: ");									// Request input for depot name
				depotEntered = console.nextLine();																			// Read depot name from console
				depotEntered = depotEntered.replace(" ", "-");
				System.out.print("\n");
				int i = getDepotIndex(depotEntered);
				if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0)
				{
					int productIndex = -1;
					for (int j = 0; j < d[i].getProduct(); j++)
					{
						if (d[i].getProduct() > 0 && d[i].getProductName(j) != null && d[i].getProductName(j).equalsIgnoreCase(productEntered))
						{
							productIndex = j;
						}
					}
					if (productIndex >= 0)
					{
						do																										// Loop to keep asking for input if number is negative
						{
							System.out.print("Please enter quantity to be removed: ");											// Request input for quantity to be added
							if (console.hasNextInt())																			// Check if input is an integer
							{
								removeQuantity = console.nextInt();															// If input is an integer, assign to variable productQuantity
								console.nextLine();																			// Clears any extra characters from buffer
								System.out.print("\n");
								if (removeQuantity <= 0)																		// If the entered number is negative or zero
									System.out.println("Invalid input. Please enter a positive number.\n");						// Inform user invalid input and ask for another number
							}
						}
						while (removeQuantity <= 0);																			// Exit loop if quantity is greater than zero
						
						if (removeQuantity > d[i].getProductQuantity(productIndex))
							System.out.println("Invalid input, quantity selected exceeds available quantity. Nothing has been removed\n");
						else if (removeQuantity < d[i].getProductQuantity(productIndex))
						{
							System.out.println(removeQuantity+" items of product "+d[i].getProductName(productIndex)+" have been removed from depot "+d[i].getName()+"\n");
							d[i].setProductQuantity(d[i].getProductQuantity(productIndex)-removeQuantity, productIndex);
						}
						else if (removeQuantity == d[i].getProductQuantity(productIndex))
						{
							System.out.println("All items of product "+d[i].getProductName(productIndex)+" have been removed from depot "+d[i].getName()+" and the product has been removed.\n");
							d[i].clearProduct(productIndex);
						}
					}
				}		
				else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) == -1)
					System.out.println("Depot with that name does not exist. No product has been removed.\n");
				else if (depotEntered != null && depotEntered.length() == 0)
					System.out.println("Nothing entered. No product has been removed\n");						
			}
			else if (productEntered != null && productEntered.length() > 0 && productLocation[2] == 0)
				System.out.println("Product with that name does not exist. Nothing has been removed\n");
			else if (productEntered != null && productEntered.length() == 0)
				System.out.println("Nothing entered. No product has been removed\n");
		}
		else
			System.out.println("No depots exist\n");
		enterContinue();
	}
	
	/*/****************************************************************
	 * Remove product from depot
	 * 
	 * User is requested to input the name of the depot they wish to
	 * remove the product from.
	 * 
	 * A check is performed to see if the depot name matches that of
	 * the one passed down as an argument from where the product is
	 * stored.
	 * 
	 * If it matches and the quantity of the product is greater than 1
	 * a single item is removed from the product.
	 * 
	 * If the quantity is 1, the clearProduct method is called from
	 * the Depot class to clear the product.
	 ******************************************************************/

	/*/**************************************************************************************************************************************************************
	 * Request a list of products
	 * 
	 * A check is performed to see if any depots exist.
	 * A list of available depots is displayed to the user.
	 * A depot name is requested to be input by the user.
	 * 
	 * A loop is used to check the name of each product in the specified depot.
	 * If the name of the product is not null (meaning a product exists), the product info is displayed to the user.
	 * 
	 * If no products exist in the depot, the user is informed.
	 * 
	 ****************************************************************************************************************************************************************/
	public void listProduct()
	{				
		String depotEntered = null;
		if (depotCount > 0)																	
		{
			availableDepots();																					// Call method to display available depots
			System.out.print("Please enter name of a depot: ");
			depotEntered = console.nextLine();																		
			depotEntered = depotEntered.replace(" ", "-");
			System.out.print("\n");
			int i = getDepotIndex(depotEntered);
			if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0 && d[i].getProduct() > 0)
			{
				for (int j = 0; j < d[i].getProduct(); j++)
				{
					if (d[i].getProduct() > 0 && d[i].getProductName(j) != null)
						System.out.println("Product "+d[i].getProductName(j)+" has price: $"+String.format("%.2f", d[i].getProductPrice(j))+", weight: "+d[i].getProductWeight(j)+"kg, and quantity: "+d[i].getProductQuantity(j)); // Print product info
				}
				System.out.println();
			}
			else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0 && d[i].getProduct() == 0)
				System.out.println("No products exist in depot "+d[i].getName()+"\n");
			else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) == -1)
				System.out.println("Depot with that name does not exist.\n");
			else if (depotEntered != null && depotEntered.length() == 0)
				System.out.println("Nothing entered.\n");
		}
		else
			System.out.println("No depots exist\n");															// If no depots exist, inform user
		enterContinue();																						// Call method to request user to press enter to continue
	}
	
	/*/**************************************************************************************************************************************************************
	 * Request a list of products
	 * 
	 * A check is performed to see if any depots exist.
	 * A product name is requested to be input by the user.
	 * 
	 * If a product with the same name exists in any depot, the product info and depot location is displayed to the user.
	 * 
	 * If a product does not exist in either depot, the user is informed.
	 ****************************************************************************************************************************************************************/
	public void locateProduct()
	{
		if (depotCount > 0)																	// Check if either depot 1 or depot 2 exists
		{
			String productEntered = null;																			// Initialise variable for product name input
			System.out.print("Please enter name of product to locate: ");										// Request user to input name of product to locate
			productEntered = console.nextLine();																	// Read product name from console
			productEntered = productEntered.replace(" ", "-");
			System.out.print("\n");
			int[] productLocation = checkProductInDepot(productEntered);
			if (productEntered !=null && productEntered.length() > 0 && productLocation[2] > 0)													// Check if something was entered
			{
				for (int i = 0; i < depotCount; i++)
				{
					for (int j = 0; j < d[i].getProduct(); j++)
					{
						if (d[i].getProduct() > 0 && d[i].getProductName(j) != null && d[i].getProductName(j).equalsIgnoreCase(productEntered))
							System.out.println("Product "+d[i].getProductName(j)+" is in depot "+d[i].getName()+" with quantity "+d[i].getProductQuantity(j)+"\n");
					}
				}
			}
			else if (productEntered != null && productEntered.length() > 0 && productLocation[2] == 0)
				System.out.println("Product with that name does not exist.\n");																				// Call method to request user to press enter to continue
			else
				System.out.println("Nothing entered.\n");
		}
		else																									// If no depots exist, inform user
		System.out.println("No depots exist\n");
	enterContinue();		
	}
		
	/*/**************************************************************************************************************************************************************
	 * Request a list of products
	 * 
	 * A check is performed to see if any depots exist.
	 * A list of available depots is displayed to the user.
	 * A depot name is requested to be input by the user.
	 * 
	 * A loop is used to check the name of each product in the specified depot.
	 * If the name of the product is not null (meaning a product exists), the product price is multiplied by the product quantity and added to the cumulative total.
	 * 
	 * If no products exist in the depot, the user is informed.
	 * 
	 ****************************************************************************************************************************************************************/	
	public void valueProduct()
	{
		String productEntered = null, depotEntered = null;															// Initialise variable for product name and depot name input
		double totalProductPrice = 0;																			// Initialise variable for total product price
		
		if (depotCount > 0)																						// Check if either depot 1 or depot 2 exists
		{
			availableDepots();																					// Call method to display available depots
			System.out.print("Please enter name of a depot: ");													// Request user to input name of depot
			depotEntered = console.nextLine();																		// Read depot name from console
			depotEntered = depotEntered.replace(" ", "-");
			System.out.print("\n");
			int i = getDepotIndex(depotEntered);
			if(depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0 && d[i].getProduct() > 0)																	// Check if depot 1 has any products in it
			{
				for(int j = 0; j <= d[i].getProduct(); j++)																// Loop to check each product in depot
				{
					productEntered = d[i].getProductName(j);												// Read product name from product in depot
					if (productEntered != null)															// If product name is not null (meaning the product exists)
					{
						totalProductPrice += (d[i].getProductPrice(j)*d[i].getProductQuantity(j));	// Add product price to total product price
					}	
				}
				System.out.println("Total cumulative value of products in depot "+d[i].getName()+": $"+String.format("%.2f", totalProductPrice)+"\n"); // Print total product value
			}
			else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) >= 0 && d[i].getProduct() == 0)
				System.out.println("No products exist in depot "+d[i].getName()+"\n"); 				
			else if (depotEntered != null && depotEntered.length() > 0 && getDepotIndex(depotEntered) == -1)
				System.out.println("Depot with that name does not exist.\n");
			else if (depotEntered != null && depotEntered.length() == 0)
				System.out.println("Nothing entered.\n");
				
		}
		else
			System.out.println("No depots exist\n");															// If no depots exist, inform user
		enterContinue();																						// Call method to request user to press enter to continue	
	}
	
	public void exportData()
	{
		String fileName = "out.txt";
		PrintWriter outputStream;
		
		try
		{
			outputStream = new PrintWriter(fileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println ("Error opening the file "+fileName+"\n");
			enterContinue();
			return;
		}
		
		if (depotCount > 0)
		{
			for (int i = 0; i < depotCount; i++)
			{
				if (d[i].getProduct() > 0)
				{
					for (int j = 0; j < d[i].getProduct(); j++)
					{
						if (d[i].getProduct() > 0 && d[i].getProductName(j) != null)
						{
							outputStream.println(d[i].getName()+" "+d[i].getProductName(j)+" "+d[i].getProductPrice(j)+" "+d[i].getProductWeight(j)+" "+d[i].getProductQuantity(j));
						}
					}
				}
				else
				{
					outputStream.println(d[i].getName());
				}
			}
		}
		outputStream.close();
		System.out.println("Data exported to "+fileName+" successfully.\n");
		enterContinue();
	}
	
	public void importData()
	{
		
	}
	
	/*/**************************************************************************************************************************************************************
	 * Enter to continue
	 * 
	 * Used as a pause in code to allow user to read what has happened before menu is showed again
	 ****************************************************************************************************************************************************************/
	private void enterContinue()
	{
		System.out.print("Press enter key to continue\n");														// Request user to press key to continue
		console.nextLine();																						// Read any input from console
		System.out.print("\n");
	}

	
	/*/**************************************************************************************************************************************************************
	 * Add quantity
	 * 
	 * Requests input for product quantity, not allowing a negative input
	 ****************************************************************************************************************************************************************/
	public int addQuantity()
	{
		int productQuantity = 0;																				// Initialise variable for product quantity
		do																										// Loop to keep asking for input if number is negative
		{
			System.out.print("Please enter quantity to be added: ");											// Request input for quantity to be added
			if (console.hasNextInt())																			// Check if input is an integer
			{
				productQuantity = console.nextInt();															// If input is an integer, assign to variable productQuantity
				console.nextLine();																			// Clears any extra characters from buffer
				System.out.print("\n");
				if (productQuantity <= 0)																		// If the entered number is negative or zero
					System.out.println("Invalid input. Please enter a positive number.\n");						// Inform user invalid input and ask for another number
			}
		}
		while (productQuantity <= 0);																			// Exit loop if quantity is greater than zero
		return productQuantity;																					// Return quantity added
	}
	
	/*/**************************************************************************************************************************************************************
	 * Product weight
	 *
	 * Requests input for roduct weight, not allowing a negative input
	 ****************************************************************************************************************************************************************/
	public double productWeight()
	{
		double productWeight = 0;																				// Initialise variable for product weight
		do																										// Loop to keep asking for input if number is negative
		{
			System.out.print("Please enter weight of product (kg): ");											// Request input for product weight
			if (console.hasNextDouble())																		// Check if input is a double
			{
				productWeight = console.nextDouble();															// If input is a double, assign to variable productWeight
				console.nextLine();																				// Clears any extra characters from buffer
				System.out.print("\n");
				if (productWeight <= 0)																			// If the entered number is negative or zero
					System.out.println("Invalid input. Please enter a positive number.\n");						// Inform user invalid input and ask for another number
			}
		}
		while (productWeight <= 0);																				// Exit loop if weight is positive
		return productWeight;																					// Return product weight
	}

	/*/**************************************************************************************************************************************************************
	 * Product Price
	 * 
	 * Requests input for product price, not allowing a negative input
	 ****************************************************************************************************************************************************************/
	public double productPrice()
	{
		double productPrice = 0.0;																				// Initialise variable for product price
		do																										// Loop to keep asking for input if number is negative
		{
			System.out.print("Please enter price of product: $");												// Request input for product price
			if (console.hasNextDouble())																		// Check in input is a double
			{
				productPrice = console.nextDouble();															// If input is a double, assign to variable productPrice
				console.nextLine();																				// Clears any extra characters from buffer
				System.out.print("\n");
				if (productPrice <= 0)																			// If the entered number is negative or zero
					System.out.println("Invalid input. Please enter a positive number.\n");						// Inform user invalid input and ask for another number
			}
		}
		while (productPrice <= 0);																				// Exit loop if price is positive
		return productPrice;																					// Return product price
	}	
	
	/*/**************************************************************************************************************************************************************
	 * Available depots
	 * 
	 * Lists the available depots
	 ****************************************************************************************************************************************************************/
	public void availableDepots()
	{
		if (depotCount != 0)
		{
			System.out.println("Available depots:");
			for (int i = 0; i < depotCount; i++)
			{																															
				System.out.println(d[i].getName());
			}
			System.out.println();
		}
	}
}