package hospitalmanagementsystem.patterns.behavioral.mediator.md2;

/**
 * CommunicationComponent — Concrete Colleague in Mediator pattern MD2
 * Represents the communication subsystem.
 * It responds to emergency events by notifying families, authorities,
 * and updating public displays.
 * Author: Yassin
 */
public class CommunicationComponent extends EmergencyComponent {

    /**
     * Constructs the communication component registered with the given mediator.
     *
     * @param mediator the shared mediator
     */
    public CommunicationComponent(EmergencyMediator mediator) {
        super(mediator);
    }

    /**
     * Notifies family members about an emergency incident.
     *
     * @param incidentId the ID of the emergency incident
     * @param message    the message to send to family
     */
    public void notifyFamily(String incidentId, String message) {
        System.out.println("[Communication] Notifying family for incident " + incidentId);
        System.out.println("  - Family contact information retrieved");
        System.out.println("  - Message: \"" + message + "\"");
        System.out.println("  - Notification sent via SMS and phone call");
        System.out.println("  ✓ Family notification completed");
    }

    /**
     * Alerts authorities about an emergency incident.
     *
     * @param incidentId the ID of the emergency incident
     * @param alertType  the type of alert to send
     */
    public void alertAuthorities(String incidentId, String alertType) {
        System.out.println("[Communication] Alerting authorities for incident " + incidentId);
        System.out.println("  - Alert type: " + alertType);
        System.out.println("  - Police/Fire/Ambulance notified as needed");
        System.out.println("  - Emergency services coordination initiated");
        System.out.println("  ✓ Authority alerts sent");
    }

    /**
     * Updates public display boards with emergency information.
     *
     * @param incidentId the ID of the emergency incident
     * @param info       the information to display
     */
    public void updateDisplay(String incidentId, String info) {
        System.out.println("[Communication] Updating public display for incident " + incidentId);
        System.out.println("  - Emergency room display updated");
        System.out.println("  - Information: \"" + info + "\"");
        System.out.println("  - Hospital entrance boards updated");
        System.out.println("  ✓ Public displays updated");
    }

    /**
     * Sends emergency broadcast messages.
     *
     * @param incidentId the ID of the emergency incident
     * @param broadcast  the broadcast message
     */
    public void sendBroadcast(String incidentId, String broadcast) {
        System.out.println("[Communication] Sending emergency broadcast for incident " + incidentId);
        System.out.println("  - Hospital PA system activated");
        System.out.println("  - Message: \"" + broadcast + "\"");
        System.out.println("  - Staff mobile devices notified");
        System.out.println("  ✓ Emergency broadcast completed");
    }
}
