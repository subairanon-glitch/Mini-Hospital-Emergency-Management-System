public class EmergencyQueue {

    // Inner node class for the linked-list based queue
    private class QueueNode {
        Patient patient;
        QueueNode next;
        QueueNode(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private QueueNode front; // next patient to be treated
    private QueueNode rear;  // last patient added
    private int size;

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Enqueue - add a new patient at the rear of the queue
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " (ID: " + patient.getPatientId() + ") added to emergency queue.");
    }

    // Dequeue - remove and return the patient at the front (next for treatment)
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient treated = front.patient;
        front = front.next;
        if (front == null) rear = null; // queue became empty
        size--;
        System.out.println("Patient " + treated.getName() + " (ID: " + treated.getPatientId() + ") is now being treated.");
        return treated;
    }

    // Display every patient currently waiting, in FIFO order
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("  No patients currently waiting.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() { return front == null; }
    public int getSize() { return size; }
}
