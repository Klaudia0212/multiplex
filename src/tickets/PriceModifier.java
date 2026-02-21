package tickets;

import cinema.Screening;
import cinema.Seat;

public interface PriceModifier {
    double modify(Screening screening, Seat seat);
}