import java.util.ArrayList;
import java.util.List;


class OutOfStockException extends Exception {

    private final int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}


class InvalidQuantityException extends Exception {

    public InvalidQuantityException(String message) {
        super(message);
    }
}


class Warehouse {

    private int stock;

    public Warehouse(int stock) {
        this.stock = stock;
    }

    public void issue(String item, int qty)
            throws OutOfStockException,
                   InvalidQuantityException {

  
        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero."
            );
        }

        if (qty > stock) {

            int shortfall = qty - stock;

            throw new OutOfStockException(
                    "Not enough stock for " + item,
                    shortfall
            );
        }

        stock -= qty;

        System.out.println(
                "Issued " + qty + " " + item +
                ". Remaining stock: " + stock
        );
    }
}

class StockRequest {

    String item;
    int quantity;

    StockRequest(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

public class StockIssue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse(50);

        List<StockRequest> requests = new ArrayList<>();

        requests.add(new StockRequest("Laptop", 20));
        requests.add(new StockRequest("Mouse", 10));
        requests.add(new StockRequest("Keyboard", 30));
        requests.add(new StockRequest("Monitor", 0));
        requests.add(new StockRequest("Printer", -5));

        for (StockRequest request : requests) {

            try {

                warehouse.issue(
                        request.item,
                        request.quantity
                );

            } catch (OutOfStockException e) {

                System.out.println(
                        "Out of stock: " +
                        request.item
                );

                System.out.println(
                        "Shortfall: " +
                        e.getShortfall()
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                        "Invalid quantity for " +
                        request.item +
                        ": " +
                        e.getMessage()
                );
            }

            System.out.println("----------------------------");
        }
    }
}