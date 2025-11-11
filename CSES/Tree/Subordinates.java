package CSES.Tree;
import java.io.*;
import java.util.*;

public class Subordinates {
    private static final int[] result;
    private static final List<List<Integer>> tree;
    private static  final int[] outDegree;
    
    static {//Tree builder
        // Initialize static fields using fast input
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        result = new int[n];
        outDegree = new int[n];
        tree = new ArrayList<>(n);
        
        // empty-arraylist all lists
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        
        // Build tree
        for (int i = 1; i < n; i++) {
            int parent = fr.nextInt() - 1;
            // tree.get(parent).add(i);
            tree.get(i).add(parent);
            outDegree[parent]++;
        }
    }
    
    public static void main(String[] args) {
        // Calculate subordinates using DFS
        // dfs(0);
        toplogicalSort();
        // Use StringBuilder for efficient string concatenation
        StringBuilder sb = new StringBuilder(result.length * 2);
        for (int count : result) {
            sb.append(count).append(' ');
        }
        
        // Print result
        System.out.println(sb);
    }
    
    // private static void dfs(int node) {
    //     for (int child : tree.get(node)) {
    //         dfs(child);
    //         result[node] += result[child] + 1;
    //     }
    // }

    private static void toplogicalSort(){
        Queue<Integer> queue = new LinkedList<>();
        for(int index=0;index<result.length;index++){
            if(outDegree[index] == 0) queue.add(index);
        }

        while(!queue.isEmpty()){
            int curr = queue.poll();
            for(int parent:tree.get(curr)){
                outDegree[parent]--;
                result[parent] += (result[curr] + 1);
                if(outDegree[parent] == 0){
                    queue.add(parent);
                }
            }
        }
    }
    
    // Optimized FastReader with minimal features needed
    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;
        
        private FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        private String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        private int nextInt() {
            return Integer.parseInt(next());
        }
    }
}