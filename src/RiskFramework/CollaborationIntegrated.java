package RiskFramework;

import java.io.*;
import java.util.*;

public class CollaborationIntegrated extends GeneratorIntegrated {

    final String collaboration_file = "e:\\r2q\\collaboration.txt";
    final int no_of_collaborations = 500;

    FileReader fr1, fr2;
    BufferedReader br1, br2;
    int lineCount1, lineCount2;
    int j;
    String s;
    StringBuffer collaborationBuffer = new StringBuffer();

    ArrayList<String> request_cache = new ArrayList<>();
    ArrayList<String> requester_cache = new ArrayList<>();

    public void initArrayListCache(String requester_file, String request_file) {
        //read the files and store it into an array list...
        //also count no of lines in each file..
        try {

            fr1 = new FileReader(requester_file);
            br1 = new BufferedReader(fr1);

            fr2 = new FileReader(request_file);
            br2 = new BufferedReader(fr2);

            while (br1.ready()) {
                request_cache.add(br1.readLine());
                lineCount1++;
            }
            while (br2.ready()) {
                requester_cache.add(br2.readLine());
                lineCount2++;
            }
            br1.close();
            br2.close();
            fr1.close();
            fr2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}    
//next labelRequest..
