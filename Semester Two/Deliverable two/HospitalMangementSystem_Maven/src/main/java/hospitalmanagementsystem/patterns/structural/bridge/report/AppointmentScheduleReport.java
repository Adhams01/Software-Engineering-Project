package hospitalmanagementsystem.patterns.structural.bridge.report;

public class AppointmentScheduleReport extends Report {

    // Sample appointment data (in production, loaded from AppointmentDAO)
    private final String[][] appointments;

    /**
     * Constructs an AppointmentScheduleReport with the given renderer
     * and sample appointment data.
     *
     * @param renderer the output format renderer
     */
    public AppointmentScheduleReport(ReportRenderer renderer) {
        super(renderer);
        // Seed with realistic HMS appointment data
        this.appointments = new String[][] {
                { "Appt ID", "Patient NID", "Date/Time", "Status" },
                { "A1", "123", "2025-12-30 10:00", "SCHEDULED" },
                { "A2", "123", "2026-01-02 12:30", "SCHEDULED" },
                { "B1", "999", "2025-12-28 09:00", "CANCELLED" },
                { "C1", "456", "2026-01-15 14:00", "SCHEDULED" }
        };
    }

    @Override
    public String getTitle() {
        return "AppointmentScheduleReport";
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(renderer.renderHeader(getTitle()));
        if (appointments == null || appointments.length == 0) {
            sb.append(renderer.renderRow(new String[] { "No appointments available" }));
        } else {
            for (String[] row : appointments) {
                sb.append(renderer.renderRow(row));
            }
        }
        sb.append(renderer.renderFooter());

        return sb.toString();
    }
}