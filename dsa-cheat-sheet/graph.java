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
    public String findOrder(int n)
    {
        
       
    }
}