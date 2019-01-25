

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */


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
