package hospitalmanagementsystem.patterns.behavioral.mediator.md2;

/**
 * EmergencyMediatorDemo — Demonstrates Mediator pattern MD2
 * Shows how emergency response events trigger coordinated actions
 * across Triage, Medical, Facilities, and Communication components
 * without any component knowing about the others.
 * Author: Yassin
 */
public class EmergencyMediatorDemo {

    public static void main(String[] args) {

        System.out.println("=== Mediator Pattern MD2: Emergency Response Coordination Demo ===");
        System.out.println("Author: Yassin");
        System.out.println();

        // Step 1: Create the ConcreteMediator
        EmergencyCoordinator coordinator = new EmergencyCoordinator();

        // Step 2: Create all Concrete Colleagues, each registered with the mediator
        TriageComponent triage = new TriageComponent(coordinator);
        MedicalComponent medical = new MedicalComponent(coordinator);
        FacilitiesComponent facilities = new FacilitiesComponent(coordinator);
        CommunicationComponent communication = new CommunicationComponent(coordinator);

        // Step 3: Register all consumer colleagues with the mediator
        coordinator.setTriageComponent(triage);
        coordinator.setMedicalComponent(medical);
        coordinator.setFacilitiesComponent(facilities);
        coordinator.setCommunicationComponent(communication);

        System.out.println("Setup: Mediator + 4 colleagues wired. No component knows any other directly.");
        System.out.println();

        // --- Demo 1: Emergency detected ---
        System.out.println("--- Action: Emergency detected for incident E001 ---");
        triage.assessEmergency("E001", "CRITICAL");
        System.out.println();

        // --- Demo 2: Severity update ---
        System.out.println("--- Action: Severity update for incident E001 ---");
        triage.triggerSeverityUpdate("E001");
        System.out.println();

        // --- Demo 3: Emergency resolved ---
        System.out.println("--- Action: Emergency resolved for incident E001 ---");
        triage.resolveEmergency("E001");
        System.out.println();

        // --- Demo 4: Multiple simultaneous emergencies ---
        System.out.println("--- Action: Multiple emergencies detected ---");
        triage.assessEmergency("E002", "MODERATE");
        System.out.println();
        triage.assessEmergency("E003", "HIGH");
        System.out.println();

        // --- Summary ---
        System.out.println("=== Mediator Demo Summary ===");
        System.out.println("- TriageComponent triggered 5 events.");
        System.out.println("- Each event was routed through EmergencyCoordinator.");
        System.out.println("- MedicalComponent, FacilitiesComponent, CommunicationComponent");
        System.out.println("  each reacted without knowing about each other.");
        System.out.println("- 4 colleagues + 1 mediator = 4 dependencies (not 12).");
        System.out.println("- Adding a new component = register with mediator only.");
        System.out.println("- Emergency response coordination achieved without direct coupling.");
        System.out.println("=== Demo Complete ===");
    }
}
