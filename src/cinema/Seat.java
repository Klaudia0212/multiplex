package cinema;

import set.Zone;

public class Seat {

    private String row;
    private int number;
    private Zone zone;

    public Seat(String row, int number, Zone zone) {
        this.row = row;
        this.number = number;
        this.zone = zone;
    }

    public String seatCode(){
        return row + number;
    }

    public Zone getZone(){
        return zone;
    }
}
