package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Bot class represents computer-controlled players in the UNO card game.
 * Bots inherit from the Player class and have logic for selecting cards to play.
 *
 * @author Ashwin Jojo
 */
public class Bot extends Player 
{
    private int midCardColor; // Stores the color of the middle card
    private Card midCardOfComputer; // Stores the middle card held by the computer

    /**
     * Constructor for creating a new Bot player with a specified player count.
     *
     * @param count The player count of the bot.
     */
    public Bot(int count) 
    {
        super(count, 1); // Call the superclass constructor to initialize the player count and type
    }

    /**
     * Logic for the bot to select and return a card to play based on the middle card and its hand.
     *
     * @param midCardColor The color of the middle card.
     * @param midCardOfComputer The middle card held by the computer.
     * @return The selected card to play, or null if no valid card is found.
     */
    public Card sendCard(int midCardColor, Card midCardOfComputer) 
    {
        this.midCardColor = midCardColor; // Set the middle card color
        this.midCardOfComputer = midCardOfComputer; // Set the middle card held by the computer
        return selectedCard();
    }

    /**
     * Logic for selecting a card from the bot's hand based on the middle card and game rules.
     *
     * @return The selected card to play, or null if no valid card is found.
     */
    private Card selectedCard() 
    {
        ArrayList<Card> cardsInHand = getCards(); // Get the cards in the bot's hand

        if (midCardOfComputer instanceof WildCard) 
        {
            WildCard midCard = (WildCard) midCardOfComputer;

            if (midCard.getTypeOfWildCard().equals("WildDraw") && checkDraw()
                    && !checkCardColor(midCardColor, -2, null)) 
            { 
                for (int i = 0; i < cardsInHand.size(); i++) 
                {
                    if (cardsInHand.get(i).getTypeOfCard().equals("Wild")) 
                    {
                        WildCard temp = (WildCard) cardsInHand.get(i);
                        if (temp.getTypeOfWildCard().equals("WildDraw"))
                            return getCardByIndex(i);
                    }
                }
            }
        }

        if (midCardOfComputer instanceof BonusCard) 
        {
            BonusCard midCard = (BonusCard) midCardOfComputer;

            if (midCard.getTypeOfBonus().equals("Draw") && checkBonusDraw()) 
            {              
                for (Card card : cardsInHand) 
                {
                    if (card instanceof BonusCard) 
                    {
                        BonusCard bonusCard = (BonusCard) card;

                        if (bonusCard.getTypeOfBonus().equals("Draw")) 
                        {
                            return getCardByIndex(cardsInHand.indexOf(card));
                        }
                    }
                }
            }
        }

        for (Card card : cardsInHand) 
        {
            if (checkWithMiddleCard(card)) 
            {
                return getCardByIndex(cardsInHand.indexOf(card));
            }
        }
        return null;
    }

    /**
     * Check if the card can be played based on the rules and the middle card.
     *
     * @param card The card to check.
     * @return True if the card can be played, false otherwise.
     */
    private boolean checkWithMiddleCard(Card card) 
    {
        if (card instanceof WildCard) 
        {
            if (midCardOfComputer instanceof WildCard) 
            {
                return !checkCardColor(midCardColor, -2, null);
            } 
            else 
            {
                if (midCardOfComputer instanceof BonusCard) 
                {
                    return !checkCardColor(midCardOfComputer.getColorOfCard(), -1,
                            ((BonusCard) midCardOfComputer).getTypeOfBonus());
                } 
                else 
                {
                    NormalCard midCard = (NormalCard) midCardOfComputer;
                    return !checkCardColor(midCardOfComputer.getColorOfCard(), midCard.getCountOfCard(), null);
                }
            }
        }
        
        else if (card instanceof BonusCard) 
        {
            BonusCard bonusCard = (BonusCard) card;

            if (midCardOfComputer instanceof WildCard) 
            {
                return bonusCard.getColorOfCard() == midCardColor;
            }
            
            else if (midCardOfComputer instanceof BonusCard) 
            {
                BonusCard newMidCard = (BonusCard) midCardOfComputer;
                return newMidCard.getColorOfCard() == bonusCard.getColorOfCard()
                        || newMidCard.getTypeOfBonus().equals(bonusCard.getTypeOfBonus());
            }             
            else 
            {
                NormalCard newMidCard = (NormalCard) midCardOfComputer;
                return newMidCard.getColorOfCard() == bonusCard.getColorOfCard();
            }
        } 
        else 
        {
            NormalCard normalCard = (NormalCard) card;

            if (midCardOfComputer instanceof WildCard) 
            {
                return normalCard.getColorOfCard() == midCardColor;
            } 

            else if (midCardOfComputer instanceof BonusCard) 
            {
                BonusCard newMidCard = (BonusCard) midCardOfComputer;
                return newMidCard.getColorOfCard() == normalCard.getColorOfCard();
            } 
            else 
            {
                NormalCard newMidCard = (NormalCard) midCardOfComputer;
                return newMidCard.getColorOfCard() == normalCard.getColorOfCard()
                        || newMidCard.getCountOfCard() == normalCard.getCountOfCard();
            }
        }
    }
}