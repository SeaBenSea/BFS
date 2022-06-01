import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFSearch {

    // class vertex
    public class vertex {
        /*
         * color: color of the vertex
         * distance: distance from the source
         * pi: parent of the vertex
         * c: char name of the vertex
         */
        public String color;
        public int distance;
        public vertex pi;
        public char c;

        // constructor
        public vertex(String color, int distance, vertex pi, char c) {
            this.color = color;
            this.distance = distance;
            this.pi = pi;
            this.c = c;
        }

        // constructor
        public vertex(String color, int distance, char c) {

            this.color = color;
            this.distance = distance;
        }

    }

    /**
     * print the status of the vertex
     * 
     * @param graph
     * @param s
     */
    public void print_status_of_vertex(ArrayList<BFSearch.vertex> graph, int s) {
        /*
         * graph: graph
         * s: source
         */

        System.out.println("\n");
        // print the status of each vertex in the graph
        for (int i = 0; i < graph.size(); i++) {
            System.out.println("vertex " + graph.get(i).c + ": color: " + graph.get(i).color);
        }
    }

    /**
     * Bfs search
     * 
     * @param adjmatrix
     * @param s
     */
    public void BFS(int[][] adjmatrix, int s) {
        /*
         * adjmatrix: adjacency matrix
         * s: source
         */

        // create a graph
        ArrayList<vertex> graph = new ArrayList<vertex>(adjmatrix.length);

        // add vertices to the graph
        for (int i = 0; i < adjmatrix.length; i++) {
            graph.add(new vertex("white", Integer.MAX_VALUE, null, (char) (i + 82)));
        }

        // print the status of the graph
        print_status_of_vertex(graph, s);

        // set starting vertex
        graph.get(s).color = "gray";
        graph.get(s).distance = 0;
        graph.get(s).pi = null;

        // print the status of the graph
        print_status_of_vertex(graph, s);

        // create a queue and add the starting vertex
        Queue<vertex> queue = new LinkedList<vertex>();
        queue.add(graph.get(s));

        // while the queue is not empty
        while (!queue.isEmpty()) {
            // get the first vertex in the queue
            vertex u = queue.remove();
            // do the bfs
            for (int i = 0; i < adjmatrix[u.distance].length; i++) {
                if (adjmatrix[graph.indexOf(u)][i] == 1) {
                    if (graph.get(i).color == "white") {
                        graph.get(i).color = "gray";
                        graph.get(i).distance = u.distance + 1;
                        graph.get(i).pi = u;
                        queue.add(graph.get(i));
                    }
                }
            }
            u.color = "black";
            // print the status of the graph
            print_status_of_vertex(graph, s);
        }

    }

    /**
     * Path from source to destination
     * 
     * @param adjmatrix
     * @param s
     * @param t
     */
    public void Path(int[][] adjmatrix, int s, int t) {
        /*
         * adjmatrix: adjacency matrix
         * s: source
         * t: target
         */

        ArrayList<vertex> graph = new ArrayList<vertex>(adjmatrix.length);

        for (int i = 0; i < adjmatrix.length; i++) {
            graph.add(new vertex("white", Integer.MAX_VALUE, null, (char) (i + 82)));
        }

        graph.get(s).color = "gray";
        graph.get(s).distance = 0;
        graph.get(s).pi = null;

        Queue<vertex> queue = new LinkedList<vertex>();
        queue.add(graph.get(s));

        while (!queue.isEmpty()) {
            vertex u = queue.remove();
            for (int i = 0; i < adjmatrix[u.distance].length; i++) {
                if (adjmatrix[graph.indexOf(u)][i] == 1) {
                    if (graph.get(i).color == "white") {
                        graph.get(i).color = "gray";
                        graph.get(i).distance = u.distance + 1;
                        graph.get(i).pi = u;
                        queue.add(graph.get(i));
                    }
                }
            }
            u.color = "black";

        }

        System.out.println("\n");
        // print information of the path
        System.out.println("Path from " + graph.get(s).c + " to " + graph.get(t).c + ": ");
        // set target vertex
        vertex v = graph.get(t);
        // while target's parent is not null print the vertex and update the target
        while (v != null) {
            System.out.print(v.c + " ");
            v = v.pi;
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int[][] adjmatrix = { { 0, 1, 0, 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0, 1, 1, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0, 0, 1, 0 } };
        BFSearch t = new BFSearch();
        t.BFS(adjmatrix, 1);
        t.Path(adjmatrix, 1, 3);

    }
}
