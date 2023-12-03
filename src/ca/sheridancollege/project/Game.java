package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Game class serves as the entry point for the UNO card game.
 * It allows players to choose the game mode (single or multiplayer) and sets up the game accordingly.
 *
 * @author Nehamaria Roy
 */
public class Game 
{
    private static GamePlay UNOGame; // Instance of the UNO card game

    /**
     * The main method that starts the UNO card game.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        int totalPlayers; // The total number of players in the game
        int playMode; // The chosen play mode (single or multiplayer)

        System.out.print(
                "\nPress 1 to play in single mode\nPress 2 to play in a Multiplayer Mode : ");
        playMode = input.nextInt();

        switch (playMode) 
        {
            case 1:
                System.out.print("Please Enter Total Number of Players (3-4-5) : ");
                break;
            case 2:
                System.out.print("Please Enter The Number Of Players : ");
                break;            
        }

        totalPlayers = input.nextInt();
        UNOGame = new GamePlay(totalPlayers, playMode); // Create an instance of the GamePlay class
        UNOGame.play(); // Start the UNO card game
        input.close();
    }
}
