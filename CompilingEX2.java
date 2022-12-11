import java.util.*;
import java.io.*;

public class CompilingEX2{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
         
        System.out.print("Please input file name: ");
        String fileinput = sc.next();
         
        File file = new File(fileinput);
        Compiler entry = new Compiler();

       
        String input = entry.convertFile(file);
        System.out.println("The file is: " + input); 
    }
}
