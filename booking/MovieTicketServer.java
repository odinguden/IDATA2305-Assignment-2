package booking;

public class MovieTicketServer {
	private String movieName;
	private int availableSeats;

	public MovieTicketServer(int seats, String movieName) {
		this.movieName = movieName;
		this.availableSeats = seats;
	}

	public void bookTicket(String userName, int numberOfSeats) {
		System.out.println(userName + ": " + availableSeats + " seats available for " + movieName);

		if ((availableSeats - numberOfSeats) < 0) {
			System.out.println(userName + ": Seats not available for " + movieName);
			return;
		}
		availableSeats = availableSeats - numberOfSeats;
		System.out.println(userName + ": " + numberOfSeats + " seats booked successfully for" + movieName);
		System.out.println(userName + ": " + availableSeats + " remaining for " + movieName);
	}

}
