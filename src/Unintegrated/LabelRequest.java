//after collaboration..
package Unintegrated;
import java.io.*;
import java.util.*;


public class LabelRequest {
    
     StringBuffer  user=new StringBuffer(),
                       securityLevel=new StringBuffer(),
                       userType=new StringBuffer(),
                       domain=new StringBuffer(),
                       object=new StringBuffer(),
                       accessMode=new StringBuffer(),
                       riskScore=new StringBuffer(),
                       sensitivity=new StringBuffer();
      StringBuffer sb=new StringBuffer();
        int i;
         String userExt,userTypeExt,domainExt,objectExt,accessModeExt,sensitivityExt; 
          String label;
            double securityLevelExt;
            double riskScoreExt;
            double d;
      final String requester_file="e:\\r2q\\requester.txt";
      final String request_file= "e:\\r2q\\request.txt";  
      final String collaboration_file="e:\\r2q\\collaboration.txt";
      final String labelled_request_file="e:\\r2q\\labelled_request.txt";
            int viewRequests=0,editRequests=0,executeRequests=0;
            
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
     public  double getRandomDoubleBetweenRange(double min, double max){
//    double x = (Math.random()*((max-min)+1))+min;
//    return x;
    Random r = new Random();
    double randomValue = min + (max - min) * r.nextDouble();
    return  randomValue;
    }
    public void extractDataCollaboration(){
             //Display contents..
             // System.out.print(sb.toString()+"\n");
              
              //Extract info from the line..
                         
               for(i=0;i<sb.indexOf("\t");i++){user.append(sb.charAt(i));    } i++;
                for(;i<sb.indexOf("\t",i);i++){securityLevel.append(sb.charAt(i));} i++;
                 for(;i<sb.indexOf("\t",i);i++){userType.append(sb.charAt(i));    } i++;
                  for(;i<sb.indexOf("\t",i);i++){domain.append(sb.charAt(i));    } i++;
                   for(;i<sb.indexOf("\t",i);i++){object.append(sb.charAt(i));    } i++;
                    for(;i<sb.indexOf("\t",i);i++){accessMode.append(sb.charAt(i));    } i++;
                   for(;i<sb.indexOf("\t",i);i++){riskScore.append(sb.charAt(i));}   i++;
                   for(;i<sb.indexOf("\0",i);i++){sensitivity.append(sb.charAt(i));}   //i++;
              
                    userExt=user.toString();
                   userTypeExt=userType.toString();
                    domainExt=domain.toString();
                    objectExt=object.toString();
                    accessModeExt=accessMode.toString();
                    sensitivityExt=sensitivity.toString(); 
            securityLevelExt=Double.parseDouble(securityLevel.toString());  
            riskScoreExt=Double.parseDouble(riskScore.toString());  
            
            //Display extracted contents..
            
//            System.out.println(userExt+","
//                           +securityLevelExt+","
//                           +userTypeExt+","
//                           +domainExt+","
//                           +objectExt+","
//                           +accessModeExt+","
//                           +riskScoreExt+","
//                           +sensitivityExt+"\n");
            }
        
    public void userClassification(String file){
        //based on fact that the random number generated lies in the range of userType or not..
          d=getRandomDoubleBetweenRange(0,1);
          label="Bad";
        if(userTypeExt.equals("Honest")&&(d<0.950))
        {label="Good";}
        else if (userTypeExt.equals("Selfish")&&(d<0.700))
         {label="Good";}
        else if(userTypeExt.equals("Malicious")&&d<0.10)
         {label="Good";}
        String Write=sb.toString()+"\t"+d+"\t"+label+"\n";
        appendToFile(file, Write);
        
        System.out.println(Write);
         //reset extracted strings..
              user.delete(0, sb.length());
              securityLevel.delete(0, sb.length());
              userType.delete(0, sb.length());
              domain.delete(0, sb.length());
              object.delete(0, sb.length());
              accessMode.delete(0, sb.length());
              riskScore.delete(0, sb.length());
              sensitivity.delete(0, sb.length());
               
            //reset extracted lines
             sb.delete(0, sb.length());
    }

    public static void main(String[] args) throws Exception {
        LabelRequest cmp=new LabelRequest();
       
          FileReader fr=new FileReader(cmp.collaboration_file);    
          BufferedReader br=new BufferedReader(fr); 
          cmp.writeToFile("", cmp.labelled_request_file);     //clear contents..
          
          while(br.ready())
          {                
              //Read a line and store it into a character buffer
           while((cmp.i=br.read())!=10)         //ascii for \n=10
              {
               // System.out.print((char)i);
                  cmp.sb.append((char)cmp.i);
              }
           //Append null to show end of line..
           cmp.sb.append("\0");
           cmp.extractDataCollaboration();
           //count types of accessMode Requests to clculate Probability
           if(cmp.accessModeExt.equals("View"))
               cmp.viewRequests++;
           else if(cmp.accessModeExt.equals("Edit"))
               cmp.editRequests++;
           else if(cmp.accessModeExt.equals("Execute"))
           cmp.executeRequests++;
           
            cmp.userClassification(cmp.labelled_request_file);
            }
//           System.out.println(cmp.viewRequests+cmp.editRequests+cmp.executeRequests);
          System.out.println("\nLabelledRequest.txt\n:"+"userList:\nsecurityLevel:\nuserTypeList:\n"+
                    "domain:\n object:\naccessMode:\nnormalisedSensitivity:\n sensitivity:\n"+"random no generated for type of request:\nRequestlabel\n");
             br.close();
              fr.close();
              
    
   
    
}
}
//next reputation..