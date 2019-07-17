package RiskFramework;

import java.io.*;
import java.util.*;

public class GeneratorIntegrated {

    final int no_of_requests = 100;
    final int no_of_users = 100;     //make sure it is equal to userlist.size 
    final int no_of_objects = 106;        //make sure it`s equal to objectSize.list!
    final String requester_file = "e:\\r2q\\requester.txt";
    final String request_file = "e:\\r2q\\request.txt";

    int i, x, y, z;
    double normalisedSensitivity;
    double securityLevel;

    StringBuffer writerTextBuffer = new StringBuffer();
    StringBuffer sensitivityBuffer = new StringBuffer();

    double sensitivityArray[] = new double[no_of_objects];

    ArrayList<String> domainList = new ArrayList<>();//Creating arraydomainList
    ArrayList<String> objectList = new ArrayList<>();
    ArrayList<String> accessModeList = new ArrayList<>();
    ArrayList<String> sensitivityLevelList = new ArrayList<>();

    ArrayList<String> userTypeList = new ArrayList<>();
    ArrayList<String> userList = new ArrayList<>();

    public int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public double getRandomDoubleBetweenRange(double min, double max) {
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return randomValue;
    }

    public void writeToFile(String text, String file) {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            byte b[] = text.getBytes();
            bout.write(b);
            bout.flush();
            bout.close();
            fout.close();
//     System.out.println("Write success"); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String file, String text) {
        try {
            // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(file, true));
            out.write(text);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public GeneratorIntegrated() //constructor
    {

        domainList.add("domain0");
        domainList.add("domain1");//Adding object in arraydomainList    
        domainList.add("domain2");
        domainList.add("domain3");
        domainList.add("domain4");
        domainList.add("domain5");
        domainList.add("domain6");
        domainList.add("domain7");
        domainList.add("domain8");
        domainList.add("domain9");

        for (i = 0; i < no_of_objects; i++) {
            objectList.add("object" + i);
        }

        accessModeList.add("View");
        accessModeList.add("Edit");
        accessModeList.add("Execute");

        sensitivityLevelList.add("Top_Secret");
        sensitivityLevelList.add("Secret");
        sensitivityLevelList.add("Confidential");
        sensitivityLevelList.add("Unclassified");

        userTypeList.add("Selfish");
        userTypeList.add("Honest");
        userTypeList.add("Malicious");

        for (i = 0; i < no_of_users; i++) {
            userList.add("user" + i);
        }

    }

}
//next collaborationInteg.
