package hospitalmanagementsystem.patterns.behavioral.observer.os1;

/**
 * Demonstration class for the Observer and Singleton patterns (OS1).
 * Author: Maged
 */
public class ObserverSingletonDemo {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("HOSPITAL MANAGEMENT SYSTEM - Observer & Singleton Pattern Demo");
        System.out.println("============================================================");
        System.out.println();

        // Step 1: Obtain the Singleton EventBus instance
        System.out.println("Step 1: Initializing Singleton EventBus");
        HospitalEventBus eventBus = HospitalEventBus.getInstance();
        System.out.println("EventBus singleton obtained: " + eventBus.hashCode());
        System.out.println();

        // Step 2: Create and register observers
        System.out.println("Step 2: Creating and Registering Observers");
        System.out.println("------------------------------------------------------------");

        Doctor cardiologist = new Doctor("DOC001", "Sarah Johnson", "CARDIOLOGY", "Cardiologist");
        Doctor neurologist = new Doctor("DOC002", "Michael Chen", "NEUROLOGY", "Neurologist");
        Nurse nurseDay = new Nurse("NUR001", "Emily Davis", "CARDIOLOGY", "DAY");
        Nurse nurseNight = new Nurse("NUR002", "James Wilson", "NEUROLOGY", "NIGHT");
        LabTechnician labTech1 = new LabTechnician("LAB001", "Lisa Brown", "HEMATOLOGY");
        LabTechnician labTech2 = new LabTechnician("LAB002", "Robert Taylor", "MICROBIOLOGY");
        AdminStaff reception = new AdminStaff("ADM001", "Amanda White", "RECEPTION");
        AdminStaff billing = new AdminStaff("ADM002", "David Miller", "BILLING");

        eventBus.attachListener(cardiologist);
        eventBus.attachListener(neurologist);
        eventBus.attachListener(nurseDay);
        eventBus.attachListener(nurseNight);
        eventBus.attachListener(labTech1);
        eventBus.attachListener(labTech2);
        eventBus.attachListener(reception);
        eventBus.attachListener(billing);

        System.out.println("Total registered listeners: " + eventBus.getListenerCount());
        System.out.println();

        // Step 3: Demonstrate event publication
        System.out.println("Step 3: Publishing Hospital Events");
        System.out.println("------------------------------------------------------------");

        System.out.println("\nEvent 1: Patient Status Change (Cardiology)");
        eventBus.publishEvent(new HospitalEvent(
                EventType.PATIENT_STATUS_CHANGE,
                "Patient John Doe's condition changed from stable to critical",
                "CARDIOLOGY", "PatientMonitoringSystem"));

        System.out.println("\nEvent 2: Lab Results Ready");
        eventBus.publishEvent(new HospitalEvent(
                EventType.LAB_RESULTS_READY,
                "CBC and metabolic panel results ready for patient Jane Smith",
                "LAB", "LaboratorySystem"));

        System.out.println("\nEvent 3: Bed Freed (Neurology)");
        eventBus.publishEvent(new HospitalEvent(
                EventType.BED_FREED,
                "Bed 302 in Neurology ward is now available",
                "NEUROLOGY", "BedManagementSystem"));

        System.out.println("\nEvent 4: Emergency Alert (Hospital-wide)");
        eventBus.publishEvent(new HospitalEvent(
                EventType.EMERGENCY_ALERT,
                "Code Blue in ICU - Immediate medical response required",
                "ICU", "EmergencySystem"));

        // Step 4: Demonstrate listener detachment
        System.out.println("\n============================================================");
        System.out.println("Step 4: Demonstrating Listener Detachment");
        System.out.println("------------------------------------------------------------");

        System.out.println("\nDetaching billing admin staff...");
        boolean detached = eventBus.detachListener("ADMIN-ADM002");
        System.out.println("Detachment " + (detached ? "successful" : "failed"));
        System.out.println("Remaining listeners: " + eventBus.getListenerCount());

        System.out.println("\nPublishing another event (should not notify billing):");
        eventBus.publishEvent(new HospitalEvent(
                EventType.PATIENT_DISCHARGED,
                "Patient John Doe discharged from Cardiology",
                "CARDIOLOGY", "DischargeSystem"));

        // Step 5: Verify Singleton behavior
        System.out.println("\n============================================================");
        System.out.println("Step 5: Verifying Singleton Behavior");
        System.out.println("------------------------------------------------------------");

        HospitalEventBus anotherInstance = HospitalEventBus.getInstance();
        System.out.println("First instance hash:  " + eventBus.hashCode());
        System.out.println("Second instance hash: " + anotherInstance.hashCode());
        System.out.println("Same instance? " + (eventBus == anotherInstance));

        // Final summary
        System.out.println("\n============================================================");
        System.out.println("DEMONSTRATION COMPLETE");
        System.out.println("============================================================");
        System.out.println("Singleton Pattern: Thread-safe EventBus using Bill Pugh initialization");
        System.out.println("Observer Pattern: Decoupled event notification with filtering");
        System.out.println("Memory Management: WeakReference for automatic cleanup");
        System.out.println();
    }
}
