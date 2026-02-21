package tickets;

import cinema.Screening;
import cinema.Seat;

public interface Pricing {
    double calculatePrice(Screening screening, Seat seat);
}
