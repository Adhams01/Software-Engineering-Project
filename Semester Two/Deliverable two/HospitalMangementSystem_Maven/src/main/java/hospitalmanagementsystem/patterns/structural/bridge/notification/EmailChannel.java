package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * EmailChannel — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * Concrete Implementor: Sends notifications via Email channel.
 * Implements NotificationChannel interface for email delivery.
 * 
 * Deliverable #2: Bridge Pattern implementation showing how different
 * delivery channels can be used interchangeably with any notification type.
 */
public class EmailChannel implements NotificationChannel {
    
    @Override
    public void sendNotification(String message, String notificationType) {
        System.out.println("[EMAIL] Sending " + notificationType + ": " + message);
        System.out.println("        To: patient@example.com");
        System.out.println("        Subject: " + notificationType + " Notification");
        System.out.println("        Body: " + message);
        System.out.println("        Status: ✓ Sent via Email");
        System.out.println();
    }
}
