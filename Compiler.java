import java.util.*;
import java.io.*;

class Compiler{
    
    
    public Compiler(){}
    public String tranferFile(File file) throws Exception{
              String content = "";

           Scanner fileScan = new Scanner(file);

         while(fileScan.hasNext()){
             input2 = input2 + " " + fileScan.nextLine() ;
        }
         fileScan.close();
         return input2;
    }
}