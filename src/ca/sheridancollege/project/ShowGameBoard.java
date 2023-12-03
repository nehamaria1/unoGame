package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * ShowGameBoard is a utility class used for displaying the current state of the UNO game board.
 * It can display both multiple cards and a single card with their colors and values.
 *
 * @author Nehamaria Roy
 */
public class ShowGameBoard 
{
    private ArrayList<Card> cards; // The list of cards to be displayed.

    /**
     * Constructor for displaying multiple cards on the game board.
     *
     * @param cards The list of cards to be displayed.
     */
    public ShowGameBoard(ArrayList<Card> cards) 
    {
        this.cards = cards;
        printGameBoard(1);
    }

    /**
     * Constructor for displaying a single card on the game board.
     *
     * @param card The single card to be displayed.
     */
    public ShowGameBoard(Card card) 
    {
        cards = new ArrayList<>();
        cards.add(card);
        printGameBoard(0);
    }

    /**
     * Private method to print the game board, either for multiple cards or a single card.
     *
     * @param middleCardIndex Flag to indicate whether to display the card index when showing multiple cards.
     */
    private void printGameBoard(int middleCardIndex) 
    {
        // Printing card separators.
        for (int i = 0; i < cards.size(); i++) 
        {
            System.out.print("********* ");
        }
        System.out.print("\n");
        
        // Printing card colors and types.
        for (int i = 0; i < cards.size(); i++) 
        {
            if (cards.get(i) instanceof NormalCard || cards.get(i) instanceof BonusCard) 
            {
                Card card = (Card) cards.get(i);
                String cardColor = null;
                switch (card.getColorOfCard()) 
                {
                    case 1:
                        cardColor = "RED";
                        break;
                    case 2:
                        cardColor = "BLU";
                        break;
                    case 3:
                        cardColor = "GRN";
                        break;
                    case 4:
                        cardColor = "YLW";
                        break;
                }
                System.out.print("*  " + cardColor + "  * ");                                
            } 
            else 
            {
                WildCard wildCard = (WildCard) cards.get(i);
                if (wildCard.getTypeOfWildCard().equals("wildDraw"))
                    System.out.print("* Wild  * ");
                else
                    System.out.print("* Color * ");
            }
        }
        System.out.print("\n");
        
        // Printing card values.
        for (int i = 0; i < cards.size(); i++) 
        {
            if (cards.get(i) instanceof NormalCard) 
            {
                NormalCard normalCard = (NormalCard) cards.get(i);
                System.out.print("*   " + normalCard.getCountOfCard() + "   * ");
            } 
            else if (cards.get(i) instanceof WildCard) 
            {
                WildCard wildCard = (WildCard) cards.get(i);
                if (wildCard.getTypeOfWildCard().equals("WildDraw"))
                    System.out.print("* Draw  * ");
                else
                    System.out.print("*       * ");
            } 
            else 
            {
                BonusCard bonusCard = (BonusCard) cards.get(i);                
                if (bonusCard.getTypeOfBonus().equals("Skip"))
                    System.out.print("* Skip  * ");
                else if (bonusCard.getTypeOfBonus().equals("Reverse"))
                    System.out.print("*Reverse* ");
                else
                    System.out.print("* Draw  * ");                            
            }
        }
        System.out.print("\n");
        
        // Printing card separators.
        for (int i = 0; i < cards.size(); i++) 
        {
            System.out.print("********* ");            
        }
        System.out.print("\n");
        
        // Printing card indices for multiple cards.
        if (middleCardIndex == 1) 
        {
            for (int i = 0; i < cards.size(); i++) 
            {
                String format = i < 10 ? "   (%d)    " : "   (%d)   ";
                System.out.print(String.format(format, i + 1));
            }
            System.out.print("\n");
        }
    }
}
