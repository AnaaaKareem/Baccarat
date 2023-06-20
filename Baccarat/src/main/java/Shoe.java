// TODO: Implement the Shoe class in this file

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is a constructor for the shoe which is used to store the cards and deal them. The shoe is created with
 * a specific number of decks which is either 6 or 8. The shoe is created with a list of cards which are shuffled and
 * dealt. The shoe is used to deal cards to the player and banker. The shoe is also used to calculate the number of
 * cards left in the shoe. Also, Validations are put to check if the shoe is empty or not.
 *
 * @author Karim Amr Elsayed Khater
 * @extends Card
 */

public class Shoe extends Card {

    // List of cards in the shoe
    protected List<BaccaratCard> baccarat;

    /**
     * Constructs a new shoe with the specified number of decks for playing Baccarat.
     *
     * @param decks the number of decks to use in the shoe, must be 6 or 8
     * @throws CardException if the number of decks is not 6 or 8
     */
    public Shoe(int decks) throws CardException {
        super(Rank.ACE, Suit.CLUBS);

        if (decks == 6 || decks == 8) {
            baccarat = new LinkedList<>();
            for (int i = 0; i < decks; i++) {
                for (Suit suit : Suit.values()) {
                    for (Rank rank : Rank.values()) {
                        BaccaratCard baccaratCurrent = new BaccaratCard(rank, suit);
                        baccarat.add(baccaratCurrent);
                    }
                }
            }
        } else {
            throw new CardException("Deck Size must be between 6 or 8");
        }
    }

    /**
     * Method used to return the number of cards left in the shoe.
     *
     * @return the number of cards left in the shoe
     */
    public int size() {
        return baccarat.size();
    }

    /**
     * Method used to shuffle the shoe.
     */
    public void shuffle() {
        Collections.shuffle(baccarat);
    }

    /**
     * Method used to deal a card from the shoe.
     *
     * @return the card dealt from the shoe
     * @throws CardException if the shoe is empty
     */
    public Card deal() throws CardException {
        if (size() == 0) {
            throw new CardException("Wrong number of cards in shoe");
        } else {
            BaccaratCard Card = baccarat.get(0);
            baccarat.remove(0);
            return Card;
        }
    }
}