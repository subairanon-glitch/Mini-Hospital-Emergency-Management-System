import java.util.Scanner;

public class Main {

    private static PatientBST patientRecords = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentHistory = new TreatmentStack();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: displayAllPatients(); break;
                case 5: addToQueue(); break;
                case 6: displayQueue(); break;
                case 7: dequeueAndTreat(); break;
                case 8: displayTreatmentHistory(); break;
                case 9: popLastTreatment(); break;
                case 10: addVisit(); break;
                case 11: removeVisit(); break;
                case 12: searchVisit(); break;
                case 13: displayVisitHistory(); break;
                case 0: System.out.println("Exiting system. Goodbye!"); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Mini Hospital Emergency Management System =====");
        System.out.println(" 1. Register new patient (BST)");
        System.out.println(" 2. Search patient by ID (BST)");
        System.out.println(" 3. Delete patient (BST)");
        System.out.println(" 4. Display all patients - in-order (BST)");
        System.out.println(" 5. Add patient to emergency queue");
        System.out.println(" 6. Display emergency queue");
        System.out.println(" 7. Dequeue next patient & complete treatment (Queue -> Stack)");
        System.out.println(" 8. Display treatment history (Stack)");
        System.out.println(" 9. Pop most recent treatment record (Stack)");
        System.out.println("10. Add a visit to a patient's history (Linked List)");
        System.out.println("11. Remove a visit from a patient's history (Linked List)");
        System.out.println("12. Search a visit in a patient's history (Linked List)");
        System.out.println("13. Display a patient's visit history (Linked List)");
        System.out.println(" 0. Exit");
    }

    // ---------- BST operations ----------

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = sc.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientRecords.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient result = patientRecords.search(id);
        if (result == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + result);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        patientRecords.delete(id);
    }

    private static void displayAllPatients() {
        System.out.println("Patients in ascending order of Patient ID:");
        patientRecords.inorderTraversal();
    }

    // ---------- Queue operations ----------

    private static void addToQueue() {
        int id = readInt("Enter Patient ID to add to emergency queue: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No such patient registered. Register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void displayQueue() {
        System.out.println("Patients currently waiting:");
        emergencyQueue.displayQueue();
    }

    // ---------- Stack operations (linked to Queue) ----------

    private static void dequeueAndTreat() {
        Patient treated = emergencyQueue.dequeue();
        if (treated == null) return;

        System.out.print("Enter treatment details for " + treated.getName() + ": ");
        String details = sc.nextLine();
        System.out.print("Enter completion date (e.g. 2026-09-05): ");
        String date = sc.nextLine();

        TreatmentRecord record = new TreatmentRecord(treated.getPatientId(), treated.getName(), details, date);
        treatmentHistory.push(record);
    }

    private static void displayTreatmentHistory() {
        System.out.println("Treatment history (most recent first):");
        treatmentHistory.displayStack();
    }

    private static void popLastTreatment() {
        treatmentHistory.pop();
    }

    // ---------- Singly Linked List operations (per patient visit history) ----------

    private static void addVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No such patient registered.");
            return;
        }
        int visitId = readInt("Enter Visit ID: ");
        System.out.print("Enter Visit Date: ");
        String date = sc.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = sc.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = sc.nextLine();

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added to " + patient.getName() + "'s history.");
    }

    private static void removeVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No such patient registered.");
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "Visit ID not found for this patient.");
    }

    private static void searchVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No such patient registered.");
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit == null ? "Visit not found." : "Found: " + visit);
    }

    private static void displayVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No such patient registered.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    // ---------- Input helper ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return value;
    }
}
