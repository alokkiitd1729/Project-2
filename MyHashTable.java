

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */

// Java program to demonstrate implementation of our 
// own hash table with chaining for collision detection 
import java.util.ArrayList; 
  
// A node of chains 
class HashNode<K, V> 
{ 
    K key; 
    V value; 
  
    // Reference to next node 
    HashNode<K, V> next; 
  
    // Constructor 
    public HashNode(K key, V value) 
    { 
        this.key = key; 
        this.value = value; 
    } 
}   
// Class to represent entire hash table 
class MyHashTable<K, V> 
{ 
    // bucketArray is used to store array of chains 
    private ArrayList<HashNode<K, V>> bucketArray; 
  
    // Current capacity of array list 
    private int numBuckets; 
  
    // Current size of array list 
    private int size; 
  
    // Constructor (Initializes capacity, size and 
    // empty chains. 
    public MyHashTable() 
    { 
        bucketArray = new ArrayList<>(); 
        numBuckets = 26; 
        size = 0; 
  
        // Create empty chains 
        for (int i = 0; i < numBuckets; i++) 
            bucketArray.add(null); 
    } 
  
    public int size() { return size; } 
    public boolean isEmpty() { return size() == 0; } 
    // This implements hash function to find index 
    // for a key 
    private int getHashIndex(K key) 
    { 
        int hashCode = key.hashCode(); 
        int index = hashCode % numBuckets; 
        return index; 
    } 
  
    // Method to remove a given key 
    public V remove(K key) 
    { 
        // Apply hash function to find index for given key 
        int bucketIndex = getHashIndex(key); 
  
        // Get head of chain 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
  
        // Search for key in its chain 
        HashNode<K, V> prev = null; 
        while (head != null) 
        { 
            // If Key found 
            if (head.key.equals(key)) 
                break; 
  
            // Else keep moving in chain 
            prev = head; 
            head = head.next; 
        } 
  
        // If key was not there 
        if (head == null) 
            return null; 
  
        // Reduce size 
        size--; 
  
        // Remove key 
        if (prev != null) 
            prev.next = head.next; 
        else
            bucketArray.set(bucketIndex, head.next); 
  
        return head.value; 
    } 
  
    // Returns value for a key 
    public V get(K key) 
    { 
        // Find head of chain for given key 
        int bucketIndex = getHashIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
  
        // Search key in chain 
        while (head != null) 
        { 
            if (head.key.equals(key)) 
                return head.value; 
            head = head.next; 
        } 
  
        // If key not found 
        return null; 
    } 
    public boolean IsMember(K key) {
    	int bucketIndex = getHashIndex(key); 
    	boolean x = false;
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
        while (head != null) 
        { 
            if (head.key.equals(key)) 
            { 
                x= true;
                return x; 
            } 
            head = head.next; 
        } 
        return x;
    }
  
    // Adds a key value pair to hash 
    public void addPositionForWord(K key, V value) 
    { 
        // Find head of chain for given key 
        int bucketIndex = getHashIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
  
        // Check if key is already present 
        while (head != null) 
        { 
            if (head.key.equals(key)) 
            { 
                head.value = value; 
                return; 
            } 
            head = head.next; 
        }  
        // Insert key in chain 
        size++; 
        head = bucketArray.get(bucketIndex); 
        HashNode<K, V> newNode = new HashNode<K, V>(key, value); 
        newNode.next = head; 
        bucketArray.set(bucketIndex, newNode); 
  
        // If load factor goes beyond threshold, then 
        // double hash table size 
        if ((1.0*size)/numBuckets >= 0.7) 
        { 
            ArrayList<HashNode<K, V>> temp = bucketArray; 
            bucketArray = new ArrayList<>(); 
            numBuckets = 2 * numBuckets; 
            size = 0; 
            for (int i = 0; i < numBuckets; i++) 
                bucketArray.add(null); 
  
            for (HashNode<K, V> headNode : temp) 
            { 
                while (headNode != null) 
                { 
                    addPositionForWord(headNode.key, headNode.value); 
                    headNode = headNode.next; 
                } 
            } 
        } 
    }
}
