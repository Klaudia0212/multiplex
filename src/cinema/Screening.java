package cinema;

import Reservation.Customer;
import tickets.Pricing;
import set.ScreeningType;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Screening {

    private ScreeningType type;
    private LocalDate day;
    private Movie movie;
    private LocalTime time;
    private Auditorium auditorium;
    private Pricing pricing;
    private List<String> reservedSeats = new ArrayList<>();
    private List<String> soldSeats = new ArrayList<>();
    private Map<String, Customer> reservedBy = new HashMap<>();
    private Map<String, Customer> bookedBy = new HashMap<>();


    public Screening(ScreeningType type, Movie movie, LocalDate day, LocalTime time){
        this.type = type;
        this.day = day;
        this.movie = movie;
        this.time = time;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public List<String> availableSeats() {
        List<String> available = new ArrayList<>(auditorium.getSeatCodes());
        available.removeAll(reservedSeats);
        available.removeAll(soldSeats);
        return available;
    }

    public void reserveSeats(String... seatNumbers) {
        for (String seat : seatNumbers) {
            if (!availableSeats().contains(seat)) {
                throw new IllegalArgumentException("Nie ma takiego miejsca (lub zajęte): " + seat);
            }
        }
        for (String seat : seatNumbers) {
            reservedSeats.add(seat);
        }
    }

    public void reserveSeats(Customer customer, String... seatNumbers) {
        List<String> available = availableSeats();
        for (String seat : seatNumbers) {
            if (!available.contains(seat)) {
                throw new IllegalArgumentException("Nie ma takiego miejsca (lub zajęte): " + seat);
            }
        }
        List<String> seats = new ArrayList<>();
        for (String seat : seatNumbers) {
            reservedSeats.add(seat);
            reservedBy.put(seat, customer);
            seats.add(seat);
        }
        customer.addReservation(this, seats);
    }

    public void buyTicket(String... seatNumbers) {
        for (String seat : seatNumbers) {
            if (!availableSeats().contains(seat)) {
                throw new IllegalArgumentException("Nie ma takiego miejsca (lub zajęte): " + seat);
            }
        }
        for (String seat : seatNumbers) {
            soldSeats.add(seat);
        }
    }

    public void buyTicket(Customer customer, String... seatNumbers) {
        List<String> available = availableSeats();
        for (String seat : seatNumbers) {
            if (!available.contains(seat)) {
                throw new IllegalArgumentException("Nie ma takiego miejsca (lub zajęte): " + seat);
            }
        }
        List<String> seats = new ArrayList<>();
        for (String seat : seatNumbers) {
            soldSeats.add(seat);
            bookedBy.put(seat, customer);
            seats.add(seat);
        }
        customer.addBooking(this, seats);
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public double priceForSeat(String seatCode) {
        Seat seat = auditorium.getSeat(seatCode);
        return pricing.calculatePrice(this, seat);
    }

    public void checkAvailableSeats(){
        List<String> available = new ArrayList<>(availableSeats());

        for (int i = 0; i < available.size(); i++) {
            String code = available.get(i);
            Seat seat = auditorium.getSeat(code);

            System.out.print(code + "-" + seat.getZone() + "  ");
        }
        System.out.println();
    }

    public ScreeningType getType() {
        return type;
    }

    public LocalDate getDay() {
        return day;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalTime getTime() {
        return time;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public List<String> getReservedSeats() {
        return reservedSeats;
    }

    public List<String> getSoldSeats() {
        return soldSeats;
    }

    @Override
    public String toString() {
        String text =  "Sala " + type +
                " dzień: " + day +
                ", Seans: "+ "'" + movie + "'" +
                ", godzina: " + time
                + " | " + getPricing() ;
        if ( type == ScreeningType.THREE_D){
            text += " | dopłata za seans w 3D: + 10 ";
        }
       return text;
    }
}
