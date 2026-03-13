import java.util.HashSet;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        int p = 12; // 2^12 = 4096 kova
        HyperLogLog hll = new HyperLogLog(p);
        HashSet<String> realSet = new HashSet<>();

        System.out.println("Veriler ekleniyor...");
        for (int i = 0; i < 100000; i++) {
            String element = UUID.randomUUID().toString();
            hll.add(element);
            realSet.add(element);
        }

        double estimate = hll.estimate();
        int realSize = realSet.size();
        double error = Math.abs(estimate - realSize) / realSize * 100;

        System.out.println("Gerçek Boyut: " + realSize);
        System.out.println("HLL Tahmini: " + (int)estimate);
        System.out.println("Hata Payı: %" + String.format("%.2f", error));
        System.out.println("Teorik Hata Sınırı (1.04/sqrt(m)): %" + String.format("%.2f", (1.04 / Math.sqrt(1 << p)) * 100));
    }
}