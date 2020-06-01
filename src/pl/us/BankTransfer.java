package pl.us;

public class BankTransfer extends Payment {

	private String bankAccount;
	private String transferTitle;

	public BankTransfer(String bankAccount, String transferTitle) {
		this.bankAccount = bankAccount;
		this.transferTitle = transferTitle;
	}

	@Override
	public void pay(Order someOrder) {
		someOrder.setOrderStatus(true);
	}

	@Override
	public void printTransferConfirmation() {
		System.out.println("\u001B[36m" + "Paid by BANK TRANSFER\nBank Account: " + this.bankAccount + "\nTransfer Title: " + this.transferTitle + "\u001B[0m");
	}
}
