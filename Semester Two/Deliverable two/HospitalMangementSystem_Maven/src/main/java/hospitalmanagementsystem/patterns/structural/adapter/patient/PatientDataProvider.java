package hospitalmanagementsystem.patterns.structural.adapter.patient;
public interface PatientDataProvider {

    /**
     * Retrieves patient information by their unique ID (national ID in HMS).
     *
     * @param patientId the patient's national ID (String)
     * @return formatted patient data string, or null if not found
     */
    String getPatientById(String patientId);

    /**
     * Retrieves the medical history for a given patient.
     *
     * @param patientId the patient's national ID (String)
     * @return formatted history string, or empty string if none
     */
    String getPatientHistory(String patientId);

    /**
     * Checks whether a patient record is currently active.
     *
     * @param patientId the patient's national ID (String)
     * @return true if the patient is active, false otherwise
     */
    boolean isPatientActive(String patientId);
}
