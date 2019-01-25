

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */


import java.util.*;
public class MySort {
	public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries) {
		ArrayList<SearchResult> al=new ArrayList<SearchResult>();
		int k=listOfSortableEntries.length();
		for(int i=0;i<k-1;i++) {
			for(int j=0;j<k-1-i;j++) {
				if(listOfSortableEntries.ithelement(j).compareTo(listOfSortableEntries.ithelement(j+1)) == -1) {
					listOfSortableEntries.swapconsicutive(j);
				}
			}
			al.add(listOfSortableEntries.ithelement(k-1));
		}
		return al;
	}
}


