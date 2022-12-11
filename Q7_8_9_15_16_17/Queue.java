public class Queue {
    Node top;
    Node tail;
    int size = 0;

    public void enqueue(Process p) {
        Node temp = new Node(p);
        if (top == null) {
            top = temp;
            tail = temp;
        } else {
            tail.next = temp;
            tail = tail.next;
        }
        size++;
    }

    public Process dequeue() {
        if (top == null)
            return null;
        Process temp = top.data;
        top = top.next;
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Process p) {
        Node temp = top;
        while (temp != null) {
            if (temp.data.id == p.id)
                return true;
            temp = temp.next;
        }
        return false;
    }
}

class Node {
    Process data;
    Node next;

    public Node() {
    }

    public Node(Process data) {
        this.data = data;
    }

    public Node(Process data, Node next) {
        this.data = data;
        this.next = next;
    }
}
