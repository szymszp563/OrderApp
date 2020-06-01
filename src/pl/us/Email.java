package pl.us;

public class Email implements Inote {
	private String email;

	public Email(String email) {
		this.email = email;
	}

	@Override
	public void printTransferConfirmation() {
		System.out.println("\u001B[36m" + "Using EMAIL, email address: " + this.email + ", transfer confirmation message:" + "\u001B[0m");

	}
}
