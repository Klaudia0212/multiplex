package cinema;

import set.SeatCodeComparator;
import set.Zone;

import java.util.*;

public class Auditorium {

    private String name;
    private List<Screening> screeningList;
    private Map<String, Seat> seats;
    private Cinema cinema;

    public Auditorium(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.screeningList = new ArrayList<>();
        this.seats = new HashMap<>();

        for (int r = 0; r < rows; r++) {
            char rowLetter = (char) ('A' + r);
            Zone zone = pickZone(r, rows);

            for (int n = 1; n <= seatsPerRow; n++) {
                Seat seat = new Seat(String.valueOf(rowLetter), n, zone);
                seats.put(seat.seatCode(), seat);
            }
        }
    }

    public boolean hasSeat(String code) {
        return seats.containsKey(code);
    }

    public Seat getSeat(String code) {
        return seats.get(code);
    }

    public Set<String> getSeatCodes() {
        Set<String> sorted = new TreeSet<>(new SeatCodeComparator());
        sorted.addAll(seats.keySet());
        return sorted;
    }

    private Zone pickZone(int rowIndex, int totalRows) {
        int vipRows = Math.max(1, (int) Math.round(totalRows * 0.15));
        int standardRows = Math.max(1, (int) Math.round(totalRows * 0.45));
        int promoRows = Math.max(0, totalRows - vipRows - standardRows);

        int vipEnd = vipRows;
        int standardEnd = vipEnd + standardRows;
        int promoEnd = standardEnd + promoRows;

        if (rowIndex < vipEnd) return Zone.VIP;
        if (rowIndex < standardEnd) return Zone.STANDARD;
        return Zone.PROMO;
    }

    public List<Screening> addScreening(Screening screening){
        screening.setAuditorium(this);
        screeningList.add(screening);
        return screeningList;
    }

    public String getName() {
        return name;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public List<Screening> getScreeningList() {
        return screeningList;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }
}
