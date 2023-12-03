package ca.sheridancollege.project;

/**
 * NormalCard is a class that represents a standard UNO card with a color and a count.
 * It extends the Card class and inherits properties and methods for a generic card.
 *
 * @author Ashwin Jojo
 */
public class NormalCard extends Card 
{
    private int countOfCard; // The count (or number) associated with the card.

    /**
     * Constructor for creating a NormalCard with a specified color and count.
     *
     * @param color The color of the card.
     * @param count The count (or number) of the card.
     */
    public NormalCard(int color, int count) 
    {
        super(color, "Normal", count); // Calls the superclass constructor.
        this.countOfCard = count;
    }

    /**
     * Get the count (or number) of the card.
     *
     * @return The count of the card.
     */
    public int getCountOfCard() 
    {
        return countOfCard;
    }
}
