public class PatientBST {

    // Inner node class - each node holds one patient and links to left/right children
    private class BSTNode {
        Patient patient;
        BSTNode left, right;
        BSTNode(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;

    public PatientBST() {
        this.root = null;
    }

    // Public insert - starts the recursion from the root
    public void insert(Patient patient) {
        root = insert(root, patient);
    }

    // Private recursive helper that walks down the tree to place the new node
    private BSTNode insert(BSTNode current, Patient patient) {
        if (current == null) return new BSTNode(patient);

        if (patient.getPatientId() < current.patient.getPatientId()) {
            current.left = insert(current.left, patient);
        } else if (patient.getPatientId() > current.patient.getPatientId()) {
            current.right = insert(current.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return current;
    }

    // Public search - returns the Patient object, or null if not found
    public Patient search(int patientId) {
        BSTNode result = search(root, patientId);
        return result == null ? null : result.patient;
    }

    private BSTNode search(BSTNode current, int patientId) {
        if (current == null) return null;
        if (patientId == current.patient.getPatientId()) return current;
        if (patientId < current.patient.getPatientId()) return search(current.left, patientId);
        return search(current.right, patientId);
    }

    // Public delete - starts the recursion from the root
    public void delete(int patientId) {
        if (search(patientId) == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing deleted.");
            return;
        }
        root = delete(root, patientId);
        System.out.println("Patient ID " + patientId + " deleted.");
    }

    // Private recursive helper - handles the 3 deletion cases (leaf, one child, two children)
    private BSTNode delete(BSTNode current, int patientId) {
        if (current == null) return null;

        if (patientId < current.patient.getPatientId()) {
            current.left = delete(current.left, patientId);
        } else if (patientId > current.patient.getPatientId()) {
            current.right = delete(current.right, patientId);
        } else {
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            // Two children: replace with the in-order successor (smallest in right subtree)
            BSTNode successor = findMin(current.right);
            current.patient = successor.patient;
            current.right = delete(current.right, successor.patient.getPatientId());
        }
        return current;
    }

    // Helper used by delete() to find the smallest node in a subtree
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Public in-order traversal - prints patients in ascending Patient ID order
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("  No patient records found.");
            return;
        }
        inorderTraversal(root);
    }

    private void inorderTraversal(BSTNode current) {
        if (current == null) return;
        inorderTraversal(current.left);
        System.out.println("  " + current.patient);
        inorderTraversal(current.right);
    }
}
