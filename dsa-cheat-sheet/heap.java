import java.util.PriorityQueue;

class Node {
    int key;
    int value;
    Node(int key, int value) {
        this.key = this.value;
    }
};

class Main {
    public static void main() {
        // Min heap is the default. This will create a min-heap based on keys on nodes;
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> {
            return Integer.compare(n1.key, n2.key);
        });

        /*
            Operations : 
                getMin / getMax O(1)
                insert O(logN)
                delete O(logN)
                removeMin O(logN)
        */

        // Create Max heap on keys on nodes
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((n1, n2) -> {
            return Integer.compare(n2.key, n1.key);
        });
        // add to heap
        maxHeap.add(new Node(1, 10)); // This will throw an exception if max capacity of heap has been reached
        maxHeap.offer(new Node(2, 20)); // This will not throw any exception on reaching max capacity
        // get top element without removing it
        Node topEle =  maxHeap.peek();
        // get top element and remove it as well
        topEle = maxHeap.poll();
        // size and empty check
        int size = maxHeap.size();
        boolean isEmpty = maxHeap.isEmpty();
    }
}