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
