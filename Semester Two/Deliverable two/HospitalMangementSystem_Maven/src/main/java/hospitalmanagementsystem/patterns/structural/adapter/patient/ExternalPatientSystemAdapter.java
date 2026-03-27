package hospitalmanagementsystem.patterns.structural.adapter.patient;

public class ExternalPatientSystemAdapter implements PatientDataProvider {

    private final ExternalPatientSystemApi externalApi;

    public ExternalPatientSystemAdapter(ExternalPatientSystemApi externalApi) {
        if (externalApi == null) {
            throw new IllegalArgumentException("External API cannot be null");
        }
        this.externalApi = externalApi;
    }

    @Override
    public String getPatientById(String patientId) {
        int recordNumber = parsePatientId(patientId);
        if (recordNumber < 0) {
            return null;
        }

        String xmlData = externalApi.fetchPatientRecord(recordNumber);
        if (xmlData == null) {
            return null;
        }

        return convertXmlPatientToPlainText(xmlData);
    }

    @Override
    public String getPatientHistory(String patientId) {
        int patientNumber = parsePatientId(patientId);
        if (patientNumber < 0) {
            return "";
        }

        String xmlHistory = externalApi.retrieveHistoryXML(patientNumber);
        if (xmlHistory == null) {
            return "";
        }

        return convertXmlHistoryToPlainText(xmlHistory);
    }

    @Override
    public boolean isPatientActive(String patientId) {
        int patientNumber = parsePatientId(patientId);
        if (patientNumber < 0) {
            return false;
        }

        int statusCode = externalApi.checkPatientStatus(patientNumber);
        return statusCode == 1;
    }

    private int parsePatientId(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(patientId.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String convertXmlPatientToPlainText(String xml) {
        if (xml == null) {
            return null;
        }

        String id = extractXmlValue(xml, "id");
        String name = extractXmlValue(xml, "name");
        String dob = extractXmlValue(xml, "dob");
        String blood = extractXmlValue(xml, "blood");

        return "Patient [ID=" + id + ", Name=" + name
                + ", DOB=" + dob + ", Blood=" + blood + "]";
    }

    private String convertXmlHistoryToPlainText(String xml) {
        if (xml == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Medical History:\n");

        int pos = 0;
        while (true) {
            int entryStart = xml.indexOf("<entry", pos);
            if (entryStart < 0) {
                break;
            }

            int dateStart = xml.indexOf("date=\"", entryStart);
            String date = "unknown";
            if (dateStart >= 0) {
                dateStart += 6;
                int dateEnd = xml.indexOf("\"", dateStart);
                if (dateEnd >= 0) {
                    date = xml.substring(dateStart, dateEnd);
                }
            }

            int contentStart = xml.indexOf(">", entryStart) + 1;
            int contentEnd = xml.indexOf("</entry>", contentStart);
            String content = (contentEnd >= 0)
                    ? xml.substring(contentStart, contentEnd)
                    : "N/A";

            sb.append("  - [").append(date).append("] ").append(content).append("\n");
            pos = (contentEnd >= 0) ? contentEnd + 8 : xml.length();
        }

        if (sb.toString().equals("Medical History:\n")) {
            sb.append("  (no records)\n");
        }

        return sb.toString();
    }

    private String extractXmlValue(String xml, String tag) {
        if (xml == null || tag == null) {
            return "N/A";
        }

        String openTag = "<" + tag + ">";
        String closeTag = "</" + tag + ">";
        int start = xml.indexOf(openTag);
        if (start < 0) {
            return "N/A";
        }

        start += openTag.length();
        int end = xml.indexOf(closeTag, start);
        if (end < 0) {
            return "N/A";
        }

        return xml.substring(start, end);
    }
}