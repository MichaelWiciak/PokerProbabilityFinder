package comp1721.cwk2;
import java.util.LinkedList;
import java.util.Collections;
// Implement Deck class here

/**
 * Program to simulate a deck object of 52 card objects
 *
 * <p>Created for CWK2</p>
 *
 * @author Michael Wiciak
 */

public class Deck {
    /**
     * Create a Deck Object with default values.
     */
    private LinkedList<Card> deck = new LinkedList<>();
    /**
     * Default constructor for Deck Object
     */
    public Deck(){
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();
        for (Card.Suit suit: suits){
            for (Card.Rank rank: ranks){
                Card myCard = new Card(rank + " of " + suit);
                deck.add(myCard);
            }
        }
    }
    /**
     * A size method that returns the number of cards in the deck.
     *
     * @return Size of deck object as int
     */
    public int size() {
        return deck.size();
    }
    /**
     * An isEmpty method that returns true if the deck is empty of cards, false otherwise.
     *
     * @return True if the deck is empty, false otherwise
     */
    public boolean isEmpty(){
        return deck.isEmpty();
    }
    /**
     * A contains method with a Card parameter that returns true if the deck contains the specified card,
     * false otherwise.
     * @param myCard Take a card object
     * @return True if the deck contains myCard, false otherwise.
     */
    public boolean contains(Card myCard) {
        for (Card card : deck) {
            if (card.compareTo(myCard) == 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * A discard method that empties the deck of all its cards.
     */
    public void discard(){
        deck.clear();
    }
    /**
     * A deal method that removes the first card in the deck and returns it.
     * If error, it throws a CardException error.
     * @return Card object if successful
     */
    public Card deal() throws CardException{
        if (!deck.isEmpty()){
            return deck.removeFirst();
        }
        throw new CardException("Deck is empty");
    }
    /**
     * A shuffle method that rearranges cards in the deck randomly.
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }
    /**
     * A method which add a card to the deck.
     */
    public void add(Card myCard) throws CardException{
        if (size()<52) {
            deck.add(myCard);
        }
    }
}