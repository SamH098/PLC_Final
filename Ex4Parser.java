import com.unisa.ptd.lexer.Lexer;
import com.unisa.ptd.sd.Token;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Ex4Parser {

    private List<Token> tokenList;
    private Lexer lexer;
    private int index;
    private InputStream fr;

    public unParser(String fileName) throws IOException {
        tokenList = new ArrayList<>();
        lexer = new Lexer();
        fr = new BufferedInputStream(new FileInputStream(fileName));
        index = 0;
    }

    public boolean Code() throws IOException {
        boolean trve = false;
        if(stmt()){
            trve = (SCode())?true:false;
        }
        fr.close();
        return  trve;
    }

    public boolean SCode() throws IOException {
        boolean trve = false;
        int back = index;
        this.tokenList.add(lexer.nextToken(fr));
        if(tokenList.get(index).getLessema().equals("SEMICOL")){
            index++;
            if(stmt()){
                if(SCode()){
                    trve = true;
                }else index = back; 
           }else index = back;
        }else trve = true; 
        return  trve;
    }

    public boolean stmt() throws IOException{
        boolean trve = false;
        int back = index;
        this.tokenList.add(lexer.nextToken(fr));
        if(this.tokenList.get(index).getClass1().equals("IF")){
            index++;
            if(expr()){
                this.tokenList.add(lexer.nextToken(fr));
                if(this.tokenList.get(index).getClass1().equals("THEN")){
                    index++;
                    if(stmt()){
                        trve = true;
                    }else index = back;
                }else index = back;
            }else index = back;
        }else{
            if(tokenList.get(index).getClass1().contains("ID:")){
                index++;
                this.tokenList.add(lexer.nextToken(fr));
                if(this.tokenList.get(index).getLessema().equals("ASSIGN")){
                    index++;
                    if(expr()){
                        trve = true;
                    }else index = back;
                }else index = back;
            } else index = back;
        }
        return  trve;
    }

    public boolean expr() throws IOException {
        boolean trve = false;
        int back = index;
        if(term()){
            if(expr1()){
                trve = true;
            }else index = back;
        }else index = back;
        return  trve;
    }

    public boolean expr1() throws IOException {
        boolean trve = false;
        int back = index;
        this.tokenList.add(lexer.nextToken(fr));
        if(tokenList.get(index).getClass1().equals("RELOP")){
            index++;
            if(term()){
                trve = true;
            }else index = back;
        }else trve = true;  
        return  trve;
    }

    public boolean term() throws IOException{
        boolean trve = false;
        this.tokenList.add(lexer.nextToken(fr));
        String class1 = tokenList.get(index).getClass1();
        if(class1.contains("ID:") ||
           class1.contains("CONST") ||
           class1.equals("TRUE") ||
           class1.equals("FALSE")) {
            index++;
            trve = true;
        }
        return  trve;
    }
}