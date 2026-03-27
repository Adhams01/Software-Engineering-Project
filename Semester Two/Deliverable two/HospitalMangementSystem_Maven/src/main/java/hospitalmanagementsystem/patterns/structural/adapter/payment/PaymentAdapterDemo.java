package hospitalmanagementsystem.patterns.structural.adapter.payment;

/**
 * AD2 — Payment Gateway Adapter Demo
 * 
 * Demonstrates the Adapter pattern by processing payments through
 * an external payment gateway using the HMS PaymentService interface.
 * 
 * Shows how the adapter converts between HMS format and external
 * gateway format transparently.
 * 
 * Structural Pattern: Adapter
 * Owner: Yassin
 * Deliverable: #2
 */
public class PaymentAdapterDemo {
    
    /**
     * Runs the payment gateway adapter demonstration.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("AD2 - ADAPTER PATTERN DEMO");
        System.out.println("Payment Gateway Integration");
        System.out.println("Owner: Yassin");
        System.out.println("============================================\n");
        
        // Create external gateway (adaptee)
        ThirdPartyPaymentGateway externalGateway = new ThirdPartyPaymentGateway(
            "SecurePay Gateway", "api-key-12345"
        );
        
        // Create adapter
        PaymentService paymentService = new PaymentGatewayAdapter(
            externalGateway, "HOSPITAL-MERCHANT-001"
        );
        
        // Demo 1: Valid payment
        System.out.println("--- DEMO 1: Processing Valid Payment ---");
        PaymentDetails validPayment = new PaymentDetails(
            "PAT-12345",
            150.00,
            "USD",
            "CREDIT_CARD",
            "4532123456789012",
            "12/26",
            "123",
            "123 Hospital St, Medical City"
        );
        
        System.out.println("Patient: " + validPayment.getPatientId());
        System.out.println("Amount: $" + validPayment.getAmount());
        System.out.println("Card: " + validPayment.getMaskedCardNumber());
        
        PaymentResult result = paymentService.processPayment(validPayment);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result.isSuccess() ? "✓ APPROVED" : "✗ DECLINED"));
        System.out.println();
        
        // Demo 2: Invalid card number
        System.out.println("--- DEMO 2: Invalid Card Validation ---");
        PaymentDetails invalidPayment = new PaymentDetails(
            "PAT-12345",
            150.00,
            "USD",
            "CREDIT_CARD",
            "1234",  // Invalid card
            "12/26",
            "123",
            "123 Hospital St"
        );
        
        boolean isValid = paymentService.validatePaymentDetails(invalidPayment);
        System.out.println("Card validation: " + (isValid ? "Valid" : "Invalid"));
        if (!isValid) {
            PaymentResult invalidResult = paymentService.processPayment(invalidPayment);
            System.out.println("Declined reason: " + invalidResult.getMessage());
        }
        System.out.println();
        
        // Demo 3: Process refund
        System.out.println("--- DEMO 3: Processing Refund ---");
        String originalTxId = "TXN-1234567890";
        double refundAmount = 150.00;
        
        RefundResult refundResult = paymentService.processRefund(originalTxId, refundAmount);
        System.out.println("Original Transaction: " + originalTxId);
        System.out.println("Refund Amount: $" + refundAmount);
        System.out.println("Refund Result: " + refundResult);
        System.out.println("Status: " + (refundResult.isSuccess() ? "✓ REFUNDED" : "✗ FAILED"));
        System.out.println();
        
        // Demo 4: Multiple payments
        System.out.println("--- DEMO 4: Batch Payment Processing ---");
        PaymentDetails[] batchPayments = {
            new PaymentDetails("PAT-001", 50.00, "USD", "CREDIT_CARD", 
                "4532123456789012", "12/26", "123", "Address 1"),
            new PaymentDetails("PAT-002", 75.50, "USD", "CREDIT_CARD",
                "5425234567890123", "11/27", "456", "Address 2"),
            new PaymentDetails("PAT-003", 200.00, "USD", "CREDIT_CARD",
                "4111111111111111", "10/25", "789", "Address 3")
        };
        
        int successCount = 0;
        double totalProcessed = 0;
        
        for (PaymentDetails payment : batchPayments) {
            PaymentResult batchResult = paymentService.processPayment(payment);
            System.out.println("Patient " + payment.getPatientId() + ": " + 
                (batchResult.isSuccess() ? "✓" : "✗") + " $" + payment.getAmount());
            if (batchResult.isSuccess()) {
                successCount++;
                totalProcessed += payment.getAmount();
            }
        }
        
        System.out.println("\nBatch Summary:");
        System.out.println("  Successful: " + successCount + "/" + batchPayments.length);
        System.out.println("  Total Processed: $" + totalProcessed);
        System.out.println();
        
        // Summary
        System.out.println("============================================");
        System.out.println("ADAPTER PATTERN BENEFITS DEMONSTRATED:");
        System.out.println("============================================");
        System.out.println("1. HMS uses standard PaymentService interface");
        System.out.println("2. External gateway details are hidden");
        System.out.println("3. Amount converted: $ → cents automatically");
        System.out.println("4. Card tokenized for external gateway");
        System.out.println("5. Response codes translated transparently");
        System.out.println("6. No changes needed to HMS or external gateway");
        System.out.println("============================================");
    }
}
