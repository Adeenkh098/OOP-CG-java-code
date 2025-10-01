
import java.util.Scanner;

public class ATM {

    private int accountno;
    private int pin;
    private double balance;
    public static int account_cnt = 999;

    ATM(int pin, double balance) {
        account_cnt++;
        this.accountno = account_cnt;
        this.pin = pin;
        this.balance = balance;
    }

    public int get_accountno() {
        return accountno;
    }

    public void showBalance() {
        System.out.println("Balance: " + balance);
    }

    public void deposit(double amount) {
        balance = amount + balance;
        System.out.println("Present Balance is: " + balance);
    }

    public void withdraw(double amount, int pin) {
        if (this.pin == pin) {
            if (balance >= amount && amount > 0) {
                balance = balance - amount;
                System.out.println("Amount withdrawn successfully. Balance: " + balance);
            } else {
                throw new IllegalArgumentException("Insufficient balance or amount is less than 1");
            }
        } else {
            throw new SecurityException("Incorrect PIN");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Set pin number: ");
        int pin = sc.nextInt();
        System.out.print("Set initial balance: ");
        double balance = sc.nextDouble();
        ATM at1 = new ATM(pin, balance);

        while (true) {
            System.out.println("\nATM Simulator");
            System.out.println("1. Check balance");
            System.out.println("2. Get Account number");
            System.out.println("3. Withdraw");
            System.out.println("4. Deposit");
            System.out.println("5. EXIT");
            System.out.print("Choose your choice (1,2,3,4,5): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    at1.showBalance();
                    break;
                case 2:
                    System.out.println("Account Number: " + at1.get_accountno());
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double amount = sc.nextDouble();
                    System.out.print("Enter the PIN: ");
                    int PIN = sc.nextInt();
                    try {
                        at1.withdraw(amount, PIN);
                    } catch (SecurityException se) {
                        System.out.println("Incorrect PIN");
                    } catch (IllegalArgumentException ie) {
                        System.out.println(ie.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    at1.deposit(depositAmount);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
