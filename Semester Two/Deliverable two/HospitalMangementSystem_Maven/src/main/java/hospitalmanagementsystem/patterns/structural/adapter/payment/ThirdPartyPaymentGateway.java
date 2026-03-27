package hospitalmanagementsystem.patterns.structural.adapter.payment;

/**
 * AD2 — Third-Party Payment Gateway (Adaptee)
 * 
 * Simulates an external payment gateway with a different interface
 * than what the Hospital Management System expects. This class
 * represents the legacy/external system that needs to be adapted.
 * 
 * Structural Pattern: Adapter
 * Owner: Yassin
 * Deliverable: #2
 */
public class ThirdPartyPaymentGateway {
    
    private final String gatewayName;
    private final String apiKey;
    
    public ThirdPartyPaymentGateway(String gatewayName, String apiKey) {
        this.gatewayName = gatewayName;
        this.apiKey = apiKey;
    }
    
    /**
     * Make a payment using the external gateway's native format
     * 
     * @param cardToken tokenized card number
     * @param amount amount in cents
     * @param currency currency code (ISO 4217)
     * @param merchantId merchant identifier
     * @return transaction response string
     */
    public String makePayment(String cardToken, int amount, String currency, String merchantId) {
        // Simulate external API call
        System.out.println("[External Gateway: " + gatewayName + "] Processing payment...");
        System.out.println("  Card Token: " + maskToken(cardToken));
        System.out.println("  Amount: " + amount + " " + currency);
        
        // Simulate processing delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate success/failure (90% success rate for demo)
        if (Math.random() > 0.1) {
            String txId = "TXN-" + System.currentTimeMillis();
            return "SUCCESS|" + txId + "|Payment processed successfully";
        } else {
            return "FAILURE|DECLINED|Card was declined";
        }
    }
    
    /**
     * Refund a transaction using external gateway's format
     * 
     * @param originalTxId original transaction ID
     * @param refundAmount amount to refund in cents
     * @return refund response string
     */
    public String refundTransaction(String originalTxId, int refundAmount) {
        System.out.println("[External Gateway: " + gatewayName + "] Processing refund...");
        System.out.println("  Original TX: " + originalTxId);
        System.out.println("  Refund Amount: " + refundAmount);
        
        String refundId = "RFD-" + System.currentTimeMillis();
        return "SUCCESS|" + refundId + "|Refund processed successfully";
    }
    
    /**
     * Validate card token with external gateway
     * 
     * @param cardToken token to validate
     * @return validation result string
     */
    public String validateCard(String cardToken) {
        // Simple validation simulation
        if (cardToken != null && cardToken.length() >= 10) {
            return "VALID|Card token is valid";
        }
        return "INVALID|Invalid card token format";
    }
    
    private String maskToken(String token) {
        if (token == null || token.length() < 8) return "****";
        return token.substring(0, 4) + "..." + token.substring(token.length() - 4);
    }
    
    public String getGatewayName() {
        return gatewayName;
    }
}
