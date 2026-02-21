package main;

import Reservation.Customer;
import tickets.PriceCalculator;
import tickets.PriceList;
import cinema.Auditorium;
import cinema.Cinema;
import cinema.Movie;
import cinema.Screening;
import set.Zone;

import java.time.LocalDate;
import java.time.LocalTime;


import static set.ScreeningType.THREE_D;
import static set.ScreeningType.TWO_D;

public class Main {

    public static void main(String[] args){

        //konfiguracja
        Cinema c1 = new Cinema("Multikino", "Kraków");
        Cinema c2 = new Cinema("Cinema City", "Kraków");

        PriceList basePrices = new PriceList();
        basePrices.setBasePrice(Zone.VIP, 40.00);
        basePrices.setBasePrice(Zone.STANDARD, 30.00);
        basePrices.setBasePrice(Zone.PROMO, 20.00);

        PriceCalculator calculatePrice = new PriceCalculator(basePrices);

        Auditorium a1 = new Auditorium("sala 1", 3, 5);
        Auditorium a2 = new Auditorium( "sala 2", 6, 5);
        Auditorium a3 = new Auditorium( "sala 1", 6, 5);

        Movie m1 = new Movie("Król Lew");
        Movie m2 = new Movie("Władca Pierścieni");
        Movie m3 = new Movie("Harry Potter");

        Screening s1 = new Screening(TWO_D, m1, LocalDate.of(2026,03,10), LocalTime.of(15, 30));
        Screening s2 = new Screening(THREE_D, m2,LocalDate.of(2026,03,10), LocalTime.of(18, 30));
        Screening s3 = new Screening(TWO_D, m3, LocalDate.of(2026,03,11), LocalTime.of(18, 30));
        Screening s4 = new Screening(TWO_D, m3, LocalDate.of(2026,03,20), LocalTime.of(21, 00));

        s1.setPricing(calculatePrice);
        s2.setPricing(calculatePrice);
        s3.setPricing(calculatePrice);
        s4.setPricing(calculatePrice);

        c1.addAuditorium(a1);
        c1.addAuditorium(a2);
        c2.addAuditorium(a3);

        a1.addScreening(s1);
        a1.addScreening(s2);
        a2.addScreening(s3);
        a3.addScreening(s4);

        // przykładowa funcjonalność:

        c1.printProgram(LocalDate.of(2026,03,10), 7);

        s1.checkAvailableSeats();

        s1.reserveSeats("A1");

        Customer Klaudia = new Customer("Klaudia");

        s1.reserveSeats(Klaudia, "A5", "A2");
        s2.reserveSeats(Klaudia, "A3");
        Klaudia.getCustomerReservation();

        s2.buyTicket(Klaudia, "A2");
        s4.buyTicket(Klaudia, "A4");
        Klaudia.getCustomerBooking();
    }
}
