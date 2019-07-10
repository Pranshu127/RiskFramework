package RiskFramework;

public class reputationIntegrated extends labelRequestIntegrated {

    final String bad_requests_file = "e:\\r2q\\bad_requests.txt";
    int userNo;
    int userBadRequests[] = new int[no_of_users]; //get userList.size afterwards..
    double reputation_initial[] = new double[no_of_users];    //get userList.size afterwards.. instead of 100
    double new_reputation[] = new double[no_of_users];   //get userList.size afterwards.. instead of 100

    reputationIntegrated() {
        for (i = 0; i < userList.size(); i++) {
            userBadRequests[i] = 0;
            reputation_initial[i] = 1;
        }
    }

    //calculate userList arraylist size for the particular object passed as reference..
    public double calcInvGompertz(int a, int b, double c, int t) {
        double p, x, y, z, w;
        x = -1.0 * c * t;
        y = Math.pow(Math.E, x);
        z = -1.0 * b * y;
        w = Math.pow(Math.E, z);
        p = 1.0 - a * w;
        return p;
    }

    public void extractDataLabelledRequest() {
        //Display contents..
        // System.out.print(sb.toString()+"\n");

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
        for (; i < sb.indexOf("\t", i); i++) {
            sensitivityBuffer.append(sb.charAt(i));
        }
        i++;
        for (; i < sb.indexOf("\0", i); i++) {
            labelBuffer.append(sb.charAt(i));
        } //i++;

        securityLevelExt = Double.parseDouble(securityLevelBuffer.toString());
        normalisedSensitivityExt = Double.parseDouble(normalisedSensitivityBuffer.toString());

    }

    public void assignReputation() {
        userNo = Integer.parseInt(userBuffer.delete(0, 4).toString());
        if (labelBuffer.toString().equals("Bad")) {
            userBadRequests[userNo]++;
        }

    }

}
//next expected damage..
