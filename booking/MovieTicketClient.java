package booking;

public class MovieTicketClient extends Thread {
	private String name;
	private MovieTicketServer movieTicketServer;
	private int numberOfSeats;


	public MovieTicketClient(String userName, MovieTicketServer movieTicketServer, int numberOfTickets) {
		name = userName;
		this.movieTicketServer = movieTicketServer;
		this.numberOfSeats = numberOfTickets;
	}

	@Override
	public void run() {
		movieTicketServer.bookTicket(name, numberOfSeats);
	}

}
