package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * Notification — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * Abstraction: Defines the abstraction side of the Bridge pattern.
 * Uses a NotificationChannel (the implementor) to send messages.
 * 
 * Key Bridge Relationship:
 * - Notification holds a reference to NotificationChannel (the "bridge")
 * - This decouples "what" (notification type) from "how" (delivery channel)
 * 
 * Deliverable #2 Case: Notification System
 * - Abstract class extended by specific notification types
 * - Delegates actual sending to the configured channel
 */
public abstract class Notification {
    
    // The bridge connection - reference to implementation
    protected NotificationChannel channel;
    
    /**
     * Constructor takes a channel (implementor)
     * This is the key bridge relationship
     * 
     * @param channel The notification channel to use for sending
     */
    public Notification(NotificationChannel channel) {
        this.channel = channel;
    }
    
    /**
     * Send a message through the configured channel
     * Delegates to the implementation side
     * 
     * @param message The message to send
     */
    public void send(String message) {
        channel.sendNotification(message, getNotificationType());
    }
    
    /**
     * Get the type of notification
     * Used to categorize messages in the channel
     * 
     * @return The notification type string
     */
    public abstract String getNotificationType();
}
