

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */


import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;
public class SearchEngine extends MySort{
	Vector<String>soadw1;
    InvertedPageIndex ipix;
SearchEngine() {
	ipix = new InvertedPageIndex();
}
public void addPage(String titleofpage) {  // 1st Action............

	PageEntry pe = new PageEntry(titleofpage);
	pe.addingposition();
	pe.addingposition2();
	ipix.addPage(pe);
	for(int i=0;i<ipix.setofpages.length();i++) {
		ipix.setofpages.ithelement(i).setofpages1=ipix.setofpages;
	}
	addallwordstoipix(titleofpage);
	
}
	// method for adding all the (string(word),wordentry) pair to hashmap  of invertedpageindex
public void addallwordstoipix(String titleofpage) {	
	   // put all the words in myset of this particular webpage
	   // considering punctuation marks as SPACE....
	try {
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
  	 	    	  soadw1.add(str);
  	 	    	  temp = "";
  	  	      }
  	    	  else if(c!=' '&&c!='{'&&c!='}'&&c!='['&&c!=']'&&c!='<'&&c!='>'&&c!='='&&c!='('&&c!=')'&&c!='.'&&c!=','&&c!=';'&&c!="'".charAt(0)&&c!='"'&&c!='?'&&c!='#'&&c!='!'&&c!='-'&&c!=':') {
  	    	    temp = temp + Character.toString(c);;
  	      }
  	     
  	    	  else if(c==' '||c=='{'||c=='}'||c=='['||c==']'||c=='<'||c=='>'||c=='='||c=='('||c==')'||c=='.'||c==','||c==';'||c=="'".charAt(0)||c=='"'||c=='?'||c=='#'||c=='!'||c=='-'||c==':') {
  	    	 String str=temp.toLowerCase();
  	    	  soadw1.add(str);
  	    	  temp = "";
  	      }
  	    
  	      }
  	   } 
