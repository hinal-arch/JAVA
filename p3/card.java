import java.util.Objects;
import java.util.Scanner;

class Card {
    private String rank, suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

 
    public String toString() {
        return rank + " of " + suit;
    }

  
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card other = (Card) obj;
        return rank.equals(other.rank) && suit.equals(other.suit);
    }

  
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cards: ");
        int n = Integer.parseInt(sc.nextLine().trim());

        Card[] cards = new Card[n];
        int count = 0;
        boolean duplicateFound = false;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter rank for card " + (i + 1) + ": ");
            String rank = sc.nextLine().trim();

            System.out.print("Enter suit for card " + (i + 1) + ": ");
            String suit = sc.nextLine().trim();

            Card newCard = new Card(rank, suit);
            boolean isDuplicate = false;

            
            for (int j = 0; j < count; j++) {
                if (newCard.equals(cards[j])) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("Duplicate found: " + newCard);
                duplicateFound = true;
                break; 
            }

            cards[count] = newCard;
            count++;
        }

        sc.close();

        if (!duplicateFound) {
            System.out.println("No duplicates found.");
        }
    }
}
