package ru.hw_sprint_2.model;

import java.util.HashMap;
import java.util.Map;

public class YearlyReport {
    private Map<String, Integer> yearlyReport = new HashMap<>();

    public void putData(String month, Integer data) {
        yearlyReport.put(month, data);
    }

    public int getAmountPerMonth(String month) {
        return yearlyReport.get(month);
    }

    public int getSum() {
        int sum = 0;
        for (Integer amount : yearlyReport.values()) {
            sum += amount;
        }
        return sum;
    }
}