// now taking words back from set and adding to hash map of ipix as (string,wordentry) pair...
		   int k3 = soadw1.size();
		   boolean check1 = false;
		   for(int i = 0;i<k3;i++) {
			   String s2 = soadw1.get(i);
			   if(ipix.m.IsMember(s2)==true) {
				   WordEntry we = ipix.m.get(s2);
				   PageEntry pe1 = new PageEntry(titleofpage);
				   Position po = new Position(pe1,i);
				   we.addPosition(po);
				   check1 = true;
			   }
			   if(check1==false){
				   WordEntry we1 = new WordEntry(s2);
				   PageEntry pe1 = new PageEntry(titleofpage);
				   Position po = new Position(pe1,i);
				   we1.addPosition(po);
				   ipix.m.addPositionForWord(s2,we1);
			   }
		   }
	}
	   catch(Exception e) {
		   System.out.print("");
	   }	
}
public String queryFindPagesWhichContainWord(String x) {  // 2nd Action..........
	      MySet<PageEntry> mpe = ipix.getPageWhichContainWord(x);
	      SearchResult[] sr = new SearchResult[mpe.length()];
	  if(mpe.length()>0) {
		String s= "";
		for(int i = 0;i<mpe.length();i++) {
			float relevance = ipix.relevance(x,mpe.ithelement(i).titleofpage);
			PageEntry pe = mpe.ithelement(i);
			SearchResult sr1 = new SearchResult(pe,relevance);
			sr[i]=sr1;
		}
		ArrayList<SearchResult> temp1 = sortThisList1(sr);
		for(int i=0;i<temp1.size();i++) {
			  float relevance = temp1.get(i).getRelevance();
			  s=s+ temp1.get(i).getPageEntry().titleofpage + " "+Float.toString(relevance)+", ";
		  }
		//System.out.println(s.substring(0,(s.length()-2)));
		return s.substring(0,(s.length()-2));	
	 }
	  if(mpe.length()==0) {
		  //System.out.println("No webpage contains word " + x);
		  return "No webpage contains word " + x; 
	  }
	  return "";
}
  public String queryFindPositionsOfWordInAPage(String x , String y) { //3rd action
	  MySet<PageEntry> mi = ipix.setofpages;
	  boolean checky = false;
	  boolean checkyx = false;
	  String s = "";
	 for(int i = 0;i<mi.length();i++) {
         if(mi.ithelement(i).titleofpage.equals(y) ) {
			 checky = true;
		 }
		 if(mi.ithelement(i).titleofpage.equals(y) && mi.ithelement(i).soadw.contains(x)) {
			  int h = mi.ithelement(i).listofwordindex3(x).size();
			 for(int k=0;k<h;k++) {
				 s=s+ Integer.toString(mi.ithelement(i).listofwordindex3(x).get(k))+", ";
			 }
			 checkyx = true;
			 return s.substring(0,(s.length()-2));
		 }
	 }
	 if(checky==false ) {
		 //System.out.println("No webpage "+y+" found");
		 return ("No webpage "+y+" found");
	 }
	 else if(checky == true && checkyx==false ) {
		 //System.out.println("Webpage "+y+" does not contain word "+x);
		 return ("Webpage "+y+" does not contain word "+x);
		 
	 }
	 return "";
  }
   public boolean ismember2(PageEntry p,String str[]) {
	  int x=0;
	  int y=p.soadw.size();
	  for(int j=0;j<str.length;j++) {
		  boolean b = false;
		  for(int k=0;k<y;k++) {
			  if(p.soadw.get(k).equals(str[j])) {
				  b=true;
			  }
		  }
		  if(b==true) {
			  x++;
		  }
	  }
	  if(x!=str.length) {
		  return false;
	  }
	  return true;
  }
   public MySet<PageEntry> getPagesWhichContainAllWords(String str[]) { // we have to provide ordering of pages based on their relevance
	  MySet<PageEntry> mi = ipix.getPageWhichContainWord(str[1]);
		MySet<PageEntry> temp = new MySet<PageEntry>();
		for(int i=0;i<mi.length();i++) {
			if(ismember2(mi.ithelement(i),str)==true) {
				temp.addElement(mi.ithelement(i));
			}
		}
		return temp;
	}
	  public ArrayList<SearchResult> sortThisList1(SearchResult l[]) {
		ArrayList<SearchResult> al=new ArrayList<SearchResult>();
		int k=l.length;
		for(int i=0;i<k;i++) {
			for(int j=0;j<k-1-i;j++) {
				if(l[j].getRelevance()>l[j+1].getRelevance()) {
					SearchResult temp1 = l[j];
					l[j]=l[j+1];
					l[j+1]=temp1;
				}
			}
			al.add(l[k-i-1]);
		}
		return al;
	}

 public String queryFindPagesWhichContainAllWords(String str[]) {   //4th Action
	  String s="";
	  MySet<PageEntry> mpe = getPagesWhichContainAllWords(str);
	  SearchResult[] sr = new SearchResult[mpe.length()];
	  for(int i=0;i<mpe.length();i++) {
		  float relevance = ipix.orand(str, mpe.ithelement(i).titleofpage);
		  SearchResult temp = new SearchResult(mpe.ithelement(i),relevance);
		  sr[i]=temp;
	  }
	  ArrayList<SearchResult> temp1 = sortThisList1(sr);
	  
	  for(int i=0;i<temp1.size();i++) {
		  float relevance = temp1.get(i).getRelevance();
		  s=s+ temp1.get(i).getPageEntry().titleofpage + " "+Float.toString(relevance)+", ";
	  }
	  return s.substring(0,(s.length()-2));
  }
 
   public boolean ismember(Vector<String> v,String s) {
	  boolean b = false;
	  for(int i=0;i<v.size();i++) {
		  if(v.get(i).equals(s)) {
			  b=true;
		  }
	  }
	  return b;
  }
  public boolean ismember3(Vector<PageEntry> v,PageEntry s) {
	  boolean b = false;
	  for(int i=0;i<v.size();i++) {
		  if(v.get(i).titleofpage.equals(s.titleofpage)) {
			  b=true;
		  }
	  }
	  return b;
  }
 public boolean ismember4(MySet<PageEntry> v,PageEntry s) {
	  boolean b = false;
	  for(int i=0;i<v.length();i++) {
		  if(v.ithelement(i).titleofpage.equals(s.titleofpage)) {
			  b=true;
		  }
	  }
	  return b;
  }
   public String queryFindPagesWhichContainPhrase22(String str[]) {  // 6th Action
	  String s = "";
	  MySet<PageEntry> mp = new MySet<PageEntry>();
	  MySet<PageEntry> mpe = ipix.getPageWhichContainWord(str[0]);
	  MySet<PageEntry> mpe1 = ipix.getPageWhichContainWord(str[1]);
	  for(int i=0;i<mpe.length();i++) {
		  if(ismember4(mpe1,mpe.ithelement(i))==true) {
			  mp.addElement(mpe.ithelement(i));
		  }
	  }
	   for(int k=0;k<mp.length();k++) {
		  s=s+ mp.ithelement(k).titleofpage+", ";
	  }
	  return s.substring(0,(s.length()-2));
  }
  public Vector<String> removeduplicate(Vector<String> m) {
	  Vector<String> temp= new Vector<String>();
	  temp.add(m.get(0));
	  for(int i=1;i<m.size();i++) {
		 if(ismember(temp,m.get(i))==false) {
			 temp.add(m.get(i));
		 }
	  }
	  return temp;
  }
   public Vector<PageEntry> removeduplicate2(Vector<PageEntry> m) {
	  Vector<PageEntry> temp= new Vector<PageEntry>();
	  temp.add(m.get(0));
	  for(int i=1;i<m.size();i++) {
		 if(ismember3(temp,m.get(i))==false) {
			 temp.add(m.get(i));
		 }
	  }
	  return temp;
  }
 
  
   public String queryFindPagesWhichContainAnyOfTheseWords(String str[]) {  // 5th Action
	  String s = "";
	  Vector<String> v=new Vector<String>();
	  Vector<PageEntry> v2=new Vector<PageEntry>();
	   for(int i=0;i<str.length;i++) {
		   MySet<PageEntry> mi = ipix.getPageWhichContainWord(str[i]);
		   for(int j=0;j<mi.length();j++) {
			   v2.add(mi.ithelement(j));
			   v.add(mi.ithelement(j).titleofpage);
		   }
	   }
	   Vector<PageEntry> v3=removeduplicate2(v2);
	  // Vector<String> v1=removeduplicate(v);
	   SearchResult[] sr = new SearchResult[v3.size()];
	   for(int i=0;i<v3.size();i++) {
			  float relevance = ipix.orand(str, v3.get(i).titleofpage);
			  SearchResult temp = new SearchResult(v3.get(i),relevance);
			  sr[i]=temp;
		  }
	   ArrayList<SearchResult> temp1 = sortThisList1(sr);
	   for(int i=0;i<temp1.size();i++) {
			  float relevance = temp1.get(i).getRelevance();
			  s=s+ temp1.get(i).getPageEntry().titleofpage + " "+Float.toString(relevance)+", ";
		  }
		  return s.substring(0,(s.length()-2)); 
  }
  public String queryFindPagesWhichContainPhrase(String str[]) {  // 6th Action
	  String s = "";
	  MySet<SearchResult> msr = new MySet<SearchResult>();
	  MySet<PageEntry> mpe = ipix.getPagesWhichContainPhrase(str);
	  for(int i=0;i<mpe.length();i++) {
		  PageEntry pe2 = mpe.ithelement(i);
		  float f2 = ipix.PhraseQueriesRelevance(str,pe2.titleofpage) ; // NOT OR/AND relevance..phrase relevance
		  SearchResult sr2 = new SearchResult(pe2,f2);
		  msr.addElement(sr2);
	  }
	  ArrayList<SearchResult> temp2 = sortThisList(msr);
	  for(int j=0;j<temp2.size();j++) {
		  s = s + temp2.get(j).getPageEntry().titleofpage + ", "; 
	  }
	   for(int j=0;j<mpe.length();j++) {
		  s = s + mpe.ithelement(j).titleofpage + ", "; 
	  }
	  return s.substring(0,(s.length()));
  }

