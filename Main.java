import booking.MovieTicketClient;
import booking.MovieTicketServer;

public class Main {
    public static void main(String[] args) {
        MovieTicketServer movieTicketServer = new MovieTicketServer(
					10,
					"Troll"
				);

        // Creating 4 threads
        Thread t1 = new MovieTicketClient("Xiangming", movieTicketServer, 3);
        Thread t2 = new MovieTicketClient("Ilaria", movieTicketServer, 2);
        Thread t3 = new MovieTicketClient("Sam", movieTicketServer, 3);
        Thread t4 = new MovieTicketClient("Andreas", movieTicketServer, 4);

        // Starting all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

