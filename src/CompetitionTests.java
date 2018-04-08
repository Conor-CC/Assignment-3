import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        //TODO
        //Filename is just data.txt for real submission
            CompetitionDijkstra cd = new CompetitionDijkstra("data.txt", 100, 100, 100);
            assertNotNull(cd);
    }

    @Test
    public void testTimeOnTiny() {
        CompetitionDijkstra cd = new CompetitionDijkstra("src/data.txt", 3233, 7, 2368726);
        int t = cd.timeRequiredforCompetition();
        System.out.println(t);
        CompetitionFloydWarshall fw = new CompetitionFloydWarshall("src/data.txt", 3233, 7, 2368726);
        t = fw.timeRequiredforCompetition();
        System.out.println(t);

        fw = new CompetitionFloydWarshall("", 3233, 7, 2368726);
        System.out.println(fw.timeRequiredforCompetition());
    }

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall fw = new CompetitionFloydWarshall("data.txt", 100, 100, 100);
        assertNotNull(fw);
    }

//    public static void main(String[] args) {
//        try {
//            CompetitionDijkstra cd = new CompetitionDijkstra("src/data.txt", 100, 100, 100);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //TODO - more tests
}
