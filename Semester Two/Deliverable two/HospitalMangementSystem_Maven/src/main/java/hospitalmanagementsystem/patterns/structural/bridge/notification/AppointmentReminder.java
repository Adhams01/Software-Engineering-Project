package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * AppointmentReminder — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * Refined Abstraction: A specific type of notification for appointment reminders.
 * Can be sent via any channel (Email, SMS, or In-App) thanks to the Bridge pattern.
 * 
 * Deliverable #2: Shows how new notification types can be added without
 * modifying existing channel implementations.
 */
public class AppointmentReminder extends Notification {
    
    /**
     * Constructor - takes any notification channel
     * 
     * @param channel The channel to send through (Email, SMS, or In-App)
     */
    public AppointmentReminder(NotificationChannel channel) {
        super(channel);
    }
    
    @Override
    public String getNotificationType() {
        return "Appointment Reminder";
    }
}
