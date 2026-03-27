package hospitalmanagementsystem.patterns.structural.decorator.report;

/**
 * DC1 — Header Decorator
 * 
 * Adds a hospital header to any report. Can include
 * hospital name, logo placeholder, and date.
 * 
 * Structural Pattern: Decorator
 * Owner: Adham
 * Deliverable: #2
 */
public class HeaderDecorator extends ReportDecorator {
    
    private final String hospitalName;
    
    /**
     * Constructs a header decorator for the given report.
     * 
     * @param report the report to decorate
     * @param hospitalName the hospital name to display in header
     */
    public HeaderDecorator(ReportComponent report, String hospitalName) {
        super(report);
        this.hospitalName = hospitalName;
    }
    
    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append("============================================\n");
        sb.append("  ").append(hospitalName.toUpperCase()).append("\n");
        sb.append("  Hospital Management System\n");
        sb.append("  Generated: ").append(java.time.LocalDateTime.now()).append("\n");
        sb.append("============================================\n\n");
        sb.append(decoratedReport.generate());
        return sb.toString();
    }
    
    @Override
    public String getTitle() {
        return decoratedReport.getTitle();
    }
}
