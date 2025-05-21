import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionHistory implements Serializable {
    private String type;
    private double amount;
    private LocalDateTime timeStamp;

    public TransactionHistory(String type, double amount, LocalDateTime timeStamp) {
        this.type = type;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }
    public void displayTransactionHistory(){
        System.out.println(timeStamp + " | " + type + " | " + amount);
    }

}
