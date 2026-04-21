package hospitalmanagementsystem.patterns.behavioral.templatemethod.tm1;

import java.time.LocalDate;

public class DischargeContext {

    private final String patientId;
    private final String patientName;
    private final boolean emergencyCase;
    private final double outstandingBalance;
    private final LocalDate admissionDate;
    private final LocalDate dischargeDate;

    public DischargeContext(
            String patientId,
            String patientName,
            boolean emergencyCase,
            double outstandingBalance,
            LocalDate admissionDate,
            LocalDate dischargeDate
    ) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.emergencyCase = emergencyCase;
        this.outstandingBalance = outstandingBalance;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public boolean isEmergencyCase() {
        return emergencyCase;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }
}
