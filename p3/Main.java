import java.util.Objects;

public class Main {


    static class Customer implements Cloneable {
        private String name;
        private Address address;

        public Customer(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public Address getAddress() {
            return address;
        }

   
        public static class Address {
            private String line;
            private String city;
            private String pincode;

            public Address(String line, String city, String pincode) {
                this.line = line;
                this.city = city;
                this.pincode = pincode;
            }

            public String getLine() {
                return line;
            }

            public String getCity() {
                return city;
            }

            public String getPincode() {
                return pincode;
            }

            @Override
            public String toString() {
                return line + ", " + city + " - " + pincode;
            }
        }

 
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

     
    static class Account {
        private String accountNumber;
        private String ownerName;
        private double balance;

        public Account(String accountNumber, String ownerName, double balance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = balance;
        }

   
        @Override
        public String toString() {
            return "Account [Number=" + accountNumber + ", Owner=" + ownerName + ", Balance=₹" + balance + "]";
        }

         @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Account account = (Account) o;
            return Objects.equals(accountNumber, account.accountNumber);
        }

         @Override
        public int hashCode() {
            return Objects.hash(accountNumber);
        }
    }

     public static void main(String[] args) {
         Customer.Address addr1 = new Customer.Address("123 Main St", "Tech City", "560001");
        Customer customer1 = new Customer("Alice", addr1);

         Account acc1 = new Account("ACC1001", customer1.getName(), 1500.50);
        Account acc2 = new Account("ACC1001", customer1.getName(), 1500.50);  
        Account acc3 = new Account("ACC1002", "Bob", 3000.00);

         System.out.println("--- Printing Accounts ---");
        System.out.println(acc1);
        System.out.println(acc3);

         System.out.println("\n--- Comparing Accounts ---");
        System.out.println("acc1 equals acc2 (Same Account Number): " + acc1.equals(acc2));
        System.out.println("acc1 equals acc3 (Different Account Number): " + acc1.equals(acc3));

         System.out.println("\n--- Instanceof Type Checking ---");
        Object testObj = acc1;
        if (testObj instanceof Account) {
            System.out.println("testObj is indeed an instance of Account.");
        }

         try {
            Customer clonedCustomer = (Customer) customer1.clone();
            System.out.println("\n--- Cloning Customer ---");
            System.out.println("Original Customer: " + customer1.getName() + " | Address: " + customer1.getAddress());
            System.out.println("Cloned Customer:   " + clonedCustomer.getName() + " | Address: " + clonedCustomer.getAddress());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

