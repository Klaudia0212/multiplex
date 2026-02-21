package tickets;

import set.Zone;

import java.util.EnumMap;
import java.util.Map;

public class PriceList {
    private Map<Zone, Double> basePrice = new EnumMap<>(Zone.class);
    private double charge3D = 10.00;

    public void setBasePrice(Zone zone, double price) {
        basePrice.put(zone, price);
    }

    public double getBasePrice(Zone zone) {
        Double p = basePrice.get(zone);
        if (p == null) throw new IllegalStateException("Brak ceny bazowej dla: " + zone);
        return p;
    }

    public double getCharge3D() {
        return charge3D;
    }

    public void setCharge3D(double charge3D) {
        this.charge3D = charge3D;
    }

    @Override
    public String toString() {
        return "VIP = " + getBasePrice(Zone.VIP) +
                ", STANDARD = " + getBasePrice(Zone.STANDARD) +
                ", PROMO = " + getBasePrice(Zone.PROMO);
    }
}
