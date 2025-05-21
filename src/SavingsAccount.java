import java.io.Serializable;

public class SavingsAccount extends BankAccount implements Serializable {
    private double interestRate;


    public SavingsAccount (String accountNumber, String accountName, double initialDeposit, double interestRate) {
        super(accountNumber, accountName, initialDeposit);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        addTransaction("Deposit", amount);
    }
    @Override
    public void withdraw(double amount){
      if(balance >= amount){
          balance -= amount;
          addTransaction("Withdraw", amount);
      }
      else
          System.out.println("Insufficient Funds");
    }
    @Override
    public void displayDetails() {
        System.out.printf("""
        Account Type: Savings
        Account Number: %s
        Holder Name: %s
        Balance: %.2f
        Interest Rate: %.2f%%
        """, accountNumber, accountName, balance, interestRate * 100);
    }

    public void applyInterest(){
        balance += balance * interestRate;
        addTransaction("Interest Applied", interestRate);
    }

}
