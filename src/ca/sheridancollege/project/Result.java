package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Result is a class that represents the result of a round in the UNO card game.
 * It contains the players' scores and determines the winner of the round.
 *
 * @author Nehamaria Roy
 */
public class Result 
{
    private Player[] players; // An array of players in the round.
    private int winner; // The index of the winning player in the players array.

    /**
     * Constructor for creating a Result object based on a list of players.
     *
     * @param players The list of players in the round.
     */
    public Result(ArrayList<Player> players) 
    {
        this.players = players.toArray(new Player[players.size()]);
        rankPlayer();
    }

    /**
     * Private method to rank the players based on their scores and determine the winner.
     */
    private void rankPlayer() 
    {
        Arrays.sort(players, Comparator.comparingInt(Player::getScore).reversed());
        winner = players[0].getCardCount();
    }

    /**
     * Get the index of the winning player.
     *
     * @return The index of the winning player.
     */
    public int getWinner() 
    {
        return winner;
    }

    /**
     * Print the result of the round, including each player's score.
     */
    public void printResult() 
    {
        StringJoiner resultString = new StringJoiner(" | ");
        for (Player player : players) 
        {
            resultString.add("Player " + (player.getCardCount() + 1) + " : " + player.getScore());
        }
        System.out.println(resultString);
    }
}
