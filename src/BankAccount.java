import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class BankAccount  implements Serializable {
    private static final long serialVersionUID = 7877768675072104282L;
    protected String accountNumber;
    protected ArrayList<TransactionHistory> history = new ArrayList<>();
    protected String accountName;
    protected double balance;

    public BankAccount(String accountName, String accountNumber, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = initialDeposit;

    }
    public abstract void withdraw(double amount);
    public abstract void deposit(double amount);
    public void displayDetails(){
        System.out.printf("" +
                " Account Number: %s\n" +
                "Holder Name: %s\n" +
                "Balance: %.2f", accountNumber, accountName, balance);

    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountName() {
        return accountName;
    }
    public double getBalance() {
        return balance;
    }
    public void addTransaction(String type, double amount) {
        history.add(new TransactionHistory(type, amount, LocalDateTime.now()));
    }
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (TransactionHistory t : history) {
            t.displayTransactionHistory();
        }
    }
}
