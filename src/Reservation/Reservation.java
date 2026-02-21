package Reservation;

import cinema.Screening;

import java.util.List;

public class Reservation {

    private Customer customer;
    private Screening screening;
    private List<String> seats;

    public Reservation (Customer customer, Screening screening, List<String> seats){
        this.customer = customer;
        this.screening = screening;
        this.seats = seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Screening getScreening() {
        return screening;
    }

    public List<String> getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        String cinemaName = screening.getAuditorium().getCinema().getCinemaName();
        String auditoriumName = screening.getAuditorium().getName();
        return
                "Kino: "  + cinemaName +
                        " | Seans: " + "'" + screening.getMovie().getName() + "'" +
                        "| dzień: " + screening.getDay() +
                        ", godzina: " + screening.getTime() +
                        " | sala: " + auditoriumName +
                        "| miejsce: " + seats;
    }
}
