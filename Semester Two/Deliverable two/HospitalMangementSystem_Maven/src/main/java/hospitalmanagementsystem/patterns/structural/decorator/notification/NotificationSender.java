package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Component Interface for Notification Sender Decorator
 * 
 * Defines the interface for all notification senders (both base
 * senders and decorated ones). This allows decorators to be used
 * interchangeably with base senders.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public interface NotificationSender {
    
    /**
     * Send a notification message
     * @param message the notification message to send
     * @return true if sent successfully, false otherwise
     */
    boolean sendNotification(String message);
    
    /**
     * Get the sender type/name
     * @return sender type description
     */
    String getSenderType();
}
