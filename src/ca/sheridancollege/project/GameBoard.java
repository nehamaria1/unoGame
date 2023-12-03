package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * GameBoard class represents the game board for the UNO card game. It manages players, cards, and game rules.
 * It includes methods to create and manage the game, check turns, validate plays, distribute cards, and handle game logic.
 *
 * @author Nehamaria Roy
 */
public class GameBoard 
{
    private ArrayList<Player> players; // List of players in the game
    private ArrayList<Card> cards; // List of cards in the game deck

    private int isWildCardDraw; // Counter for Wild Draw cards
    private int wildCardColor; // Color chosen for Wild cards
    private int isGameDraw; // Counter for Draw cards
    private boolean isClockwise; // Indicates the direction of play (clockwise or anti-clockwise)
    private Card midCard; // The card placed in the middle of the game board
    private int turn; // Current player's turn
    private boolean isFirstRound; // Flag to indicate if it's the first round of the game

    /**
     * Constructor to initialize the GameBoard with specified total players and play mode.
     *
     * @param totalPlayers The total number of players in the game.
     * @param playMode The chosen play mode (single or multiplayer).
     */
    public GameBoard(int totalPlayers, int playMode) 
    {
        players = new ArrayList<>();
        cards = new ArrayList<>();
        midCard = null;
        isWildCardDraw = 0;
        wildCardColor = 0;
        Random random = new Random();
        this.turn = random.nextInt(totalPlayers);
        this.isClockwise = true;
        this.isFirstRound = true;
        createCardsGame();
        createGamePlayers(playMode, totalPlayers);
        setMidCard();
    }

    /**
     * Set the first round flag to false.
     * This method is called after the first round of the game.
     * 
     */
    public void setFirstRound() 
    {
        isFirstRound = false;
    }

    /**
     * Get the current player's turn.
     *
     * @return The current player's turn.
     */
    public int getTurn() 
    {
        return turn;
    }

    /**
     * Method to check if the direction of play is clockwise.
     * 
     * @return True if the direction of play is clockwise, false otherwise.
     */
    public boolean isClockwise() 
    {
        return isClockwise;
    }

    /**
     * Method to get the color chosen for Wild cards.
     * 
     * @return The color chosen for Wild cards.
     */
    private void createCardsGame() 
    {
        ArrayList<Card> tempCardList = new ArrayList<>(); 
        for (int i = 1; i < 5; i++) 
        {            
            for (int j = 0; j < 10; j++) 
            {
                if (j == 0) 
                {
                    NormalCard normalCard = new NormalCard(i, j);
                    tempCardList.add(normalCard);
                } 
                else 
                {
                    NormalCard normalCard1 = new NormalCard(i, j);
                    NormalCard normalCard2 = new NormalCard(i, j);
                    tempCardList.add(normalCard1);
                    tempCardList.add(normalCard2);
                }
            }
            for (String bonusType : new String[] { "Reverse", "Skip", "Draw" }) 
            {
                BonusCard bonusCard1 = new BonusCard(i, bonusType);
                BonusCard bonusCard2 = new BonusCard(i, bonusType);
                tempCardList.add(bonusCard1);
                tempCardList.add(bonusCard2);
            }
        }

        for (int i = 0; i < 4; i++) 
        {
            WildCard wildCard1 = new WildCard(0, "WildDraw");
            WildCard wildCard2 = new WildCard(0, "ChangeColor");
            tempCardList.add(wildCard1);
            tempCardList.add(wildCard2);
        }

        Random random = new Random();

        while (true) 
        {
            if (tempCardList.size() == 0)
                break;
            int index = random.nextInt(tempCardList.size());
            cards.add(tempCardList.get(index));
            tempCardList.remove(index);
        }
    }

    /**
     * Method to create players for the game.
     * 
     * @param playMode The chosen play mode (single or multiplayer).
     * @param totalPlayers The total number of players in the game.
     */
    private void createGamePlayers(int playMode, int totalPlayers) 
    {
        if (playMode == 1) 
        {
            Player tempPlayer = new Player(0, 0);
            players.add(tempPlayer);

            for (int i = 1; i < totalPlayers; i++) 
            {
                Bot tempCom = new Bot(i);
                players.add(tempCom);
            }
        } 
        else if (playMode == 2) 
        {
            for (int i = 0; i < totalPlayers; i++) 
            {
                Player tempBot = new Player(i, 0);
                players.add(tempBot);
            }
        }

        for (int i = 0; i < players.size(); i++) 
        {
            for (int j = 0; j < 7; j++) 
            {
                distributeCards(i);
            }
        }
        setMidCard();
    }

    /**
     * Method to set the middle card of the game.
     * 
     */
    private void setMidCard() 
    {
        int i = 0;

        while (true) 
        {
            if (!(cards.get(i) instanceof WildCard)) 
            {
                midCard = cards.get(i);
                cards.remove(i);
                break;
            } 
            else 
            {
                i++;
            }
        }
    }

