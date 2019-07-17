package RiskFramework;

import java.io.*;
import java.util.*;

public class Simulation extends expectedDamage {

    final int pauseInterval = 02;
    final String SimulationFile = "e:\\r2q\\Simulation.txt";
    final String ReputationChanges_file = "e:\\r2q\\ReputationChanges.txt";
    final String RiskScoreMatrixExcelFile = "e:\\r2q\\RiskScoreChanges.csv";
    final String RiskScoreChanges_file = "e:\\r2q\\RiskScoreChanges.txt";
    final int no_of_time_units = 100;               //100
    final int no_of_simultaneous_requests = 100;    //100 is enough..
    final double poissionLambda = 0.45;

    String[] stringArray = new String[102];
    StringBuilder Buff = new StringBuilder();

    double ReputationMatrix[][] = new double[no_of_time_units][no_of_users];
    double RiskScoreMatrix[][] = new double[no_of_time_units][no_of_users];
    double poissionValue, poissionNumerator;
    ArrayList<String> collaboration_cache = new ArrayList<>();
    int lineCountCollaboration, k, l;
    double a, reputation;
    StringBuffer reputation_previous = new StringBuffer();
    StringBuffer reputation_new = new StringBuffer();

    public int calcFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return (n * calcFactorial(n - 1));
        }
    }

    public void init_collaboration_to_array_list_cache() {
        try {
            fr1 = new FileReader(collaboration_file);
            br1 = new BufferedReader(fr1);
            while (br1.ready()) {
                collaboration_cache.add(br1.readLine());
                lineCountCollaboration++;
            }
            br1.close();
            fr1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double poissionValue(int k) {
        d = Math.pow(Math.E, -1.0 * poissionLambda);
        poissionNumerator = (Math.pow(poissionLambda, (double) k) * d);
        poissionValue = poissionNumerator / calcFactorial(k);
        return poissionValue;
    }

    public void writeSimulationFile() {
        l = generateRandomIntIntRange(0, no_of_collaborations - 1);
        sb.append(collaboration_cache.get(l));  //fetched collaboration random..
        sb.append("\0");    //to mark the end of string..
        extractDataCollaboration();
    }

    public void LabelSimulation() {
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
    }

    public void assign_new_reputation() {
        //fetch reputationlevel from   newReputation array..
        assignReputation();

        writerTextBuffer.append(sb).append("\t")
                .append(labelBuffer).append("\t")
                .append(userBadRequests[userNo]).append("\t")
                .append(reputation_initial[userNo]).append("\t")
                .append(new_reputation[userNo]).append("\n");

        appendToFile(SimulationFile, writerTextBuffer.toString());
    }

}
//next expected Threat..
