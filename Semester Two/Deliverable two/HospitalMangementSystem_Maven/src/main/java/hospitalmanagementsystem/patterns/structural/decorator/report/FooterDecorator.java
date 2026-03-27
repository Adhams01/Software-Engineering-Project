package hospitalmanagementsystem.patterns.structural.decorator.report;

/**
 * DC1 — Footer Decorator
 * 
 * Adds a footer to any report with page numbers,
 * confidentiality notice, and contact information.
 * 
 * Structural Pattern: Decorator
 * Owner: Adham
 * Deliverable: #2
 */
public class FooterDecorator extends ReportDecorator {
    
    private final int pageNumber;
    private final int totalPages;
    
    /**
     * Constructs a footer decorator for the given report.
     * 
     * @param report the report to decorate
     * @param pageNumber current page number
     * @param totalPages total number of pages
     */
    public FooterDecorator(ReportComponent report, int pageNumber, int totalPages) {
        super(report);
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
    }
    
    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(decoratedReport.generate());
        sb.append("\n");
        sb.append("--------------------------------------------\n");
        sb.append("CONFIDENTIAL - For authorized personnel only\n");
        sb.append("Questions? Contact: admin@hospital.com\n");
        sb.append("Page ").append(pageNumber).append(" of ").append(totalPages).append("\n");
        sb.append("============================================\n");
        return sb.toString();
    }
}
