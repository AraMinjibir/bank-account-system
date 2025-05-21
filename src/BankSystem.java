import java.io.*;
import java.util.ArrayList;

public class BankSystem {
    private ArrayList<BankAccount> accounts;
    private final String FILE_NAME = "accounts.dat";

    public BankSystem() {
        accounts = new ArrayList<>();
        loadAccountsFromFile();
    }
    public void displayAllAccounts(){
        for(BankAccount acc: accounts){
            acc.displayDetails();
            System.out.println("-----------------");
        }
    }
    public void createAccount(){
        System.out.print("Enter your full Name: ");
        String name = Main.scanner.nextLine();

        System.out.print("Enter the initial Deposit Amount: ");
        double deposit = Double.parseDouble(Main.scanner.nextLine());

        System.out.print("Choose the Account type you want open, (1. Savings 2. Current: )");
        int type = Integer.parseInt(Main.scanner.nextLine());

        if(type == 1){
            System.out.print("Enter the Interest rate, eg 0.5%: ");
            double rate = Double.parseDouble(Main.scanner.nextLine());
            createSavingsAccount(name, deposit, rate );
        }else if(type == 2){
            System.out.print("Enter the overdraft limit:");
            double limit = Double.parseDouble(Main.scanner.nextLine());
            createCurrentAccount(name, deposit, limit);
        }
        saveAccountsToFile();
    }
    public void deposit(){
        BankAccount acc = getAccount();
        if(acc != null){
            System.out.print("Enter the Amount you want to deposit: ");
            double amount = Double.parseDouble(Main.scanner.nextLine());
            acc.deposit(amount);
            System.out.println("Amount Deposited Successfully!.");
            saveAccountsToFile();
        }
    }
    public void withdraw(){
        BankAccount acc = getAccount();
        if (acc != null){
            System.out.println("Enter the Amount you want to Withdraw: ");
            double amount = Double.parseDouble(Main.scanner.nextLine());
            acc.withdraw(amount);
            System.out.println("Amount" + amount + " Widthrawn Successfully ");
            saveAccountsToFile();
        }
    }
    public void showBalance(){
        BankAccount acc = getAccount();
        if(acc != null){
            System.out.printf("Account Holder: %s\nBalance: %.2f\n",
                    acc.getAccountName(), acc.getBalance());
        }
    }
    public void applyInterest(){
        BankAccount acc = getAccount();

        if(acc instanceof SavingsAccount){
            ((SavingsAccount) acc).applyInterest();
            System.out.println("Interest applied.");
        }else
            System.out.println("Interest only applies to savings accounts.");
    }
    public void viewTransactionHistory() {
        BankAccount acc = getAccount();
        if (acc != null) {
            System.out.println("Transaction History for Account: " + acc.getAccountNumber());
            acc.showHistory();
        }
    }
    private void saveAccountsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
    public void loadAccountsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (ArrayList<BankAccount>) ois.readObject();
            System.out.println("Accounts loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    private String generateAccountNumber() {
        String accountNumber;
        do {
            long number = (long)(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            accountNumber = String.valueOf(number);
        } while (isAccountNumberTaken(accountNumber));
        return accountNumber;
    }
    private boolean isAccountNumberTaken(String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
    private void createSavingsAccount(String name, double deposit, double rate){
        String number = generateAccountNumber();
        BankAccount acc = new SavingsAccount(name, number, deposit, rate);
        accounts.add(acc);
        System.out.println("Savings account created. Account number: " + number);
        acc.displayDetails();
    }
    private void createCurrentAccount(String name, double deposit, double draftlimit){
        String number = generateAccountNumber();

        BankAccount acc = new CurrentAccount(name,number, deposit, draftlimit);
        accounts.add(acc);
        System.out.println("Current account created. Account number: " + number);
        acc.displayDetails();
    }
    private BankAccount findAllAccounts(String accountNumber){
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                System.out.println("Account found!.");
                return acc;
            }
        }
        System.out.println("Account not Found!.");
        return null;
    }
    private BankAccount getAccount() {
        System.out.print("Enter your Account Number: ");
        String  accNo = Main.scanner.nextLine().trim();
        BankAccount acc = findAllAccounts(accNo);
        if(acc == null){
            System.out.println("Account not Found!.");
        }
        return acc;
    }


}
