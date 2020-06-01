package pl.us;

public class Credit extends Payment {

	private byte loanPaymentNumber;
	private String bank;
	private float annualEquivalentRate;

	public Credit(byte loanPaymentNumber, String bank, float annualEquivalentRate) {
		this.loanPaymentNumber = loanPaymentNumber;
		this.bank = bank;
		this.annualEquivalentRate = annualEquivalentRate;
	}

	@Override
	public void pay(Order someOrder) {
		someOrder.setOrderStatus(true);
	}

	@Override
	public void printTransferConfirmation() {
		System.out.println("\u001B[36m" + "Paid by CREDIT\nLoan Payment Number: " + this.loanPaymentNumber + "\nBank: " + this.bank + "\nAnnual Equivalent Rate: " + this.annualEquivalentRate + "%" + "\u001B[0m");
	}
}
