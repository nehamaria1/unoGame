package ca.sheridancollege.project;

/**
 * Card class represents a playing card in the UNO card game.
 * It contains information about the card's color, type, and value.
 *
 * @author Ashwin Jojo
 */
public class Card 
{
    private int colorOfCard; // Stores the color of the card
    private String typeOfCard; // Stores the type of the card
    private int valueOfCard; // Stores the value of the card

    /**
     * Constructor for creating a new Card with the specified attributes.
     *
     * @param cardColor The color of the card.
     * @param cardType The type of the card.
     * @param cardValue The value of the card.
     */
    public Card(int cardColor, String cardType, int cardValue) 
    {
        this.colorOfCard = cardColor;
        this.typeOfCard = cardType;
        this.valueOfCard = cardValue;
    }

    /**
     * Get the color of the card.
     *
     * @return The color of the card.
     */
    public int getColorOfCard() 
    {
        return colorOfCard;
    }

    /**
     * Get the type of the card.
     *
     * @return The type of the card.
     */
    public String getTypeOfCard() 
    {
        return typeOfCard;
    }

    /**
     * Get the value of the card.
     *
     * @return The value of the card.
     */
    public int getValueOfCard() 
    {
        return valueOfCard;
    }
}
