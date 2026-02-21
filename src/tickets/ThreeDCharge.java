package tickets;

import cinema.Screening;
import cinema.Seat;
import set.ScreeningType;

public class ThreeDCharge implements PriceModifier {
    private final PriceList priceList;

    public ThreeDCharge(PriceList priceList) {
        this.priceList = priceList;
    }

    @Override
    public double modify(Screening screening, Seat seat) {
        return screening.getType() == ScreeningType.THREE_D ? priceList.getCharge3D() : 0.0;
    }
}