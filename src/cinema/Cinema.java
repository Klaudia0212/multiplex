package cinema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private String cinemaName;
    private String address;
    private List<Auditorium> auditoriums;

    public Cinema (String cinemaName, String address){
        this.cinemaName = cinemaName;
        this.address = address;
        this.auditoriums = new ArrayList<Auditorium>();
    }

    public void addAuditorium(Auditorium auditorium){
        auditoriums.add(auditorium);
        auditorium.setCinema(this);
    }

   public void printProgram(LocalDate from, int days){
       LocalDate end = from.plusDays(days);

       List<Screening> cinemaScreenings = new ArrayList<>();
       for (Auditorium auditorium : auditoriums) {
           cinemaScreenings.addAll(auditorium.getScreeningList());
       }

       for (Screening s : cinemaScreenings) {
           LocalDate d = s.getDay();
           if (!d.isBefore(from) && d.isBefore(end)) {
               System.out.println(s);
           }
       }
   }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }
}
