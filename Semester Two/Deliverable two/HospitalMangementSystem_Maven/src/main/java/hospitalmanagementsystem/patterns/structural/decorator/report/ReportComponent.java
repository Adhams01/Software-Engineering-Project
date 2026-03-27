package hospitalmanagementsystem.patterns.structural.decorator.report;

/**
 * DC1 — Component Interface for Report Decorator Pattern
 * 
 * Defines the interface for all report components (both concrete
 * reports and decorators). This allows decorators to be used
 * interchangeably with base reports.
 * 
 * Structural Pattern: Decorator
 * Owner: Adham
 * Deliverable: #2
 */
public interface ReportComponent {
    
    /**
     * Generates the report content.
     * 
     * @return formatted report string
     */
    String generate();
    
    /**
     * Gets the report title.
     * 
     * @return report title
     */
    String getTitle();
    
    /**
     * Gets the report description.
     * 
     * @return report description
     */
    String getDescription();
}
