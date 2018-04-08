import org.junit.Test;

import java.io.IOException;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        //TODO
    }

    @Test
    public void testFWConstructor() {
        //TODO
    }

    public static void main(String[] args) {
        try {
            CompetitionDijkstra cd = new CompetitionDijkstra("src/data.txt", 100, 100, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO - more tests
}
