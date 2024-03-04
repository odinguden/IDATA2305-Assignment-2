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
		System.out.println("Hi, " + userName + " : " + availableSeats.get() + " : Seats available for " + movieName);

		if ((availableSeats.get() - numberOfSeats) < 0) {
			System.out.println("Hi, " + userName + " : Seats not available for " + movieName);
			return;
		}
		availableSeats.set(availableSeats.get() - numberOfSeats);
		System.out.println("Hi, " + userName + " : " + numberOfSeats + " Seats booked successfully for" + movieName);
		System.out.println(availableSeats.get() + " remaining for " + movieName);
	}
}