public void performAction(String actionMessage) {
	
	 String[] partial=actionMessage.split(" ");
	 String x1 = partial[0];
		if( actionMessage.length()>=1 && x1.equals("addPage")) {    //addPage
    		 
      			String x = partial[1];
   			 addPage(x);
			
			 
    	   }
		if( actionMessage.length()>=1 && x1.equals("queryFindPagesWhichContainWord")) {    //queryFindPagesWhichContainWord
 		  
   			String x = partial[1].toLowerCase();
			
   			if(x.equals("stack")||x.equals("stacks")) {
				
   				System.out.println(queryFindPagesWhichContainWord("stack") );
				
   			}
			
   			else if(x.equals("structure")||x.equals("structures")) {
				
   				System.out.println(queryFindPagesWhichContainWord("structure") + queryFindPagesWhichContainWord("structures"));
				
   			}
			
   			else if(x.equals("application")||x.equals("application")) {
   				System.out.println(queryFindPagesWhichContainWord("application") + queryFindPagesWhichContainWord("applications"));
				
   			}
			
   			else {
			
   				System.out.println(queryFindPagesWhichContainWord(x));
				
   			}
 	   }
		if( actionMessage.length()>=1 && x1.equals("queryFindPositionsOfWordInAPage")) {    //queryFindPositionsOfWordInAPage
	 		 
	   			String x = partial[1].toLowerCase();
	   			String y = partial[2];
	   			if(x.equals("stack")||x.equals("stacks")) {
	   				System.out.println(queryFindPositionsOfWordInAPage("stack",y) + queryFindPositionsOfWordInAPage("stacks",y));
	   			}
	   			else if(x.equals("structure")||x.equals("structures")) {
	   				System.out.println(queryFindPositionsOfWordInAPage("structure",y) + queryFindPositionsOfWordInAPage("structures",y));
	   			}
	   			else if(x.equals("application")||x.equals("application")) {
	   				System.out.println(queryFindPositionsOfWordInAPage("application",y) + queryFindPositionsOfWordInAPage("applications",y));
	   			}
	   			else {
	   				System.out.println(queryFindPositionsOfWordInAPage(x,y));
	   			}
	 	   }
          
		  	if(actionMessage.length()>=1 && x1.equals("queryFindPagesWhichContainAllWords")) {
				String partial1[]=new String[partial.length-1];
				for(int i=1;i<partial.length;i++) {
					partial1[i-1]=partial[i];
				}
				System.out.println(queryFindPagesWhichContainAllWords(partial1));
			}
			if(actionMessage.length()>=1 && x1.equals("queryFindPagesWhichContainAnyOfTheseWords")) {
				String partial1[]=new String[partial.length-1];
				for(int i=1;i<partial.length;i++) {
					partial1[i-1]=partial[i];
				}
				System.out.println(queryFindPagesWhichContainAnyOfTheseWords(partial1));
			}
			if(actionMessage.length()>=1 && x1.equals("queryFindPagesWhichContainPhrase")) {
				String partial1[]=new String[partial.length-1];
				for(int i=1;i<partial.length;i++) {
					partial1[i-1]=partial[i];
				}
				System.out.println(queryFindPagesWhichContainPhrase22(partial1));
			}
	
}
}
