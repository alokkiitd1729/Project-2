public class PageIndex {
MyLinkedList<WordEntry> ll;
//MyHashTable wwe = new MyHashTable<String,WordEntry>();
int k = ll.length();

public void addPositionForWord(String str , Position p) {
	boolean pergence = false;
	 for(int i = 0; i<k;i++) {
		 if(ll.ithelement(i).s==str) {
			 ll.ithelement(i).addPosition(p);
			 pergence = true;
		 }
	 }
     if(pergence==false) {
    	 WordEntry w = new WordEntry(str);
    	 ll.addElement(w);
      	 w.addPosition(p);
     }
} 
public MyLinkedList<WordEntry> getWordEntries() {
	return ll;
}
}