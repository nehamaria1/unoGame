package test.ca.sheridancollege.project;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import ca.sheridancollege.project.GamePlay;

public class GamePlayTest {
    @Test
    void testPlay() {
        String input = "1\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Mocking System.out to capture printed output
        TestHelper testHelper = new TestHelper();
        System.setOut(testHelper.getPrintStream());

        // Creating a new GamePlay instance
        GamePlay gamePlay = new GamePlay(4, 1);

        // Running the play method
        gamePlay.play();

        // Restoring System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Verifying the printed output
        assertTrue(testHelper.getOutput().contains("Press 1 to start a new game"));
        assertTrue(testHelper.getOutput().contains("Thanks for playing."));
    }

    // Helper class to capture printed output
    private static class TestHelper {
        private final StringBuilder output = new StringBuilder();

        public String getOutput() {
            return output.toString();
        }

        public PrintStream getPrintStream() {
            return new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    output.append((char) b);
                }
            });
        }
    }
}
