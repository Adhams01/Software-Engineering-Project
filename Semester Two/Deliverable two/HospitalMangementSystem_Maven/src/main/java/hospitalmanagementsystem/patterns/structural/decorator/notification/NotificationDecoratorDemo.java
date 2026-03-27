package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Notification Decorator Demo
 * 
 * Demonstrates the Decorator pattern by creating notification senders with
 * various combinations of decorators (logging, retry, throttling).
 * 
 * Shows how decorators can be stacked and combined dynamically.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public class NotificationDecoratorDemo {
    
    /**
     * Runs the decorator pattern demonstration.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("DC2 - DECORATOR PATTERN DEMO");
        System.out.println("Notification Sender Decorators");
        System.out.println("Owner: Yassin");
        System.out.println("============================================\n");
        
        // Create the base sender
        NotificationSender baseSender = new BaseNotificationSender("Hospital System");
        
        // Demo 1: Base sender
        System.out.println("--- DEMO 1: Base Sender ---");
        System.out.println("Sender Type: " + baseSender.getSenderType());
        baseSender.sendNotification("Welcome to our hospital!");
        
        // Demo 2: With logging
        System.out.println("\n--- DEMO 2: With Logging Decorator ---");
        NotificationSender loggingSender = new LoggingSender(baseSender);
        System.out.println("Sender Type: " + loggingSender.getSenderType());
        loggingSender.sendNotification("Patient appointment reminder");
        
        // Demo 3: With retry
        System.out.println("\n--- DEMO 3: With Retry Decorator ---");
        NotificationSender retrySender = new RetrySender(baseSender, 3, 500);
        System.out.println("Sender Type: " + retrySender.getSenderType());
        retrySender.sendNotification("Emergency notification");
        
        // Demo 4: With throttling
        System.out.println("\n--- DEMO 4: With Throttling Decorator ---");
        NotificationSender throttlingSender = new ThrottlingSender(baseSender, 1000, 5);
        System.out.println("Sender Type: " + throttlingSender.getSenderType());
        for (int i = 1; i <= 3; i++) {
            System.out.println("\nSending message " + i + ":");
            throttlingSender.sendNotification("Message " + i + " - Lab results ready");
        }
        
        // Demo 5: Stacked decorators
        System.out.println("\n--- DEMO 5: Stacked Decorators (Throttling -> Retry -> Logging -> Base) ---");
        NotificationSender stackedSender = new ThrottlingSender(
            new RetrySender(
                new LoggingSender(baseSender), 
                2, 300
            ), 
            500, 3
        );
        System.out.println("Sender Type: " + stackedSender.getSenderType());
        stackedSender.sendNotification("Critical system update notification");
        
        // Demo 6: Multiple rapid messages with stacked decorators
        System.out.println("\n--- DEMO 6: Multiple Rapid Messages with Stacked Decorators ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("\nRapid message " + i + ":");
            stackedSender.sendNotification("Urgent: Doctor paging - Message " + i);
        }
        
        // Summary
        System.out.println("\n============================================");
        System.out.println("DECORATOR PATTERN BENEFITS DEMONSTRATED:");
        System.out.println("============================================");
        System.out.println("1. Base sender unchanged - no subclassing needed");
        System.out.println("2. Decorators can be combined in any order");
        System.out.println("3. Cross-cutting concerns (logging, retry, throttle) separated");
        System.out.println("4. Runtime decoration - choose features dynamically");
        System.out.println("5. Single sender can have multiple decorators");
        System.out.println("6. Each decorator has single responsibility");
        System.out.println("============================================");
    }
}
