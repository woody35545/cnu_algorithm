package view;

import java.util.Scanner;

public final class AppView {
	private static Scanner scanner = new Scanner(System.in);

	private AppView() {

	}

	public static int inputInt() throws NumberFormatException {
		return Integer.parseInt(AppView.scanner.next());
	}

	public static String inputString() {
		return AppView.scanner.next();	
	}

	public static char inputChar() {
		return AppView.scanner.next().charAt(0);
	}

	private static final boolean DEBUG_MODE = true;

	public static void outputDebugMessage(String aString) {
		if (DEBUG_MODE) {
			System.out.print(aString);
		}
	}

	public static void outputLine(String aString) {
		System.out.println(aString);
	}

	public static void output(String aString) {
		System.out.print(aString);
	}

}
