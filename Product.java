
public class Product {
	
	/*Product Name */
	private String name;
	/* Product Demand Rate */
	private double demandRate;
	/* Product Setup Cost */
	private double setupCost;
	/* Product Unit Cost */
	private double unitCost;
	/*Product Inventory Cost */
	private double inventoryCost;
	/* Product Selling Price */
	private double sellingPrice;
	
	Product(){
		this.name = new String();
	}
	
	/*  Getter/accessor for Product Name */
	public String getName() {
		return name;
	}
	/* Setter/Mutator for Product Name */
	public void setName(String name) {
		this.name = name;
	}
	/* Getter/accessor for Demand Rate */
	public double getDemandRate() {
		return demandRate;
	}
	/* Setter/Mutator for Demand Rate */
	public void setDemandRate(double demandRate) {
		this.demandRate = demandRate;
	}
	/* Getter/accessor for Setup Cost */
	public double getSetupCost() {
		return setupCost;
	}
	/* Setter/Mutator for Setup Cost */
	public void setSetupCost(double setupCost) {
		this.setupCost = setupCost;
	}
	/* Getter/accessor for Unit Cost */
	public double getUnitCost() {
		return unitCost;
	}
	/* Setter/Mutator for Unit Cost */
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	/* Getter/accessor for Inventory Cost */
	public double getInventoryCost() {
		return inventoryCost;
	}
	/* Setter/Mutator for Inventory Cost */
	public void setInventoryCost(double inventoryCost) {
		this.inventoryCost = inventoryCost;
	}
	
	/* Getter/accessor for Selling Price */
	public double getSellingPrice() {
		return sellingPrice;
	}
	
	/* Setter/Mutator for Selling Price */
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

}
