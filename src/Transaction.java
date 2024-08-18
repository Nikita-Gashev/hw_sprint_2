package src;

class Transaction {
    String name;
    boolean isExpense;
    int quantity;
    int unitPrice;

    Transaction(String name, String isExpense, String quantity, String unitPrice) {
        this.name = name;
        this.isExpense = Boolean.parseBoolean(isExpense);
        this.quantity = Integer.parseInt(quantity);
        this.unitPrice = Integer.parseInt(unitPrice);
    }
}
