class Node {
    int w;
    int x;
    Node(int x, int w) {
        this.x = x;
        this.w = w;
    }
};

class Main {
     
    private void adjList() {
        // Adjacencey list -> An array of size "n+1" where each array element is a List<Integer>
        List<Integer>[] A = new ArrayList[n+1];
        for(int i=0;i<=n;i++) {
            A[i] = new ArrayList<>();
        }
    }

    private void topologicalSort(List<Integer> A[]) {
         // topo logical sort
        int[] indegree = new int[n+1];
        for(int i=0;i<n-1;i++) {
            // increase indegree as required
            indegree[i] += 1;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<k;i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            int top = q.poll();
            // q.poll();
            // Typecasting int to char in java
            char ch = (char)('a' + top);
            // append char to 
            sb.append(ch);
            for(int x:A[top]) {
                indegree[x] -= 1;
                if(indegree[x] == 0) {
                    q.add(x);
                }
            }
        }
        return sb.toString();
    }
    
    private static List<Integer> dijkstra(int[][] edge,int n, int m,int src){
        List<Node>[] A = new List[n];
        for(int i=0;i<n;i++) {
            A[i] = new ArrayList<>();
        }

        for(int[] e : edge) {
            int x = e[0];
            int y = e[1];
            int w = e[2];
            A[x].add(new Node(y, w));
            A[y].add(new Node(x, w));
        }
        // min heap of weights
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.w, b.w);
        });

        pq.add(new Node(src, 0));
        int[] minDist = new int[n];
        for(int i=0;i<n;i++) {
            if(i != src) {
                minDist[i] = Integer.MAX_VALUE;
            }
        }
        while(!pq.isEmpty()) {
            int cur = pq.peek().x;
            int w = pq.peek().w;
            pq.poll();

            for(Node nbr : A[cur]) {
                int y = nbr.x;
                int dw = nbr.w;

                if(minDist[y] > w+dw) {
                    minDist[y] = w+dw;
                    pq.add(new Node(y, w + dw));
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int x:minDist) {
            ans.add(x);
        }
        return ans;

    }
}