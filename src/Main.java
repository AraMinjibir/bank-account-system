import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BankSystem system = new BankSystem();

    public static void main(String[] args) {
        system.loadAccountsFromFile();
        boolean running = true;

        while(running){
            printMenu();

            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice){
                case 1:
                    system.createAccount();
                    break;
                case 2:
                    system.deposit();
                    break;
                case 3:
                    system.withdraw();
                    break;
                case 4:
                    system.showBalance();
                    break;
                case 5:
                    system.applyInterest();
                    break;
                case 6:{
                    System.out.println("All Accounts:");
                    system.displayAllAccounts();
                    break;
                }
                case 7:
                    system.viewTransactionHistory();
                    break;
                case 8:
                    System.out.println("Goodbye..............!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    static void printMenu() {
        System.out.println("""
            \n--- Bank Account Menu ---
            1. Create Account
            2. Deposit
            3. Withdraw
            4. Show Balance
            5. Apply Interest (Savings Only)
            6.Show All Accounts
            7. View Transaction History
            8. Exit
            Enter your choice:""");
    }
}