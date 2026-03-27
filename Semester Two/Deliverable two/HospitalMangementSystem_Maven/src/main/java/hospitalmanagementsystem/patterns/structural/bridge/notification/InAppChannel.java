package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * InAppChannel — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * Concrete Implementor: Sends notifications via In-App notification center.
 * Implements NotificationChannel interface for in-application delivery.
 * 
 * Deliverable #2: Bridge Pattern implementation showing dashboard
 * notifications as an alternative channel for all notification types.
 */
public class InAppChannel implements NotificationChannel {
    
    @Override
    public void sendNotification(String message, String notificationType) {
        System.out.println("[IN-APP] Sending " + notificationType + ": " + message);
        System.out.println("         To: Patient Dashboard");
        System.out.println("         Notification Center: New alert");
        System.out.println("         Content: " + message);
        System.out.println("         Status: ✓ Displayed in App");
        System.out.println();
    }
}