    /**
     * Method to shuffle the cards in the game deck.
     * 
     */
    public void shuffleCards() 
    {
        ArrayList<Card> tempCardList = new ArrayList<>();
        Random random = new Random();

        while (true) 
        {
            if (cards.size() == 0)
                break;
            int index = random.nextInt(cards.size());
            tempCardList.add(cards.get(index));
            cards.remove(index);
        }
        cards = tempCardList;
    }

    /**
     * Method to distribute cards to players.
     * 
     * @param index The index of the player to distribute cards to.
     */
    public void distributeCards(int index) 
    {
        players.get(index).addCard(cards.get(0));
        cards.remove(0);
    }

    /**
     * Method to check if the player has a valid card to play.
     * 
     * @return True if the player has a valid card to play, false otherwise.
     */
    public void checkPlayerTurn() 
    {
        if (isClockwise) 
        {
            turn++;
            if (turn > players.size() - 1) 
            {
                turn = 0;
            }
        } 
        else 
        {
            turn--;
            if (turn < 0) 
            {
                turn = players.size() - 1;
            }
        }
    }

    /**
     * Get the number of cards in the player's hand.
     * 
     * @return The number of cards in the player's hand.
     */
    public int getPlayerCardsCount() 
    {
        if (players.get(turn).getTypeOfPlayer() == 0) 
        {
            return players.get(turn).getCardCount();
        }
        return 0;
    }

