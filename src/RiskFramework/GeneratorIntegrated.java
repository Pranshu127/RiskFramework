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

        objectList.add("object0");
        objectList.add("object1");
        objectList.add("object2");
        objectList.add("object3");
        objectList.add("object4");
        objectList.add("object5");
        objectList.add("object6");
        objectList.add("object7");
        objectList.add("object8");
        objectList.add("object9");
        objectList.add("object10");
        objectList.add("object11");
        objectList.add("object12");
        objectList.add("object13");
        objectList.add("object14");
        objectList.add("object15");
        objectList.add("object16");
        objectList.add("object17");
        objectList.add("object18");
        objectList.add("object19");
        objectList.add("object20");
        objectList.add("object21");
        objectList.add("object22");
        objectList.add("object23");
        objectList.add("object24");
        objectList.add("object25");
        objectList.add("object26");
        objectList.add("object27");
        objectList.add("object28");
        objectList.add("object29");
        objectList.add("object30");
        objectList.add("object31");
        objectList.add("object32");
        objectList.add("object33");
        objectList.add("object34");
        objectList.add("object35");
        objectList.add("object36");
        objectList.add("object37");
        objectList.add("object38");
        objectList.add("object39");
        objectList.add("object40");
        objectList.add("object41");
        objectList.add("object42");
        objectList.add("object43");
        objectList.add("object44");
        objectList.add("object45");
        objectList.add("object46");
        objectList.add("object47");
        objectList.add("object48");
        objectList.add("object49");
        objectList.add("object50");
        objectList.add("object51");
        objectList.add("object52");
        objectList.add("object53");
        objectList.add("object54");
        objectList.add("object55");
        objectList.add("object56");
        objectList.add("object57");
        objectList.add("object58");
        objectList.add("object59");
        objectList.add("object60");
        objectList.add("object61");
        objectList.add("object62");
        objectList.add("object63");
        objectList.add("object64");
        objectList.add("object65");
        objectList.add("object66");
        objectList.add("object67");
        objectList.add("object68");
        objectList.add("object69");
        objectList.add("object70");
        objectList.add("object71");
        objectList.add("object72");
        objectList.add("object73");
        objectList.add("object74");
        objectList.add("object75");
        objectList.add("object76");
        objectList.add("object77");
        objectList.add("object78");
        objectList.add("object79");
        objectList.add("object80");
        objectList.add("object81");
        objectList.add("object82");
        objectList.add("object83");
        objectList.add("object84");
        objectList.add("object85");
        objectList.add("object86");
        objectList.add("object87");
        objectList.add("object88");
        objectList.add("object89");
        objectList.add("object90");
        objectList.add("object91");
        objectList.add("object92");
        objectList.add("object93");
        objectList.add("object94");
        objectList.add("object95");
        objectList.add("object96");
        objectList.add("object97");
        objectList.add("object98");
        objectList.add("object99");
        objectList.add("object100");
        objectList.add("object101");
        objectList.add("object102");
        objectList.add("object103");
        objectList.add("object104");
        objectList.add("object105");

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

        userList.add("user0");
        userList.add("user1");
        userList.add("user2");
        userList.add("user3");
        userList.add("user4");
        userList.add("user5");
        userList.add("user6");
        userList.add("user7");
        userList.add("user8");
        userList.add("user9");
        userList.add("user10");
        userList.add("user11");
        userList.add("user12");
        userList.add("user13");
        userList.add("user14");
        userList.add("user15");
        userList.add("user16");
        userList.add("user17");
        userList.add("user18");
        userList.add("user19");
        userList.add("user20");
        userList.add("user21");
        userList.add("user22");
        userList.add("user23");
        userList.add("user24");
        userList.add("user25");
        userList.add("user26");
        userList.add("user27");
        userList.add("user28");
        userList.add("user29");
        userList.add("user30");
        userList.add("user31");
        userList.add("user32");
        userList.add("user33");
        userList.add("user34");
        userList.add("user35");
        userList.add("user36");
        userList.add("user37");
        userList.add("user38");
        userList.add("user39");
        userList.add("user40");
        userList.add("user41");
        userList.add("user42");
        userList.add("user43");
        userList.add("user44");
        userList.add("user45");
        userList.add("user46");
        userList.add("user47");
        userList.add("user48");
        userList.add("user49");
        userList.add("user50");
        userList.add("user51");
        userList.add("user52");
        userList.add("user53");
        userList.add("user54");
        userList.add("user55");
        userList.add("user56");
        userList.add("user57");
        userList.add("user58");
        userList.add("user59");
        userList.add("user60");
        userList.add("user61");
        userList.add("user62");
        userList.add("user63");
        userList.add("user64");
        userList.add("user65");
        userList.add("user66");
        userList.add("user67");
        userList.add("user68");
        userList.add("user69");
        userList.add("user70");
        userList.add("user71");
        userList.add("user72");
        userList.add("user73");
        userList.add("user74");
        userList.add("user75");
        userList.add("user76");
        userList.add("user77");
        userList.add("user78");
        userList.add("user79");
        userList.add("user80");
        userList.add("user81");
        userList.add("user82");
        userList.add("user83");
        userList.add("user84");
        userList.add("user85");
        userList.add("user86");
        userList.add("user87");
        userList.add("user88");
        userList.add("user89");
        userList.add("user90");
        userList.add("user91");
        userList.add("user92");
        userList.add("user93");
        userList.add("user94");
        userList.add("user95");
        userList.add("user96");
        userList.add("user97");
        userList.add("user98");
        userList.add("user99");
    }

}
//next collaborationInteg.
