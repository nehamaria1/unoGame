package ca.sheridancollege.project;

/**
 * WildCard is a subclass of Card representing a special type of UNO card.
 * It has a color, type, and a specific type associated with it.
 *
 * @author Ashwin Jojo
 */
public class WildCard extends Card 
{
    private String typeOfWildCard; // The type of this WildCard, e.g., "WildDraw" or "ChangeColor".

    /**
     * Constructor for WildCard.
     *
     * @param color The color of the WildCard (0 for wild).
     * @param type The specific type of the WildCard ("WildDraw" or "ChangeColor").
     */
    public WildCard(int color, String type)
    {
        super(color, "Wild", 50); // WildCards have a fixed high value of 50.
        this.typeOfWildCard = type;
    }

    /**
     * Get the type of the WildCard.
     *
     * @return The type of the WildCard (e.g., "WildDraw" or "ChangeColor").
     */
    public String getTypeOfWildCard() 
    {
        return typeOfWildCard;
    }
}
