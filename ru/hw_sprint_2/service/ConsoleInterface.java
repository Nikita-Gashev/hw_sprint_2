package ru.hw_sprint_2.service;

import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner = new Scanner(System.in);
    private ReportMaker reportMaker = new ReportMaker();
    private boolean isGetMonthlyReport = false;
    private boolean isGetYearlyReport = false;

    public void printMenu() {

        while (true) {
            System.out.println(""" 
                    Что Вы хотите сделать?
                    1 - Считать все месячные отчеты
                    2 - Считать годовй отчет
                    3 - Сверить отчеты
                    4 - Ввести информацию обо всех месячных отчетах
                    5 - Вывести информацию о годовом отчете
                    6 - Выйти""");

            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    reportMaker.getMonthlyReport();
                    isGetMonthlyReport = true;
                    break;
                case 2:
                    reportMaker.getYearlyReport();
                    isGetYearlyReport = true;
                    break;
                case 3:
                    if (isGetMonthlyReport && isGetYearlyReport) {
                        reportMaker.checkData();
                        break;
                    } else {
                        System.out.println("Необходимо считать отчеты");
                        break;
                    }
                case 4:
                    if (isGetMonthlyReport) {
                        reportMaker.printMonthInformation();
                        break;
                    } else {
                        System.out.println("Необходимо считать месячные отчеты");
                        break;
                    }
                case 5:
                    if (isGetYearlyReport) {
                        reportMaker.printYearInformation();
                        break;
                    } else {
                        System.out.println("Необходимо считать годовой отчет");
                        break;
                    }
                case 6:
                    System.out.println("До встречи!");
                    return;
                default:
                    System.out.println("Такая команда отсутствует");
            }
        }
    }
}


