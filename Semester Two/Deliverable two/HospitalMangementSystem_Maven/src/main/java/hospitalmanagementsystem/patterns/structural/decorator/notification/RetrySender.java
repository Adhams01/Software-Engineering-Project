package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Retry Decorator
 * 
 * Adds retry functionality to notification senders.
 * Automatically retries failed notifications up to a maximum number
 * of attempts with a configurable delay between retries.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public class RetrySender extends SenderDecorator {
    
    private int maxRetries;
    private long retryDelayMs;
    
    public RetrySender(NotificationSender sender, int maxRetries, long retryDelayMs) {
        super(sender);
        this.maxRetries = maxRetries;
        this.retryDelayMs = retryDelayMs;
    }
    
    @Override
    public boolean sendNotification(String message) {
        System.out.println("[RETRY] Attempting to send with up to " + maxRetries + " retries");
        
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            System.out.println("[RETRY] Attempt " + attempt + " of " + maxRetries);
            
            boolean result = super.sendNotification(message);
            
            if (result) {
                System.out.println("[RETRY] Notification sent successfully on attempt " + attempt);
                return true;
            }
            
            if (attempt < maxRetries) {
                System.out.println("[RETRY] Attempt " + attempt + " failed, waiting " + retryDelayMs + "ms before retry");
                try {
                    Thread.sleep(retryDelayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("[RETRY] Retry interrupted");
                    return false;
                }
            }
        }
        
        System.out.println("[RETRY] All " + maxRetries + " attempts failed");
        return false;
    }
    
    @Override
    public String getSenderType() {
        return "RetrySender(maxRetries=" + maxRetries + ", " + wrappedSender.getSenderType() + ")";
    }
}
