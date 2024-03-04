package booking;

import java.util.concurrent.atomic.AtomicInteger;

public class MoveTicketServerAtomic extends MovieTicketServer {
	private String movieName;
	private AtomicInteger availableSeats;

	public MoveTicketServerAtomic(int seats, String movieName) {
		super(seats, movieName);
		this.movieName = movieName;
		this.availableSeats = new AtomicInteger(seats);
	}

	@Override
	public void bookTicket(String userName, int numberOfSeats) {
		System.out.println(userName + ": " + availableSeats.get() + " seats available for " + movieName);

		if ((availableSeats.get() - numberOfSeats) < 0) {
			System.out.println(userName + ": Seats not available for " + movieName);
			return;
		}
		availableSeats.set(availableSeats.get() - numberOfSeats);
		System.out.println(userName + ": " + numberOfSeats + " seats booked successfully for" + movieName);
		System.out.println(userName + ": " + availableSeats.get() + " remaining for " + movieName);
	}
}
