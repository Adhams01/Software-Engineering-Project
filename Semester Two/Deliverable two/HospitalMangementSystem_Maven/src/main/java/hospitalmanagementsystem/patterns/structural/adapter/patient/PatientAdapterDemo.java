package hospitalmanagementsystem.patterns.structural.adapter.patient;
public class PatientAdapterDemo {

    public static void main(String[] args) {

        System.out.println("=== Adapter Pattern: External Patient System Demo ===");
        System.out.println("Author: [ANDREW]");
        System.out.println();

        // Step 1: Create the external legacy system (Adaptee)
        System.out.println("Step 1: External legacy system created (uses int IDs + XML)");
        ExternalPatientSystemApi externalSystem = new ExternalPatientSystemApi();
        System.out.println();

        // Step 2: Wrap it with the Adapter so HMS can use it via PatientDataProvider
        System.out.println("Step 2: Adapter wraps external system to match PatientDataProvider interface");
        PatientDataProvider provider = new ExternalPatientSystemAdapter(externalSystem);
        System.out.println();

        // Step 3: HMS code uses PatientDataProvider — unaware of external system details
        System.out.println("Step 3: HMS queries patients through the adapted interface");
        System.out.println("        (String IDs, plain-text responses, boolean status)");
        System.out.println();

        // --- Query patient 123 ---
        System.out.println("--- Query Patient ID: \"123\" ---");
        String patient123 = provider.getPatientById("123");
        System.out.println("Result: " + patient123);
        System.out.println("Active: " + provider.isPatientActive("123"));
        System.out.println("History:");
        System.out.println(provider.getPatientHistory("123"));

        // --- Query patient 456 ---
        System.out.println("--- Query Patient ID: \"456\" ---");
        String patient456 = provider.getPatientById("456");
        System.out.println("Result: " + patient456);
        System.out.println("Active: " + provider.isPatientActive("456"));
        System.out.println("History:");
        System.out.println(provider.getPatientHistory("456"));

        // --- Query patient 999 (inactive) ---
        System.out.println("--- Query Patient ID: \"999\" (inactive/discharged) ---");
        String patient999 = provider.getPatientById("999");
        System.out.println("Result: " + patient999);
        System.out.println("Active: " + provider.isPatientActive("999"));
        System.out.println();

        // --- Query non-existent patient ---
        System.out.println("--- Query Patient ID: \"777\" (not found) ---");
        String patient777 = provider.getPatientById("777");
        System.out.println("Result: " + patient777);
        System.out.println("Active: " + provider.isPatientActive("777"));
        System.out.println();

        // --- Query with invalid ID ---
        System.out.println("--- Query Patient ID: \"abc\" (invalid format) ---");
        String patientAbc = provider.getPatientById("abc");
        System.out.println("Result: " + patientAbc);
        System.out.println("Active: " + provider.isPatientActive("abc"));
        System.out.println();

        // --- Summary ---
        System.out.println("=== Adapter Demo Summary ===");
        System.out.println("Adaptations performed:");
        System.out.println("  1. String patient IDs  -> int record numbers");
        System.out.println("  2. XML data responses  -> plain-text format");
        System.out.println("  3. Numeric status codes -> boolean active flag");
        System.out.println("HMS code never touches ExternalPatientSystemApi directly.");
        System.out.println("If the external system changes, only the Adapter is modified.");
        System.out.println("=== Demo Complete ===");
    }
}
