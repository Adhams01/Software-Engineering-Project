package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Base Notification Sender (Concrete Component)
 * 
 * The basic notification sender implementation that can be decorated
 * with additional behaviors like logging, retry, and throttling.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public class BaseNotificationSender implements NotificationSender {
    
    private final String senderName;
    
    public BaseNotificationSender(String senderName) {
        this.senderName = senderName;
    }
    
    @Override
    public boolean sendNotification(String message) {
        // Simulate sending notification
        System.out.println("[" + senderName + "] Sending: " + message);
        
        // Simulate processing delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // Simulate 95% success rate
        return Math.random() > 0.05;
    }
    
    @Override
    public String getSenderType() {
        return "BaseNotificationSender(" + senderName + ")";
    }
    
    public String getSenderName() {
        return senderName;
    }
}
