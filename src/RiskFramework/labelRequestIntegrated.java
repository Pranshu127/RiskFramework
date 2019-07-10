package RiskFramework;

public class labelRequestIntegrated extends CollaborationIntegrated {

    final String labelled_request_file = "e:\\r2q\\labelled_request.txt";
    StringBuffer userBuffer = new StringBuffer(),
            securityLevelBuffer = new StringBuffer(),
            userTypeBuffer = new StringBuffer(),
            domainBuffer = new StringBuffer(),
            objectBuffer = new StringBuffer(),
            accessModeBuffer = new StringBuffer(),
            normalisedSensitivityBuffer = new StringBuffer();
    StringBuffer sb = new StringBuffer();
    StringBuffer labelBuffer = new StringBuffer();
    double securityLevelExt, normalisedSensitivityExt, d;
    double view_probability, edit_probability, execute_probability;
    int viewRequests = 0, editRequests = 0, executeRequests = 0;

    public void extractDataCollaboration() {

        //Extract info from the line..
        for (i = 0; i < sb.indexOf("\t"); i++) {
            userBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\t", i); i++) {
            securityLevelBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\t", i); i++) {
            userTypeBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\t", i); i++) {
            domainBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\t", i); i++) {
            objectBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\t", i); i++) {
            accessModeBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\t", i); i++) {
            normalisedSensitivityBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\0", i); i++) {
            sensitivityBuffer.append(sb.charAt(i));
        }   //i++;

        securityLevelExt = Double.parseDouble(securityLevelBuffer.toString());
        normalisedSensitivityExt = Double.parseDouble(normalisedSensitivityBuffer.toString());
    }

    public void userClassification(String file) {
        //based on fact that the random number generated lies in the range of userType or not..
        d = getRandomDoubleBetweenRange(0, 1);
        labelBuffer.append("Bad");
        if (userTypeBuffer.toString().equals("Honest") && (d < 0.950)) {
            labelBuffer.delete(0, labelBuffer.length());
            labelBuffer.append("Good");
        } else if (userTypeBuffer.toString().equals("Selfish") && (d < 0.700)) {
            labelBuffer.delete(0, labelBuffer.length());
            labelBuffer.append("Good");
        } else if (userTypeBuffer.toString().equals("Malicious") && d < 0.10) {
            labelBuffer.delete(0, labelBuffer.length());
            labelBuffer.append("Good");
        }

        writerTextBuffer.append(sb.toString()).append("\t")
                //                .append(d).append("\t")
                .append(labelBuffer.toString()).append("\n");
        appendToFile(file, writerTextBuffer.toString());
        clearBuffers();
    }

    public void clearBuffers() {
        writerTextBuffer.delete(0, writerTextBuffer.length());
        //reset extracted strings..
        userBuffer.delete(0, sb.length());
        securityLevelBuffer.delete(0, sb.length());
        userTypeBuffer.delete(0, sb.length());
        domainBuffer.delete(0, sb.length());
        objectBuffer.delete(0, sb.length());
        accessModeBuffer.delete(0, sb.length());
        normalisedSensitivityBuffer.delete(0, sb.length());
        sensitivityBuffer.delete(0, sb.length());
        labelBuffer.delete(0, labelBuffer.length());
        //reset extracted lines
        sb.delete(0, sb.length());
    }

}
//next reputation..
