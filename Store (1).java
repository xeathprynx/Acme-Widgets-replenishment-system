public class Store {
	/* Three Product Objects */
	private Product product[];
	private String storeName;

	/* Counter for Products */
	private int productCounter = 1;

	/*
	 * Name: Store Parameters: none Return: None Description: Contructor for
	 * Class
	 */
	Store(String name) {
		storeName = name;
		product = new Product[3];
		for(int i = 0; i < 3; i++)
		product[i] = new Product();
		this.productCounter = 1;
	}
	
	public String getName(){
		return storeName;
	}
	
	/*
	 * Name: addProduct Parameters: String name,double demandRate,double
	 * setupCost, double unitCost, double inventoryCost, double sellingPrice
	 * Return: None Description: Method for adding a newproduct
	 */
	public void addProduct(String name, double demandRate, double setupCost, double unitCost, double inventoryCost,
			double sellingPrice) {
		if(product.length < productCounter)
			doubleSize();
		product[this.productCounter-1] = new Product();
		product[this.productCounter-1].setName(name);
		product[this.productCounter-1].setDemandRate(demandRate);
		product[this.productCounter-1].setSetupCost(setupCost);
		product[this.productCounter-1].setUnitCost(unitCost);
		product[this.productCounter-1].setInventoryCost(inventoryCost);
		product[this.productCounter-1].setSellingPrice(sellingPrice);
		this.productCounter++;
	}
	
	public void doubleSize(){
		Product updatedArray[] = new Product[product.length*2];
		for (int i = 0; i < product.length; i++) {
			updatedArray[i] = product[i];
		}
	}
	
	public void removeProduct(String name, int number) {
		for(int i = number; i < this.productCounter; i++)
			product[i-1] = product[i];
	}

	/*
	 * Name: productExists Parameters: String productName Return: boolean
	 * Description: Method for checking if a product exists for each product, if
	 * a certain product exists return true, if no product exists return false
	 */
	public boolean productExists(String productName) {
		for(Product p: product){
			if(p.getName().toLowerCase().equals(productName))
				return true;
		}
		return false;
	}

	/*
	 * Name: getProductNumber Parameters: String productName Return: integer
	 * Description: Method for getting product number, checks if product names
	 * are equal, if so return product number
	 */
	public int getProductNumber(String productName) {

		for(int i = 0; i < product.length; i++)
			if(product[i].getName().toLowerCase().equals(productName.toLowerCase()))
				return i+1;
		return -1;
	}

	public String getProductName(int productNumber) {
		if (productNumber >= productCounter) {
			return null;
		}
		return product[productNumber-1].getName();
	}

	/*
	 * Name: updateproductName Parameters: String name, int ProductNo Return:
	 * none Description: Method for updating product Name, takes names and
	 * ProductNo, if ProductNo equal to number set the name to the name for that
	 * number
	 */
	public void updateproductName(String name, int ProductNo) {
		if(product[ProductNo-1] != null)
		product[ProductNo-1].setName(name);
	}

	/*
	 * Name: getProduct Parameters: String productName Return: product number
	 * Description: Method to getting product Name, takes productName, if
	 * product number equal to number return product to the name for that number
	 */
	public Product getProduct(String productName) {
		for(Product p:product){
			if(p.getName().toLowerCase().equals(productName.toLowerCase()))
				return p;
		}
		return null;
	}

	/*
	 * Name: getProductCounter Parameters: none Return: productCounter
	 * Description: Method to keep track of product counter
	 */
	public int getProductCounter() {
		return productCounter;
	}

	/*
	 * Name: setProductCounter Parameters: productCounter Return: none
	 * Description: Method to set product counter
	 */
	public void setProductCounter(int productCounter) {
		this.productCounter = productCounter;
	}

	/*
	 * Name: updateProduct Parameters: int productNo,String name,double
	 * demandRate,double setupCost, double unitCost, double inventoryCost,
	 * double sellingPrice Return: none Description: Method to instantiate
	 * variables based on product counter
	 */
	public void updateProduct(int productNo, String name, double demandRate, double setupCost, double unitCost,
			double inventoryCost, double sellingPrice) {
		product[productNo-1] = new Product();
		product[productNo-1].setName(name);
		product[productNo-1].setDemandRate(demandRate);
		product[productNo-1].setSetupCost(setupCost);
		product[productNo-1].setUnitCost(unitCost);
		product[productNo-1].setInventoryCost(inventoryCost);
		product[productNo-1].setSellingPrice(sellingPrice);
	}

}
