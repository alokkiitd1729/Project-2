

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */

public class Position {
PageEntry p ;
int wordindex;
Position(PageEntry x , int k) {
	p=x;
	wordindex=k;
}
public  PageEntry getPageEntry() {
        return p;
}
public  int getWordIndex() {
	return wordindex;
}
}
