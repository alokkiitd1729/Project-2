public class WordEntry {
 MyLinkedList<Position> ll;
 AVLTree avltree = new AVLTree();
 String s="";
 float f; // have to write formulae for calculating it!! 
 WordEntry(String word) {
         s=s+word;
 }
 public  void addPosition (Position position) {
 	  ll.addElement(position);
	  avltree.insert(position);
 }
 public  void addPositions(MyLinkedList<Position> positions) {
       
 }
 public MyLinkedList<Position> getAllPositionsForThisWord() {
 	return ll;
 }
 public float getTermFrequency(String word) {
     return f;
 }
}