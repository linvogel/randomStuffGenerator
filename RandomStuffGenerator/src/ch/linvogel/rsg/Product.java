package ch.linvogel.rsg;

public class Product {
	
	public String id;
	public String name;
	public int price;
	public int stock;
	
	public Product(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.id = UUID.v4();
	}
	
	public String toCSV() {
		return new StringBuilder(7).append(id).append(",").append(name.replace(",", ".")).append(",").append(price).append(",").append(stock).toString();
	}
	
}
