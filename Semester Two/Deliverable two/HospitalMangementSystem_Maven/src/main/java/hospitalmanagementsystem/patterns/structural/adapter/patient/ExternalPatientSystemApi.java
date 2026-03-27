package hospitalmanagementsystem.patterns.structural.adapter.patient;

public class ExternalPatientSystemApi {

    /**
     * Fetches a patient record by numeric record number.
     * (External system uses int IDs, not String national IDs)
     *
     * @param recordNumber the external system's numeric record ID
     * @return XML-formatted patient data string
     */
    public String fetchPatientRecord(int recordNumber) {
        // Simulate external system response with XML-style data
        if (recordNumber == 123) {
            return "<patient><id>123</id><name>Ahmed Hassan</name>"
                 + "<dob>1990-05-15</dob><blood>A+</blood></patient>";
        } else if (recordNumber == 456) {
            return "<patient><id>456</id><name>Sara Ali</name>"
                 + "<dob>1985-11-22</dob><blood>O-</blood></patient>";
        } else if (recordNumber == 999) {
            return "<patient><id>999</id><name>Omar Khalid</name>"
                 + "<dob>2000-03-10</dob><blood>B+</blood></patient>";
        }
        return null; // Patient not found in external system
    }

    /**
     * Retrieves patient history as an XML document.
     * (External system returns XML, not plain text)
     *
     * @param patientNumber the external system's numeric patient ID
     * @return XML-formatted history string
     */
    public String retrieveHistoryXML(int patientNumber) {
        if (patientNumber == 123) {
            return "<history><patientId>123</patientId>"
                 + "<entry date=\"2025-06-01\">Routine checkup — normal</entry>"
                 + "<entry date=\"2025-09-15\">Flu symptoms — prescribed antibiotics</entry>"
                 + "<entry date=\"2025-12-30\">Follow-up — recovered</entry>"
                 + "</history>";
        } else if (patientNumber == 456) {
            return "<history><patientId>456</patientId>"
                 + "<entry date=\"2025-08-20\">Lab work — cholesterol elevated</entry>"
                 + "</history>";
        }
        return "<history><patientId>" + patientNumber + "</patientId></history>";
    }

    /**
     * Checks patient status in the external system.
     * Returns numeric status codes (external convention):
     *   1 = active, 0 = inactive, -1 = not found
     *
     * @param patientNumber the external system's numeric patient ID
     * @return status code (1=active, 0=inactive, -1=not found)
     */
    public int checkPatientStatus(int patientNumber) {
        if (patientNumber == 123 || patientNumber == 456) {
            return 1; // active
        } else if (patientNumber == 999) {
            return 0; // inactive (discharged)
        }
        return -1; // not found
    }
}
