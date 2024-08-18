package ru.hw_sprint_2.service;

import ru.hw_sprint_2.model.MonthlyReport;
import ru.hw_sprint_2.model.Transaction;
import ru.hw_sprint_2.model.YearlyReport;
import ru.hw_sprint_2.util.FileReader;
import ru.hw_sprint_2.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ReportMaker {
    private MonthlyReport monthlyReport = new MonthlyReport();
    private YearlyReport yearlyIncomeReport = new YearlyReport();
    private YearlyReport yearlyExpensesReport = new YearlyReport();

    public void getMonthlyReport() {
        FileReader fileReader = new FileReader();
        for (int i = 1; i <= Constants.MONTHS.length; i++) {
            List<String> month = fileReader.readFileContents("m.20210" + i + ".csv");
            List<Transaction> transactionsPerMonth = new ArrayList<>();
            for (int j = 1; j < month.size(); j++) {
                String line = month.get(j);
                String[] lineContents = line.split(",");
                Transaction transaction = new Transaction(lineContents[0], lineContents[1], lineContents[2], lineContents[3]);
                transactionsPerMonth.add(transaction);
            }
            monthlyReport.putData(Constants.MONTHS[(i - 1)], transactionsPerMonth);
        }
    }

    public void printMonthInformation() {
        for (String month : Constants.MONTHS) {
            int maxIncome = monthlyReport.findMaxIncomeValuePerMonth(month);
            String maxIncomeName = monthlyReport.findMaxIncomeNamePerMonth(month);
            int maxExpense = monthlyReport.findMaxExpenseValuePerMonth(month);
            String maxExpenseName = monthlyReport.findMaxExpenseNamePerMonth(month);
            System.out.printf("Месяц: %s%n", month);
            System.out.printf("Самый прибыльный товар: %s - %d%n", maxIncomeName, maxIncome);
            System.out.printf("Самая большая трата: %s - %d%n", maxExpenseName, maxExpense);
        }
    }

    public void getYearlyReport() {
        FileReader fileReader = new FileReader();
        List<String> year = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < year.size(); i++) {
            String line = year.get(i);
            String[] lineContents = line.split(",");
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);
            if (isExpense) {
                switch (lineContents[0]) {
                    case "01" -> yearlyExpensesReport.putData(Constants.MONTHS[0], Integer.parseInt(lineContents[1]));
                    case "02" -> yearlyExpensesReport.putData(Constants.MONTHS[1], Integer.parseInt(lineContents[1]));
                    case "03" -> yearlyExpensesReport.putData(Constants.MONTHS[2], Integer.parseInt(lineContents[1]));
                }
            } else {
                switch (lineContents[0]) {
                    case "01" -> yearlyIncomeReport.putData(Constants.MONTHS[0], Integer.parseInt(lineContents[1]));
                    case "02" -> yearlyIncomeReport.putData(Constants.MONTHS[1], Integer.parseInt(lineContents[1]));
                    case "03" -> yearlyIncomeReport.putData(Constants.MONTHS[2], Integer.parseInt(lineContents[1]));
                }
            }
        }
    }

    public void printYearInformation() {
        System.out.println("""
                2021 год
                Прибыль по каждому месяцу:""");
        for (String month : Constants.MONTHS) {
            int profit = yearlyIncomeReport.getAmountPerMonth(month) - yearlyExpensesReport.getAmountPerMonth(month);
            System.out.printf("%s: %d%n", month, profit);
        }
        int averageConsumption = yearlyExpensesReport.getSum() / Constants.MONTHS.length;
        System.out.printf("Средний расход за все имеющиеся операции в году: %d%n", averageConsumption);
        int averageIncome = yearlyIncomeReport.getSum() / Constants.MONTHS.length;
        System.out.printf("Средний доход за все имеющиеся операции в году: %d%n", averageIncome);
    }

    public void checkData() {
        for (String month : Constants.MONTHS) {
            if (monthlyReport.getSumIncomePerMonth(month) != yearlyIncomeReport.getAmountPerMonth(month)) {
                System.out.printf("Обнаружено несоотвествие доходов в %s%n", month);
                if (monthlyReport.getSumExpensesPerMonth(month) != yearlyExpensesReport.getAmountPerMonth(month)) {
                    System.out.printf("Обнаружено несоотвествие расходов в %s%n", month);
                }
            }
        }
    }
}
