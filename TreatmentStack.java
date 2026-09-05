public class TreatmentStack {

    // Inner node class for the linked-list based stack
    private class StackNode {
        TreatmentRecord record;
        StackNode next;
        StackNode(TreatmentRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private StackNode top; // most recently completed treatment
    private int size;

    public TreatmentStack() {
        this.top = null;
        this.size = 0;
    }

    // Push - add a newly completed treatment record on top of the stack
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record pushed: " + record);
    }

    // Pop - remove and return the most recently completed treatment record
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord popped = top.record;
        top = top.next;
        size--;
        System.out.println("Treatment record popped: " + popped);
        return popped;
    }

    // Display every treatment record, most recent first
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("  No treatment records found.");
            return;
        }
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.record);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() { return top == null; }
    public int getSize() { return size; }
}
