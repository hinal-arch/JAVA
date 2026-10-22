import java.util.Scanner;

class Product {
    private final String name;
    private final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }

    static Product findProduct(char code) {
        return switch (code) {
            case 'N' -> new Product("Notebook", 45);
            case 'P' -> new Product("Pen", 10);
            case 'M' -> new Product("Marker", 25);
            case 'F' -> new Product("File", 30);
            default -> null;
        };
    }
}

class Purchase {
    private int receiptNo;
    private Product product;
    private int quantity;

    static int nextReceipt = 201;
    static int totalPurchases = 0;
    static int totalRevenue = 0;

    Purchase(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.receiptNo = nextReceipt++;
        totalPurchases++;
        totalRevenue += getAmount();
    }

    int getAmount() {
        return product.getPrice() * quantity;
    }

    static int getTotalPurchases() {
        return totalPurchases;
    }

    static int getTotalRevenue() {
        return totalRevenue;
    }

    public String toString() {
        return "Receipt " + receiptNo + ": "
                + product.getName() + " x " + quantity
                + " = " + getAmount();
    }
}

public class StationeryShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            char code = sc.next().charAt(0);
            int quantity = sc.nextInt();

            Product p = Product.findProduct(code);

            if (p == null || quantity <= 0) {
                System.out.println("Invalid item: " + code + " " + quantity);
            } else {
                Purchase purchase = new Purchase(p, quantity);
                System.out.println(purchase);
            }
        }

        System.out.println("Total receipts: "
                + Purchase.getTotalPurchases()
                + ", Total revenue: "
                + Purchase.getTotalRevenue());

        sc.close();
    }
}
