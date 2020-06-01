package pl.us;

public class OrderPosition {

	private String itemName;
	private float unitPrice;
	private int vat;
	private float quantity;

	public OrderPosition(String itemName, float unitPrice, int vat, float quantity) {
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.vat = vat;
		this.quantity = quantity;
	}

	public double grossValueCalculate() {
		return unitPrice * quantity + taxCalculate();
	}

	public double taxCalculate() {
		return unitPrice * quantity * vat / 100;
	}

	@Override
	public String toString() {
		return "OrderPosition{" + "itemName='" + itemName + '\'' + ", unitPrice=" + unitPrice + ", vat=" + vat + ", quantity=" + quantity
				+ '}';
	}
}
