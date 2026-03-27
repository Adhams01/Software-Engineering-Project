package hospitalmanagementsystem.patterns.structural.decorator.report;

/**
 * DC1 — Abstract Report Decorator
 * 
 * Base class for all report decorators. Maintains a reference to
 * the decorated ReportComponent and delegates operations to it.
 * Decorators can add behavior before or after delegating.
 * 
 * Structural Pattern: Decorator
 * Owner: Adham
 * Deliverable: #2
 */
public abstract class ReportDecorator implements ReportComponent {
    
    protected ReportComponent decoratedReport;
    
    /**
     * Constructs a decorator wrapping the given report component.
     * 
     * @param report the report to decorate
     */
    public ReportDecorator(ReportComponent report) {
        this.decoratedReport = report;
    }
    
    @Override
    public String generate() {
        return decoratedReport.generate();
    }
    
    @Override
    public String getTitle() {
        return decoratedReport.getTitle();
    }
    
    @Override
    public String getDescription() {
        return decoratedReport.getDescription();
    }
}
