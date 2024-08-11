package src;

import java.util.ArrayList;
import java.util.HashMap;

class MonthlyReport {
    private HashMap<String, ArrayList<Transaction>> monthlyReport = new HashMap<>();

    void putData(String month, ArrayList<Transaction> data) {
        monthlyReport.put(month, data);
    }

    int getSumIncomePerMonth(String month) {
        ArrayList<Transaction> transactions = monthlyReport.get(month);
        int sumIncome = 0;
        for (Transaction transaction : transactions) {
            if (!transaction.isExpense) {
                sumIncome = sumIncome + transaction.quantity * transaction.unitPrice;
            }
        }
        return sumIncome;
    }

    int getSumConsumptionPerMonth(String month) {
        ArrayList<Transaction> transactions = monthlyReport.get(month);
        int sumConsumption = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                sumConsumption = sumConsumption + transaction.quantity * transaction.unitPrice;
            }
        }
        return sumConsumption;
    }

    int findMaxIncomeValuePerMonth(String month) {
        ArrayList<Transaction> transactions = monthlyReport.get(month);
        int maxIncome = 0;
        for (Transaction transaction : transactions) {
            if (!transaction.isExpense) {
                int income = transaction.quantity * transaction.unitPrice;
                if (income > maxIncome) {
                    maxIncome = income;
                }
            }
        }
        return maxIncome;
    }

    String findMaxIncomeValueNamePerMonth(String month) {
        ArrayList<Transaction> transactions = monthlyReport.get(month);
        int maxIncome = 0;
        String nameMaxIncome = "";
        for (Transaction transaction : transactions) {
            if (!transaction.isExpense) {
                int income = transaction.quantity * transaction.unitPrice;
                if (income > maxIncome) {
                    maxIncome = income;
                    nameMaxIncome = transaction.name;
                }
            }
        }
        return nameMaxIncome;
    }

    int findMaxConsumptionValuePerMonth(String month) {
        ArrayList<Transaction> transactions = monthlyReport.get(month);
        int maxConsumption = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                int consumption = transaction.quantity * transaction.unitPrice;
                if (consumption > maxConsumption) {
                    maxConsumption = consumption;
                }
            }
        }
        return maxConsumption;
    }

    String findMaxConsumptionValueNamePerMonth(String month) {
        ArrayList<Transaction> transactions = monthlyReport.get(month);
        int maxConsumption = 0;
        String nameMaxConsumption = "";
        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                int income = transaction.quantity * transaction.unitPrice;
                if (income > maxConsumption) {
                    maxConsumption = income;
                    nameMaxConsumption = transaction.name;
                }
            }
        }
        return nameMaxConsumption;
    }


}
