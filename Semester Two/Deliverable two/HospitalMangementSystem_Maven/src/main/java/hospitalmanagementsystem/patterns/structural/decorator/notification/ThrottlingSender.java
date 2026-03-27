package hospitalmanagementsystem.patterns.structural.decorator.notification;

/**
 * DC2 — Throttling Decorator
 * 
 * Adds rate limiting to notification senders to prevent flooding.
 * Enforces minimum interval between messages and maximum messages per minute.
 * 
 * Structural Pattern: Decorator
 * Owner: Yassin
 * Deliverable: #2
 */
public class ThrottlingSender extends SenderDecorator {
    
    private long minIntervalMs;
    private long lastSentTime = 0;
    private int maxMessagesPerMinute;
    private int messageCount = 0;
    private long windowStartTime = System.currentTimeMillis();
    
    public ThrottlingSender(NotificationSender sender, long minIntervalMs, int maxMessagesPerMinute) {
        super(sender);
        this.minIntervalMs = minIntervalMs;
        this.maxMessagesPerMinute = maxMessagesPerMinute;
    }
    
    @Override
    public boolean sendNotification(String message) {
        long currentTime = System.currentTimeMillis();
        
        // Check minimum interval between messages
        if (currentTime - lastSentTime < minIntervalMs) {
            long waitTime = minIntervalMs - (currentTime - lastSentTime);
            System.out.println("[THROTTLE] Rate limiting: waiting " + waitTime + "ms to maintain minimum interval");
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        
        // Check messages per minute limit
        if (currentTime - windowStartTime > 60000) { // Reset window after 1 minute
            windowStartTime = currentTime;
            messageCount = 0;
        }
        
        if (messageCount >= maxMessagesPerMinute) {
            long waitTime = 60000 - (currentTime - windowStartTime);
            System.out.println("[THROTTLE] Rate limit exceeded: " + messageCount + " messages sent in current minute");
            System.out.println("[THROTTLE] Waiting " + waitTime + "ms for next minute window");
            try {
                Thread.sleep(waitTime);
                windowStartTime = System.currentTimeMillis();
                messageCount = 0;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        
        System.out.println("[THROTTLE] Sending message " + (messageCount + 1) + " of " + maxMessagesPerMinute + " allowed per minute");
        
        boolean result = super.sendNotification(message);
        
        if (result) {
            lastSentTime = System.currentTimeMillis();
            messageCount++;
        }
        
        return result;
    }
    
    @Override
    public String getSenderType() {
        return "ThrottlingSender(interval=" + minIntervalMs + "ms, maxPerMin=" + maxMessagesPerMinute + ", " + wrappedSender.getSenderType() + ")";
    }
}
