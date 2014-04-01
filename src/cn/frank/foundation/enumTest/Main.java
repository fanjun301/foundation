package cn.frank.foundation.enumTest;

public class Main {

	public static void main(String[] args) {

		basicOperation();
		switchOperation(Status.OPEN);
	}

	private static void switchOperation(Status so) {
		switch (so) {
		case OPEN:
			System.out.println("This is OPEN status");
			break;
		case PENDING:
			System.out.println("This is PENDING status");
			break;

		default:
			break;
		}
	}

	private static void basicOperation() {
		Status so = Status.valueOf("OPEN");
		so.displayMessage();
		System.out.println(so.ordinal());
		System.out.println(String.format("code: %s , message %s", so.getCode(),
				so.getMessage()));
	}

}
