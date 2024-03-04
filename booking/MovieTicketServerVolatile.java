package booking;

public class MovieTicketServerVolatile extends MovieTicketServer {
	private String movieName;
	private volatile int availableSeats;

	public MovieTicketServerVolatile(int seats, String movieName) {
		super(seats, movieName);
		this.movieName = movieName;
		this.availableSeats = seats;
	}

	@Override
	public void bookTicket(String userName, int numberOfSeats) {
		System.out.println("Hi, " + userName + " : " + availableSeats + " : Seats available for " + movieName);

		if ((availableSeats - numberOfSeats) < 0) {
			System.out.println("Hi, " + userName + " : Seats not available for " + movieName);
			return;
		}
		availableSeats = availableSeats - numberOfSeats;
		System.out.println("Hi, " + userName + " : " + numberOfSeats + " Seats booked successfully for" + movieName);
		System.out.println(availableSeats + " remaining for " + movieName);
	}
}
