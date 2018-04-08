/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CompetitionFloydWarshall {

    private class Edge {
        final int from;
        final int to;
        double weight;

        Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    int sA, sB, sC;
    int V;
    double[][] dist;
    int slowest;
    boolean run = true;
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        FileInputStream fs = null;
        if (sA < sB && sA < sC)
            slowest = sA;
        else if (sB < sA && sB < sC)
            slowest = sB;
        else if (sC < sA && sC < sB)
            slowest = sC;
        if (filename != null) {

            try {
                fs = new FileInputStream(filename);
            } catch (FileNotFoundException e) {
                run = false;
                e.printStackTrace();
            }
            Scanner scanner = null;
            if (fs != null) {
                scanner = new Scanner(fs);

                Scanner digScanner;
                String data = "";
                this.sA = sA;
                this.sB = sB;
                this.sC = sC;
                while (scanner.hasNextLine()) {
                    data += scanner.nextLine() + "\n";
                }
                String[] dataArray = data.split("\n");
                int interSectionCount = Integer.parseInt(dataArray[0]);
                int streetCount = Integer.parseInt(dataArray[1]);
                V = interSectionCount;
                dist = new double[V][V];
                int from, to;
                for (int i = 0; i < V; i++) {
                    dist[i][i] = 0;
                }
                double weight;
                for (int i = 0; i < streetCount; i++) {
                    String currentEntry = dataArray[i + 2];
                    digScanner = new Scanner(currentEntry);
                    from = digScanner.nextInt();/*Integer.parseInt(currentArray[0]);*/
                    to = digScanner.nextInt();/*Integer.parseInt(currentArray[1]);*/
                    weight = digScanner.nextDouble();/*Double.parseDouble(currentArray[2])*/
                    dist[from][to] = weight;
                }
            }
        }
        else {
            run = false;
        }
        //TODO
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        if (run) {
            double largest = -1;
            for (int k = 1; k < V; k++) {
                for (int i = 1; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            if (dist[i][k] + dist[k][j] > largest) {
                                largest = dist[i][k] + dist[k][j];
                            }
                        }
                    }
                }
            }
            largest *= 1000;
            double res = largest/slowest;
            return (int) res;
        }
        return -1;
    }

}