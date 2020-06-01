package pl.us;

public class Cash extends Payment {
	@Override
	public void pay(Order someOrder) {
		someOrder.setOrderStatus(true);
	}

	@Override
	public void printTransferConfirmation() {
		System.out.println("\u001B[36m" + "Paid by CASH" + "\u001B[0m");
	}
}
