
import java.util.Random;
import java.util.Scanner;

public class ATMSimulator {

    private static double balance = 1000.00; // Default initial balance
    private static final int PIN = 1234;     // Set your fixed PIN
    private static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Auto-generate a 6-digit account number
        Random rand = new Random();
        int accountNumber = 100000 + rand.nextInt(900000); // 6-digit

        System.out.println("Welcome to the ATM");
        System.out.println("Your Account Number is: " + accountNumber);

        // PIN Verification
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = sc.nextInt();

            if (enteredPin == PIN) {
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Card blocked.");
            sc.close();
            return;
        }

        // Show menu after successful login
        int choice;
        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    public static void checkBalance() {
        System.out.printf("Your current balance is: ₹%.2f%n", balance);
    }

    public static void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("₹%.2f deposited successfully.%n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public static void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("₹%.2f withdrawn successfully.%n", amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}
