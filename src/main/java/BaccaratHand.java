import java.util.LinkedList;

/**
 * This class create objects from the class BaccaratCard used for playing the game. The object created is stored in a
 * linked list for easy and quick access. The methods provide some basic calculations such as adding and calculate the
 * total value plus checking if it is a natural. Each card contains an index and each card is converted to a string
 * within the hand.
 *
 * @author Karim Amr Elsayed Khater
 */
public class BaccaratHand {

    // The hand is stored in a linked list
    protected LinkedList<BaccaratCard> hand;

    /**
     * This constructor is used to create a hand which is a linked list of cards
     */
    public BaccaratHand() {
        hand = new LinkedList<>();
    }

    /**
     * This method is used to return the size of the hand
     *
     * @return the size of the hand
     */
    public int size() {
        return hand.size();
    }

    /**
     * This method is used to add a card to the hand
     *
     * @param card the card to be added to the hand
     */
    public void add(Card card) {
        hand.add((BaccaratCard) card);
    }

    /**
     * This method is used to calculate the total value of the hand with the condition if it surpasses 10 then the total
     * is the remainder of the total divided by 10
     *
     * @return the total value of the hand
     */
    public int value() {
        int total = 0;
        for (BaccaratCard card : hand) {
            total += card.value();
        }
        return total % 10;
    }

    /**
     * This method is used to check if the hand is a natural which is a total of 8 or 9 with a hand size of 2
     *
     * @return true if the hand is a natural, false otherwise
     */
    public boolean isNatural() {
        if (value() == 8 || value() == 9) {
            if (hand.size() == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to get the hand and returns it as a string
     *
     * @return the hand of the card
     */
    public String toString() {
        StringBuilder hands = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            hands.append(hand.get(i).toString());
            if (i + 1 != hand.size()) {
                hands.append(" ");
            }
        }
        return hands.toString();
    }

    /**
     * This method is used to discard the hand
     */
    public void discard() {
        hand.clear();
    }

    /**
     * This method is used to get the card at a specific index
     *
     * @param index the index of the card
     * @return the card at the index within the hand
     */
    public Card get(int index) {
        return hand.get(index);
    }
}
