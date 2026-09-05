public class Demo {

    public static void main(String[] args) {
        PatientBST patientRecords = new PatientBST();
        EmergencyQueue emergencyQueue = new EmergencyQueue();
        TreatmentStack treatmentHistory = new TreatmentStack();

        section("1. PATIENT RECORDS - BINARY SEARCH TREE");

        Patient p1 = new Patient(105, "Nimal Perera", 34, "0771234567", "Fracture");
        Patient p2 = new Patient(102, "Kamala Silva", 58, "0777654321", "Chest Pain");
        Patient p3 = new Patient(110, "Ruwan Fernando", 21, "0719876543", "Fever");
        Patient p4 = new Patient(101, "Ishara De Zoysa", 45, "0765554433", "Migraine");

        System.out.println("Inserting patients 105, 102, 110, 101 ...");
        patientRecords.insert(p1);
        patientRecords.insert(p2);
        patientRecords.insert(p3);
        patientRecords.insert(p4);

        System.out.println("\nSearching for Patient ID 102:");
        System.out.println("  " + patientRecords.search(102));

        System.out.println("\nIn-order traversal (ascending Patient ID):");
        patientRecords.inorderTraversal();

        System.out.println("\nDeleting Patient ID 102:");
        patientRecords.delete(102);

        System.out.println("\nIn-order traversal after deletion:");
        patientRecords.inorderTraversal();

        section("2. EMERGENCY PATIENT QUEUE - QUEUE");

        System.out.println("Displaying an empty queue first:");
        emergencyQueue.displayQueue();

        System.out.println("\nEnqueuing patients 105, 110, 101 ...");
        emergencyQueue.enqueue(p1);
        emergencyQueue.enqueue(p3);
        emergencyQueue.enqueue(p4);

        System.out.println("\nCurrent queue:");
        emergencyQueue.displayQueue();

        System.out.println("\nDequeuing the next patient for treatment:");
        Patient treated = emergencyQueue.dequeue();

        System.out.println("\nQueue after dequeue:");
        emergencyQueue.displayQueue();

        section("3. TREATMENT HISTORY - STACK");

        System.out.println("Displaying an empty stack first:");
        treatmentHistory.displayStack();

        System.out.println("\nPushing completed treatment for " + treated.getName() + ":");
        TreatmentRecord r1 = new TreatmentRecord(treated.getPatientId(), treated.getName(),
                "Cast applied for fracture", "2026-09-05");
        treatmentHistory.push(r1);

        System.out.println("\nPushing another completed treatment for " + p3.getName() + ":");
        TreatmentRecord r2 = new TreatmentRecord(p3.getPatientId(), p3.getName(),
                "Fever medication administered", "2026-09-05");
        treatmentHistory.push(r2);

        System.out.println("\nCurrent treatment history (most recent first):");
        treatmentHistory.displayStack();

        System.out.println("\nPopping the most recent treatment record:");
        treatmentHistory.pop();

        System.out.println("\nTreatment history after pop:");
        treatmentHistory.displayStack();

        section("4. PATIENT VISIT HISTORY - SINGLY LINKED LIST");

        System.out.println("Displaying " + p1.getName() + "'s visit history (empty at first):");
        p1.getVisitHistory().displayVisits();

        System.out.println("\nAdding two visits for " + p1.getName() + ":");
        Visit v1 = new Visit(1, "2025-01-10", "Dr. Perera", "Sprained ankle", "Rest and ice pack");
        Visit v2 = new Visit(2, "2026-09-05", "Dr. Jayasuriya", "Fracture", "Cast applied");
        p1.getVisitHistory().addVisit(v1);
        p1.getVisitHistory().addVisit(v2);

        System.out.println("\nVisit history now:");
        p1.getVisitHistory().displayVisits();

        System.out.println("\nSearching for Visit ID 1:");
        System.out.println("  " + p1.getVisitHistory().searchVisit(1));

        System.out.println("\nRemoving Visit ID 1:");
        p1.getVisitHistory().removeVisit(1);

        System.out.println("\nVisit history after removal:");
        p1.getVisitHistory().displayVisits();

        section("DEMO COMPLETE");
    }

    private static void section(String title) {
        System.out.println("\n==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
