package pl.us;

public abstract class Payment {
	protected double paymentAmount;
	private long paymentId;

	public abstract void pay(Order someOrder);
	public abstract void printTransferConfirmation();
}
