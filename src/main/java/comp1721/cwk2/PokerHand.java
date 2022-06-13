package comp1721.cwk2;
// Implement PokerHand class here

import java.util.LinkedList;
import java.util.Arrays;

/**
 * Program to simulate a hand object of 5 card objects
 *
 * <p>Created for CWK2</p>
 *
 * @author Michael Wiciak
 */
public class PokerHand {

    public static final int  FULL_SIZE = 5;
    private LinkedList<Card> hand = new LinkedList<Card>();
    /**
     *  A default constructor that creates an empty hand.
     *
     */
    public PokerHand(){
    }
    /**
     * A constructor with a String parameter that specifies the cards that should be added to the hand,using two-character abbreviations for the cards. For example, an argument of "2D JC" should result in the Two of Diamonds and Jack of Clubs being added to the hand.
     * @param myString String object
     */
    public PokerHand(String myString) throws CardException{
        String[] parts = myString.split(" ");
        if (parts.length>FULL_SIZE){
            throw new CardException("Too many cards");
        }
        for (String part : parts) {
            Card myCard = new Card(part);
            hand.add(myCard);
        }


    }
    /**
     * A size method that returns the number of cards in the deck.
     * @return Int size of hand
     */
    public int size() {
        return hand.size();
    }
    /**
     * A discard method that empties the hand of all its cards.
     */
    public void discard(){
        if (size()==0){
            throw new CardException("Can't discard from an empty hand");
        }
        hand.clear();
    }
    /**
     * Method discardTo,with a Deck parameter,which empties the hand of cards and returns each of them
     * to the specified deck.
     * @param myDeck Deck object
     */
    public void discardTo(Deck myDeck){
        if (size()==0){
            throw new CardException("Can't discard from an empty hand");
        }
        for (int i=0; i<size(); i++){
            if (myDeck.contains(hand.get(i))){
                throw new CardException("Duplicate card");
            }
            myDeck.add(hand.get(i));
        }
        discard();
    }
    /**
     * A method which add a card to the deck.
     * @param myCard takes a card object
     */
    public void add(Card myCard){
        if (size()>=FULL_SIZE){
            throw new CardException("Tried to add to a hand of 5 cards");
        }
        for (Card card : hand) {
            if (card.compareTo(myCard) == 0) {
                throw new CardException("Duplicate Card tried to be added");
            }
        }

        hand.add(myCard);
    }
    /**
     * Detects if hand has 1 pair
     * @return boolean
     */
    public boolean isPair(){
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int counter=0;
        for (Card card : hand) {
            for (Card comparingAgainst : hand) {
                if (card.compareTo(comparingAgainst) < 0){
                    if (card.getRank() == comparingAgainst.getRank()){
                        counter ++;
                    }
                }
            }
        }
        return counter == 1;
    }
    /**
     * Detects if hand has 2 pair
     * @return boolean
     */
    public boolean isTwoPairs(){
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int counter=0;
        for (Card card : hand) {
            for (Card comparingAgainst : hand) {
                if (card.compareTo(comparingAgainst) < 0){
                    if (card.getRank() == comparingAgainst.getRank()){
                        counter ++;
                    }
                }
            }
        }
        return counter == 2;
    }
    /**
     * Detects if hand has 3 of a kind
     * @return boolean
     */
    public boolean isThreeOfAKind(){
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int counterOfThree=0;
        int counter = 0;
        int counterIsPair=0;
        for (Card card : hand) {
            for (Card comparingAgainst : hand) {
                if (card.compareTo(comparingAgainst) < 0){
                    if (card.getRank() == comparingAgainst.getRank()){
                        counter ++;
                        counterIsPair ++;
                    }
                }
            }
            if (counter > 2){
                return false;
            }
            if (counterIsPair == 4){
                return false;
            }
            if (counter == 2) {
                counterOfThree++;
            }
            counter = 0;
        }
        return counterOfThree > 0;
    }
    /**
     * Detects if hand has 4 of a kind
     * @return boolean
     */
    public boolean isFourOfAKind(){
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int counterOfFour=0;
        int counter = 0;
        int counterIsPair=0;
        for (Card card : hand) {
            for (Card comparingAgainst : hand) {
                if (card.compareTo(comparingAgainst) < 0){
                    if (card.getRank() == comparingAgainst.getRank()){
                        counter ++;
                        counterIsPair ++;
                    }
                }
            }
            if (counter == 3) {
                counterOfFour++;
            }
            counter = 0;
        }
        return counterOfFour > 0;
    }
    /**
     * Detects if hand has full house
     * @return boolean
     */
    public boolean isFullHouse(){
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int counterOfThree=0;
        int counter = 0;
        int counterIsPair=0;
        int counterForPair=0;
        for (Card card : hand) {
            for (Card comparingAgainst : hand) {
                if (card.compareTo(comparingAgainst) < 0){
                    if (card.getRank() == comparingAgainst.getRank()){
                        counter ++;
                        counterIsPair ++;
                    }
                }
            }
            if (counter > 2){
                return false;
            }
            if (counterIsPair == 4){
                counterForPair++;
            }
            if (counter == 2) {
                counterOfThree++;
            }
            counter = 0;
        }
        return counterOfThree > 0 && counterForPair > 0;
    }
    /**
     * Detects if hand has a flush
     * @return boolean
     */
    public boolean isFlush(){
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int counter=1;
        for (Card card : hand) {
            for (Card comparingAgainst : hand) {
                if (card.getSuit() != comparingAgainst.getSuit()){
                    counter = 0;
                }
            }
        }
        return counter == 1;
    }
    /**
     * Detects if hand has a straight
     * @return boolean
     */
    public boolean isStraight(){
        Character[] rankOrder = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        if (hand.size()!=FULL_SIZE){
            return false;
        }
        int[] orderTable = {0, 0 ,0 ,0 ,0};
        int indexOfOrderTable=0;
        for (Card card : hand) {
            for (int i = 0; i < rankOrder.length; i++){
                if (rankOrder[i] == card.toString().charAt(0)){
                    orderTable[indexOfOrderTable]=i;
                }
            }
            indexOfOrderTable++;
        }
        Arrays.sort(orderTable);

        if (orderTable[4] == 12 && orderTable[0] == 0 && orderTable[1] == 1
                && orderTable[2] == 2 && orderTable[3] == 3){
            return true;
        }

        for (int i=0; i<orderTable.length - 1; i++){
            if (orderTable[i] != orderTable[i+1]-1){
                return false;
            }
        }
        return true;
    }

    /**
     * A toString method, overriding the default version, which returns a string in which cards are shown in two-character form, separated by spaces—e.g., "2D JC 7H".
     * @return string representation of the hand
     */
    @Override
    public String toString(){
        StringBuilder parts = new StringBuilder();
        for (int i=0; i<size(); i++){
            if (parts.length() != 0){
                parts.append(" ");
            }
            Card myCard = hand.get(i);
            parts.append(myCard.toString());
        }
        return parts.toString();
    }
}