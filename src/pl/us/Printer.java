package pl.us;

public class Printer implements Inote {

	public Printer() {
	}

	@Override
	public void printTransferConfirmation() {
		System.out.println("\u001B[36m" + "Using PRINTER, transfer confirmation message:" + "\u001B[0m");
	}
}
