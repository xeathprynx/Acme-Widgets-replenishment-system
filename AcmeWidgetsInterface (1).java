
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class AcmeWidgetsInterface {

	private Store store;
	private Scanner in;

	// Constructor - Initialize the Scanner and Store Object
	AcmeWidgetsInterface() {
		in = new Scanner(System.in);
		store = null;
	}

	/*
	 * Name: displayMenu Parameters: none Return: None Description: Builds text
	 * menu
	 */
	void displayMenu() {
		System.out.println("Enter 1 to Input Data for One Product");
		System.out.println("Enter 2 to Delete One Product");
		System.out.println("Enter 3 to Show Data for One Product");
		System.out.println("Enter 4 to Show The Replenishment Strategy for a Product");
		System.out.println("Enter 5 to Show All Products");
		System.out.println("Enter 6 to Exit Program");
		System.out.print("Please Enter your Choice: ");
	}

	/*
	 * Name: getMenuOption Parameters: none Return: Returns Menu integer choice
	 * Description: Gets User input for menu option as an integer
	 */
	int getMenuOption() {
		int choice = -1;
		try {
			choice = in.nextInt();
			in.nextLine();
		} catch (Exception e) // catches invalid inputs
		{
			System.out.println("Invalid Input!");
			choice = -1;
			in.next();
		}
		return choice;
	}

	/*
	 * Name: getStringInput Parameters: none Return: Returns String input
	 * Description: Called to get Strings inside program
	 */
	String getStringInput() {
		String input = in.nextLine();
		return input;
	}

	/*
	 * Name: getDoubleInput Parameters: none Return: Returns Double input
	 * Description: Called to get Double values inside program
	 */
	double getDoubleInput() {
		double input = -1;
		try {
			input = in.nextDouble();
			if (input < 0)
				throw new IllegalArgumentException();
		} catch (IllegalArgumentException e)// catches minus numbers input
		{
			System.out.println("Invalid Input cannot use Minus Numbers!");
			input = -1;

		} catch (Exception e) // catches other invalid inputs
		{
			System.out.println("Invalid Input cannot use alpha characters here!");
			input = -1;
			in.next();
		}
		return input;
	}

	/*
	 * Name: run Parameters: none Return: Nothing Description: Main program,
	 * Gets all input and displays all Output
	 */
	public void run() {
		String name;
		int choice;
		boolean b;
		LinkedList<Store> allStores = new LinkedList<Store>();
		boolean innerLoop = true;
		while (true) {
			System.out.println("1. Choose Store");
			System.out.println("2. Display Stores");
			System.out.println("3. Open");
			System.out.println("4. Save");
			System.out.println("5. Exit");
			choice = getMenuOption();

			switch (choice) {
			case 1:
				if (allStores.isEmpty()) {
					System.out.println("No stores loaded please choose open option to load stores from file");
					continue;
				} else {
					System.out.println("Enter store name: ");
					name = getStringInput();
					store = null;
					for (Store s : allStores) {
						if (s.getName().toLowerCase().equals(name.toLowerCase())) {
							store = s;
							innerLoop = true;
							break;
						}
					}
					if(store == null)
					System.out.println("No such store found");
					else
					break;
				}
				continue;
			case 2:
				if (allStores.isEmpty()) {
					System.out.println("No stores loaded please choose open option to load stores from file");
					continue;
				} else
					for (Store s : allStores)
						System.out.println(s.getName());
				continue;
			case 3:
				// open
				try {
					Store s;
					String data;
					BufferedReader br = new BufferedReader(new FileReader("input.dat"));
					data = br.readLine();
					while (data != null) {
						s = new Store(data);
						br.readLine();
						data = br.readLine();
						while (true) {
							s.addProduct(
									data.substring(6),
									Double.parseDouble(br.readLine().substring(13)),
									Double.parseDouble(br.readLine().substring(12)),
									Double.parseDouble(br.readLine().substring(11)),
									Double.parseDouble(br.readLine().substring(16)),
									Double.parseDouble(br.readLine().substring(15)));
							br.readLine();
							data = br.readLine();
							if(data != null){
								if(data.startsWith("Name:"))
									continue;
								else 
									break;
							}
							else
								break;
						}
						allStores.add(s);
					}
					br.close();
				} catch (Exception e) {e.printStackTrace();}
				continue;
			case 4:
				// save
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("input.dat"));
					Product p;
					boolean newLine = false;
					for(Store s: allStores){
						if(newLine)
							bw.newLine();
						bw.write(s.getName());
						bw.newLine();
						for(int i = 1; i < s.getProductCounter(); i++){
							bw.newLine();
							p = s.getProduct(s.getProductName(i));
							bw.write("Name: " + p.getName());
							bw.newLine();
							bw.write("demand rate: " + p.getDemandRate());
							bw.newLine();
							bw.write("setup cost: " + p.getSetupCost());
							bw.newLine();
							bw.write("unit cost: " + p.getUnitCost());
							bw.newLine();
							bw.write("inventory cost: " + p.getInventoryCost());
							bw.newLine();
							bw.write("selling price: " + p.getSellingPrice());
							bw.newLine();
						}
						newLine = true;
					}
					bw.close();
				} catch (Exception e) {e.printStackTrace();}
				continue;
			case 5:
				System.exit(0);
			default:
				continue;
			}
			b = true;
			while (b) {
				displayMenu();
				choice = getMenuOption();
				switch (choice) {
				case 1: {
					if (store.getProductCounter() > 3) {
						System.out.println("Sorry, Three Products Already Exist!");
						System.out.println("Would you like to remove a product and add a new Product?Type Yes or No");
						String choiceforremoval = getStringInput();
						if (choiceforremoval.equals("Yes")) {
							System.out.print("Enter Name of Product that you want to remove: ");
							name = getStringInput();
							name = name.toLowerCase();
							if (store.productExists(name)) {
								System.out.println("Removed - Now please enter details of the new Product!");
								System.out.println("Enter new Product Name: ");
								String productName = getStringInput();
								productName = productName.toLowerCase();
								System.out.print("Enter Product Demand Rate: ");
								double demandRate = getDoubleInput();
								System.out.print("Enter Setup Cost: ");
								double setupCost = getDoubleInput();
								System.out.print("Enter Unit Cost: ");
								double unitCost = getDoubleInput();
								System.out.print("Enter Inventory Cost: ");
								double inventoryCost = getDoubleInput();
								System.out.print("Enter Selling Price: ");
								double sellingPrice = getDoubleInput();
								store.updateProduct(this.store.getProductNumber(name), productName, demandRate,
										setupCost, unitCost, inventoryCost, sellingPrice);
								System.out.println("Product Has been Added");
							} else {
								System.out.println("No Such Product In Database!");
							}
						}
						break;
					}
					System.out.print("Enter Product Name: ");
					name = getStringInput();
					int n = 1;
					try {
						n = Integer.parseInt(name);
					} catch (Exception e) {

					}
					if (n < 0) {
						System.out.println("Invalid Name!");
						break;
					}
					while (!(name.length() >= 3 && name.length() <= 10)) {
						System.out.println("Invalid - Name should be at least 3 and maximum 10 charactars long!");
						System.out.println("Please Enter Again: ");
						name = getStringInput();
					}
					name = name.toLowerCase();
					if (store.productExists(name)) {
						System.out.println(name + " already exists");
						System.out.println("Enter 1 to Change Name or 2 to Change Data of the Product");
						int ch = getMenuOption();
						if (ch == 1) {
							System.out.println("Enter new Product Name: ");
							String productName = getStringInput();
							this.store.updateproductName(productName, this.store.getProductNumber(name));
						} else if (ch == 2) {

							System.out.print("Enter Product Demand Rate: ");
							double demandRate = getDoubleInput();
							System.out.print("Enter Setup Cost: ");
							double setupCost = getDoubleInput();
							System.out.print("Enter Unit Cost: ");
							double unitCost = getDoubleInput();
							System.out.print("Enter Inventory Cost: ");
							double inventoryCost = getDoubleInput();
							System.out.print("Enter Selling Price: ");
							double sellingPrice = getDoubleInput();
							store.updateProduct(this.store.getProductNumber(name), name, demandRate, setupCost,
									unitCost, inventoryCost, sellingPrice);
							System.out.println("Product Has been Updated");
						} else {
							System.out.println("Invalid Choice!");
						}
						break;

					}
					double demandRate = -1;
					double setupCost = -1;
					double unitCost = -1;
					double inventoryCost = -1;
					double sellingPrice = -1;
					while (demandRate == -1) {
						System.out.print("Enter Product Demand Rate: ");
						demandRate = getDoubleInput();
					}
					while (setupCost == -1) {
						System.out.print("Enter Setup Cost: ");
						setupCost = getDoubleInput();
					}
					while (unitCost == -1) {
						System.out.print("Enter Unit Cost: ");
						unitCost = getDoubleInput();
					}
					while (inventoryCost == -1) {
						System.out.print("Enter Inventory Cost: ");
						inventoryCost = getDoubleInput();
					}
					while (sellingPrice == -1) {
						System.out.print("Enter Selling Price: ");
						sellingPrice = getDoubleInput();
					}
					store.addProduct(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
					System.out.println("Product Has been Added");
					break;
				}
				case 2:
					System.out.println("Enter product name: ");
					name = getStringInput().toLowerCase();
					if (!store.productExists(name)) {
						System.out.println("Sorry that product does not exist!");
					} else {
						store.removeProduct(name, store.getProductNumber(name));
					}
					break;
				case 3: {
					if (store.getProductCounter() <= 1) {
						System.out.println("No products exist in store.");
						break;
					}
					System.out.println("All products: ");
					for (int i = 1; i < store.getProductCounter(); i++) {
						System.out.println(" " + store.getProductName(i));
					}
					System.out.print("Enter Product Name to Display it's Data: ");
					name = getStringInput();
					name = name.toLowerCase();
					if (!store.productExists(name)) {
						System.out.println("Sorry that product does not exist!");
					} else {
						System.out.println("Product Information");
						Product product = store.getProduct(name);
						System.out.println("Name: " + product.getName());
						System.out.println("Demand Rate: " + product.getDemandRate());
						System.out.println("Setup Cost: " + product.getSetupCost());
						System.out.println("Unit Cost: " + product.getUnitCost());
						System.out.println("Inventory Cost: " + product.getInventoryCost());
						System.out.println("Selling Price: " + product.getSellingPrice());
					}
					break;
				}
				case 4: {
					System.out.print("Enter Product Name: ");
					name = getStringInput();
					name = name.toLowerCase();
					if (!store.productExists(name)) {
						System.out.println("Sorry that product does not exist!");
					} else {
						double tUnitCost = 0;
						double tSetupCost = 0;
						double tInventory = 0;

						System.out.print("Enter Number of Weeks: ");
						int n = getMenuOption();
						System.out.println("Product Information");
						Product product = store.getProduct(name);
						System.out.print("Week \t");
						System.out.print("Quantity Order \t");
						System.out.print("Demand \t");
						System.out.println("Inventory");
						double q = Math.ceil(Math.sqrt(
								(2 * product.getDemandRate() * product.getSetupCost()) / (product.getInventoryCost())));

						int inventory = (int) q;

						for (int i = 0; i < n; i++) {
							System.out.print(i + 1 + "\t");
							if (i == 0) {
								System.out.print((int) q + "\t\t");
								tUnitCost += q;
								tSetupCost = tSetupCost + product.getSetupCost();
							} else {
								if (inventory >= product.getDemandRate()) {
									System.out.print("0" + "\t\t");
								} else {
									if (i + 1 >= n) {
										q = product.getDemandRate() - inventory;
										tSetupCost = tSetupCost + product.getSetupCost();
										tUnitCost += q;
									} else {
										int demand = 0;
										for (int j = i; j < n; j++) {
											demand += product.getDemandRate();
										}
										demand = demand - inventory;
										if (demand < q) {
											q = demand;
											tUnitCost += q;
										}
									}
									tSetupCost = tSetupCost + product.getSetupCost();
									System.out.print((int) q + "\t\t");
									inventory = (int) (inventory + q);
								}
							}
							if (inventory >= product.getDemandRate()) {
								inventory = (int) (inventory - product.getDemandRate());
							}
							tInventory = tInventory + inventory;
							System.out.print((int) product.getDemandRate() + "\t");
							System.out.println((int) inventory);
						}

						// System.out.println("Total Inventory: "+tInventory);
						// System.out.println("Total Unit Cost: "+tUnitCost);
						// System.out.println("Total Setup Cost: "+tSetupCost);
						double purchaseCost = tSetupCost + tUnitCost * product.getUnitCost();
						// System.out.println("Purchase Cost: "+purchaseCost);
						double inventCost = tInventory * product.getInventoryCost();
						// System.out.println("Inventory Cost: "+inventCost);
						double totalCost = purchaseCost + inventCost;

						System.out.printf("Total Profit:%.2f",
								(n * product.getDemandRate() * product.getSellingPrice() - totalCost));
						System.out.println("");
					}
					break;
				}
				case 5:
					if (store.getProductCounter() <= 1) {
						System.out.println("Sorry no product exists!");
					} else {
						for (int i = 1; i < store.getProductCounter(); i++) {
							System.out.println("Product Information");
							System.out.println("-------------------");
							System.out.println("Product " + i);
							Product product = store.getProduct(store.getProductName(i));
							System.out.println("Name: " + product.getName());
							System.out.println("Demand Rate: " + product.getDemandRate());
							System.out.println("Setup Cost: " + product.getSetupCost());
							System.out.println("Unit Cost: " + product.getUnitCost());
							System.out.println("Inventory Cost: " + product.getInventoryCost());
							System.out.println("Selling Price: " + product.getSellingPrice());
						}
					}
					break;
				case 6: {
					b = false;
					break;
				}
				default: {
					System.out.println("Invalid Choice!");
				}
				}

			}
		}
	}

	/*
	 * Name: Main Parameters: String[] args Return: Description: Executes
	 * Program
	 */
	public static void main(String[] args) {
		File f = new File("input.dat");
		try {
			if(!f.exists()){
				BufferedWriter bw = new BufferedWriter(new FileWriter("input.dat"));
				bw.write("location1:");
				bw.newLine();
				bw.newLine();
				bw.write("Name: spring");
				bw.newLine();
				bw.write("demand rate: 50");
				bw.newLine();
				bw.write("setup cost: 200");
				bw.newLine();
				bw.write("unit cost: 5.2");
				bw.newLine();
				bw.write("inventory cost: 2");
				bw.newLine();
				bw.write("selling price: 3");
				bw.newLine();
				bw.newLine();
				bw.write("Name: bolt");
				bw.newLine();
				bw.write("demand rate: 50");
				bw.newLine();
				bw.write("setup cost: 300");
				bw.newLine();
				bw.write("unit cost: 4");
				bw.newLine();
				bw.write("inventory cost: 2");
				bw.newLine();
				bw.write("selling price: 3.1");
				bw.newLine();
				bw.newLine();
				bw.write("location2:");
				bw.newLine();
				bw.newLine();
				bw.write("Name: linchpin");
				bw.newLine();
				bw.write("demand rate: 100");
				bw.newLine();
				bw.write("setup cost: 200.5");
				bw.newLine();
				bw.write("unit cost: 4");
				bw.newLine();
				bw.write("inventory cost: 2.1");
				bw.newLine();
				bw.write("selling price: 2");
				bw.newLine();
				
				bw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		AcmeWidgetsInterface intFace = new AcmeWidgetsInterface();
		intFace.run();
	}

}