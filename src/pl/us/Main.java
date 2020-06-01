package pl.us;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RED = "\u001B[31m";


	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Hello!");
    	for(;;){
			System.out.println("Let's create new order!\nIs this order going to use electronic confirmation?(Y/N)");
			Order order = new Order(booleanValueTyped(in));
			System.out.println(ANSI_YELLOW + "Created new order (id: " + order.getOrderId() + ") at " + order.getExecutionDate() + ANSI_RESET);
			if(!menu(in, order)) {
				System.out.println("Good bye!");
				break;
			}

		}

    }

	public static boolean booleanValueTyped(BufferedReader in) throws IOException {
    	String value = in.readLine().toUpperCase();
    	if(value.equals("Y") || value.equals("N")){
    		return value.equals("Y");
		}
		System.out.println(ANSI_RED + "Wrong value typed, try again (Choose correct value - Y/N)" + ANSI_RESET);
    	return booleanValueTyped(in);
	}

	public static int number1234ValueTyped(BufferedReader in) throws IOException {
    	String value = in.readLine();
    	if(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")){
    		return Integer.parseInt(value);
		}
		System.out.println(ANSI_RED + "Wrong value typed, try again. (Choose correct value - 1 or 2 or 3 or 4)" + ANSI_RESET);
    	return number1234ValueTyped(in);
	}

	public static int number123ValueTyped(BufferedReader in) throws IOException {
    	String value = in.readLine();
    	if(value.equals("1") || value.equals("2") || value.equals("3")){
    		return Integer.parseInt(value);
		}
		System.out.println(ANSI_RED + "Wrong value typed, try again. (Choose correct value - 1 or 2 or 3)" + ANSI_RESET);
    	return number123ValueTyped(in);
	}

	public static boolean menu(BufferedReader in, Order order) throws IOException {
		System.out.println("What would you like to do? Type:\n1 - to see current total order value\n2 - to see current total tax value\n3 - to add next order position\n4 - to pay and print transfer confirmation");
		boolean end = false;
		switch (number1234ValueTyped(in)) {
		case 1:
			System.out.println(ANSI_YELLOW + "Current total value: " + order.orderValue() + ANSI_RESET);
			break;
		case 2:
			System.out.println(ANSI_YELLOW + "Current total tax value: " + order.taxValue()+ ANSI_RESET);
			break;
		case 3:
			order.addOrderItem(createOrder(in));
			System.out.println(ANSI_YELLOW + "Current order position list:" + ANSI_RESET);
			order.showOrders();
			break;
		case 4:
			order.pay(getPaymentType(in));
			end = true;
			break;
		}
		if(!end){
			return menu(in, order);
		}
		System.out.println("Would you like to create new order?(Y/N)");
		return booleanValueTyped(in);
	}

	public static OrderPosition createOrder(BufferedReader in) throws IOException {
		System.out.println("Type item name:");
		String itemName = in.readLine();
		System.out.println("Type unit price value(XX.XX):");
		float unitPrice = provideValidFloatValue(in);
		System.out.println("Type tax value(23 or 8 or 5 or 0):");
		int vat = provideValidIntegerTaxValue(in);
		System.out.println("Type quantity value(XX.XX):");
		float quantity = provideValidFloatValue(in);
		return new OrderPosition(itemName, unitPrice, vat, quantity);
	}

	public static float provideValidFloatValue(BufferedReader in) throws IOException {
    	try{
    		return Float.valueOf(in.readLine());
		} catch (NumberFormatException ex) {
			System.out.println(ANSI_RED + "Wrong float value typed, try again (XX.XX)" + ANSI_RESET);
    		return provideValidFloatValue(in);
		}
	}

	public static int provideValidIntegerTaxValue(BufferedReader in) throws IOException {
    	try{
			String value = in.readLine();
			if(!(value.equals("23") || value.equals("8") || value.equals("5") || value.equals("0"))){
				System.out.println(ANSI_RED + "Wrong vat value typed, try again (23 or 8 or 5 or 0)" + ANSI_RESET);
				return provideValidIntegerTaxValue(in);
			}
    		return Integer.valueOf(value);
		} catch (NumberFormatException ex) {
			System.out.println(ANSI_RED + "Wrong vat value typed, try again (23 or 8 or 5 or 0)" + ANSI_RESET);
    		return provideValidIntegerTaxValue(in);
		}
	}

	public static Payment getPaymentType(BufferedReader in) throws IOException {
		System.out.println("Choose form of payment:\n1 - CASH\n2 - CREDIT\n3 - BANK TRANSFER");
		switch (number123ValueTyped(in)){
		case 1:
			return new Cash();
		case 2:
			return new Credit((byte)100, "LoanBankUS", 6.9f);
		case 3:
			System.out.println("Type bank account");
			String bankAccount = in.readLine();
			System.out.println("Type transfer title");
			String transferTitle = in.readLine();
			return new BankTransfer(bankAccount, transferTitle);
		}

		throw new RuntimeException(ANSI_RED + "Something went wrong!" + ANSI_RESET);
	}
}
