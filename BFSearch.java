import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFSearch {

    public class vertex {
        public String color;
        public int distance;
        public vertex pi;
        public char c;

        public vertex(String color, int distance, vertex pi, char c) {
            this.color = color;
            this.distance = distance;
            this.pi = pi;
            this.c = c;
        }

        public vertex(String color, int distance, char c) {

            this.color = color;
            this.distance = distance;
        }

    }

    public void print_status_of_vertex(ArrayList<BFSearch.vertex> graph, int s) {

        System.out.println("\n");
        for (int i = 0; i < graph.size(); i++) {
            System.out.println("vertex " + graph.get(i).c + ": color: " + graph.get(i).color);
        }
    }



    public void BFS(int[][] adjmatrix, int s) {

        ArrayList<vertex> graph = new ArrayList<vertex>(adjmatrix.length);

        for (int i = 0; i < adjmatrix.length; i++) {
            graph.add(new vertex("white", Integer.MAX_VALUE, null, (char) (i + 82)));
        }

        print_status_of_vertex(graph, s);

        graph.get(s).color = "gray";
        graph.get(s).distance = 0;
        graph.get(s).pi = null;

        print_status_of_vertex(graph, s);

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
            print_status_of_vertex(graph, s);
        }

    }

    public void Path(int[][] adjmatrix, int s, int t) {
        ArrayList<vertex> graph = new ArrayList<vertex>(adjmatrix.length);

        for (int i = 0; i < adjmatrix.length; i++) {
            graph.add(new vertex("white", Integer.MAX_VALUE, null, (char) (i + 82)));
        }

        print_status_of_vertex(graph, s);

        graph.get(s).color = "gray";
        graph.get(s).distance = 0;
        graph.get(s).pi = null;

        print_status_of_vertex(graph, s);

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
            print_status_of_vertex(graph, s);
        }
        System.out.println("\n");
        System.out.println("Path from " + graph.get(s).c + " to " + graph.get(t).c + ": ");
        vertex v = graph.get(t);
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
