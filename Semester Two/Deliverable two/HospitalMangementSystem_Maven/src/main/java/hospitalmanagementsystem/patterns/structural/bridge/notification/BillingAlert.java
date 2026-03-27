package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * BillingAlert — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * Refined Abstraction: A specific type of notification for billing/payment alerts.
 * Can be sent via any channel (Email, SMS, or In-App) thanks to the Bridge pattern.
 * 
 * Deliverable #2: Demonstrates that different notification types (AppointmentReminder,
 * BillingAlert) can reuse the same set of channels interchangeably.
 */
public class BillingAlert extends Notification {
    
    /**
     * Constructor - takes any notification channel
     * 
     * @param channel The channel to send through (Email, SMS, or In-App)
     */
    public BillingAlert(NotificationChannel channel) {
        super(channel);
    }
    
    @Override
    public String getNotificationType() {
        return "Billing Alert";
    }
}
