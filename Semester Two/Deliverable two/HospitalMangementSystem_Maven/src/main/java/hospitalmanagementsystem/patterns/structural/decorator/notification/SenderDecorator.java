package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Abstract Sender Decorator
 * 
 * Base class for all notification sender decorators. Maintains a reference
 * to the wrapped NotificationSender and delegates operations to it.
 * Decorators can add behavior before or after delegating.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public abstract class SenderDecorator implements NotificationSender {
    
    protected NotificationSender wrappedSender;
    
    public SenderDecorator(NotificationSender sender) {
        this.wrappedSender = sender;
    }
    
    @Override
    public boolean sendNotification(String message) {
        return wrappedSender.sendNotification(message);
    }
    
    @Override
    public String getSenderType() {
        return wrappedSender.getSenderType();
    }
}
