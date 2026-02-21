package tickets;

import cinema.Screening;
import cinema.Seat;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator implements Pricing {

    private PriceList priceList;
    private List<PriceModifier> modifiers = new ArrayList<>();

    public PriceCalculator(PriceList priceList){
        this.priceList = priceList;
        modifiers.add(new ThreeDCharge(priceList));
    }

    @Override
    public double calculatePrice(Screening screening, Seat seat) {
        double price = priceList.getBasePrice(seat.getZone());
        for (PriceModifier m : modifiers) {
            price += m.modify(screening, seat);
        }
        return price;
    }

    @Override
    public String toString() {
        return "Cennik: " + priceList;
    }
}
