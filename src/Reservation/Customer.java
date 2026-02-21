package Reservation;

import cinema.Screening;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Reservation> customerReservation = new ArrayList<>();
    private List<Reservation> customerBooking = new ArrayList<>();

    public Customer (String name){
        this.name = name;
    }

    public void addReservation(Screening screening, List<String> seats) {
        customerReservation.add(new Reservation(this, screening, seats));
    }

    public void addBooking(Screening screening, List<String> seats) {
        customerBooking.add(new Reservation(this, screening, seats));
    }

    public List<Reservation> getCustomerReservation() {
        double totalPrice = 0;
        for (Reservation r : customerReservation) {
            Screening s = r.getScreening();
            for (String seat : r.getSeats()) {
                totalPrice += s.priceForSeat(seat);
            }
        }
        System.out.println(name + " rezerwacja: " + customerReservation + " Kwota do zapłaty: " + totalPrice);
        return customerReservation;
    }

    public List<Reservation> getCustomerBooking() {
        double totalPrice = 0;
        for (Reservation r : customerBooking) {
            Screening s = r.getScreening();
            for (String seat : r.getSeats()) {
                totalPrice += s.priceForSeat(seat);
            }
        }
        System.out.println(name + " kupione bilety: " + customerBooking + " Zapłacono: " + totalPrice);
        return customerBooking;
    }
    public String getName() {
        return name;
    }

}
