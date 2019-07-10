//This class is the class on the lowest level
//the class it extends needs to be updated regularly..
package RiskFramework;

import com.opencsv.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class integrated extends ExpectedThreat {

    final String ReputationMatrixExcelFile = "E:\\r2q\\REputationMAtrix.csv";

    public void GeneratorMethod() {
        //Request code
        writeToFile("", request_file);   //empty previous contents..
        //Pick a sensitivity for each object..
        for (i = 0; i < objectList.size(); i++) {
            sensitivityArray[i] = getRandomDoubleBetweenRange(0.50, 1.00);
        }

        for (i = 0; i < no_of_requests; i++) {
            x = generateRandomIntIntRange(0, domainList.size() - 1);
            y = generateRandomIntIntRange((x) * 10, (x + 1) * 10 - 1);  //for each domain there are fixed objects..
            z = generateRandomIntIntRange(0, accessModeList.size() - 1);

            //some objects are common to all domains..
            //last 6 objcts in object list..
            if (i > no_of_requests - 10) {
                y = generateRandomIntIntRange(100, objectList.size() - 1);  //last 10 entries are meant for common objects.. 
            }
            //Claculate sensitivity Level based on normalisedSensitivity
            if (sensitivityArray[y] >= 0.50 && sensitivityArray[y] < 0.601) //Honest
            {
                sensitivityBuffer.append(sensitivityLevelList.get(3));
            } else if (sensitivityArray[y] >= 0.6010 && sensitivityArray[y] < 0.751) //selfish
            {
                sensitivityBuffer.append(sensitivityLevelList.get(2));
            } else if (sensitivityArray[y] >= 0.751 && sensitivityArray[y] < 0.900) //selfish
            {
                sensitivityBuffer.append(sensitivityLevelList.get(1));
            } else {
                sensitivityBuffer.append(sensitivityLevelList.get(0));
            }

            writerTextBuffer.append(domainList.get(x)).append("\t")
                    .append(objectList.get(y)).append("\t")
                    .append(accessModeList.get(z)).append("\t")
                    .append(sensitivityArray[y]).append("\t")
                    .append(sensitivityBuffer.toString()).append("\n");

            sensitivityBuffer.delete(0, sensitivityBuffer.length());
            appendToFile(request_file, writerTextBuffer.toString());
            writerTextBuffer.delete(0, writerTextBuffer.length());
        }
        System.out.println("request.txt\n" + "domain:\t object:\taccessMode:\trisk score:\t sensitivity:\t");

        //Requester code      
        writeToFile("", requester_file);    //empty previous contents..
        for (int i = 0; i < userList.size(); i++) {
            securityLevel = getRandomDoubleBetweenRange(0, 0.949);
            x = generateRandomIntIntRange(0, 2);

            writerTextBuffer.append(userList.get(i)).append("\t")
                    .append(securityLevel).append("\t")
                    .append(userTypeList.get(x)).append("\n");

            appendToFile(requester_file, writerTextBuffer.toString());
            writerTextBuffer.delete(0, writerTextBuffer.length());
        }
        System.out.println("\nrequester.txt\n" + "userList:\tecurityLevel:\tuserTypeList:\n");
    }

    public void collaborationMethod() {

        {
            initArrayListCache(requester_file, request_file);
            writeToFile("", collaboration_file);  //clear the file

            for (j = 0; j < no_of_collaborations; j++) {
                int l1 = generateRandomIntIntRange(0, lineCount1 - 1);
                int l2 = generateRandomIntIntRange(0, lineCount2 - 1);
                collaborationBuffer.append(request_cache.get(l1)).append("\t").append(requester_cache.get(l2)).append("\n");

                //Finally write to file collaboration_file
                appendToFile(collaboration_file, collaborationBuffer.toString());
                collaborationBuffer.delete(0, collaborationBuffer.length());
            }
            System.out.println("\nCollaboration.txt\n:" + "userList:\tsecurityLevel:\tuserTypeList:\t"
                    + "domain:\t object:\taccessMode:\tnormalisedSensitivity:\t sensitivity:\n");
        }
    }

    public void labelRequestMethod() {
        try {
            FileReader fr = new FileReader(collaboration_file);
            BufferedReader br = new BufferedReader(fr);
            writeToFile("", labelled_request_file);     //clear contents..

            while (br.ready()) {
                //Read a line and store it into a character buffer
                while ((i = br.read()) != 10) //ascii for \n=10
                {
                    // System.out.print((char)i);
                    sb.append((char) i);
                }
                //Append null to show end of line..
                sb.append("\0");
                extractDataCollaboration();
                //classify users
                userClassification(labelled_request_file);
            }
            System.out.println("\nLabelledRequest.txt\n:" + "userList:\tsecurityLevel:\tuserTypeList:\t"
                    + "domain:\t object:\taccessMode:\tnormalisedSensitivity:\t sensitivity:\t" + "Requestlabel\t");
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reputationMethod() {
        try {
            FileReader fr = new FileReader(labelled_request_file);
            BufferedReader br = new BufferedReader(fr);
            writeToFile("", bad_requests_file);
            while (br.ready()) {
                //Read a line and store it into a character buffer
                while ((i = br.read()) != 10) {
                    sb.append((char) i);
                }
                //Append null to show end of line..
                sb.append("\0");

                extractDataLabelledRequest();

                //count types of accessMode Requests to clculate Probability
                if (accessModeBuffer.toString().equals("View")) {
                    viewRequests++;
                } else if (accessModeBuffer.toString().equals("Edit")) {
                    editRequests++;
                } else if (accessModeBuffer.toString().equals("Execute")) {
                    executeRequests++;
                }

                assignReputation();
                //This shall occur after manipulation of extracted data..
                //reset extracted strings..
                clearBuffers();
            }
            //calculateProbability of occurance..
            view_probability = (double) viewRequests / no_of_collaborations;
            edit_probability = (double) editRequests / no_of_collaborations;
            execute_probability = (double) executeRequests / no_of_collaborations;

            br.close();
            fr.close();

            //calculate new reputation score based on number of bad requests..
            for (i = 0; i < no_of_users; i++) //use userlist.size afterwards..instead of 100
            {
                new_reputation[i] = calcInvGompertz(1, 10, 0.3, userBadRequests[i]);
            }

            //write to bad requests file..
            for (i = 0; i < no_of_users; i++) //use userlist.size afterwards..instead of 100
            {
                writerTextBuffer.append("user").append(i).append("\t")
                        .append(userBadRequests[i]).append("\t")
                        .append(reputation_initial[i]).append("\t")
                        .append(new_reputation[i]).append("\n");
                appendToFile(bad_requests_file, writerTextBuffer.toString());
                writerTextBuffer.delete(0, writerTextBuffer.length());

            }
            System.out.println("bad_requests.txt:\nuser:\t no of bad requests.:\tReputation initial:\t updated Reputation..\t");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ExpectedDamageMethod() {
        try {
            FileReader fr = new FileReader(labelled_request_file);
            BufferedReader br = new BufferedReader(fr);
            writeToFile("", expected_damage_file);//clear contents..
            while (br.ready()) {
                //Read a line and store it into a character buffer
                while ((i = br.read()) != 10) {
                    // System.out.print((char)i);
                    sb.append((char) i);
                }
                //Append null to show end of line..
                sb.append("\0");
                extractDataLabelledRequest();
                calculateExpectedDamage();
                assignDamage();

            }
            br.close();
            fr.close();
            System.out.println("\nExpectedDamage.txt\n:" + "userList:\tsecurityLevel:\tuserTypeList:\t"
                    + "domain:\t object:\taccessMode:\tnormalisedSensitivity:\t sensitivity:\t" + "Requestlabel\t"
                    + "ExpectedDamage:\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SimulationMethod() {
        init_collaboration_to_array_list_cache();   //initially...
        try {
            writeToFile("", SimulationFile);         //clear contents..
            for (j = 0; j < no_of_time_units; j++) {
                System.out.print(" " + j);
                k = generateRandomIntIntRange(0, no_of_simultaneous_requests);   //no. of requests that generate simultaneously..  100 is the limit..
                a = getRandomDoubleBetweenRange(0.0, Double.MAX_VALUE - 1);
                if (a < poissionValue(k)) {//generate new requests,new reputation..
                    for (; k > 0; k--) {
                        x = generateRandomIntIntRange(0, no_of_collaborations - 1);     //choose a collaboration file line..
                        writeSimulationFile();
                        LabelSimulation();
                        assign_new_reputation();
                        //clear buffers..
                        clearBuffers();
                    }
//     collaboration_cache.clear();
                    System.out.println("generated! Sleeping...");
                    TimeUnit.SECONDS.sleep(pauseInterval);
                } else {//a>k
                    //sleep...
                    System.out.println("Sleeping...");
                    TimeUnit.SECONDS.sleep(pauseInterval);        //pause for 3 secs...
                }

                appendToFile(SimulationFile, "\n");
                //storing the new reputation changes in ReputationChanges Matrix..
                for (l = 0; l < no_of_users; l++) {
                    ReputationMatrix[l][j] = new_reputation[l];
                }

                //updating the previous reputation..
                for (l = 0; l < no_of_users; l++) {
                    reputation_initial[l] = new_reputation[l];
                }

                //calculating the new reputation..
                for (l = 0; l < no_of_users; l++) {
                    new_reputation[l] = calcInvGompertz(1, 10, 0.3, userBadRequests[l]);
                }
            }
            System.out.println("\nSimulation.txt\n:" + "userList:\tsecurityLevel:\tuserTypeList:\t"
                    + "domain:\t object:\taccessMode:\tnormalisedSensitivity:\t sensitivity:\t"
                    + "RequestLabel:\tbadRequestsBy User:\tpreviousreputation:\tnewReputation:\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ExpectedThreatMethod() {
        try {
            FileReader fr = new FileReader(SimulationFile);
            BufferedReader br = new BufferedReader(fr);
            writeToFile("", final_file);//clear contents..
            k = 0;
            while (br.ready()) {
                //simulation number count..             
                //Read a line and store it into a character buffer
                while ((i = br.read()) != 10) {
                    sb.append((char) i);
                }
                //Append null to show end of line..
                sb.append("\0");
                if (sb.length() == 1) //if reads empty line..
                {
                    sb.delete(0, sb.length());
                    appendToFile(final_file, "\n");
                    k++;
                } else {
                    finalOutput();
                    clearBuffers();      //clear all Buffers..

                }

            }
            br.close();
            fr.close();
            System.out.println("\nFinal_file.txt\n:" + "userList:\tsecurityLevel:\tuserTypeList:\t"
                    + "domain:\t object:\taccessMode:\tnormalisedSensitivity:\t sensitivity:\t"
                    + "RequestLabel:\tbadRequestsBy User:\tpreviousreputation:\tnewReputation:\n"
                    + "ExpectedDamage:\tmodellingUncertaintyFunction:\tModeling_Expected_Utility_Function:\texpectedThreat:\tRiskScore:\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MatrixToFile(double matrix[][], String file) {
        //simply outputs the reputation matrix to a file..
        try {
            writeToFile("", file);
            for (l = 0; l < no_of_users; l++) {
                writerTextBuffer.append("user").append(l).append("\t");
                if (file.equals(ReputationChanges_file)) {
                    writerTextBuffer.append("1.0").append("\t");
                }
                for (int m = 0; m < no_of_time_units; m++) {
                    writerTextBuffer.append(matrix[l][m]).append("\t");
                }
                writerTextBuffer.append("\n");
                appendToFile(file, writerTextBuffer.toString());
                writerTextBuffer.delete(0, writerTextBuffer.length());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Matrix TO File generated successfully!");
    }

    public void MatrixToExcelFile(String inputFile, String outputFile) {

        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);

            File file = new File(outputFile);
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            //insert Header..
            sb.append("user");
            if (inputFile.equals(ReputationChanges_file)) {
                sb.append("\t").append("Initial Reputation");
            }
            for (k = 1; k < 103; k++) {
                sb.append("\t").append("TimeUnit ").append(k);
            }

            sb.append("\0");

            l = 0;
            for (k = 0; k < 102; k++) {
                for (; l < sb.indexOf("\t", l); l++) {
                    Buff.append(sb.charAt(l));
                }
                l++;
                stringArray[k] = Buff.toString();
                Buff.delete(0, Buff.length());
            }
            writer.writeNext(stringArray);
            sb.delete(0, sb.length());

            //atart Writing From File..(ReputationChanges_file)
            while (br.ready()) {
                //Read a line and store it into a character buffer
                while ((i = br.read()) != 10) //ascii for \n=10
                {
                    sb.append((char) i);
                }
                //Append null to show end of line..
                sb.append("\0");
                //store it into the Strig Array
                l = 0;
                for (k = 0; k < 101; k++) {
                    for (; l < sb.indexOf("\t", l); l++) {
                        Buff.append(sb.charAt(l));
                    }
                    l++;
                    stringArray[k] = Buff.toString();
                    Buff.delete(0, Buff.length());
                }

                for (; l < sb.indexOf("\0"); l++) {
                    Buff.append(sb.charAt(l));
                }
                l++;
                stringArray[101] = Buff.toString();
                Buff.delete(0, Buff.length());
                writer.writeNext(stringArray);
                sb.delete(0, sb.length());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Matrix to csv generated successfully..");
    }

    public static void main(String[] args) {
        integrated obj = new integrated();
        obj.GeneratorMethod();
        obj.collaborationMethod();
        obj.labelRequestMethod();
        obj.reputationMethod();             //try to count no_of_users..
        obj.ExpectedDamageMethod();         //not without upper function reputation
        obj.SimulationMethod();               //not without repuatationMethod..
        obj.ExpectedThreatMethod();           //not without reputation..
//       //output Files..
        obj.MatrixToFile(obj.ReputationMatrix, obj.ReputationChanges_file);
        obj.MatrixToExcelFile(obj.ReputationChanges_file, obj.ReputationMatrixExcelFile);
        obj.MatrixToFile(obj.RiskScoreMatrix, obj.RiskScoreChanges_file);       //not withoutExpected Threat
        obj.MatrixToExcelFile(obj.RiskScoreChanges_file, obj.RiskScoreMatrixExcelFile);

    }

}
