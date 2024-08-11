package src;

import java.util.ArrayList;

class ReportMaker {
    private MonthlyReport monthlyReport = new MonthlyReport();
    private YearlyReport yearlyIncomeReport = new YearlyReport();
    private YearlyReport yearlyConsumptionReport = new YearlyReport();
    private String[] months = {"Январь", "Февраль", "Март"};

    void getMonthlyReport() {
        FileReader fileReader = new FileReader();
        for (int i = 1; i <= 3; i++) {
            ArrayList<String> month = fileReader.readFileContents("m.20210" + i + ".csv");
            ArrayList<Transaction> transactionsPerMonth = new ArrayList<>();
            for (int j = 1; j < month.size(); j++) {
                String line = month.get(j);
                String[] lineContents = line.split(",");
                Transaction transaction = new Transaction(lineContents[0], lineContents[1], lineContents[2], lineContents[3]);
                transactionsPerMonth.add(transaction);
            }
            switch (i) {
                case 1:
                    monthlyReport.putData(months[0], transactionsPerMonth);
                    break;
                case 2:
                    monthlyReport.putData(months[1], transactionsPerMonth);
                    break;
                case 3:
                    monthlyReport.putData(months[2], transactionsPerMonth);
                    return;
            }
        }
    }

    void printMonthInformation() {
        for (String month : months) {
            int maxIncome = monthlyReport.findMaxIncomeValuePerMonth(month);
            String maxIncomeName = monthlyReport.findMaxIncomeValueNamePerMonth(month);
            int maxConsumption = monthlyReport.findMaxConsumptionValuePerMonth(month);
            String maxConsumptionName = monthlyReport.findMaxConsumptionValueNamePerMonth(month);
            System.out.printf("Месяц: %s%n", month);
            System.out.printf("Самый прибыльный товар: %s - %d%n", maxIncomeName, maxIncome);
            System.out.printf("Самая большая трата: %s - %d%n", maxConsumptionName, maxConsumption);
        }
    }

    void getYearlyReport() {
        FileReader fileReader = new FileReader();
        ArrayList<String> year = fileReader.readFileContents("y.2021.csv");
        for (int i = 1; i < year.size(); i++) {
            String line = year.get(i);
            String[] lineContents = line.split(",");
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);
            if (isExpense) {
                switch (lineContents[0]) {
                    case "01" -> yearlyConsumptionReport.putData(months[0], Integer.parseInt(lineContents[1]));
                    case "02" -> yearlyConsumptionReport.putData(months[1], Integer.parseInt(lineContents[1]));
                    case "03" -> yearlyConsumptionReport.putData(months[2], Integer.parseInt(lineContents[1]));
                }
            } else {
                switch (lineContents[0]) {
                    case "01" -> yearlyIncomeReport.putData(months[0], Integer.parseInt(lineContents[1]));
                    case "02" -> yearlyIncomeReport.putData(months[1], Integer.parseInt(lineContents[1]));
                    case "03" -> yearlyIncomeReport.putData(months[2], Integer.parseInt(lineContents[1]));
                }
            }
        }
    }

    void printYearInformation() {
        System.out.println("""
                2021 год
                Прибыль по каждому месяцу:""");
        for (String month : months) {
            int profit = yearlyIncomeReport.getAmountPerMonth(month) - yearlyConsumptionReport.getAmountPerMonth(month);
            System.out.printf("%s: %d%n", month, profit);
        }
        int averageConsumption = yearlyConsumptionReport.getSum() / months.length;
        System.out.printf("Средний расход за все имеющиеся операции в году: %d%n", averageConsumption);
        int averageIncome = yearlyIncomeReport.getSum() / months.length;
        System.out.printf("Средний доход за все имеющиеся операции в году: %d%n", averageIncome);
    }

    void checkData() {
        for (String month : months) {
            if (monthlyReport.getSumIncomePerMonth(month) != yearlyIncomeReport.getAmountPerMonth(month)) {
                System.out.printf("Обнаружено несоотвествие доходов в %s%n", month);
                if (monthlyReport.getSumConsumptionPerMonth(month) != yearlyConsumptionReport.getAmountPerMonth(month)) {
                    System.out.printf("Обнаружено несоотвествие расходов в %s%n", month);
                }
            }
        }
    }
}
