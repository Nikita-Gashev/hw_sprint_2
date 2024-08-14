package ru.hw_sprint_2.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MonthlyReport {
    private Map<String, List<Transaction>> monthlyReport = new HashMap<>();

    public void putData(String month, List<Transaction> data) {
        monthlyReport.put(month, data);
    }

    public int getSumIncomePerMonth(String month) {
        List<Transaction> transactions = monthlyReport.get(month);
        int sumIncome = 0;
        for (Transaction transaction : transactions) {
            if (!transaction.isExpense) {
                sumIncome = sumIncome + transaction.quantity * transaction.unitPrice;
            }
        }
        return sumIncome;
    }

    public int getSumExpensesPerMonth(String month) {
        List<Transaction> transactions = monthlyReport.get(month);
        int sumExpenses = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                sumExpenses = sumExpenses + transaction.quantity * transaction.unitPrice;
            }
        }
        return sumExpenses;
    }

    public int findMaxIncomeValuePerMonth(String month) {
        List<Transaction> transactions = monthlyReport.get(month);
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

    public String findMaxIncomeNamePerMonth(String month) {
        List<Transaction> transactions = monthlyReport.get(month);
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

    public int findMaxExpenseValuePerMonth(String month) {
        List<Transaction> transactions = monthlyReport.get(month);
        int maxExpense = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                int expense = transaction.quantity * transaction.unitPrice;
                if (expense > maxExpense) {
                    maxExpense = expense;
                }
            }
        }
        return maxExpense;
    }

    public String findMaxExpenseNamePerMonth(String month) {
        List<Transaction> transactions = monthlyReport.get(month);
        int maxExpense = 0;
        String nameMaxExpense = "";
        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                int income = transaction.quantity * transaction.unitPrice;
                if (income > maxExpense) {
                    maxExpense = income;
                    nameMaxExpense = transaction.name;
                }
            }
        }
        return nameMaxExpense;
    }


}
