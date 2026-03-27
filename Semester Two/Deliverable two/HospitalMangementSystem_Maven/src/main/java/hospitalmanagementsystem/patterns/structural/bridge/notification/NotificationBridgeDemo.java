package hospitalmanagementsystem.patterns.structural.bridge.notification;

/**
 * NotificationBridgeDemo — BRIDGE PATTERN BR1 (Deliverable #2)
 * Part of: Software Design & Development - Structural Design Patterns
 * Author: [ADHAM]
 * 
 * Demonstration: Shows how different notification types can use different 
 * channels interchangeably using the Bridge pattern.
 * 
 * This demo proves the Bridge Pattern benefits:
 * ✓ Abstraction (Notification types) and Implementation (Channels) are decoupled
 * ✓ New notification types can be added without changing channels
 * ✓ New channels can be added without changing notification types
 * ✓ Same notification can be sent via multiple channels
 * ✓ Same channel can handle multiple notification types
 * 
 * Deliverable #2: Structural Design Patterns - Bridge Pattern Case #1
 * Case: Notification System (Message Type × Delivery Channel)
 */
public class NotificationBridgeDemo {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("BRIDGE PATTERN DEMO: Notification System");
        System.out.println("========================================");
        System.out.println();
        
        // Create channels (implementations)
        System.out.println("1. Creating notification channels...");
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel inApp = new InAppChannel();
        System.out.println("   ✓ Email channel ready");
        System.out.println("   ✓ SMS channel ready");
        System.out.println("   ✓ In-App channel ready");
        System.out.println();
        
        // Create notifications (abstractions) paired with channels
        System.out.println("2. Creating notifications with different channels...");
        System.out.println();
        
        // Appointment reminders via different channels
        System.out.println("--- Appointment Reminder via EMAIL ---");
        Notification reminderEmail = new AppointmentReminder(email);
        reminderEmail.send("Your appointment with Dr. Smith is tomorrow at 2:00 PM");
        
        System.out.println("--- Appointment Reminder via SMS ---");
        Notification reminderSms = new AppointmentReminder(sms);
        reminderSms.send("Your appointment with Dr. Smith is tomorrow at 2:00 PM");
        
        System.out.println("--- Appointment Reminder via IN-APP ---");
        Notification reminderInApp = new AppointmentReminder(inApp);
        reminderInApp.send("Your appointment with Dr. Smith is tomorrow at 2:00 PM");
        
        // Billing alerts via different channels
        System.out.println("--- Billing Alert via EMAIL ---");
        Notification alertEmail = new BillingAlert(email);
        alertEmail.send("Payment of $150.00 is due for your recent consultation");
        
        System.out.println("--- Billing Alert via SMS ---");
        Notification alertSms = new BillingAlert(sms);
        alertSms.send("Payment of $150.00 is due for your recent consultation");
        
        System.out.println("========================================");
        System.out.println("BRIDGE PATTERN BENEFIT DEMONSTRATED:");
        System.out.println("========================================");
        System.out.println("✓ Same notification type (AppointmentReminder)");
        System.out.println("  can use ANY channel (Email, SMS, In-App)");
        System.out.println();
        System.out.println("✓ Same channel (Email) can be used by");
        System.out.println("  ANY notification type (Reminder, Alert)");
        System.out.println();
        System.out.println("✓ Adding a new notification type requires");
        System.out.println("  NO changes to existing channels");
        System.out.println();
        System.out.println("✓ Adding a new channel requires");
        System.out.println("  NO changes to existing notifications");
        System.out.println("========================================");
    }
}
