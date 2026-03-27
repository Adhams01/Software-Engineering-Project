package hospitalmanagementsystem.patterns.structural.bridge.report;

public class BillingSummaryReport extends Report {

    // Sample billing data (in production, loaded from BillingDAO)
    private final String[][] billingRows;

    /**
     * Constructs a BillingSummaryReport with the given renderer
     * and sample billing data.
     *
     * @param renderer the output format renderer
     */
    public BillingSummaryReport(ReportRenderer renderer) {
        super(renderer);
        // Seed with realistic HMS billing data
        this.billingRows = new String[][] {
                { "Patient NID", "Service", "Amount", "Status" },
                { "123", "Consultation", "150.00", "PAID" },
                { "123", "Lab Work", "320.00", "PENDING" },
                { "999", "X-Ray", "200.00", "PAID" },
                { "456", "Surgery Prep", "1500.00", "PENDING" }
        };
    }

    @Override
    public String getTitle() {
        return "Billing Summary Report";
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(renderer.renderHeader(getTitle()));
        if (billingRows == null || billingRows.length == 0) {
            sb.append(renderer.renderRow(new String[] { "No billing records available" }));
        } else {
            for (String[] row : billingRows) {
                sb.append(renderer.renderRow(row));
            }
        }
        sb.append(renderer.renderFooter());
        return sb.toString();
    }
}