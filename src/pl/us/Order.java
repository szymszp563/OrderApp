package pl.us;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
	private LocalDateTime executionDate = LocalDateTime.now();
	private boolean orderStatus;
	private String orderId;
	private List<OrderPosition> orderPositionList = new ArrayList<>();
	private boolean electronicConfirmation;

	public Order(boolean electronicConfirmation) {
		Random random = new Random();
		this.orderId = random.ints(48, 123)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(9)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		this.electronicConfirmation = electronicConfirmation;
	}

	public double orderValue() {
		return this.orderPositionList.isEmpty() ? 0d : this.orderPositionList.stream().mapToDouble(OrderPosition::grossValueCalculate).sum();
	}

	public double taxValue() {
		return this.orderPositionList.isEmpty() ? 0d : this.orderPositionList.stream().mapToDouble(OrderPosition::taxCalculate).sum();
	}

	public boolean pay(Payment somePayment) {
		somePayment.pay(this);
		this.printTransferConfirmation(somePayment);
		return this.orderStatus;
	}

	public void printTransferConfirmation(Payment somePayment) {

		Inote inote = electronicConfirmation ? new Email("customer@us.edu.pl") : new Printer();

		inote.printTransferConfirmation();

		somePayment.printTransferConfirmation();
	}

	public void showOrders() {
		orderPositionList.forEach(op -> System.out.println("\u001B[33m" + op.toString() + "\u001B[0m"));
	}

	public boolean addOrderItem(OrderPosition someOrder) {
		return orderPositionList.add(someOrder);
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getExecutionDate() {
		return executionDate;
	}

	public String getOrderId() {
		return orderId;
	}
}
