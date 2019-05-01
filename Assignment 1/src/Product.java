public class Product
{
	private String name;
	private double price;
	private double weight;
	private int quantity;
	
	//add comments
	public void setName(String newName) { //
		name = newName;
	}
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	public void setWeight(double newWeight) {
		weight = newWeight;
	}
	public void setQuantity(int newQuantity) {
		quantity = newQuantity;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public double getWeight() {
		return weight;
	}
	public int getQuantity() {
		return quantity;
	}

}
