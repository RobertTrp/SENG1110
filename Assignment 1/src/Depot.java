
public class Depot
{
	private String name;
	private Product product1, product2, product3;
	
	//add comments
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	public void clear() {
		name = null;
	}
	public void setProduct() {
		if (product1 == null)
			product1 = new Product();
		else if (product1 != null && product2 == null)
			product2 = new Product();
		else if (product1 != null && product2 != null && product3 == null)
			product3 = new Product();
	}
	public int getProduct() {
		if ((product1 != null && product2 == null && product3 == null) || (product1 == null && product2 != null && product3 == null) || (product1 == null && product2 == null && product3 != null))
			return 1;
		else if ((product1 != null && product2 != null && product3 == null) || (product1 != null && product2 == null && product3 != null) || (product1 == null && product2 != null && product3 != null))
			return 2;
		else if (product1 != null && product2 != null && product3 != null)
			return 3;
		return 0;
	}
	public String getProduct1Name() {
		if (product1 != null)
			return product1.getName();
		return null;
	}
	public String getProduct2Name() {
		if (product2 != null)
			return product2.getName();
		return null;
	}
	public String getProduct3Name() {
		if (product3 != null)
			return product3.getName();
		return null;
	}
}
	
	//add other methods
