import java.util.HashMap;
import java.util.Map;

public class RateLookup {

    private static final Map<String, Double> commissionRateMap = new HashMap<>();
    private static final Map<String, Double> overridingRateMap = new HashMap<>();

    static {
        // CommissionRate: key = "policyYear:ageGroup"
        commissionRateMap.put("1:AGE_0_50", 2.0);
        commissionRateMap.put("2:AGE_0_50", 1.0);
        commissionRateMap.put("3:AGE_0_50", 1.0);

        commissionRateMap.put("1:AGE_51_60", 2.0);
        commissionRateMap.put("2:AGE_51_60", 2.0);
        commissionRateMap.put("3:AGE_51_60", 1.0);

        // OverridingRate: key = "isAnnual:ageGroup"
        overridingRateMap.put("true:AGE_0_50", 20.0);
        overridingRateMap.put("false:AGE_0_50", 16.0);
        overridingRateMap.put("any:AGE_51_60", 13.0); // same regardless of isAnnual
    }

    public static double CommissionRate(int policyYear, int age) {
        String ageGroup = (age >= 0 && age <= 50) ? "AGE_0_50" :
                          (age >= 51 && age <= 60) ? "AGE_51_60" : "UNKNOWN";
        return commissionRateMap.getOrDefault(policyYear + ":" + ageGroup, 0.0);
    }

    public static double OverridingRate(boolean isAnnual, int age) {
        String ageGroup = (age >= 0 && age <= 50) ? "AGE_0_50" :
                          (age >= 51 && age <= 60) ? "AGE_51_60" : "UNKNOWN";
        String key = (ageGroup.equals("AGE_51_60")) ? "any:" + ageGroup :
                     isAnnual + ":" + ageGroup;
        return overridingRateMap.getOrDefault(key, 0.0);
    }

    public static void main(String[] args) {
        System.out.println("CommissionRate: " + CommissionRate(1, 25) + "%");
        System.out.println("OverridingRate: " + OverridingRate(true, 25) + "%");
    }
}
