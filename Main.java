import java.util.*;

class Graph {
    private final int V;
    private final List<List<Character>> adj;

    // Constructor untuk inisialisasi graf dengan jumlah vertex (simpul) V
    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);

        // Inisialisasi adjacency list untuk setiap vertex
        for (int i = 0; i < V; i++)
            adj.add(new LinkedList<>());
    }

    // Metode untuk menambahkan edge (sisi) antara dua vertex
    void addEdge(char u, char v) {
        adj.get(u - 'A').add(v);
    }

    // Metode untuk melakukan penelusuran BFS (Breadth-First Search) dari vertex start
    void BFS(char start) {
        // Array untuk menyimpan status kunjungan setiap vertex
        boolean[] visited = new boolean[V];
        // Antrian untuk menyimpan vertex yang akan dikunjungi
        Queue<Character> queue = new LinkedList<>();

        // Mulai dari vertex start
        visited[start - 'A'] = true;
        queue.add(start);

        // Selama antrian tidak kosong, lakukan penelusuran BFS
        while (!queue.isEmpty()) {
            char u = queue.poll();
            System.out.print(u + " "); // Cetak vertex yang sedang dikunjungi

            // Iterasi melalui semua vertex yang bertetangga dengan vertex u
            for (char v : adj.get(u - 'A')) {
                // Jika vertex v belum dikunjungi, tambahkan ke antrian dan tandai sebagai dikunjungi
                if (!visited[v - 'A']) {
                    queue.add(v);
                    visited[v - 'A'] = true;
                }
            }
        }
    }

    // Metode untuk melakukan penelusuran DFS (Depth-First Search) dari vertex start
    void DFS(char start) {
        // Array untuk menyimpan status kunjungan setiap vertex
        boolean[] visited = new boolean[V];
        // Panggil metode rekursif untuk DFS
        DFSUtil(start, visited);
    }

    // Metode rekursif untuk melakukan penelusuran DFS
    private void DFSUtil(char v, boolean[] visited) {
        visited[v - 'A'] = true;
        System.out.print(v + " "); // Cetak vertex yang sedang dikunjungi

        // Iterasi melalui semua vertex yang bertetangga dengan vertex v
        for (char u : adj.get(v - 'A')) {
            // Jika vertex u belum dikunjungi, panggil rekursif DFSUtil
            if (!visited[u - 'A'])
                DFSUtil(u, visited);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Buat objek graf dengan 9 vertex
        Graph graph = new Graph(9);
        // Tambahkan edge antara vertex-vertex yang sesuai
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'D');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'G');
        graph.addEdge('E', 'H');
        graph.addEdge('E', 'F');
        graph.addEdge('G', 'H');
        graph.addEdge('H', 'I');
        graph.addEdge('I', 'F');
        graph.addEdge('F', 'C');

        // Cetak hasil penelusuran BFS dari vertex 'A'
        System.out.println("BFS Traversal starting from A:");
        graph.BFS('A');

        // Cetak hasil penelusuran DFS dari vertex 'A'
        System.out.println("\nDFS Traversal starting from A:");
        graph.DFS('A');
    }
}
