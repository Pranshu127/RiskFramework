//1st file..
package Unintegrated;
import java.io.*;
import java.util.*;
public class Generator {
    
    final int no_of_requests=100;
    final String requester_file="e:\\r2q\\requester.txt";
    final String request_file= "e:\\r2q\\request.txt";  
     ArrayList<String> domainList=new ArrayList<>();//Creating arraydomainList
     ArrayList<String> objectList=new ArrayList<>(); 
     ArrayList<String> accessModeList=new ArrayList<>();
     ArrayList<String> sensitivityLevelList=new ArrayList<>();
     
     ArrayList<String> userTypeList=new ArrayList<>();
     ArrayList<String> userList=new ArrayList <>();
      
    String sensitivity; 
    int i,x,y,z;
    double normalisedSensitivity;
    double securityLevel;
    public int generateRandomIntIntRange(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
    }
    
    public double getRandomDoubleBetweenRange(double min, double max){
//    double x = (Math.random()*((max-min)+1))+min;
//    return x;
    Random r = new Random();
    double randomValue = min + (max - min) * r.nextDouble();
    return  randomValue;
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
     System.out.println("Write success"); 
     
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
//            System.out.println("append success");
         }
          catch(Exception e){e.printStackTrace();}
    
    }
    
    public void initArrayLists()
    {
     
     domainList.add("domain0");
     domainList.add("domain1");//Adding object in arraydomainList    
     domainList.add("domain2");    
     domainList.add("domain3");    
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
    public static void main(String[] args) {
        
        Generator gen=new Generator();
        
        //Request code
        gen.initArrayLists();
   
        
       
       
        gen.writeToFile("",gen.request_file);   //empty previous contents..
       for( gen.i=0;gen.i<gen.no_of_requests;gen.i++)  
           {  
               gen.x=gen.generateRandomIntIntRange(0,gen.domainList.size()-1);
               gen.y=gen.generateRandomIntIntRange(0,gen.objectList.size()-1);
               gen.z=gen.generateRandomIntIntRange(0,gen.accessModeList.size()-1);
               //int w=generateRandomIntIntRange(0,sensitivityLevelList.size()-1);
                              
               //Claculate sensitivity Level based on risk Score
               gen.normalisedSensitivity=gen.getRandomDoubleBetweenRange(0.50,1.00);
               
                if(gen.normalisedSensitivity>=0.50&&gen.normalisedSensitivity<0.601)  //Honest
                    gen.sensitivity=gen.sensitivityLevelList.get(3);
                else if(gen.normalisedSensitivity>=0.6010&&gen.normalisedSensitivity<0.751)     //selfish
                    gen.sensitivity=gen.sensitivityLevelList.get(2);
                 else if(gen.normalisedSensitivity>=0.751&&gen.normalisedSensitivity<0.900)     //selfish
                    gen.sensitivity=gen.sensitivityLevelList.get(1);   
                 else
                   gen.sensitivity=gen.sensitivityLevelList.get(0);   
                       
                       
               String text=gen.domainList.get(gen.x)+"\t"+
                  gen.objectList.get(gen.y)+"\t"+
                  gen.accessModeList.get(gen.z)+"\t"+ gen.normalisedSensitivity +"\t"+
                  gen.sensitivity+"\n";
               
          gen.appendToFile(gen.request_file,text);
            }
            System.out.println("request.txt\n"+"domain:\n object:\naccessMode:\nrisk score:\n sensitivity:\n");
            //Requester code
            
         
            
               
        gen.writeToFile("",gen.requester_file);
        for(int i=0;i<gen.userList.size();i++)
        {
                gen.securityLevel=gen.getRandomDoubleBetweenRange(0,0.949);
                gen.x=gen.generateRandomIntIntRange(0,2);
                   
                  String text=gen.userList.get(i)+"\t"+ gen.securityLevel+"\t"+
                   gen.userTypeList.get(gen.x)+"\n";
               
          gen.appendToFile(gen.requester_file,text);
          
        }
        System.out.println("\nrequester.txt\n"+"userList:\nsecurityLevel:\nuserTypeList:\n");
}
}

//next collaboration..