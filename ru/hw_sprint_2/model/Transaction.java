package ru.hw_sprint_2.model;

public class Transaction {
    public String name;
    public boolean isExpense;
    public int quantity;
    public int unitPrice;

    public Transaction(String name, String isExpense, String quantity, String unitPrice) {
        this.name = name;
        this.isExpense = Boolean.parseBoolean(isExpense);
        this.quantity = Integer.parseInt(quantity);
        this.unitPrice = Integer.parseInt(unitPrice);
    }
}
