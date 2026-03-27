package hospitalmanagementsystem.patterns.structural.decorator.report;

/**
 * DC1 — Watermark Decorator
 * 
 * Adds a confidential watermark to any report.
 * Can be used for marking sensitive documents.
 * 
 * Structural Pattern: Decorator
 * Owner: Adham
 * Deliverable: #2
 */
public class WatermarkDecorator extends ReportDecorator {
    
    private final String watermarkText;
    
    /**
     * Constructs a watermark decorator with default "CONFIDENTIAL" text.
     * 
     * @param report the report to decorate
     */
    public WatermarkDecorator(ReportComponent report) {
        this(report, "CONFIDENTIAL");
    }
    
    /**
     * Constructs a watermark decorator with custom text.
     * 
     * @param report the report to decorate
     * @param watermarkText the watermark text to display
     */
    public WatermarkDecorator(ReportComponent report, String watermarkText) {
        super(report);
        this.watermarkText = watermarkText;
    }
    
    @Override
    public String generate() {
        String original = decoratedReport.generate();
        String[] lines = original.split("\n");
        StringBuilder sb = new StringBuilder();
        
        // Add watermark at the top
        sb.append("*** ").append(watermarkText).append(" ***\n\n");
        
        // Insert watermark every few lines
        int lineCount = 0;
        for (String line : lines) {
            sb.append(line).append("\n");
            lineCount++;
            if (lineCount % 5 == 0 && lineCount < lines.length - 2) {
                sb.append("  [ ").append(watermarkText).append(" ]\n");
            }
        }
        
        // Add watermark at the bottom
        sb.append("\n*** ").append(watermarkText).append(" ***\n");
        
        return sb.toString();
    }
}
