public class VisitLinkedList {
    private Visit head; // first visit in the list

    public VisitLinkedList() {
        this.head = null;
    }

    // Adds a new visit to the end of the list
    public void addVisit(Visit newVisit) {
        if (head == null) {
            head = newVisit;
            return;
        }
        Visit current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newVisit;
    }

    // Removes a visit by its visit ID. Returns true if removed.
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        Visit current = head;
        while (current.next != null) {
            if (current.next.getVisitId() == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Searches for a visit by its visit ID
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.getVisitId() == visitId) return current;
            current = current.next;
        }
        return null;
    }

    // Prints every visit in the list, oldest to newest
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No previous visits recorded.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println("  " + current);
            current = current.next;
        }
    }

    public boolean isEmpty() { return head == null; }
}
