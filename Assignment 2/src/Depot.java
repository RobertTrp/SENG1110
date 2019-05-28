

/*/*************************************************************************************************************
 * Programming assignment 1
 * 
 * 		   Authors: Kate McAlpine and Robert Trpeski
 * Student numbers:	   c3078083		     c3244194
 * 			   Lab:	Wednesday 10am-12pm - ES105
 ***************************************************************************************************************/

public class Depot
{
	private String name;
	private Product[] p = new Product[5];
	private static int productCount = 0;
	
	
	/*/************************************************************************************************************************************************************************
	 * When this method is called it returns the private variable 'name'
	 **************************************************************************************************************************************************************************/
	public String getName()
	{
		return name;
	}
	
	/*/************************************************************************************************************************************************************************
	 * When this method is called it assigns the variable 'newName' passed as an argument, to the private variable 'name'
	 **************************************************************************************************************************************************************************/
	public void setName(String newName)
	{
		name = newName;
	}
	
	/*/************************************************************************************************************************************************************************
	 * When this method is called it clears the private variable 'name', by setting it to 'null'
	 **************************************************************************************************************************************************************************/
	public void clearName()
	{
		name = null;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes in a name, quantity, weight and price as arguments
	 * It then checks if product 1 is empty, and if so proceeds to create a new product by calling the methods from the Product class to set the new variables
	 * If product 1 exists, it proceeds to check product 2 and then product 3.
	 **************************************************************************************************************************************************************************/
	public void setProduct(String newName, int newQuantity, double newWeight, double newPrice)
	{
		if (productCount < p.length)
		{
			p[productCount] = new Product();
			p[productCount].setName(newName);
			p[productCount].setQuantity(newQuantity);
			p[productCount].setWeight(newWeight);
			p[productCount].setPrice(newPrice);
			productCount++;
		}
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method returns how many products exist in the depot
	 **************************************************************************************************************************************************************************/
	public int getProduct()
	{
		return productCount;
	}
	
	
	
	public int checkProductPosition(String nameEntered)
	{
		for (int i = 0; i <= productCount; i++)
		{
			if (p[i] != null && nameEntered.equalsIgnoreCase(p[i].getName()))
				return i;
		}
		return -1;
	}
	
	public String checkProductName(String nameEntered)
	{
		for (int i = 0; i <= productCount; i++)
		{
			if (p[i] != null && nameEntered.equalsIgnoreCase(p[i].getName()))
				return p[i].getName();
		}
		return null;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the name of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public String getProductName(int productNumber)
	{
		if (productNumber < productCount && productNumber >= 0 && p[productNumber] != null)
			return p[productNumber].getName();
		return null;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the weight of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public double getProductWeight(int productNumber)
	{
		if (productNumber < productCount && productNumber >= 0)
			return p[productNumber].getWeight();
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the quantity of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public int getProductQuantity(int productNumber)
	{
		if (productNumber < productCount && productNumber >= 0)
			return p[productNumber].getQuantity();
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the price of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public double getProductPrice(int productNumber)
	{
		if (productNumber < productCount && productNumber >= 0)
			return p[productNumber].getPrice();
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes quantity and a product number as arguments
	 * A check is performed to see if a product exists and the product number matches
	 * If there is a match the new product quantity is assigned to that product
	 **************************************************************************************************************************************************************************/
	public void setProductQuantity(int newQuantity, int productNumber)
	{
		if (productNumber < productCount && productNumber >= 0)
			p[productNumber].setQuantity(newQuantity);
	}

	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and clears the product corresponding to that integer by calling a method from the product class and setting product to null
	 **************************************************************************************************************************************************************************/
	public void clearProduct(int productNumber)
	{
		if (productNumber < productCount && productNumber >= 0)
		{
			for (int i = productNumber; i < productCount-1; i++)
			{
				System.out.print(i);
				p[i] = p[i+1];
			}
			p[productCount].clear();
			p[productCount] = null;
			productCount--;
		}
	}
	
}
