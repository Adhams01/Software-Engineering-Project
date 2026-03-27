package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Logging Decorator
 * 
 * Adds logging functionality to notification senders.
 * Logs before and after sending notifications for audit trail.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public class LoggingSender extends SenderDecorator {
    
    public LoggingSender(NotificationSender sender) {
        super(sender);
    }
    
    @Override
    public boolean sendNotification(String message) {
        System.out.println("[LOG] " + java.time.LocalDateTime.now() + " - Preparing to send notification");
        System.out.println("[LOG] Message content: '" + message + "'");
        System.out.println("[LOG] Sender type: " + wrappedSender.getSenderType());
        
        boolean result = super.sendNotification(message);
        
        if (result) {
            System.out.println("[LOG] Notification sent successfully");
        } else {
            System.out.println("[LOG] Notification sending failed");
        }
        
        return result;
    }
    
    @Override
    public String getSenderType() {
        return "LoggingSender(" + wrappedSender.getSenderType() + ")";
    }
}
