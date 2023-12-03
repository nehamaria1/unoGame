package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Player is a class that represents a player in the UNO card game.
 * Each player has a set of cards, a type, and methods to interact with the game.
 *
 * @author Ashwin Jojo
 */
public class Player 
{
    private int numberOfPlayers; // The total number of players in the game.
    private ArrayList<Card> cards; // The player's hand, which contains a list of cards.
    private int selectedIndexOfCard; // Index of the selected card in the player's hand.
    private int typeOfPlayer; // The type of player (human or computer).

    /**
     * Constructor for creating a player with a specified number of players and type.
     *
     * @param numberOfPlayers The total number of players in the game.
     * @param typeOfPlayer The type of the player (0 for human, 1 for computer).
     */
    public Player(int numberOfPlayers, int typeOfPlayer) 
    {
        this.numberOfPlayers = numberOfPlayers;
        cards = new ArrayList<>();
        this.typeOfPlayer = typeOfPlayer;
    }

    /**
     * Get the total number of players in the game.
     *
     * @return The number of players.
     */
    public int getNumberOfPlayers() 
    {
        return numberOfPlayers;
    }

    /**
     * Get the player's hand (list of cards).
     *
     * @return The list of cards in the player's hand.
     */
    public ArrayList<Card> getCards() 
    {
        return cards;
    }

    /**
     * Get the index of the currently selected card in the player's hand.
     *
     * @return The index of the selected card.
     */
    public int getSelectedIndexOfCard() 
    {
        return selectedIndexOfCard;
    }

    /**
     * Get the type of the player (human or computer).
     *
     * @return The type of the player (0 for human, 1 for computer).
     */
    public int getTypeOfPlayer() 
    {
        return typeOfPlayer;
    }

    /**
     * Add a card to the player's hand.
     *
     * @param card The card to add to the player's hand.
     */
    public void addCard(Card card) 
    {
        cards.add(card);
    }

    /**
     * Remove the currently selected card from the player's hand.
     */
    public void removeCard() 
    {
        cards.remove(selectedIndexOfCard);
    }

    /**
     * Get the number of cards in the player's hand.
     *
     * @return The number of cards in the player's hand.
     */
    public int getCardCount() 
    {
        return cards.size();
    }    

    /**
     * Get a card from the player's hand by its index.
     *
     * @param index The index of the card to retrieve.
     * @return The selected card.
     */
    public Card getCardByIndex(int index) 
    {
        selectedIndexOfCard = index;
        return cards.get(index);
    }   

    /**
     * Check if the player has a card with the specified color, number, or type.
     *
     * @param color The color to check.
     * @param number The number to check.
     * @param type The type to check.
     * @return True if the player has a matching card; otherwise, false.
     */
    public boolean checkCardColor(int color, int number, String type) 
    {
        for (Card i : cards) 
        {
            if (i.getColorOfCard() == color)
                return true;
            if (i instanceof NormalCard && (number != -1 && number != -2)) 
            {
                NormalCard card = (NormalCard) i;
                if (card.getCountOfCard() == number)
                    return true;
            }
            if (number == -1 && i instanceof BonusCard) 
            {
                BonusCard card = (BonusCard) i;
                if (card.getTypeOfBonus().equals(type)) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the player has a WildDraw card in their hand.
     *
     * @return True if the player has a WildDraw card; otherwise, false.
     */
    public boolean checkDraw() 
    {
        for (Card i : cards) 
        {
            if (i instanceof WildCard) 
            {
                if (((WildCard) i).getTypeOfWildCard().equals("WildDraw"))
                    return true;
            }
        }
        return false;
    }

    /**
     * Check if the player has a BonusCard with a "Draw" type in their hand.
     *
     * @return True if the player has a BonusCard with a "Draw" type; otherwise, false.
     */
    public boolean checkBonusDraw() 
    {
        for (Card i : cards) 
        {
            if (i instanceof BonusCard) 
            {
                if (((BonusCard) i).getTypeOfBonus().equals("Draw"))
                    return true;
            }
        }
        return false;
    }

    /**
     * Calculate the total score of the player's hand based on the card values.
     *
     * @return The total score of the player's hand.
     */
    public int getScore() 
    {
        int score = 0;
        for (Card i : cards)
            score += i.getValueOfCard();
        return score;
    }
}
