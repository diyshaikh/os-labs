import java.util.ArrayList;

public class PriorityQueue {
    ArrayList<Process> heap;
    int size;

    public PriorityQueue() {
        this.heap = new ArrayList<>();
        size = 0;
    }

    public void enqueue(Process p) {
        heap.add(p);
        size++;
        int childIndex = heap.size() - 1;
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;

            if (heap.get(parentIndex).priority > heap.get(childIndex).priority) {
                swap(parentIndex, childIndex);
            } else {
                return;
            }

            childIndex = parentIndex;
        }
    }

    public Process dequeue() {
        Process p = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        size--;

        int parentIndex = 0;
        int swappedIndex = -1;
        while (parentIndex < heap.size()) {
            int leftIndex = (2 * parentIndex) + 1;
            int rightIndex = (2 * parentIndex) + 2;

            if (leftIndex >= heap.size() || rightIndex >= heap.size())
                break;

            if (heap.get(rightIndex).priority < heap.get(parentIndex).priority && heap.get(rightIndex).priority < heap.get(leftIndex).priority) {
                swappedIndex = rightIndex;
            } else if (heap.get(leftIndex).priority < heap.get(parentIndex).priority && heap.get(leftIndex).priority < heap.get(rightIndex).priority) {
                swappedIndex = leftIndex;
            } else {
                System.out.println("break");
                break;
            }
            swap(parentIndex, swappedIndex);
            parentIndex = swappedIndex;
        }

        return p;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int index1, int index2) {
        Process temp = heap.get(index2);
        heap.set(index2, heap.get(index1));
        heap.set(index1, temp);
    }

    public Process top() {
        return heap.get(0);
    }
}
