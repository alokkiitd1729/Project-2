 /* Class AVLNode */
 class AVLNode
 {    
     AVLNode left, right;
     Position data;
     int height;
     /* Constructor */
     public AVLNode()
     {
         left = null;
         right = null;
         data = null;
         height = 0;
     }
     /* Constructor */
     public AVLNode(Position p)
     {
         left = null;
         right = null;
         data = p;
         height = 0;
     }     
 } 
 /* Class AVLTree */
 public class AVLTree
 {
     private AVLNode root;     
     /* Constructor */
     public AVLTree()
     {
         root = null;
     }
     /* ((1))Function to insert data */
     public void insert(Position data)
     {
         root = insert(data, root);
     }
     // ###################################################################################################
     /* Function to get height of node */
     private int height(AVLNode t )
     {
         return t == null ? -1 : t.height;
     }
     /* Function to max of left/right node */
     private int max(int lhs, int rhs)
     {
         return lhs > rhs ? lhs : rhs;
     }
     /* Function to insert data recursively */
     private AVLNode insert(Position x, AVLNode t)
     {
         if (t == null)
             t = new AVLNode(x);
         else if (x.wordindex <= t.data.wordindex)
         {
             t.left = insert( x, t.left );
             if( height( t.left ) - height( t.right ) == 2 )
                 if( x.wordindex < t.left.data.wordindex )
                     t = Rightrotation( t );
                 else
                     t = leftrightrotation( t );
         }
         else if( x.wordindex > t.data.wordindex )
         {
             t.right = insert( x, t.right );
             if( height( t.right ) - height( t.left ) == 2 )
                 if( x.wordindex > t.right.data.wordindex)
                     t = Leftrotation( t );
                 else
                     t = rightleftrotation( t );
         }
         else
           ;  // Duplicate; do nothing
         t.height = max( height( t.left ), height( t.right ) ) + 1;
         return t;
     }
     /* Rotate left (current node becomes left child of
      *  it's right child and left child of it's right child becomes it's left child)*/     
     private AVLNode Rightrotation(AVLNode k2)
     {
         AVLNode k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
         k1.height = max( height( k1.left ), k2.height ) + 1;
         return k1;
     }
     /* Rotate binary tree node with right child */
     private AVLNode Leftrotation(AVLNode k1)
     {
         AVLNode k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
         k2.height = max( height( k2.right ), k1.height ) + 1;
         return k2;
     }
     /**
      * Double rotate binary tree node: first left child
      * with its right child; then node k3 with new left child */
     private AVLNode leftrightrotation(AVLNode k3)
     {
         k3.left = Leftrotation( k3.left );
         return Rightrotation( k3 );
     }
     /**
      * Double rotate binary tree node: first right child
      * with its left child; then node k1 with new right child */      
     private AVLNode rightleftrotation(AVLNode k1)
     {
         k1.right = Rightrotation( k1.right );
         return Leftrotation( k1 );
     }
     // ###################################################################################################
     /*((2)) Functions to search for an element */
     public boolean search(Position val, String pagename)
     {
         return search(root, val, pagename);
     }
     private boolean search(AVLNode r, Position val, String pagename) 
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.data.wordindex;
             if (val.wordindex < rval) {
                 r = r.left;
             }
             else if (val.wordindex > rval) {
                 r = r.right;
             }
            else if (val.wordindex==rval && val.getPageEntry().titleofpage.equals(pagename)==false) {
            	 r = r.left;
             }
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val, pagename);
         }
         return found;
     }     
 }
 
 