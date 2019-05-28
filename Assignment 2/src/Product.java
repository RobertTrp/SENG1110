

/*/*************************************************************************************************************
 * Programming assignment 1
 * 
 * 		   Authors: Kate McAlpine and Robert Trpeski
 * Student numbers:	   c3078083		     c3244194
 * 			   Lab:	Wednesday 10am-12pm - ES105
 ***************************************************************************************************************/

public class Product
{
	private String name;
	private double price;
	private double weight;
	private int quantity;
	

	public void setName(String newName)	{						// This method takes a name String as an argument and assigns it to name	
		name = newName;
	}
	public void setPrice(double newPrice) { 					// This method takes a price double as an argument and assigns it to price
		price = newPrice;
	}
	public void setWeight(double newWeight) {					// This method takes a weight double as an argument and assigns it to weight
		weight = newWeight;
	}
	public void setQuantity(int newQuantity) {					// This method takes a quantity integer as an argument and assigns it to quantity
		quantity = newQuantity;
	}
	public String getName() {									// This method returns name
		return name;
	}
	public double getPrice() {									// This method returns price
		return price;
	}
	public double getWeight() {									// This method returns weight
		return weight;
	}
	public int getQuantity() {									// This method returns quantity
		return quantity;
	}
	public void clear() {										// This method clears all variables
		name = null;
		price = 0;
		weight = 0;
		quantity = 0;
	}

}