// TODO: Implement the BaccaratCard class in this file

/**
 * This class constructor used to represent and calculate the value of a card in the game. It extends from the class
 * card hence, some methods are reused. Furthermore, The card information is extracted which are the rank and suit which
 * are converted to string. The rank of the card is used to compare the value of the card with other cards calculate the
 * total with condition of surpassing a total of 10.
 *
 * @author Karim Amr Elsayed Khater
 * @extends Card
 */

public class BaccaratCard extends Card {

    /**
     * This constructor is used to create a card with a rank and suit.
     *
     * @param r Rank of the card
     * @param s Suit of the card
     */
    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
    }

    /**
     * This method is used to get the suit of the card.
     *
     * @return Suit of the card
     */
    public Suit getSuit() {
        return super.getSuit();
    }

    /**
     * This method is used to get the rank of the card.
     *
     * @return Rank of the card
     */
    public Rank getRank() {
        return super.getRank();
    }

    /**
     * This method is used to get the string representation of the card.
     *
     * @return String representation of the card
     */
    public String toString() {
        return super.toString();
    }

    /**
     * This method is used to compare the rank of the card with another card.
     *
     * @param other The card to be compared with which is of type BaccaratCard
     * @return 0 if the cards are equal, positive number if the card is greater than the other card, negative number if
     * the card is less than the other card
     */
    public int compareTo(BaccaratCard other) {
        return super.compareTo(other);
    }

    /**
     * This method is used to check if the card is equal to another card.
     *
     * @param other The card to be compared with
     * @return True if the card is equal to the other card, false otherwise
     */
    public boolean equals(BaccaratCard other) {
        return super.equals(other);
    }

    /**
     * This method is used to get the value of the card and check if it is equal to 10 and set the value back to
     * zero if true.
     *
     * @return Value of the card
     */
    public int value() {
        int cardValue = super.value();
        if (cardValue == 10) {
            cardValue = 0;
        }
        return cardValue;
    }
}