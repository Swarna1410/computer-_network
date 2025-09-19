// Program 5: Develop a program to find the shortest path between vertices 
// using the Bellman–Ford algorithm.

package com.smvitm.clab;
import java.util.Scanner;

public class BellmanFordDemo {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int V, E = 1, checkNegative;
        int w[][] = new int[20][20];
        int edge[][] = new int[50][2];

        // Read number of vertices
        System.out.print("Enter the number of vertices: ");
        V = in.nextInt();

        // Read weight matrix
        System.out.println("Enter the weight matrix (0 if no edge): ");
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                w[i][j] = in.nextInt();
                if (w[i][j] != 0) {
                    edge[E][0] = i;
                    edge[E][1] = j;
                    E++;
                }
            }
        }

        // Call Bellman–Ford
        checkNegative = bellmanFord(w, V, E - 1, edge);

        // Print result
        if (checkNegative == 1)
            System.out.println("\nNo negative weight cycle\n");
        else
            System.out.println("\nNegative weight cycle exists\n");
    }

    // Bellman–Ford function
    public static int bellmanFord(int w[][], int V, int E, int edge[][]) {
        int u, v, flag = 1, S;
        int distance[] = new int[20];
        int parent[] = new int[20];

        // Initialize distances
        for (int i = 1; i <= V; i++) {
            distance[i] = 999;
            parent[i] = -1;
        }

        // Read source vertex
        System.out.print("Enter the source vertex: ");
        S = in.nextInt();
        distance[S] = 0;

        // Relax edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (int k = 1; k <= E; k++) {
                u = edge[k][0];
                v = edge[k][1];
                if (distance[u] + w[u][v] < distance[v]) {
                    distance[v] = distance[u] + w[u][v];
                    parent[v] = u;
                }
            }
        }

        // Check for negative weight cycle
        for (int k = 1; k <= E; k++) {
            u = edge[k][0];
            v = edge[k][1];
            if (distance[u] + w[u][v] < distance[v]) {
                flag = 0;
            }
        }

        // Print results if no negative cycle
        if (flag == 1) {
            for (int i = 1; i <= V; i++) {
                System.out.println("Vertex " + i + " -> cost = " + distance[i] +
                                   " parent = " + parent[i]);
            }
        }
        return flag;
    }
}
