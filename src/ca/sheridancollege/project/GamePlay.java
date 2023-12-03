package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * GamePlay class represents the gameplay of the UNO card game.
 * It contains logic for starting new games, managing rounds, and tracking game results.
 *
 * @author Nehamaria Roy
 */
public class GamePlay 
{
    private GameBoard board; // The game board
    private int totalPlayers; // Total number of players
    private int playMode; // Play mode (single or multiplayer)
    private ArrayList<Result> results; // List of game results

    /**
     * Constructor to initialize the GamePlay object with the total number of players and play mode.
     *
     * @param totalPlayers The total number of players in the game.
     * @param playMode The chosen play mode (single or multiplayer).
     */
    public GamePlay(int totalPlayers, int playMode) 
    {
        this.totalPlayers = totalPlayers;
        this.playMode = playMode;
        results = new ArrayList<>();
    }

    /**
     * Main game loop for UNO. Allows players to start new games, manage rounds, and exit the game.
     */
    public void play() 
    {
        Scanner input = new Scanner(System.in);
        int userInput;

        while (true) 
        {
            System.out.print("Press 1 to start a new game\nPress 2 to Exit: ");
            userInput = input.nextInt();
            if (userInput == 1)
                playUNO();
            else if (userInput == 2)
                break;
            else
                System.out.print("Invalid input, please try again!");
        }
        input.close();
        printAllResult();
        System.out.print("Thanks for playing.");
    }

    /**
     * Method to start a new UNO game. It initializes the game board, manages player turns, and tracks game results.
     */
    private void playUNO() 
    {
        board = new GameBoard(totalPlayers, playMode);

        Scanner input = new Scanner(System.in);
        int userInput;
        board.checkMiddle();
        board.setFirstRound();

        while (true) 
        {
            printPlayers();
            board.printMidCard();
            if (board.checkGame()) 
            {
                System.out.print("\nRound Finished.\n");
                Result resultOfGame = new Result(board.getPlayersList());
                results.add(resultOfGame);
                printRound();
                break;
            }
            if (board.isClockwise())
                System.out.println("\n Clockwise Rotation. ( ---> )");
            else
                System.out.println("Anti-Clockwise Rotation. ( <--- )");
            board.shuffleCards();
            System.out.println("\nIt's the turn for Player " + (board.getTurn() + 1) + ". Play the game.");
            if (board.isBotTurn()) 
            {
                if (!board.handCheck())
                    board.distributeCards(board.getTurn());
                if (!board.handCheck()) 
                {
                    board.checkPlayerTurn();
                    continue;
                }
                board.botPlay();
                continue;
            }

            board.printHandCard();

            if (!board.handCheck()) 
            {
                System.out.println("\nYou do not have a valid card, try again!");

                while (true)
                {
                    System.out.print("\nPress 1 to select a card : ");
                    userInput = input.nextInt();
                    if (userInput == 1) 
                    {
                        board.distributeCards(board.getTurn());
                        break;
                    }
                }

                board.printHandCard();

                if (!board.handCheck()) 
                {
                    while (true) 
                    {
                        System.out.print("\nPress 1 to pass your turn as you don't have a valid card: ");
                        userInput = input.nextInt();
                        if (userInput == 1)
                            break;
                    }

                    board.checkPlayerTurn();
                    continue;
                }

            }

            while (true) 
            {

                System.out.print("\nSelect a card that you would like to play: ");
                userInput = input.nextInt() - 1;

                if (userInput > -1 && userInput < board.getPlayerCardsCount()) 
                {
                    if (board.playTurn(userInput)) 
                    {
                        break;
                    } 
                    else 
                    {
                        System.out.println("Your card is not valid, please try again!");
                        continue;
                    }
                }
                System.out.println("Invalid input, please try again!");
            }
        }   
        input.close();
    }

    /**
     * Method to print information about the players, including their names and the number of cards they hold.
     */
    private void printPlayers() 
    {
        ArrayList<Player> players = board.getPlayersList(); 

        for (int i = 0; i < players.size(); i++)         
        {
            String playerName = "Player " + (i + 1);
            int cardCounts = players.get(i).getCardCount();
            String playerInfo = String.format("%s Cards: %d", playerName, cardCounts);
            System.out.print(playerInfo);
            if (i != players.size() - 1) 
            {
                System.out.print(" | ");
            }
        }
        System.out.println();
    }

    /**
     * Method to print the results of a round, including the winner of the round.
     */
    private void printRound() 
    {
        Result latestRound = results.get(results.size() - 1);
        System.out.printf("%nRound Result:%n");
        latestRound.printResult();
        int winner = latestRound.getWinner() + 1;
        System.out.printf("Winner of the Round Is Player %d%n", winner);
    }

    /**
     * Method to print the results of all played rounds.
     */
    private void printAllResult() 
    {
        System.out.println("\nResult of all the played rounds:\n");
        String line = " ------------  ";
        System.out.print("------- | ");
        
        for (int j = 0; j < totalPlayers; j++)
            System.out.print(line);

        System.out.print("\n");
        
        for (Result result : results) 
        {
            System.out.print("Round " + (results.indexOf(result) + 1) + " : ");
            result.printResult();
        }
        System.out.print("\n");
    }
}
