//after generator..
package Unintegrated;
import java.io.*;
import java.util.*;

public class Collaboration {

    FileReader fr1,fr2;
    BufferedReader br1,br2;
    int lineCount1,lineCount2;
    int i,j;
      String s;
      StringBuffer collaboration=new StringBuffer();
      Random r = new Random();
      final String requester_file="e:\\r2q\\requester.txt";
      final String request_file= "e:\\r2q\\request.txt";  
      final String collaboration_file="e:\\r2q\\collaboration.txt";
      final int no_of_collaborations=500;
       ArrayList<String> request_cache = new ArrayList<>();
        ArrayList<String> requester_cache = new ArrayList<>();
      
    public void initArrayListCache(String requester_file,String request_file)
    {
    //read the files and store it into an array list...
        //also count no of lines in each file..
        try{
         
         fr1=new FileReader(requester_file);    
         br1=new BufferedReader(fr1);    
          
        fr2=new FileReader(request_file);    
        br2=new BufferedReader(fr2);  
         
        while (br1.ready()) {
        request_cache.add(br1.readLine());  
        lineCount1++;}
      while (br2.ready()) {
        requester_cache.add(br2.readLine()); 
      lineCount2++;}
       br1.close();br2.close();
       fr1.close();  fr2.close();
           
                        
        }
        catch(Exception e){e.printStackTrace();}
        
    }
    
    
    public int generateRandomIntIntRange(int min, int max) {
    
    return r.nextInt((max - min) + 1) + min;
    }
    
    public void writeToFile(String text,String file)
    {
        try{
     FileOutputStream fout=new FileOutputStream(file);    
     BufferedOutputStream bout=new BufferedOutputStream(fout);    
     byte b[]=text.getBytes();    
     bout.write(b);    
     bout.flush();    
     bout.close();    
     fout.close();    
     System.out.println("success"); 

        }
        catch(Exception e){e.printStackTrace();}
    }

    public void appendToFile(String file,String text)
    {
         try{
        // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter(file, true)); 
            out.write(text); 
            out.close(); 
         }
          catch(Exception e){e.printStackTrace();}
    
    }
    
    public static void main(String[] args) throws Exception {
       
            Collaboration c=new Collaboration();
            c.initArrayListCache(c.requester_file,c.request_file);
           
           
            c.writeToFile("", c.collaboration_file);  //clear the file
            
            for(c.j=0;c.j<c.no_of_collaborations;c.j++)
            {
            int l1=c.generateRandomIntIntRange(0,c.lineCount1-1);
            int l2=c.generateRandomIntIntRange(0,c.lineCount2-1);
                 
             c.collaboration.append(c.request_cache.get(l1)).append("\t").append(c.requester_cache.get(l2)).append("\n");
//             System.out.println(c.collaboration.toString());
             //Finally write to file collaboration_file
             c.appendToFile(c.collaboration_file,c.collaboration.toString());
             c.collaboration.delete(0, c.collaboration.length());
                }
            System.out.println("\nCollaboration.txt\n:"+"userList:\nsecurityLevel:\nuserTypeList:\n"+
                    "domain:\n object:\naccessMode:\nnormalisedSensitivity:\n sensitivity:\n");
             

    //Writing to the collaboraion file..
    
    }
    
}

//next compare..
