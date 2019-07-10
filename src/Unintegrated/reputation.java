//after compare..
package Unintegrated;
import java.io.*;
import java.util.*;

public class reputation {
    
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
         int userNo;
            double securityLevelExt;
            double riskScoreExt;
            StringBuffer label=new StringBuffer();
            String labelExt;
            final String labelled_request_file="e:\\r2q\\labelled_request.txt";
            final String bad_requests_file="e:\\r2q\\bad_requests.txt";
            int userBadRequests[]=new int[100]; //get userList.size afterwards..
           int reputation_initial[]=new int[100];    //get userList.size afterwards.. instead of 100
           double new_reputation[]=new double[100];   //get userList.size afterwards.. instead of 100
           
           
            reputation()
            {
            for(i=0;i<100;i++)
            {userBadRequests[i]=0;
                reputation_initial[i]=1;
            }
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
     public  double getRandomDoubleBetweenRange(double min, double max){
//    double x = (Math.random()*((max-min)+1))+min;
//    return x;
    Random r = new Random();
    double randomValue = min + (max - min) * r.nextDouble();
    return  randomValue;
    }
      public double calcInvGompertz (int a,int b, double c,int t)
    {double p,x,y,z,w;
    //x=(-1.0*b*Math.E);
    x=-1.0*c*t;
    y=Math.pow(Math.E,x);
    z=-1.0*b*y;
    w=Math.pow(Math.E,z);
    p=1.0-a*w;
//    System.out.println(x);
//     System.out.println(y);
//      System.out.println(z);
//       System.out.println(w);
//        System.out.println(p);
    return p;
    }
    public void extractDataLabelledRequest(){
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
                   for(;i<sb.indexOf("\t",i);i++){sensitivity.append(sb.charAt(i));}   i++;
                   for(;i<sb.indexOf("\t",i);i++){} i++;    //Random number for type of request..
                    for(;i<sb.indexOf("\0",i);i++){label.append(sb.charAt(i));} //i++;
                    
                    userExt=user.toString();
                   userTypeExt=userType.toString();
                    domainExt=domain.toString();
                    objectExt=object.toString();
                    accessModeExt=accessMode.toString();
                    sensitivityExt=sensitivity.toString(); 
            securityLevelExt=Double.parseDouble(securityLevel.toString());  
            riskScoreExt=Double.parseDouble(riskScore.toString());  
            
            labelExt=label.toString();
            //Display extracted contents..
            
//            System.out.println(userExt+","
//                           +securityLevelExt+","
//                           +userTypeExt+","
//                           +domainExt+","
//                           +objectExt+","
//                           +accessModeExt+","
//                           +riskScoreExt+","
//                           +sensitivityExt+","
//                              +labelExt+"\n");
            
            
            }
        
    public void assignReputation()
    {
    userNo=Integer.parseInt(user.delete(0, 4).toString());
    //System.out.println(userNo+"\n");
        
    if(labelExt.equals("Bad"))
    {userBadRequests[userNo]++;}
        
    //This shall occur after manipulation of extracted data..
            //reset extracted strings..
              user.delete(0, sb.length());
              securityLevel.delete(0, sb.length());
              userType.delete(0, sb.length());
              domain.delete(0, sb.length());
              object.delete(0, sb.length());
              accessMode.delete(0, sb.length());
              riskScore.delete(0, sb.length());
              sensitivity.delete(0, sb.length());
              label.delete(0, sb.length());
               
            //reset extracted lines
             sb.delete(0, sb.length());
    }
    
    public static void main(String[] args) throws Exception{
            
        reputation rep=new reputation();
               
    FileReader fr=new FileReader(rep.labelled_request_file);    
          BufferedReader br=new BufferedReader(fr); 
          rep.writeToFile("", rep.bad_requests_file);
          while(br.ready())
          {                
              //Read a line and store it into a character buffer
           while((rep.i=br.read())!=10)
              {
               // System.out.print((char)i);
                  rep.sb.append((char)rep.i);
              }
           //Append null to show end of line..
           rep.sb.append("\0");
           
           rep.extractDataLabelledRequest();
           rep.assignReputation();
          }
          //display bad requests array..
//         for(rep.i=0;rep.i<100;rep.i++)
//             System.out.print(rep.userBadRequests[rep.i]+"\t");
          
           br.close();
             fr.close();
              
              //calculate new reputation score based on number of bad requests..
             for(rep.i=0;rep.i<100;rep.i++)        //use userlist.size afterwards..instead of 100
               rep.new_reputation[rep.i]=rep.calcInvGompertz(1, 10, 0.3,rep.userBadRequests[rep.i] );
              
              
              
              //write to bad requests file..
              for(rep.i=0;rep.i<100;rep.i++)        //use userlist.size afterwards..instead of 100
              {
            String text="user"+rep.i+"\t"+rep.userBadRequests[rep.i]+"\t"+rep.reputation_initial[rep.i]+"\t"+ rep.new_reputation[rep.i]+"\n" ;
            rep.appendToFile(rep.bad_requests_file,text);
              }
              System.out.println("bad_requests.txt:\nuser:\n no of bad requests.:\nReputation initial:\n updated Reputation..\n");
    }
    
}
//next simulation..