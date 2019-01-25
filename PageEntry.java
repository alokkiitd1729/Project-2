import java . util .*;
import java . io .*;
public class PageEntry {
	Vector<String> soadw=new Vector<String>();
	Vector<String> soadwwcw = new Vector<String>();// without connector words
 String titleofpage;
 PageIndex pi;
 MySet<PageEntry> setofpages1 = new MySet<PageEntry>();
 PageEntry(String pageName) {
      titleofpage = pageName;
 }
 public Vector<Integer> listofwordindex(String word) {  // note we should take indices from list of words without connector words!!
	 
	 Vector<Integer> v1=new Vector<Integer>();
	 for(int i=0;i<soadwwcw.size();i++) {
		
		 if(soadwwcw.get(i).equals(word)) {
			 v1.add(i);
		 }
	 }
	 return v1;
 }
  public Vector<Integer> listofwordindex2(String word) {  // note we should take indices from list of words without connector words!!
	 
	 Vector<Integer> v1=new Vector<Integer>();
	 
	 for(int i=0;i<soadw.size();i++) {
		 if(soadw.get(i).equals(word)) {
			 v1.add(i);
		 }
	 }
	 return v1;
 }
 public double tf(String word) {   //term frequency
	 int k1 = soadw.size();
	 double x=0;
	 for(int i=0;i<soadwwcw.size();i++) {
		 if(soadwwcw.get(i).equals(word)) {
			 x++;
		 }
	 }
	 double d=(double)x/k1;
	 return d;
 }
 public float idf(String s) {   //inverted page index
		int k1=setofpages1.length();
		int x1=0;
		for(int i=0;i<k1;i++) {
			if(setofpages1.ithelement(i).soadwwcw.contains(s)==true) {
				  x1++;
			}
		}
		float d1=(float)k1/x1;
		float d= (float)Math.log(d1);
		return d;
	}
 public float relevance(String word,String pagename) {
		float d1=idf(word);
		float d2=0; //for term frequency
		for(int i=0;i<setofpages1.length();i++) {
			if(setofpages1.ithelement(i).titleofpage==pagename) {
				d2=d2+(float)setofpages1.ithelement(i).tf(word);
			}
		}
		return d1*d2;
	}
 public float orand(String str[],String pagename){
		float d=0;	
		int k = str.length;
		for(int i=0;i<k;i++) {
			d=d + relevance(str[i],pagename);
		}
		return d;
	}
public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase) {
	float f=0;
	if(doTheseWordsRepresentAPhrase==false) {
		f=orand(str,titleofpage);
	}
	return f;
}
 // give set of all distinct words without connector words......
 public void addingposition2() {
	 int k= soadw.size();
	 for(int i = 0;i<k;i++) {
		 String s = soadw.get(i);
	 if(!s.equals("a")&&!s.equals("an")&&!s.equals("the")&&!s.equals("they")&&!s.equals("these")&&!s.equals("this")&&!s.equals("for")&&!s.equals("is")&&!s.equals("are")&&!s.equals("was")&&!s.equals("of")&&!s.equals("or")&&!s.equals("and")&&!s.equals("does")&&!s.equals("will")&&!s.equals("whose")) {
			 soadwwcw.addElement(s);
		}
	 }
 }
  public Vector<Integer> listofwordindex3(String word) {  // note we should take indices from list of words without connector words!!
	 Vector<Integer> v1=new Vector<Integer>();
	 for(int i=0;i<soadw.size();i++) {
		 if(soadw.get(i).equals(word) && i<140) {
			 v1.add(i+1);
		 }
		 else if(soadw.get(i).equals(word) && i>140) {
			 v1.add(i);
		 }
	 }
	 return v1;
 }
  public void addingposition() {
     try {
    	 // put all the words in myset of this particular webpage
  	   // considering punctuation marks as SPACE....
  	   FileInputStream fstream = new FileInputStream ("./webpages/"+titleofpage);//NOTE HERE WE HAVE TO PUT ADDRESS OF THE FILE INSTEAD OF "S"
  	   Scanner s1 = new Scanner ( fstream );
  	   Vector < String > v1 = new Vector < String >();
  	   while ( s1 . hasNextLine ()){
  	       v1.add(s1 . nextLine ());
  	   }
  	   s1.close();
  	   int k = v1.size();
         String temp = "";
  	   for(int i=0;i<k;i++){
  	       int k1 = (v1.get(i)).length();
  	       for(int j =0;j<k1;j++){
  	    	   char c= (v1.get(i)).charAt(j);
  	    	  if(j==k1-1&&c!='.') {
				  temp = temp + Character.toString(c);
  	  	    	 String str=temp.toLowerCase();
  	 	    	  soadw.add(str);
  	 	    	  temp = "";
  	  	      }
  	    	  else if(c!=' '&&c!='{'&&c!='}'&&c!='['&&c!=']'&&c!='<'&&c!='>'&&c!='='&&c!='('&&c!=')'&&c!='.'&&c!=','&&c!=';'&&c!="'".charAt(0)&&c!='"'&&c!='?'&&c!='#'&&c!='!'&&c!='-'&&c!=':') {
  	    	    temp = temp + Character.toString(c);;
  	      }
  	     
  	    	  else if(c==' '||c=='{'||c=='}'||c=='['||c==']'||c=='<'||c=='>'||c=='='||c=='('||c==')'||c=='.'||c==','||c==';'||c=="'".charAt(0)||c=='"'||c=='?'||c=='#'||c=='!'||c=='-'||c==':') {
  	    	 String str=temp.toLowerCase();
  	    	  if(str.equals("")==false){
				  soadw.add(str);
			  }
  	    	  temp = "";
  	      }
  	    
  	      }
  	   }   // punctuation converted into spaces....
  	   // added wordentry to ll of pageindex  and position to each wordentry of words
  	   int k2 = soadw.size();
  		PageEntry pe = new PageEntry(titleofpage);
  	        for(int i = 0;i<k2;i++) {
  	          Position p = new Position(pe,i);
  	          pi.addPositionForWord(soadw.get(i), p);
  	            }
     } catch(Exception e) {
  	    System.out.println("");
     }
 }

 public PageIndex getPageIndex() {
 	return pi;
 }
}

