import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Main class for Dijkstra's algorithm
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        // Define the labels for the nodes
        String[] nodeLabels = { "A", "J", "M", "R", "K", "S", "I", "N", "T", "D" };
        // Define the adjacency matrix for the graph
        int[][] matrix = {
            { 0, 53, 10, 12, 0, 0, 0, 0, 0, 0 },
            { 53, 0, 33, 0, 2, 0, 101, 0, 0, 0 },
            { 10, 33, 0, 9, 30, 18, 0, 0, 0, 0 },
            { 12, 0, 9, 0, 0, 17, 0, 0, 6, 0 },
            { 0, 2, 30, 0, 0, 14, 123, 122, 0, 0 },
            { 0, 0, 18, 17, 14, 0, 0, 137, 7, 0 },
            { 0, 101, 0, 0, 123, 0, 0, 8, 0, 71 },
            { 0, 0, 0, 0, 122, 137, 8, 0, 145, 66 },
            { 0, 0, 0, 6, 0, 7, 0, 145, 0, 212 },
            { 0, 0, 0, 0, 0, 0, 71, 66, 212, 0 },
        };

        // Create a scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start node (A-D):");
        char startNode = sc.next().toUpperCase().charAt(0);
        System.out.println("Enter end node (A-D):");
        char endNode = sc.next().toUpperCase().charAt(0);

        // Convert the start and end nodes to their corresponding indices
        int startNum = 0;
        int endNum = 0;
        for (int i = 0; i < nodeLabels.length; i++) {
            if (nodeLabels[i].charAt(0) == startNode) {
                startNum = i;
            }
            if (nodeLabels[i].charAt(0) == endNode) {
                endNum = i;
            }
        }

        // Create a graph and run Dijkstra's algorithm on it
        Graph graph = new Graph(matrix);
        graph.dijkstra(startNum, endNum);
        sc.close();
    }
}

// Class to represent a graph
class Graph {
    public int[][] matrix;
    public int numVertices;
    public static String[] nodeLabels = { "A", "J", "M", "R", "K", "S", "I", "N", "T", "D" };

    // Constructor for the graph
    public Graph(int[][] matrix) {
        numVertices = matrix.length;
        this.matrix = matrix;
    }

    // Dijkstra's algorithm
    public void dijkstra(int source, int target) {
        // Initialize the distance and previous arrays
        int[] dist = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        int[] prev = new int[numVertices];

        // Create a priority queue for the vertices
        PriorityQueue<Vertex> pq = new PriorityQueue<>(numVertices, (a, b) -> a.weight - b.weight);

        // Initialize the distance array and add the source to the queue
        for (int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        pq.add(new Vertex(source, 0));
        dist[source] = 0;

        // Main loop for Dijkstra's algorithm
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            if (visited[current.id]) {
                continue;
            }

            visited[current.id] = true;

            // Update the distances to the neighboring vertices
            for (int i = 0; i < numVertices; i++) {
                if (matrix[current.id][i] > 0 && dist[i] > dist[current.id] + matrix[current.id][i]) {
                    dist[i] = dist[current.id] + matrix[current.id][i];
                    prev[i] = current.id;
                    pq.add(new Vertex(i, dist[i]));
                }
            }
        }

        // Print the shortest path and its distance
        System.out.println("Node " + nodeLabels[target] + ": Distance from source: " + dist[target] + ", Path: "
                + getPath(prev, target));
    }

    // Method to reconstruct the shortest path
    public String getPath(int[] prev, int target) {
        List<String> path = new ArrayList<>();
        for (int at = target; at != -1; at = prev[at]) {
            path.add(nodeLabels[at]);
        }
        Collections.reverse(path);
        return path.toString();
    }

    // Class to represent a vertex in the graph
    static class Vertex {
        int id;
        int weight;

        public Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
}