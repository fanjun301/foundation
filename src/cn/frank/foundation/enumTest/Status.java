package cn.frank.foundation.enumTest;

public enum Status {

	OPEN(1, "open"), PENDING(2, "pending"), CLOSE(3, "close");

	private int code = 0;
	private String message = "";

	private Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public void displayMessage(){
		System.out.println(this.getMessage());
	}

}
