import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CompetitionDijkstra {

    private class Vertex {
        final int id;
        LinkedList<Edge> edges;

        Vertex(int id) {
            this.id = id;
            this.edges = new LinkedList<Edge>();
        }
    }

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


    final Vertex[] vertices;
    boolean[] markingArray;
    double prevWeight = 0;
    Edge prevEdge;
    double currentTotalWeight;
    double[] bigguns;
    PriorityQueue<Double> biggestWeights;

    public CompetitionDijkstra(String filename, int sA, int sB, int sC) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scanner = new Scanner(fs);
        Scanner digScanner;
        String data = "";
        while (scanner.hasNextLine()) {
            data += scanner.nextLine() + "\n";
        }
        String[] dataArray = data.split("\n");
        int interSectionCount = Integer.parseInt(dataArray[0]);
        int streetCount = Integer.parseInt(dataArray[1]);
        vertices = new Vertex[interSectionCount];
        markingArray = new boolean[interSectionCount];
        bigguns = new double[interSectionCount];
        int from, to; double weight;
        for (int i = 0; i < streetCount; i++) {
            String currentEntry = dataArray[i + 2];
            digScanner = new Scanner(currentEntry);
            //String[] currentArray = currentEntry.split("[ ]+");
            from = digScanner.nextInt();/*Integer.parseInt(currentArray[0]);*/
            to = digScanner.nextInt();/*Integer.parseInt(currentArray[1]);*/
            weight = digScanner.nextDouble();/*Double.parseDouble(currentArray[2])*/;
            if (vertices[from] == null) {
                vertices[from] = new Vertex(from);
            }
            vertices[from].edges.add(new Edge(from, to, weight));
        }
        System.out.println("Finished parsing...");
        timeRequiredForCompetition(sA, sB, sC);
        System.out.println();
    }



    public int timeRequiredForCompetition(int sA, int sB, int sC) {
        LinkedList<Edge> routes = null;
        biggestWeights = new PriorityQueue<Double>(20, Collections.reverseOrder());
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < markingArray.length; j++) {
                markingArray[j] = false;
            }
            routes = dijkstraAlgorithm(vertices[i]);
        }



        double first = biggestWeights.poll();
        double second = biggestWeights.poll();
        double third = biggestWeights.poll();

        double firstDiv = sA;
        double secondDiv = sB;
        doub    le thirdDiv = sC;

        double firstDist = first * 100;
        double secondDist = second * 100;
        double thirdDist = third * 100;
        System.out.println("\n" + firstDist);

        double resInSecs = firstDist/firstDiv;

        System.out.println(resInSecs);

        return -1;
    }


    double largest;
    int index;
    private LinkedList<Edge> dijkstraAlgorithm(Vertex sourceVertex) {
        largest = 0.0;
        currentTotalWeight = 0.0;
        LinkedList<Edge> settledEdges = new LinkedList<Edge>();
        LinkedList<Edge> unsettledEdges = new LinkedList<Edge>();
        LinkedList<Edge> edgesToAdd = new LinkedList<Edge>();

        edgesToAdd = sourceVertex.edges;
        markingArray[sourceVertex.id] = true;
        addEdges(unsettledEdges, edgesToAdd, settledEdges);
        chooseShortestForSettledNodes(unsettledEdges, settledEdges);
        while (!unsettledEdges.isEmpty()) {
            chooseShortestForSettledNodes(unsettledEdges, settledEdges);
        }
        biggestWeights.add(largest);
        return settledEdges;
    }

    private void chooseShortestForSettledNodes(LinkedList<Edge> toAmend, LinkedList<Edge> toAddTo) {
        double weight = Double.MAX_VALUE;
        Edge shortest = null;
        for (int i = 0; i < toAmend.size(); i++) {
            Edge tmp = toAmend.get(i);
            if (tmp.weight < weight) {
                weight = tmp.weight;
                shortest = tmp;
            }
        }
        toAmend.remove(shortest);
        addEdges(toAmend, vertices[shortest.to].edges, toAddTo);
        toAddTo.add(shortest);
    }

    private void addEdges(LinkedList<Edge> toAmend, LinkedList<Edge> toAdd, LinkedList<Edge> settled) {
        for (int i = 0; i < toAdd.size(); i++) {
            Edge newEdge = toAdd.get(i);
            if (!markingArray[newEdge.to]) {
                if (toAmend.size() > 0 && newEdge.from == prevEdge.from)
                    currentTotalWeight -= prevEdge.weight;
                currentTotalWeight += newEdge.weight;
                if (currentTotalWeight > largest)
                    largest = currentTotalWeight;
                Edge eToAdd = new Edge(newEdge.from, newEdge.to, currentTotalWeight/*newEdge.weight*/);
                toAmend.add(eToAdd);
                markingArray[newEdge.to] = true;
            }
            prevEdge = newEdge;
        }
    }
}