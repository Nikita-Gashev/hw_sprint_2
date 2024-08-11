package src;

import java.util.HashMap;

class YearlyReport {
    private HashMap<String, Integer> yearlyReport = new HashMap<>();

    void putData(String month, Integer data) {
        yearlyReport.put(month, data);
    }

    int getAmountPerMonth(String month) {
        return yearlyReport.get(month);
    }

    int getSum() {
        int sum = 0;
        for (Integer amount : yearlyReport.values()) {
            sum += amount;
        }
        return sum;
    }
}
