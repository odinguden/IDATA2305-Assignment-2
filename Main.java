import booking.MoveTicketServerAtomic;
import booking.MovieTicketClient;
import booking.MovieTicketServer;
import booking.MovieTicketServerSync;
import booking.MovieTicketServerVolatile;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		MovieTicketServer movieTicketServer = new MovieTicketServer(
			10,
			"Troll"
		);

		if (args.length > 0) {
			String type = args[0].toLowerCase();
			switch (type) {
				case "synchronized":
				case "sync":
				case "s":
					System.out.println("Starting in synchronized mode");
					movieTicketServer = new MovieTicketServerSync(
						10,
						"Troll"
					);
					break;
				case "volatile":
				case "vol":
				case "v":
					System.out.println("Starting in volatile mode");
					movieTicketServer = new MovieTicketServerVolatile(
						10,
						"Troll"
					);
					break;
				case "atomic":
				case "atom":
				case "a":
					System.out.println("Starting in atomic mode");
					movieTicketServer = new MoveTicketServerAtomic(
						10,
						"Troll"
					);
					break;
				default:
			}
		}

		// Creating 4 threads
		Thread t1 = new MovieTicketClient("Xiangming", movieTicketServer, 3);
		Thread t2 = new MovieTicketClient("Ilaria", movieTicketServer, 2);
		Thread t3 = new MovieTicketClient("Sam", movieTicketServer, 3);
		Thread t4 = new MovieTicketClient("Andreas", movieTicketServer, 4);

		long startTime = System.nanoTime();

		// Starting all threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		long endTime = System.nanoTime();

		t1.join();
		t2.join();
		t3.join();
		t4.join();

		long dTime = endTime - startTime;

		System.out.println("" + dTime + "ns elapsed");
	}
}

