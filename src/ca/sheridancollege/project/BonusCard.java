package ca.sheridancollege.project;

/**
 * BonusCard class represents special bonus cards in the UNO card game.
 * These cards have a color and a type, such as "Reverse," "Skip," or "Draw."
 * They inherit from the Card class and have their unique bonus type.
 *
 * @author Ashwin Jojo
 */
public class BonusCard extends Card 
{
    private String typeOfBonus; // Stores the type of bonus for this card

    /**
     * Constructor for creating a new BonusCard with a specified color and bonus type.
     *
     * @param color The color of the bonus card.
     * @param type The type of bonus (e.g., "Reverse," "Skip," or "Draw").
     */
    public BonusCard(int color, String type) 
    {
        super(color, "Bonus", 20); // Call the superclass constructor to initialize color and count
        this.typeOfBonus = type; // Set the bonus type for this card
    }

    /**
     * Get the type of bonus associated with this card.
     *
     * @return The type of bonus (e.g., "Reverse," "Skip," or "Draw").
     */
    public String getTypeOfBonus() 
    {
        return typeOfBonus;
    }
}
