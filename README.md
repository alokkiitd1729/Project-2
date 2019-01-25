
   ## Contribution
          Please contribute to this repository to help it make better. Any change like new question, 
          code improvement, doc improvement etc. is very welcome. Just send me a pull request and 
          I will review the request and approve it if it looks good.



# Project-2
##  "Search Engine"


we are going to build the basic data structure underlying search
engines: an inverted index. We will use this inverted index to answer some
simple search queries.

##  An inverted index for a set of webpages
Suppose we are given a set of webpages W. For our purposes, each webpage
w 2 W will be considered to be a sequence of words w1;w2; : : :wk. Another
way of representing the webpage could be to maintain a list of words along
with the position(s) of the words in the webpage. For example consider a
web page with the following text:

Data structures is the study of structures for storing data.
This can be represented as
f(data : 1; 10); (structures : 2; 7); (study : 5); (storing : 9)g:

Note that the small connector words like \is", \the", \of", \for" have not
been stored. Words like this are referred to as stop words and are generally
removed since they are very frequent and normally contain no information
about the content of the webpage.
This representation of the webpage is similar to the index we see at the
back of many books which tell us the page numbers where certain important
terms used in the book may be found. In fact, we can refer to this as an
index for the webpage. In mathematical notation we would say that given a
webpage w = w1;w2; : : : ;wk, the index of w is
f(u : i1(u); : : : i`(u)) : wij (u) = u; 1 <= j <=l
An index is used to and the location of a particular string (word) in a
specific document or webpage, but when we move to a collection of webpages,
we need to first figure out which of the web pages contain the string. For this
we store an inverted index. Let us try to define an inverted index formally.
Let us suppose we are given a collection C of webpages. For each page
p 2 C, let us denote by W(p) the set of all words (excluding stop words)
that occur in p. Note that
                        W(C) = Union(W(p)); p belongs to C
                        
  is the set of all words in our collection.
An inverted index for C will contain an entry for each word w 2 W(C).
This entry will contain tuples of the form (p; k) to indicate that w occurs in
the kth position of page p 2 C. Using the notation that p[k] denotes the kth
word of page p, we can say that the inverted index of C is defined as
Inv(C) = f(w : f(p; k) : p 2 C; p[k] = wg) : w 2 W(C).

##  The web search problem
The web search problem is defined as follows:
Given a collection of webpages C and a sequence of words q1 : : : qk,
and the \most relevant" set of pages p1; p2; : : : p` that contain
as many of q1 : : : qk as possible and return them in the order of
decreasing \relevance."
The question of how to measure the relevance of a webpage to a particular
query is an involved question with no easy answers. However, for the purpose
of this assignment we will work with a simple scoring function.

##   A scoring function for search term relevance
One of the simplest scoring function is term frequency-inverse document
frequency. It is used to measure how important a word w is to a webpage
p. It is a product of two factors i.e. term frequency and inverse document
frequency. Given a word w and a webpage p, the relevance score is dened
as
##        relevancew(p) = tfw(p)*idfw(p)

Term Frequency: It is the total number of occurrence of a word w in a
webpage p, denoted by fw(p). It is normalized by the total number of words
in webpage p, denoted by jW(p)j. It is defined as
##                 tfw(p) =fw(p)/|W(p)|
Inverse document frequency: It is the logarithm of the total number of web-
pages, denoted by N divide by the logarithm of the number of webpages
nw(p) that contain the word w. It is defined as
##                 idfw(p) = log(N/nw(p))

So, if we are given a search query that has a single term, say w, to
return the webpages in order of relevance we have to rst extract the entry
corresponding to w from Inv(C) and then calculate the relevance of each page
and return the pages in decreasing order of relevance.

##  Compound searches
In this assignment we will answer three kinds of search queries: AND queries,
OR queries and phrase queries. We now describe these three along with their
scoring methodology.
 OR queries: Given a search query q1 : : : qk, any page that contains any
of the words q1 to qk is a valid answer. The relevance score of a page p
is computed as
##      relevance q1:::qk(p) = Sum(relevanceqi(p))   1<=i<=k;
and pages are returned in decreasing order of relevance. Note that if
some qi does not occur in page p the relevanceqi(p) = 0.
 AND queries: Given a search query q1 : : : qk, any page that contains
all of the words q1 to qk is a valid answer. The relevance score of a page
p is computed as
##       relevanceq1:::qk(p) = Sum(relevanceqi(p))     1<=i<=k;;
and pages are returned in decreasing order of relevance.

 Phrase queries : Given a search query q1 : : : qk, any page that contains
q1 in position `, q2 in position `+1 and so on till qk in position `+k􀀀1
is said to contain the phrase q1 : : : qk at the position `. Suppose in a
webpage p having jW(p)j words, the phrase q1 : : : qk occurs m times
then the relevance score of page p for this phrase is
##   relevanceq1:::qk(p) = tfq1:::qk(p)*idfq1:::qk(p)
                     
##                    =    {m/|W(p)|-(k-1)*m}*{log(N/nw(p))}



##                 class MySet
 Write a Java class MySet using Java generic's
(https://docs.oracle.com/javase/tutorial/java/generics/types.html).
The class should be represented as MySet<X> where X is the datatype
of the set. MySet should implement the following methods:
- void addElement(X element): Add element to the set.
- MySet<X> union(MySet<X> otherSet): Return MySet which
represents a union of the current set and the otherSet.
- MySet<X> intersection(MySet<X> otherSet): Return My-
Set which represents an intersection of the current set and the
otherSet.
  
  ##            class MyLinkedList
 Write a Java class MyLinkedList using Java generic's. It should contain
the standard methods of a linked list.

##                class Position
 Write a Java class Position that represents a tuple <page p, word
position i>.
- Position(PageEntry p, int wordIndex) Constructor method.
- PageEntry getPageEntry() Return p
- int getWordIndex() Return wordIndex
  
  ##          class WordEntry
 Write a Java class WordEntry. For a string str, this class stores the list
of word indice's where str is present in the document(s).
  - WordEntry(String word): Constructor method. The argument
is the word for which we are creating the word entry.
- void addPosition(Position position): Add a position entry
for str.
- void addPositions(MyLinkedList<Position> positions): Add
multiple position entries for str.
- MyLinkedList<Position> getAllPositionsForThisWord(): Re-
turn a linked list of all position entries for str.
- float getTermFrequency(String word): Return the term fre-
quency of the word in a webpage.
  
  ##        class PageIndex
   Write a Java class PageIndex which stores one word-entry for each
unique word in the document.
- void addPositionForWord(String str, Position p): Add po-
sition p to the word-entry of str. If a word entry for str is already
present in the page index, then add p to the word entry. Other-
wise, create a new word-entry for str with just one position entry
p.
- LinkedList<WordEntry> getWordEntries(): Return a list of
all word entries stored in the page index.
  
  ##            class PageEntry
 Write a Java class PageEntry to store the the information related to a
webpage. It should contain following methods:
- PageEntry(String pageName): Constructor method. The argu-
ment is the name of the document. Read this le, and create the
page index.
- PageIndex getPageIndex(): This method returns the page in-
dex of this web-page.

 ##               class MyHashTable
 Write a Java class MyHashTable that implements the hashtable used
by the InvertedPageIndex. It maps a word to its word-entry.
- private int getHashIndex(String str): Create a hash func-
tion which maps a string to the index of its word-entry in the
hashtable. The implementation of hashtable should support chain-
ing.
- void addPositionsForWord(WordEntry w): This adds an entry
to the hashtable: stringName(w) -> positionList(w). If no word-
entry exists, then create a new word entry. However, if a word-
entry exists, then merge w with the existing word-entry.

  ##          class InvertedPageIndex
 Write a Java class InvertedPageIndex which contains the following
methods:
- void addPage(PageEntry p): Add a new page entry p to the
inverted page index.
- MySet<PageEntry> getPagesWhichContainWord(String str):
Return a set of page-entries of webpages which contain the word
str.
  
  ##         class SearchEngine
 Write a Java class SearchEngine. This is the class that we will use as
an interface to the search engine. It should contain following methods:
- SearchEngine(): This is the constructor method. It should cre-
ate an empty InvertedPageIndex.
- void performAction(String actionMessage): This the main
stub method that you have to implement. It takes an action as
a string. The list of actions, and their format will be described
later.
