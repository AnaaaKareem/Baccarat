import java.util.Scanner;

/**
 * This class is used to play the full game using the previous classes and methods created. It contains an interactive
 * mode if the player wishes to play the game continuously rather than once. It contains a scoring system to keep track
 * of the points of the player and banker. It also contains a shoe with a number of decks that the user can choose.
 * It also contains a round counter to keep track of the number of rounds played.
 *
 * @author Karim Amr Elsayed Khater
 */

class Baccarat {

    // Variables used to store the hands of the player and banker.
    private static BaccaratHand handPlayer;
    private static BaccaratHand handBanker;

    // Variable used to store the shoe.
    private static Shoe shoe;

    // Variables used to store the points of the player, banker and tie, plus a round counter.
    private static int roundCounter = 0;
    private static int playerPoints = 0;
    private static int bankerPoints = 0;
    private static int tieCounter = 0;

    private static boolean interactiveMode;

    /**
     * This method is used to select with the user plays once or play in interactive mode.
     *
     * @param args
     */
    private static void checkInteractive(String[] args) {
        interactiveMode = args.length > 0 && (args[0].equals("-i") || args[0].equals("--interactive"));
    }

    // scanner used to read the input from the user to play the game.
    private static Scanner input;

    /**
     * This method is used to create a shoe with a number of decks and shuffles them.
     * The user is asked to enter the number of decks .
     *
     */
    private static void createShoe() {
        int decks = 6;
        while (interactiveMode) {
            System.out.print("Select Number Of Decks (6 OR 8): ");
            int deckNumber = Integer.parseInt(input.next());
            if(deckNumber == 6 || deckNumber == 8) {
                decks = deckNumber;
                break;
            } else {
                System.out.print("Please Enter Either Six or Eight: ");
            }
        }
        shoe = new Shoe(decks);
        shoe.shuffle();
    }

    /**
     * This method is used to create the hands for the player and the banker. It also creates the shoe.
     *
     */
    private static void initializeGame() {
        handPlayer = new BaccaratHand();
        handBanker = new BaccaratHand();
        if (interactiveMode) {
            input = new Scanner(System.in);
        }
        createShoe();
    }

    /**
     * This method is used to make a new round and discard the previous cards from the player and banker.
     *
     */
    private static void newRound() {
        roundCounter++;
        System.out.println("Rounds: " + roundCounter);

        handPlayer.discard();
        handBanker.discard();
    }

    /**
     * This method is used to deal the cards to the player and banker.
     *
     */
    private static void dealCards() {
        handPlayer.add(shoe.deal());
        handBanker.add(shoe.deal());
        handPlayer.add(shoe.deal());
        handBanker.add(shoe.deal());
    }

    /**
     * This method is used to print the hands of the player and banker in a form of a string.
     *
     */
    private static void printHands() {
        System.out.println("Player: " + handPlayer.toString() + " = " + handPlayer.value());
        System.out.println("Banker: " + handBanker.toString() + " = " + handBanker.value());
    }

    /**
     * This method uis used to return the winner of the round using a string with if conditions to output player win,
     * banker win, or a tie.
     *
     * @param Score Which is a keyword to print it there is a winner or a tie.
     */
    private static void scoring(String Score) {
        if (Score.equals("Tie")) {
            tieCounter++;
            System.out.println("Tie");
        }

        if (Score.equals("Player")) {
            playerPoints++;
            System.out.println("Player win!");
        }

        if (Score.equals("Banker")) {
            bankerPoints++;
            System.out.println("Banker win!");
        }
    }

