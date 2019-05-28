

/*/*************************************************************************************************************
 * Programming assignment 1
 * 
 * 		   Authors: Kate McAlpine and Robert Trpeski
 * Student numbers:	   c3078083		     c3244194
 * 			   Lab:	Wednesday 10am-12pm - ES105
 ***************************************************************************************************************/

import java.util.*;

public class Interface
{
	private static Scanner console = new Scanner(System.in);
	private Depot[] d = new Depot[4];
	private static int depotCount = 0;
	
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
			System.out.println("Exit						(9)\n");
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
	            case 9: 																						//Exit
	            	System.out.println("Program has terminated");
	            	break;
	            default: System.out.println("Invalid option");
	        }
	    }
	        while(option!=9);																					// Loop menu until user inputs 9
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
			System.out.print("\n");
			while (checkDepotPosition(depotEntered) >= 0)
			{
				System.out.print("Name already exists. Please enter a different name: ");					// Request the user to input a different depot name
				depotEntered = console.nextLine();																// Read depot name entered from console
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
	
	public int checkDepotPosition(String depotEntered)
	{
		for (int i = 0; i <= depotCount; i++)
		{
			if (d[i] != null && depotEntered.equalsIgnoreCase(d[i].getName()))
				return i;
		}
		return -1;
	}
	
	public String checkDepotName(String depotEntered)
	{
		for (int i = 0; i <= depotCount; i++)
		{
			if (d[i] != null && depotEntered.equalsIgnoreCase(d[i].getName()))
				return d[i].getName();
		}
		return null;
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
			System.out.print("Please enter name for depot to be removed: ");
			depotEntered = console.nextLine();
			System.out.print("\n");
			
			
			if (depotEntered != null && depotEntered.length() > 0 && checkDepotName(depotEntered) != null)
			{
				for (int i = checkDepotPosition(depotEntered); i < depotCount-1; i++)
				{
					d[i] = d[i+1];
				}
				System.out.println("Depot "+depotEntered+" has been successfully removed\n"); 				// Inform the user that depot was successfully removed
				d[depotCount] = null;
				depotCount--;
			}
			else if (depotEntered != null && depotEntered.length() > 0 && checkDepotName(depotEntered) == null)
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
		int productLocation = -1;
		if (depotCount > 0)
		{
			System.out.print("Please enter name of product to add: ");											// Request user to input product name to be added
			productEntered = console.nextLine();																	// Read product name input from console
			System.out.print("\n");
			if (productEntered != null && productEntered.length() > 0 && checkProductInDepot(productEntered) > 0)
			{
				System.out.println("Product with that name already exists in "+checkProductInDepot(productEntered)+" depots.\n");
			}
			availableDepots();																						// Display available depots to add to
			System.out.print("Please enter depot name for product to be stored: ");									// Request input for depot name
			depotEntered = console.nextLine();																			// Read depot name from console
			System.out.print("\n");
			if (depotEntered != null && depotEntered.length() > 0 && checkDepotName(depotEntered) != null)
			{
				int i = checkDepotPosition(depotEntered);
				for (int j = 0; j < d[i].getProduct(); j++)
				{
					if (d[i].getProductName(j).equalsIgnoreCase(productEntered))
						productLocation = j;
				}
				if (productLocation >= 0)
				{
					System.out.println("Current quantity of "+d[i].getProductName(productLocation)+" in depot "+d[i].getName()+": "+d[i].getProductQuantity(productLocation)+"\n");	// Print product info with current quantity
					d[i].setProductQuantity(d[i].getProductQuantity(productLocation)+addQuantity(), productLocation);
					System.out.println("New quantity of "+d[i].getProductName(productLocation)+" in depot "+d[i].getName()+": "+d[i].getProductQuantity(productLocation)+"\n");		// Print product info with new quantity
				}
				else if (d[i].getProduct() < 5)
				{
					d[i].setProduct(productEntered, addQuantity(), productWeight(), productPrice());
					System.out.println("Product successfully added to depot "+d[i].getName()+"\n");				// Inform user product has been successfully added
				}
				else
					System.out.println("Depot "+d[i].getName()+" is full. Please remove a product to add another product.\n");	// Inform user depot is full
			}
			else if (depotEntered != null && depotEntered.length() > 0 && checkDepotName(depotEntered) == null)
				System.out.print("A depot with that name does not exist. No product has been added.");
			else if (depotEntered != null && depotEntered.length() == 0)
				System.out.print("Nothing entered. Nothing has been added");	
		}
		else
			System.out.println("No depots exist");
		System.out.println();
		enterContinue();
	}
	
	public int checkProductInDepot(String productEntered)
	{
		int location = 0;
		if (depotCount > 0)
		{
			for (int i = 0; i < depotCount; i++)
			{
				for (int j = 0, k = 0; j < d[i].getProduct(); j++)
				{
					if (d[i].getProductName(j).equalsIgnoreCase(productEntered))
						location++;
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
		int productLocation = -1;
		if (depotCount > 0)																	// Check if either depot 1 or depot 2 exist
		{
			System.out.print("Please enter name of product to remove: ");											// Request user to input product name to be added
			productEntered = console.nextLine();																	// Read product name input from console
			System.out.print("\n");
			if (productEntered != null && productEntered.length() > 0 && checkProductInDepot(productEntered) == 0)
			{
				System.out.println("Product with that name does not exist. Nothing has been removed\n");
			}
			else if (productEntered != null && productEntered.length() > 0 && checkProductInDepot(productEntered) > 0)
				
			availableDepots();																						// Display available depots to add to
			System.out.print("Please enter depot name for product to be stored: ");									// Request input for depot name
			depotEntered = console.nextLine();																			// Read depot name from console
			System.out.print("\n");
			
		}
		else
		{
			System.out.println("No depots exist\n");															// If no depots exist, inform user
			enterContinue();																						// Call method to request user to press enter to continue
		}
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
	public void removeFromDepot(String productName, String depotStored, int i)
	{
		String depotName = null;																				// Initialise variable for depot name
		System.out.print("Please enter depot name for product to be removed: ");								// Request input for depot name
		depotName = console.nextLine();																			// Read depot name from console
		System.out.print("\n");
		if (depotName != null && depotName.length() > 0)														// Check if something was entered
		{
			if (depot1 != null && depotName.equalsIgnoreCase(depot1.getName()))									// If depot 1 exists and name entered matches name of depot 1
			{
				if (depotStored.equalsIgnoreCase(depotName) && depot1.getProductQuantity(i) > 1)				// If depot name passed to method matches depot name name entered and product quantity is greater than 1
				{
					System.out.println("Old quantity of "+depot1.getProductName(i)+" in depot "+depot1.getName()+": "+depot1.getProductQuantity(i)+"\n"); // Print product info and current quantity
					depot1.setProductQuantity(depot1.getProductQuantity(i)-1, i);								// Subtract 1 from product quantity
					System.out.println("One item of product "+depot1.getProductName(i)+" has been removed from depot "+depot1.getName()+"\n");
					System.out.println("New quantity of "+depot1.getProductName(i)+" in depot "+depot1.getName()+": "+depot1.getProductQuantity(i)+"\n");	// Print product info and new quantity
				}
				else if (depotStored.equalsIgnoreCase(depotName) && depot1.getProductQuantity(i) == 1)			// If depot name passed to method matches depot name entered and product quantity is 1
				{
					depot1.clearProduct(i);																		// Delete the product
					System.out.println("Product "+productName+" has been removed from depot "+depot1.getName()+"\n"); // Inform user product has been removed
				}
				else if (depotStored.equalsIgnoreCase("both") && depot1.getProductQuantity(i) > 1)
				{
					System.out.println("Old quantity of "+depot1.getProductName(i)+" in depot "+depot1.getName()+": "+depot1.getProductQuantity(i)+"\n"); // Print product info and current quantity
					depot1.setProductQuantity(depot1.getProductQuantity(i)-1, i);								// Subtract 1 from product quantity
					System.out.println("One item of product "+depot1.getProductName(i)+" has been removed from depot "+depot1.getName()+"\n");
					System.out.println("New quantity of "+depot1.getProductName(i)+" in depot "+depot1.getName()+": "+depot1.getProductQuantity(i)+"\n");	// Print product info and new quantity
				}
				else if (depotStored.equalsIgnoreCase("both") && depot1.getProductQuantity(i) == 1)			// If depot name passed to method matches depot name entered and product quantity is 1
				{
					depot1.clearProduct(i);																		// Delete the product
					System.out.println("Product "+productName+" has been removed from depot "+depot1.getName()+"\n"); // Inform user product has been removed
				}
				else																							// If product does not exist in depot 1, inform user
					System.out.println("Product with that name does not exist in depot "+depot1.getName()+". Product has not been removed.\n");
			}
			
			else if (depot2 != null && depotName.equalsIgnoreCase(depot2.getName()))							// If depot 2 exists and name entered matches name of depot 2
			{
				if (depotStored.equalsIgnoreCase(depotName) && depot2.getProductQuantity(i) > 1)				// If depot name passed to method matches depot name name entered and product quantity is greater than 1
				{
					System.out.println("Old quantity of "+depot2.getProductName(i)+" in depot "+depot2.getName()+": "+depot2.getProductQuantity(i)+"\n");	// Print product info and current quantity
					depot2.setProductQuantity(depot2.getProductQuantity(i)-1, i);								// Subtract 1 from product quantity
					System.out.println("One item of product "+depot2.getProductName(i)+" has been removed from depot "+depot2.getName()+"\n");
					System.out.println("New quantity of "+depot2.getProductName(i)+" in depot "+depot2.getName()+": "+depot2.getProductQuantity(i)+"\n");	// Print product info and new quantity
				}
				else if (depotStored.equalsIgnoreCase(depotName) && depot2.getProductQuantity(i) == 1)			// If depot name passed to method matches depot name entered and product quantity is 1
				{
					depot2.clearProduct(i);																		// Delete the product
					System.out.println("Product "+productName+" has been removed from depot "+depot2.getName()+"\n");	// Inform user product has been removed
				}
				else if (depotStored.equalsIgnoreCase("both") && depot2.getProductQuantity(i) > 1)				// If depot name passed to method matches depot name name entered and product quantity is greater than 1
				{
					System.out.println("Old quantity of "+depot2.getProductName(i)+" in depot "+depot2.getName()+": "+depot2.getProductQuantity(i)+"\n");	// Print product info and current quantity
					depot2.setProductQuantity(depot2.getProductQuantity(i)-1, i);								// Subtract 1 from product quantity
					System.out.println("One item of product "+depot2.getProductName(i)+" has been removed from depot "+depot2.getName()+"\n");
					System.out.println("New quantity of "+depot2.getProductName(i)+" in depot "+depot2.getName()+": "+depot2.getProductQuantity(i)+"\n");	// Print product info and new quantity
				}
				else if (depotStored.equalsIgnoreCase("both") && depot2.getProductQuantity(i) == 1)			// If depot name passed to method matches depot name entered and product quantity is 1
				{
					depot2.clearProduct(i);																		// Delete the product
					System.out.println("Product "+productName+" has been removed from depot "+depot2.getName()+"\n");	// Inform user product has been removed
				}
				else																							// If product does not exist in depot 1, inform user
					System.out.println("Product with that name does not exist in depot "+depot2.getName()+". Product has not been removed.\n");
			}
			
			else																								// If depot name entered does not match an existing depot, inform user
				System.out.println("Depot with that name does not exist. Product has not been removed\n");
		}
		
		else if (depotName != null && depotName.length() == 0)													// If nothing was entered, inform user
			System.out.println("Nothing entered. Product has not been added.\n");
		enterContinue();																						// Call method to request user to press enter to continue
	}

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
		String productEntered = null, depotEntered = null;															// Initialise variables for product name and depot name input
		if (depotCount > 0)																	// Check if either depot 1 or depot 2 exist
		{
			availableDepots();																					// Call method to display available depots
			System.out.print("Please enter name of a depot: ");													// Request user to input name of depot
			depotEntered = console.nextLine();																		// Read depot name input from console
			System.out.print("\n");
			if (depotEntered !=null && depotEntered.length() > 0 && )														// Check if something was entered
			{
				
			}
		else
		{
			System.out.println("No depots exist\n");															// If no depots exist, inform user
		}
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
			System.out.print("\n");
			if (productEntered !=null && productEntered.length() > 0)													// Check if something was entered
			{
				for (int i = 0; i < depotCount; i++)
				{
					for (int j = 0; j < d[i].getProduct(); j++)
					{
						if (d[i].getProductName(j).equalsIgnoreCase(productEntered))
							System.out.println("Product "+d[i].getProductName(j)+" is in depot "+d[i].getName()+" with quantity "+d[i].getProductQuantity(j)+"\n");
					}
				}
			}
		else																									// If no depots exist, inform user
			System.out.println("No depots exist\n");
		enterContinue();																						// Call method to request user to press enter to continue
		}
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
		String productName = null, depotName = null;															// Initialise variable for product name and depot name input
		double totalProductPrice = 0;																			// Initialise variable for total product price
		
		if (depot1 != null || depot2 != null)																	// Check if either depot 1 or depot 2 exists
		{
			availableDepots();																					// Call method to display available depots
			System.out.print("Please enter name of a depot: ");													// Request user to input name of depot
			depotName = console.nextLine();																		// Read depot name from console
			System.out.print("\n");
			
			if (depotName !=null && depotName.length() > 0)														// Check if something was entered
			{
				if (depot1 != null && depotName.equalsIgnoreCase(depot1.getName()))								// If depot 1 exists and name entered matches the name of depot 1
				{
					if(depot1.getProduct() > 0)																	// Check if depot 1 has any products in it
					{
						for(int i = 1; i <= 3; i++)																// Loop to check each product in depot
						{
							productName = depot1.getProductName(i);												// Read product name from product in depot
							if (productName != null)															// If product name is not null (meaning the product exists)
							{
								totalProductPrice += (depot1.getProductPrice(i)*depot1.getProductQuantity(i));	// Add product price to total product price
							}	
						}
						System.out.println("Total cumulative value of products in depot "+depot1.getName()+": $"+String.format("%.2f", totalProductPrice)+"\n"); // Print total product value
					}
					else 
						System.out.println("No products exist in depot "+depot1.getName()+"\n"); 				// Print total product value
				}
				
				else if (depot2 != null && depotName.equalsIgnoreCase(depot2.getName()))						// If depot 2 exists and depot name entered matches depot 2
				{
					if(depot2.getProduct() > 0)																	// Check if depot 2 has any products in it
					{
						for(int i = 1; i <= 3; i++)																// Loop to check each product in depot
						{
							productName = depot2.getProductName(i);												// Read product name from product in depot
							if (productName != null)															// If product name is not null (meaning the product exists)
							{
								totalProductPrice += (depot2.getProductPrice(i)*depot2.getProductQuantity(i));	// Add product price to total product price
							}
						}
						System.out.println("Total cumulative value of products in depot "+depot2.getName()+": $"+String.format("%.2f", totalProductPrice)+"\n"); // Print total product value
					}
					else 
						System.out.println("No products exist in depot "+depot2.getName()+"\n"); 				// Print total product value
				}
				else
					System.out.println("Depot with that name does not exist.\n");								// If depot name does not match either depot, inform user
			}
			else
				System.out.println("Nothing entered.\n");														// If nothing was entered, inform user
		}
		else
		{
			System.out.println("No depots exist\n");															// If no depots exist, inform user
		}
		enterContinue();																						// Call method to request user to press enter to continue	
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