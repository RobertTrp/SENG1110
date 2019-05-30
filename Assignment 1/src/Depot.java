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
	private Product product1, product2, product3;
	
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
		if (product1 == null)
		{
			product1 = new Product();
			product1.setName(newName);
			product1.setQuantity(newQuantity);
			product1.setWeight(newWeight);
			product1.setPrice(newPrice);
		}
		else if (product1 != null && product2 == null)
		{
			product2 = new Product();
			product2.setName(newName);
			product2.setQuantity(newQuantity);
			product2.setWeight(newWeight);
			product2.setPrice(newPrice);
		}
		else if (product1 != null && product2 != null && product3 == null)
		{
			product3 = new Product();
			product3.setName(newName);
			product3.setQuantity(newQuantity);
			product3.setWeight(newWeight);
			product3.setPrice(newPrice);
		}
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method returns how many products exist in the depot
	 **************************************************************************************************************************************************************************/
	public int getProduct()
	{
		if ((product1 != null && product2 == null && product3 == null) || (product1 == null && product2 != null && product3 == null) || (product1 == null && product2 == null && product3 != null))
			return 1;
		else if ((product1 != null && product2 != null && product3 == null) || (product1 != null && product2 == null && product3 != null) || (product1 == null && product2 != null && product3 != null))
			return 2;
		else if (product1 != null && product2 != null && product3 != null)
			return 3;
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the name of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public String getProductName(int productNumber)
	{
		if (product1 != null && productNumber == 1)
			return product1.getName();
		else if (product2 != null && productNumber == 2)
			return product2.getName();
		else if (product3 != null && productNumber == 3)
			return product3.getName();
		return null;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the weight of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public double getProductWeight(int productNumber)
	{
		if (product1 != null && productNumber == 1)
			return product1.getWeight();
		else if (product2 != null && productNumber == 2)
			return product2.getWeight();
		else if (product3 != null && productNumber == 3)
			return product3.getWeight();
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the quantity of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public int getProductQuantity(int productNumber)
	{
		if (product1 != null && productNumber == 1)
			return product1.getQuantity();
		else if(product2 != null && productNumber == 2)
			return product2.getQuantity();
		else if (product3 != null && productNumber == 3)
			return product3.getQuantity();
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and returns the price of the product corresponding to that integer
	 **************************************************************************************************************************************************************************/
	public double getProductPrice(int productNumber)
	{
		if (product1 != null && productNumber == 1)
			return product1.getPrice();
		else if(product2 != null && productNumber == 2)
			return product2.getPrice();
		else if (product3 != null && productNumber == 3)
			return product3.getPrice();
		return 0;
	}
	
	/*/************************************************************************************************************************************************************************
	 * This method takes quantity and a product number as arguments
	 * A check is performed to see if a product exists and the product number matches
	 * If there is a match the new product quantity is assigned to that product
	 **************************************************************************************************************************************************************************/
	public void setProductQuantity(int newQuantity, int productNumber)
	{
		if (product1 != null && productNumber == 1)
			product1.setQuantity(newQuantity);
		else if (product2 != null && productNumber == 2)
			product2.setQuantity(newQuantity);
		else if (product3 != null && productNumber == 3)
			product3.setQuantity(newQuantity);
	}

	/*/************************************************************************************************************************************************************************
	 * This method takes an integer passed as an argument and clears the product corresponding to that integer by calling a method from the product class and setting product to null
	 **************************************************************************************************************************************************************************/
	public void clearProduct(int productNumber)
	{
		if (product1 != null && productNumber == 1)
		{
			product1.clear();
			product1 = null;
		}
		else if (product2 != null && productNumber == 2)
		{
			product2.clear();
			product2 = null;
		}
		else if (product3 != null && productNumber == 3)
		{
			product3.clear();
			product3 = null;
		}
	}
	
}
