package JavaHelpers;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

public class Tokenizer
{
  private class TokenInfo
  {
    public final Pattern regex;
    public final int token;

    public TokenInfo(Pattern regex, int token)
    {
      super();
      this.regex = regex;
      this.token = token;
    }
  }
  
  public class Token
  {
    public final int token;
    public final String sequence;
    //public Hash nameTable=new Hash();
    public Token(int token, String sequence)
    {
      super();
      this.token = token;
      this.sequence = sequence;
    }
    
  }

  private LinkedList<TokenInfo> tokenInfos;
  private LinkedList<Token> tokens;
  
  public Tokenizer()
  {
    tokenInfos = new LinkedList<TokenInfo>();
    tokens = new LinkedList<Token>();
  }
  
  public void add(String regex, int token)
  {
    tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
  }
  
  //epistrefei to condition expression gia to naoth
        
//kanw tokenize thn eisodo gia na parw auto pou thelw na dwsw
  //sthn tokenize
  public String tokenizeTrExp(String exp)
  {
	  int size=0,pos=-1;
	  size=exp.length();
	  String result=new String("");
	  
	  if(exp.contains("[") && exp.contains("]"))
	  {
		  //System.out.println("Tokenizer.tokenizeExpr line 62 contains []");
		  pos=exp.indexOf("[");
		  while(exp.charAt(pos)!=']')
		  {
			  pos++;
			  if(exp.charAt(pos)!=']')
			  {
				  result+=exp.charAt(pos);
			  }
		  }		 
	  }
	  return result;
  }
  //To mono restriction einai oxi a==a h b!=b ktl 
  //dhladh oxi epanalhpsh twn idiwn metavlitwn s ena token
  public String buildExpression(String ex)
  {
	  String result=new String(ex);	  
	  int counter=0;
	  int sz=ex.length();
	  LinkedList<String> myToks=new LinkedList<String>();	 
	
	  int pt=0;
	  while(pt<sz)
	  {
		  String tmp2=new String("");		 		  
		  //edw trexw mesa sto string mexri na vrw char kai na mhn einai true h false
		  int ctr=pt;
		  //if(ctr<sz)
		  //{
			  while((ctr<sz)&&(ex.charAt(ctr)!='&')&&(ex.charAt(ctr)!='=')&&(ex.charAt(ctr)!='!')&&(ex.charAt(ctr)!='|')&&(ex.charAt(ctr)!='<')&&(ex.charAt(ctr)!='>'))
			  {
				  //using Ascii values gia to checkarisma
				  //an vrw to prwto stoixeio diatrexw ta alla kai to allazw
				  // to pattern mou
				  if( (ex.charAt(ctr)>=48 && ex.charAt(ctr)<=57)||( ex.charAt(ctr)>=65 && ex.charAt(ctr)<=90)||(ex.charAt(ctr)>=97 && ex.charAt(ctr)<=122) || (ex.charAt(ctr)=='.') )           
				  {	
					  tmp2+=ex.charAt(ctr);				  				  
				  }			  
				  ctr++;
				  pt=ctr-1;
			  }//kleinei h while  		  			  
		  if(tmp2.length()>0) 
		  {   
			  // to prwto tsekarei gia arithmous			  
			  int cr=0;
			  int mySz=0;
			  while(cr<tmp2.length())
			  {
				  if((tmp2.charAt(cr)>=48 && tmp2.charAt(cr)<=57))
				  {
					  mySz++;
				  }
				  cr++;
			  }
			  if(cr==mySz)
			  {
				  return result;
			  }
			  //last prosthiki gia na mhn peirazei ta true false			  
			  if(tmp2.contentEquals("true")||tmp2.contentEquals("false") )
			  {
				  return result;
			  }
			  //prosthiki gia thn teleia
			  
			  if((tmp2.length()==1) && (tmp2.charAt(0)>=48 && tmp2.charAt(0)<=57) )
			  {
				  return result;//+=tmp2;     //an exw mono arithmo
			  }
			  if((tmp2.length()==1) && (tmp2.charAt(0)=='-') )
			  {
				  return result;
			  }
			  //an einai idies oi metavlites px a==a
			  //exei ginei auto pou thelw sto 1 replacement
			  if(myToks.contains(tmp2))
			  {				  
				  return result;
			  }
			//sthn result thelw allagh giati thn ex thn 'trexw'
			  //gia upodiastolh
			  if((tmp2.length()>=3) && (tmp2.charAt(0)>=48 && tmp2.charAt(0)<=57)&&(tmp2.contains(".")) )
			  {
				  return result;
			  }
			  result=result.replace(tmp2,"this->_blk->"+tmp2);
			  myToks.add(tmp2);
			 // System.out.println("The altered ex is "+result);
		  }
		  		 		  
		  pt++;
	  }
	 // System.out.println("Expr in build is "+ex);
	  return result;//result+="BlackBoard::getInstance()."+tmp2;	      
  }
  //check parentheses and trailing operators
  public void checkParentheses(String str)
  {	  	 
	  int sz=str.length();
	  int ctr=0;
	  int rightPar=0;
	  int leftPar=0;
	  while(ctr<sz)
	  {
		  if(str.charAt(ctr)=='(')
		  {
			  rightPar++;
		  }
		  if(str.charAt(ctr)==')')
		  {
			  leftPar++;
		  }
		  ctr++;
	  }
	  int op=str.lastIndexOf("&");
	  int op2=str.lastIndexOf("!=");
	  int op3=str.lastIndexOf("=");
	  int op4=str.lastIndexOf("|");
	  int pos=sz-1;
	 // System.out.println(op4);
	 
	  if((rightPar!=leftPar)||(op==pos)||(op2==pos)||(op3==pos)||(op4==pos) ) 
	  {
		  String s=new String("non valid parentheses or trailing operator");
		  throw new ParserException("Unexpected character in input: "+s);
	  }else
	  {
		 // System.out.println("Parentheses are ok");
	  }
  }
    
  
  public void tokenize(String str)
  {
    String s = str.trim();
    tokens.clear();
    while (!s.equals(""))
    {
      boolean match = false;
      for (TokenInfo info : tokenInfos)
      {
        Matcher m = info.regex.matcher(s);
        if (m.find())
        {
          match = true;
          String tok = m.group().trim();
          s = m.replaceFirst("").trim();
          tokens.add(new Token(info.token, tok));
          break;
        }
      }
      if (!match) throw new ParserException("Unexpected character in input: "+s);
    }
  }
  
  public LinkedList<Token> getTokens()
  {
    return tokens;
  }
  
}
