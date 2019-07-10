package RiskFramework;

import com.opencsv.*;
import java.io.*;
import java.util.*;

public class rough extends integrated {

    public void countLines() throws Exception {
        FileReader fr1 = new FileReader("E:\\r2q\\request.txt");
        BufferedReader br1 = new BufferedReader(fr1);

        FileReader fr2 = new FileReader("E:\\r2q\\requester.txt");
        BufferedReader br2 = new BufferedReader(fr2);
        String s;
        int lineCount1 = 0, lineCount2 = 0;
        //Count no of lines in each file..
        while ((s = br1.readLine()) != null) //Reading Content from the file line by line
        {
            lineCount1++;               //For each line increment linecount by one 

        }
        while ((s = br2.readLine()) != null) //Reading Content from the file line by line
        {
            lineCount2++;               //For each line increment linecount by one 

        }
    }

    public static void main(String[] args) {
        rough r = new rough();
        r.writeToFile("\t\nabc","e:\\r2q\\t.txt");
      for (int i=0;i<111;i++)
      {
      r.appendToFile("e:\\r2q\\objects.txt","\t"+"objectList.add(\"object"+i+"\");"+"\n");
      }
      System.out.println("success");
//            try{
//       FileReader fr1=new FileReader("E:\\r2q\\request.txt");    
//        BufferedReader br1=new BufferedReader(fr1);
//        
//        
//              FileReader fr2=new FileReader("E:\\r2q\\requester.txt");    
//        BufferedReader br2=new BufferedReader(fr2);
//        
//       ArrayList<String> request_cache = new ArrayList<>();
//        ArrayList<String> requester_cache = new ArrayList<>();
// 
//
//    while (br1.ready()) {
//        request_cache.add(br1.readLine());    }
//      while (br2.ready()) {
//        requester_cache.add(br2.readLine());    }
//           while(br.ready())
//          System.out.println(br.read());
//     for(int i=0;i<request_cache.size();i++)  
//           {  
//            System.out.println(request_cache.get(i));     
//           }  
//        for(int i=0;i<requester_cache.size();i++)  
//           {  
////            System.out.println(requester_cache.get(i));     
//           }  
//try{
//     rough r=new rough();
//System.out.println(r.userList.size());
//for(int i=0;i<100;i++)
//System.out.println(r.userBadRequests[i]);
//            }
//             catch(Exception e){e.printStackTrace();}
//r.init_collaboration_to_array_list_cache();
//for(int i=0;i<r.no_of_collaborations;i++)
//    System.out.println(r.collaboration_cache.get(i));
//
//            System.out.println(r.poissionValue(50));
//            if(Double.MAX_VALUE<r.poissionValue(100))
//            System.out.print("abcd");
//             r.k=r.generateRandomIntIntRange(0,2147483646);
//              System.out.println(Integer.MAX_VALUE);
//        try {
//            rough r = new rough();
//            FileReader fr = new FileReader("E:\\r2q\\ReputationChanges.txt");
//            BufferedReader br = new BufferedReader(fr);
//            String[] x = new String[102];
//            StringBuffer Buff = new StringBuffer();
//            List<String[]> list = new ArrayList<>();
//            int l;
//
//            // first create file object for file placed at location 
//            // specified by filepath 
//            File file = new File("E:\\r2q\\abc.csv");
//            // create FileWriter object with file as parameter 
//            FileWriter outputfile = new FileWriter(file);
//
//            // create CSVWriter object filewriter object as parameter 
//            CSVWriter writer = new CSVWriter(outputfile);
//            r.sb.append("user").append("\t").append("Initial Reputation");
//            for (int k = 1; k < 103; k++) {
//                r.sb.append("\t").append("TimeUnit ").append(k);
//            }
//            r.sb.append("\0");
//
//            l = 0;
//            for (int k = 0; k < 102; k++) {
//                for (; l < r.sb.indexOf("\t", l); l++) {
//                    Buff.append(r.sb.charAt(l));
//                }
//                l++;
//                x[k] = Buff.toString();
//                Buff.delete(0, Buff.length());
////        System.out.print(x[k]+" ");
////        System.out.println(" "+l);
//            }
//            writer.writeNext(x);
//            r.sb.delete(0, r.sb.length());
//
//            while (br.ready()) {
//                //Read a line and store it into a character buffer
//                while ((r.i = br.read()) != 10) //ascii for \n=10
//                {
//                    // System.out.print((char)i);
//                    r.sb.append((char) r.i);
//                }
//
//                //Append null to show end of line..
//                r.sb.append("\0");
//                l = 0;
//                for (int k = 0; k < 101; k++) {
//                    for (; l < r.sb.indexOf("\t", l); l++) {
//                        Buff.append(r.sb.charAt(l));
//                    }
//                    l++;
//                    x[k] = Buff.toString();
//                    Buff.delete(0, Buff.length());
////        System.out.print(x[k]+" ");
////        System.out.println(" "+l);
//                }
//
//                for (; l < r.sb.indexOf("\0"); l++) {
//                    Buff.append(r.sb.charAt(l));
//                }
//                l++;
//                x[101] = Buff.toString();
//                Buff.delete(0, Buff.length());
////        System.out.println(x[101]);
////        list.add(x);
//                writer.writeNext(x);
//                r.sb.delete(0, r.sb.length());
//            }
//
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
