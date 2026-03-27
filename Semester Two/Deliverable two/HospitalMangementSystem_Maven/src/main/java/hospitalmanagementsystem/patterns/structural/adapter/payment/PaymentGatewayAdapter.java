package hospitalmanagementsystem.patterns.structural.adapter.payment;

/**
 * AD2 — Payment Gateway Adapter
 * 
 * Adapts the ThirdPartyPaymentGateway (external system) to the
 * PaymentService interface expected by the Hospital Management System.
 * 
 * Converts between HMS payment format and external gateway format:
 * - Converts amount from dollars to cents
 * - Converts card number to token
 * - Translates result codes
 * 
 * Structural Pattern: Adapter
 * Owner: Yassin
 * Deliverable: #2
 */
public class PaymentGatewayAdapter implements PaymentService {
    
    private final ThirdPartyPaymentGateway externalGateway;
    private final String merchantId;
    
    public PaymentGatewayAdapter(ThirdPartyPaymentGateway externalGateway, String merchantId) {
        this.externalGateway = externalGateway;
        this.merchantId = merchantId;
    }
    
    @Override
    public PaymentResult processPayment(PaymentDetails details) {
        // Validate first
        if (!validatePaymentDetails(details)) {
            return new PaymentResult(false, null, 
                "Invalid payment details", details.getAmount(), "DECLINED");
        }
        
        // Convert HMS format to external format
        String cardToken = tokenizeCard(details.getCardNumber());
        int amountInCents = (int) (details.getAmount() * 100);
        String currency = details.getCurrency();
        
        // Call external gateway
        String response = externalGateway.makePayment(cardToken, amountInCents, 
                                                     currency, merchantId);
        
        // Parse external response and convert to HMS format
        return parsePaymentResponse(response, details.getAmount());
    }
    
    @Override
    public RefundResult processRefund(String transactionId, double amount) {
        // Convert amount to cents
        int amountInCents = (int) (amount * 100);
        
        // Call external gateway
        String response = externalGateway.refundTransaction(transactionId, amountInCents);
        
        // Parse response
        return parseRefundResponse(response, transactionId, amount);
    }
    
    @Override
    public boolean validatePaymentDetails(PaymentDetails details) {
        if (details == null) return false;
        if (details.getAmount() <= 0) return false;
        if (details.getCardNumber() == null || details.getCardNumber().length() < 13) return false;
        if (details.getExpiryDate() == null || !details.getExpiryDate().matches("\\d{2}/\\d{2}")) return false;
        if (details.getCvv() == null || details.getCvv().length() < 3) return false;
        
        // Validate with external gateway
        String cardToken = tokenizeCard(details.getCardNumber());
        String validation = externalGateway.validateCard(cardToken);
        return validation.startsWith("VALID");
    }
    
    // Helper methods for conversion
    private String tokenizeCard(String cardNumber) {
        // Simple tokenization (in real system, use proper tokenization service)
        if (cardNumber == null) return null;
        return "TOK-" + Integer.toHexString(cardNumber.hashCode()) + "-" + System.currentTimeMillis();
    }
    
    private PaymentResult parsePaymentResponse(String response, double originalAmount) {
        String[] parts = response.split("\\|");
        boolean success = "SUCCESS".equals(parts[0]);
        String txId = parts.length > 1 ? parts[1] : null;
        String message = parts.length > 2 ? parts[2] : "Unknown response";
        
        return new PaymentResult(success, txId, message, originalAmount, 
                                success ? "APPROVED" : "DECLINED");
    }
    
    private RefundResult parseRefundResponse(String response, String originalTxId, double amount) {
        String[] parts = response.split("\\|");
        boolean success = "SUCCESS".equals(parts[0]);
        String refundId = parts.length > 1 ? parts[1] : null;
        String message = parts.length > 2 ? parts[2] : "Unknown response";
        
        return new RefundResult(success, refundId, originalTxId, amount, message);
    }
}
