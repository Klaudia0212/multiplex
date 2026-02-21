package set;

import java.util.Comparator;

public class SeatCodeComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        char rowA = a.charAt(0);
        char rowB = b.charAt(0);
        if (rowA != rowB) return Character.compare(rowA, rowB);

        int numA = Integer.parseInt(a.substring(1));
        int numB = Integer.parseInt(b.substring(1));
        return Integer.compare(numA, numB);
    }
}