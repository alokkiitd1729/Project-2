

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */


import java.util.Vector;
public class InvertedPageIndex {
MyHashTable<String,WordEntry> m = new MyHashTable<String,WordEntry>();
MySet<PageEntry> setofpages = new MySet<PageEntry>();
public float orand(String str[],String pagename){
	float d=0;	
	int k = str.length;
	for(int i=0;i<k;i++) {
		d=d + relevance(str[i],pagename);
	}
	return d;
}
public float PhraseQueriesRelevance(String phrase[], String pagename) {
	MySet<PageEntry> msope= getPagesWhichContainPhrase(phrase); //this to know "m"!!
	MySet<String> temp=new MySet<String>(); // set of different pages which contain the phrase
	int m=0;  //no of times phrase occur in this webpage
	int wp=0;    //no of words in this webpage including connector words
	int n=temp.length();  //no of webpages which contain this phrase
	int k=phrase.length;
	for(int i=0;i<msope.length();i++) {
		boolean b=false;
		if(msope.ithelement(i).titleofpage.equals(pagename)) {
			m++;
			wp=msope.ithelement(i).soadw.size();
		}
		for(int j=0;j<temp.length();j++) {     //to prevent duplication
			if(msope.ithelement(i).titleofpage.equals(temp.ithelement(j))) {
				b=true;
			}
		}
		if(b==false) {
			temp.addElement(msope.ithelement(i).titleofpage);
		}
	}
	float d2=(float)Math.log((setofpages.length())/n);  //2nd part of calculation
	float d1=(float)m/(wp-((k-1)*m));           // 1st part of calculation
	float d3=(float)d1*d2;
	return d3;
}
public float relevance(String word,String pagename) {
	float d1=idf(word);
	float d2=0; //for term frequency
	for(int i=0;i<setofpages.length();i++) {
		if(setofpages.ithelement(i).titleofpage==pagename) {
			d2=d2+(float)setofpages.ithelement(i).tf(word);
		}
	}
	return d1*d2;
}

public float idf(String s) {   //inverted page index
	int k1=setofpages.length();
	int x1=0;
	for(int i=0;i<k1;i++) {
		if(setofpages.ithelement(i).soadwwcw.contains(s)==true) {
			  x1++;
		}
	}
	float d1=(float)k1/x1;
	float d= (float)Math.log(d1);
	return d;
}
public MySet<PageEntry> getPagesWhichContainPhrase(String phrase[]) { // not in sorted order yet we'll sort in query
	MySet<PageEntry> somepages=new MySet<PageEntry>();
	for(int i=0;i<setofpages.length();i++) {                //1st loop running for all the webpages available
		
		PageEntry pe=setofpages.ithelement(i);
		Vector<Integer> indices=pe.listofwordindex(phrase[0]);		
		for(int j=0;j<indices.size();j++) {                //2nd loop running for all indices of 1st word of phrase present in the given webpage
			
			int x=phrase.length;
			int y=0;
			for(int k=1;k<phrase.length;k++) {             //3rd loop running for all the word of the phrase except the 0th word
				Position p=new Position(pe,indices.get(j)+k);
				WordEntry we=m.get(phrase[k]);
				if(we.avltree.search(p,pe.titleofpage)) {
					
					y++;
				}
			}
			if(y==x-1) {
				somepages.addElement(pe);
			}
		}
	}
	return somepages;
}
public MySet<PageEntry> getPagesWhichContainAllWords(String str[]) { // we have to provide ordering of pages based on their relevance
	MySet<PageEntry> somepages=new MySet<PageEntry>();
	Vector<String> v=new Vector<String>();
	for(int i=0;i<str.length;i++) {
		v.add(str[i]);
	}
	int k1=setofpages.length();
	for(int i=0;i<k1;i++) {
		if(setofpages.ithelement(i).soadwwcw.containsAll(v)==true) {
			somepages.addElement(setofpages.ithelement(i));
		}
	}
	return somepages;
}
public void addPage(PageEntry p) {
	
	  setofpages.addElement(p);
	   setofpages.reverse();
}
public MySet<PageEntry> getPageWhichContainWord(String str) {
	MySet<PageEntry> somepages=new MySet<PageEntry>();
	
	int k = setofpages.length();
	 
	for(int i = 0;i<k;i++) {
		PageEntry pe = setofpages.ithelement(i);
		if( pe.soadw.contains(str)) {
			somepages.addElement(pe);
		}
	}
	 
	return somepages.reverse();
	 
}

}