    /**
     * This method is used to check if there is a natural win or not. If there is no natural win, it deals the third
     * card to the player and banker if needed. It then compares the value of the player and banker to determine the
     * winner.
     *
     * @return boolean Returns true if there is a natural win and false if there is no natural win.
     */
    private static boolean checkNatural() {
        if ((handPlayer.isNatural() || handPlayer.value() == 6 || handPlayer.value() == 7)
                && handPlayer.value() == handBanker.value()) {
            scoring("Tie");
            return true;
        } else if (handPlayer.isNatural() && handBanker.isNatural()) {
            if (handPlayer.value() > handBanker.value()) {
                scoring("Player");
            } else {
                scoring("Banker");
            }
            return true;
        } else if (handPlayer.isNatural() || (handPlayer.value() == 7 && handBanker.value() == 6)) {
            scoring("Player");
            return true;
        } else if (handBanker.isNatural() || (handPlayer.value() == 6 && handBanker.value() == 7)) {
            scoring("Banker");
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to deal the third card to the player and banker if needed. It then compares the value of the
     * player and banker to determine the winner.
     *
     * @param dealer Checks if the dealer is the player or the banker.
     */
    private static void dealThirdCard(String dealer) {
        System.out.println("Dealing third card to " + dealer);
        if (dealer.equals("player") && handPlayer.value() <= 5) {
            handPlayer.add(shoe.deal());
        } else if (dealer.equals("banker") && (handPlayer.value() > 5 && handBanker.value() <= 5)) {
            handBanker.add(shoe.deal());
        }
        if (dealer.equals("banker") && handPlayer.value() <= 5) {
            Card thirdCardValue = handPlayer.get(2);
            Card.Rank thirdCardRank = thirdCardValue.getRank();
            if (handBanker.value() <= 2 || (handBanker.value() < 7 &&
                    (thirdCardRank.equals(Card.Rank.SEVEN) || thirdCardRank.equals(Card.Rank.SIX)))
                    || (handBanker.value() < 6 &&
                    (thirdCardRank.equals(Card.Rank.FIVE) || thirdCardRank.equals(Card.Rank.FOUR)))
                    || (handBanker.value() < 5 &&
                    (thirdCardRank.equals(Card.Rank.THREE) || thirdCardRank.equals(Card.Rank.TWO)))
                    || (handBanker.value() == 3 && !(thirdCardRank.equals(Card.Rank.EIGHT)))) {
                handBanker.add(shoe.deal());
            }
        }
    }

    /**
     * This method is used to ask the player if they want to play another round or not.
     *
     * @return boolean Returns true if the player wants to play another round and false otherwise
     */
    private static boolean addRound() {
        while(true) {
            System.out.print("Another round? (y/n): ");
            String check = input.next();
            if (check.equals("y") || check.equals("Y")) {
                return true;
            } else if (check.equals("n") || check.equals("N")) {
                return false;
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    /**
     * This method is used to check if the shoe has enough cards to play another round or not.
     *
     * @return boolean Returns true if the shoe has enough cards to play another round and false if the shoe does not
     * have enough cards to play another round.
     */
    private static boolean checkRound() {
        if (shoe.size() < 6) {
            return false;
        } else {
            return !interactiveMode || addRound();
        }
    }

    /**
     * This method is used to display the game information once the game is done.
     */
    private static void displayGame() {
        System.out.println("\n" + roundCounter +" rounds played");
        System.out.println(playerPoints + " player wins");
        System.out.println(bankerPoints + " banker wins");
        System.out.println(tieCounter + " ties");
    }

    /**
     * This method is used to close the scanner if the game is in interactive mode.
     */
    private static void scannerClose() {
        // If in interactive mode
        if (interactiveMode) {
            // Closes the scanner
            input.close();
        }
    }

    /**
     * This method is used to give out the final winner of the game.
     */
    private static void finalScores() {
        if (handPlayer.value() == handBanker.value()) {
            scoring("Tie");
        }
        else if (handPlayer.value() > handBanker.value()) {
            scoring("Player");
        }
        else if (handPlayer.value() < handBanker.value()) {
            scoring("Banker");
        }
    }

    /**
     * This method is used to run the game using the methods created above.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        checkInteractive(args);
        initializeGame();
        do {
            newRound();
            dealCards();
            printHands();
            if (!checkNatural()) {
                dealThirdCard("player");
                dealThirdCard("banker");
                printHands();
                finalScores();
            }
        } while (checkRound());
        displayGame();
        scannerClose();
    }
}