import java.io.Serializable;

public class CurrentAccount extends BankAccount implements Serializable {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountName, double initialDeposit, double overdraft) {
        super(accountNumber, accountName, initialDeposit);
        this.overdraftLimit = overdraft;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        addTransaction("Deposit", amount);
    }
    @Override
    public void withdraw(double amount){
        if(balance + overdraftLimit >= amount){
            balance -= amount;
            addTransaction("Withdraw", amount);
        }
        else
            System.out.println("Amount exceeded the overdraft Limit.");
    }
    @Override
    public void displayDetails() {
        System.out.printf("""
        Account Type: Current
        Account Number: %s
        Holder Name: %s
        Balance: %.2f
        Overdraft Limit: %.2f
        """, accountNumber, accountName, balance, overdraftLimit);
    }

}
