import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AppMain {

    @Test
    public void tc1() throws FileNotFoundException {
        String name = "clustering1-example-10-solution-11.txt";
        Graph graph = readGraph(name);
        SmallCluster alg = new SmallCluster();
        long space = alg.kruskal(graph, 4);
        Assert.assertEquals(3, space);

        space = alg.kruskal(graph, 2);
        Assert.assertEquals(5, space);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String name = "clustering1.txt";
        Graph graph = readGraph(name);
        SmallCluster alg = new SmallCluster();
        alg.kruskal(graph, 4);
    }

    private static Graph readGraph(String name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(name));
        int nodeNumber = scanner.nextInt();
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        List<Edge> edges = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int nid1 = scanner.nextInt();
            int nid2 = scanner.nextInt();
            long cost = scanner.nextLong();
            if (!nodeMap.containsKey(nid1)) {
                Node node1 = new Node();
                node1.id = nid1;
                nodeMap.put(nid1, node1);
            }
            Node node1 = nodeMap.get(nid1);
            if (!nodeMap.containsKey(nid2)) {
                Node node2 = new Node();
                node2.id = nid2;
                nodeMap.put(nid2, node2);
            }
            Node node2 = nodeMap.get(nid2);
            Edge edge = new Edge();
            edge.node1 = node1;
            edge.node2 = node2;
            edge.cost = cost;
            node1.edges.add(edge);
            node2.edges.add(edge);
            edges.add(edge);
        }
        Graph graph = new Graph();
        graph.nodes = new ArrayList<>(nodeMap.values());
        graph.edges = edges;
        return graph;
    }
}
