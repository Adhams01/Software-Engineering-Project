package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * NotificationChannel interface — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * This interface defines the implementation side of the Bridge pattern.
 * It represents the "Implementor" in Bridge pattern terminology.
 * 
 * Deliverable #2 Case: Notification System (Message Type × Delivery Channel)
 * - Allows notification types and delivery channels to vary independently
 * - New channels can be added without changing notification classes
 * - New notification types can be added without changing channel classes
 * 
 * Deliverable #2: Bridge Pattern implementation showing how different 
 * notification types can be sent through various channels.
 */
public interface NotificationChannel {
    
    /**
     * Send a notification message through this channel
     * 
     * @param message The message content to send
     * @param notificationType Type of notification (e.g., "Reminder", "Alert")
     */
    void sendNotification(String message, String notificationType);
}