    /**
     * Method to check if the player has a valid card to play.
     * 
     * @return True if the player has a valid card to play, false otherwise.
     */
    public boolean playTurn(int index) 
    {
        Card tempCard = players.get(turn).getCardByIndex(index);

        if (checkCard(tempCard)) 
        {
            cards.add(midCard);
            midCard = tempCard;
            players.get(turn).removeCard();
            checkMiddle();
            return true;
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Method to check if the player has a valid card to play.
     * 
     * @return True if the player has a valid card to play, false otherwise.
     */
    public void playTurn(Card card) 
    {
        cards.add(midCard);
        midCard = card;
        players.get(turn).removeCard();
        checkMiddle();
    }

    /**
     * Method to check if the player has a valid card to play.
     * 
     * @return True if the player has a valid card to play, false otherwise.
     */
    public void checkMiddle() 
    {
        if (midCard instanceof WildCard) 
        {
            WildCard newMidCard = (WildCard) midCard;

            if (newMidCard.getTypeOfWildCard().equals("wildDraw")) 
            {
                isWildCardDraw++;
                if (isBotTurn()) 
                {
                    Random random = new Random();
                    wildCardColor = random.nextInt(4) + 1;
                } 
                else 
                {
                    Scanner input = new Scanner(System.in);
                    while (true) 
                    {
                        System.out.print(
                                "\nPlease select a color: \n1 for red, \n2 for blue, \n3 for green, \n4 for yellow.: ");
                        wildCardColor = input.nextInt();
                        if (wildCardColor < 5 && wildCardColor > 0)
                            break;
                    }
                    input.close();
                }
                checkPlayerTurn();
                
                if (!players.get(turn).checkDraw() || players.get(turn).checkCardColor(wildCardColor, -2, null)) 
                {
                    System.out.println(String.format(
                            "\nWild Draw ! Player %d got %d Cards. And Lose A Turn.\n",
                            (turn + 1), (4 * isWildCardDraw)));
                    int cardsToDistribute = isWildCardDraw * 4;
                    for (int i = 0; i < cardsToDistribute; i++) 
                    {
                        distributeCards(turn);
                    }
                    isWildCardDraw = 0;
                    checkPlayerTurn();
                }
            } 
            else 
            {
                if (isBotTurn()) 
                {
                    Random random = new Random();
                    wildCardColor = random.nextInt(4) + 1;
                } 
                else 
                {
                    Scanner input = new Scanner(System.in);
                    while (true) 
                    {
                        System.out.print(
                                "\n" + //
                                        "Please select a color: \n" + //
                                        "1 for red, \n" + //
                                        "2 for blue, \n" + //
                                        "3 for green, \n" + //
                                        "4 for yellow.: ");
                        wildCardColor = input.nextInt();
                        if (wildCardColor < 5 && wildCardColor > 0)
                            break;
                    }
                    input.close();
                }
                checkPlayerTurn();
            }
        } 
        else if (midCard instanceof BonusCard) 
        {
            BonusCard newMidCard = (BonusCard) midCard;

            if (newMidCard.getTypeOfBonus().equals("reverse")) 
            {
                isClockwise = !isClockwise;
                if (players.size() != 2)
                    checkPlayerTurn();

            } 
            else if (newMidCard.getTypeOfBonus().equals("skip")) 
            {
                checkPlayerTurn();
                System.out.println("\nSorry Player " + (turn + 1) + " You Got Skipped.\n");
                checkPlayerTurn();
            } 
            else 
            {
                isGameDraw++;
                checkPlayerTurn();

                if (!players.get(turn).checkBonusDraw() || isFirstRound) 
                {
                    System.out.println(
                            String.format("\nDraw ! Player %d got %d Cards. And Lose A Turn.\n",
                                    (turn + 1), (2 * isGameDraw)));
                    int cardsToGive = isGameDraw * 2;
                    for (int i = 0; i < cardsToGive; i++) 
                    {
                        distributeCards(turn);
                    }
                    isGameDraw = 0;
                    checkPlayerTurn();
                }
            }
        } 
        else 
        {
            isGameDraw = isWildCardDraw = wildCardColor = 0;
            checkPlayerTurn();
        }
    }

    /**
     * Method to check if the player has a valid card to play.
     * 
     * @return True if the player has a valid card to play, false otherwise.
     */
    private boolean checkCard(Card card) 
    {
        if (card instanceof WildCard) 
        {
            if (midCard instanceof WildCard) 
            {
                return !players.get(turn).checkCardColor(wildCardColor, -2, null);
            } 
            else 
            {
                if (midCard instanceof BonusCard) 
                {
                    return !players.get(turn).checkCardColor(midCard.getColorOfCard(), -1,
                            ((BonusCard) midCard).getTypeOfBonus());
                } 
                else 
                {
                    NormalCard newMidCard = (NormalCard) midCard;
                    return !players.get(turn).checkCardColor(midCard.getColorOfCard(), newMidCard.getCountOfCard(), null);
                }
            }
        } 
        else if (card instanceof BonusCard) 
        {
            BonusCard bonusCard = (BonusCard) card;
            if (midCard instanceof WildCard) 
            {
                return bonusCard.getColorOfCard() == wildCardColor;
            } 
            else if (midCard instanceof BonusCard) 
            {
                BonusCard newMid = (BonusCard) midCard;
                return newMid.getColorOfCard() == bonusCard.getColorOfCard()
                        || newMid.getTypeOfBonus().equals(bonusCard.getTypeOfBonus());
            } 
            else 
            {
                NormalCard newMid = (NormalCard) midCard;
                return newMid.getColorOfCard() == bonusCard.getColorOfCard();
            }
        } 
        else 
        {
            NormalCard normalCard = (NormalCard) card;

            if (midCard instanceof WildCard) 
            {
                return normalCard.getColorOfCard() == wildCardColor;
            } 
            else if (midCard instanceof BonusCard) 
            {
                BonusCard newMid = (BonusCard) midCard;
                return newMid.getColorOfCard() == normalCard.getColorOfCard();
            } 
            else 
            {
                NormalCard newMid = (NormalCard) midCard;
                return newMid.getColorOfCard() == normalCard.getColorOfCard()
                        || newMid.getCountOfCard() == normalCard.getCountOfCard();
            }
        }
    }

    /**
     * Method to check if the player has a valid card to play.
     * 
     * @return True if the player has a valid card to play, false otherwise.
     */
    public boolean handCheck() 
    {
        ArrayList<Card> playerCards = players.get(turn).getCards();
        for (Card i : playerCards) 
        {
            if (checkCard(i))
                return true;
        }
        return false;
    }

    /**
     * Method to check the turn of the bot.
     * 
     * @return True if it's the player's turn, false otherwise.
     */
    public boolean isBotTurn() 
    {
        return players.get(turn).getTypeOfPlayer() == 1;
    }

    /**
     * Method to play the bot's turn.
     * 
     */
    public void botPlay() 
    {
        Bot temp = (Bot) players.get(turn);
        playTurn(temp.sendCard(wildCardColor, midCard));
    }

    /**
     * Method to print hand cards of the player.
     * 
     */
    public void printHandCard() 
    {        
        if (players.get(turn).getTypeOfPlayer() == 0) 
        {
            System.out.println(String.format("Cards of Player %d are :", (turn + 1)));
            new ShowGameBoard(players.get(turn).getCards());
        }
    }

    /**
     * Method to print the middle card of the game.
     * 
     */
    public void printMidCard() 
    {
        new ShowGameBoard(midCard);

        if (midCard instanceof WildCard) 
        {
            String color = null;
            switch (wildCardColor) 
            {
                case 1:
                    color = "RED";
                    break;
                case 2:
                    color = "BLUE";
                    break;
                case 3:
                    color = "GREEN";
                    break;
                case 4:
                    color = "YELLOW";
                    break;
            }
            System.out.println("The Ground Color Is : " + color);
        }
    }

    /**
     * Method to check game status.
     * 
     * @return True if the game is over, false otherwise.
     */
    public boolean checkGame() 
    {
        return players.stream().anyMatch(player -> player.getCardCount() == 0);
    }

    /**
     * Method to get the winner of the game.
     * 
     * @return The winner of the game.
     */
    public ArrayList<Player> getPlayersList() 
    {
        return players;
    }
}