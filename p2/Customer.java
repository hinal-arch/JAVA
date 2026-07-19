import java.util.Scanner;

class Customer {
    private String name;
    private final String customerId;
    private static int customerCounter = 101;

    private static String generateCustomerId() {
        return "CUST" + customerCounter++;
    }

    Customer(String name) {
        this.name = name;
        this.customerId = generateCustomerId();
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;

    private static int accountCounter = 1;

    private static String generateAccountNumber() {
        return String.format("AC%04d", accountCounter++);
    }

    Account(String ownerName, long balance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
    }

    Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        Customer customer = new Customer(name);

        System.out.print("Enter Opening Balance: ");
        long balance = sc.nextLong();

        Account account = new Account(customer.getName(), balance);

        System.out.print("Enter Deposit Amount: ");
        long deposit = sc.nextLong();
        account.deposit(deposit);

        System.out.print("Enter Withdraw Amount: ");
        long withdraw = sc.nextLong();

        if (account.withdraw(withdraw)) {
            System.out.println("Withdrawal Successful");
        } else {
            System.out.println("Insufficient Balance");
        }

        System.out.println("\n----- Account Details -----");
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Account No    : " + account.getAccountNumber());
        System.out.println("Balance       : Rs. " + account.getBalance());

        sc.close();
    }
}
