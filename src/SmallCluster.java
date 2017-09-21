import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallCluster {

    List<Edge> sortedEdges;
    UnionFind unionFind;

    public void onInit(Graph graph) {
        unionFind = new UnionFind();
        unionFind.onInit(graph.nodes);
        ArrayList<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges, (o1, o2) -> {
            if (o1.cost < o2.cost) {
                return -1;
            }
            if (o1.cost > o2.cost) {
                return 1;
            }
            return 0;
        });
        sortedEdges = edges;
    }

    public long kruskal(Graph graph, int numberCluster) {
        onInit(graph);
        int edgeLoc = 0;
        int clusterCount = unionFind.getPartitionNumber();
        for (edgeLoc = 0; edgeLoc < sortedEdges.size() && clusterCount > numberCluster; edgeLoc++) {
            Edge edge = sortedEdges.get(edgeLoc);
            Node lead1 = unionFind.getPartitionLead(edge.node1);
            Node lead2 = unionFind.getPartitionLead(edge.node2);
            if (!lead1.equals(lead2)) {
                System.out.println("Merge edge " + edge);
                unionFind.mergePartition(lead1, lead2);
                clusterCount = unionFind.getPartitionNumber();
            }
        }
        for (; edgeLoc < sortedEdges.size(); edgeLoc++) {
            Edge edge = sortedEdges.get(edgeLoc);
            Node lead1 = unionFind.getPartitionLead(edge.node1);
            Node lead2 = unionFind.getPartitionLead(edge.node2);
            if (!lead1.equals(lead2)) {
                System.out.println(edge.cost);
                return edge.cost;
            }
        }
        return 0;
    }
}
