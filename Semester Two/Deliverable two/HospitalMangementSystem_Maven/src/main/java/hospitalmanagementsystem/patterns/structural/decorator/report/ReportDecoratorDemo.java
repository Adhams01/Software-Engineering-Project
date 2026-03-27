package hospitalmanagementsystem.patterns.structural.decorator.report;

/**
 * DC1 — Report Decorator Demo
 * 
 * Demonstrates the Decorator pattern by creating reports with
 * various combinations of decorators (header, footer, watermark).
 * 
 * Shows how decorators can be stacked and combined dynamically.
 * 
 * Structural Pattern: Decorator
 * Owner: Adham
 * Deliverable: #2
 */
public class ReportDecoratorDemo {
    
    /**
     * Runs the decorator pattern demonstration.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("DC1 - DECORATOR PATTERN DEMO");
        System.out.println("Report Enhancement System");
        System.out.println("Owner: Adham");
        System.out.println("============================================\n");
        
        // Create base reports
        ReportComponent patientReport = new BaseReport(
            "Patient Summary",
            "Monthly patient activity summary",
            "Patient: John Doe\nVisits: 3\nLast Visit: 2026-03-15\nStatus: Active"
        );
        
        ReportComponent billingReport = new BaseReport(
            "Billing Statement",
            "Monthly billing summary",
            "Account: #12345\nTotal Charges: $1,250.00\nPayments: $800.00\nBalance: $450.00"
        );
        
        // Demo 1: Simple report (no decoration)
        System.out.println("--- DEMO 1: Base Report (No Decoration) ---");
        System.out.println(patientReport.generate());
        System.out.println();
        
        // Demo 2: Report with header only
        System.out.println("--- DEMO 2: Report with Header ---");
        ReportComponent withHeader = new HeaderDecorator(patientReport, "City Hospital");
        System.out.println(withHeader.generate());
        System.out.println();
        
        // Demo 3: Report with header and footer
        System.out.println("--- DEMO 3: Report with Header + Footer ---");
        ReportComponent withHeaderFooter = new FooterDecorator(
            new HeaderDecorator(billingReport, "Metro Medical Center"),
            1, 1
        );
        System.out.println(withHeaderFooter.generate());
        System.out.println();
        
        // Demo 4: Fully decorated (header + watermark + footer)
        System.out.println("--- DEMO 4: Fully Decorated (Header + Watermark + Footer) ---");
        ReportComponent fullyDecorated = new FooterDecorator(
            new WatermarkDecorator(
                new HeaderDecorator(patientReport, "General Hospital"),
                "CONFIDENTIAL - MEDICAL RECORD"
            ),
            1, 2
        );
        System.out.println(fullyDecorated.generate());
        System.out.println();
        
        // Demo 5: Multiple decorators in different order
        System.out.println("--- DEMO 5: Different Decoration Order ---");
        ReportComponent watermarkFirst = new HeaderDecorator(
            new WatermarkDecorator(billingReport, "DRAFT"),
            "Draft Hospital"
        );
        System.out.println(watermarkFirst.generate());
        System.out.println();
        
        // Summary
        System.out.println("============================================");
        System.out.println("DECORATOR PATTERN BENEFITS DEMONSTRATED:");
        System.out.println("============================================");
        System.out.println("1. Base report unchanged - no subclassing needed");
        System.out.println("2. Decorators can be combined in any order");
        System.out.println("3. New features added without modifying existing code");
        System.out.println("4. Single report can have multiple enhancements");
        System.out.println("5. Runtime decoration - choose features dynamically");
        System.out.println("============================================");
    }
}
