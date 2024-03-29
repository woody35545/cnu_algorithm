
import java.util.Scanner;

public final class AppView {
	private static Scanner scanner = new Scanner(System.in);

	private AppView() {

	}

	public static void outputLine(String aString) {
		System.out.println(aString);
	}

	public static void output(String aString) {
		System.out.print(aString);
	}

	public static int inputInt() {
		int scannedInt;
		String scannedToken;
		while (true) {
			scannedToken = AppView.scanner.next();
			try {
				scannedInt = Integer.parseInt(scannedToken);
				return scannedInt;

			} catch (NumberFormatException e) {
			}
		}
	}

	public static int inputNumberOfVertices() {
		int numberOfVertices;
		String scannedToken;
		while (true) {
			AppView.output("? vertex 수를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;

			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) vertex 수 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}

	public static int inputNumberOfEdges() {
		int numberOfEdge;
		String scannedToken;
		while (true) {
			AppView.output("? edge 수를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				numberOfEdge = Integer.parseInt(scannedToken);
				return numberOfEdge;

			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) edge 수 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}

	public static int inputTailVertex() {
		int tailVertex;
		String scannedToken;
		while (true) {
			AppView.output("? tail vertex 를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;

			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) tail vertex 수 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}

	public static int inputHeadVertex() {
		int headVertex;
		String scannedToken;
		while (true) {
			AppView.output("? head vertex 수를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;

			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) head vertex 수 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}
}
