package dataStructures;

/**
 * BinarySearchTree implementation
 * @author AED team
 * @version 1.0
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value 
 */
public class BinarySearchTree<K extends Comparable<K>, V> 
    implements OrderedDictionary<K,V>
{

	/**
	 * BST node implementation
	 * 
	 * @author AED team
	 * @version 1.0
	 *
	 * @param <K> Generic type Key
	 * @param <V> Generic type Value 
	 */
	static class BSTNode<K,V> {                                                                   

	    //Entry stored in the node.
	    private EntryClass<K,V> entry;                                      

	    //(Pointer to) the parent node.
	    private BSTNode<K,V> parent;
	    
	    //(Pointer to) the left child.
	    private BSTNode<K,V> leftChild;

	    //(Pointer to) the right child.
	    private BSTNode<K,V> rightChild;


	    /**
	     * Constructor for BST nodes
	     * 
	     * @param key to be stored in this BST tree node
	     * @param value to be stored in this BST tree node
	     * @param left sub-tree of this node
	     * @param right sub-tree of this node
	     */
	    public BSTNode( K key, V value, BSTNode<K,V> parent,
	    		BSTNode<K,V> left, BSTNode<K,V> right )
	    {                                                                
	        entry = new EntryClass<>(key, value);
	        this.parent = parent;
	        leftChild = left; 
	        rightChild = right;                                      
	    }


	    /**
	     * Constructor for BST nodes
	     * 
	     * @param key to be stored in this BST tree node
	     * @param value to be stored in this BST tree node
	     */
	    public BSTNode( K key, V value )
	    {    
	        this(key, value, null, null, null);
	    }


	    /**
	     * Returns the parent node of the current node.
	     */
	    public BSTNode<K,V> getParent( )                                     
	    {    
	        return parent;
	    }
	    
	    /**
	     * Returns the left child node of the current node.
	     */
	    public BSTNode<K,V> getLeft( )                                     
	    {    
	        return leftChild;
	    }


	    /**
	     * Returns the right child node of the current node.
	     */
	    public BSTNode<K,V> getRight( )                                    
	    {    
	        return rightChild;
	    }
	    
	    /**
	     * Returns the entry (key and value) of the current node.
	     */
	    public EntryClass<K,V> getEntry( )                           
	    {
	        return entry;
	    }


	    /**
	     * Returns the key of the current node.
	     */
	    public K getKey( )                           
	    {
	        return entry.getKey();
	    }


	    /**
	     * Returns the value of the current node.
	     */
	    public V getValue( )                           
	    {
	        return entry.getValue();
	    }


	    /**
	     * Assigns a new entry (key and value) to the current BST node
	     */
	    public void setEntry( EntryClass<K,V> newEntry )
	    {    
	        entry = newEntry;
	    }

	    /**
	     * Sets the new value object of the current node.
	     */
	    public void setValue( V newValue )
	    {    
	        entry.setValue(newValue);
	    }


	    /**
	     * Sets the new left child node of this node
	     * 
	     * @param newLeft the new left child node of the current node
	     */
	    public void setLeft( BSTNode<K,V> newLeft )                     
	    {    
	        leftChild = newLeft;
	    }


	    /**
	     * Sets the new right child node of this node
	     * 
	     * @param newRight the new right child node of the current node
	     */
	    public void setRight( BSTNode<K,V> newRight )                   
	    {    
	        rightChild = newRight;
	    }

	    /**
	     * Sets the new parent of this node
	     * 
	     * @param newParent the new parent of the current node
	     */
	    public void setParent( BSTNode<K,V> newParent )                   
	    {    
	        parent = newParent;
	    }
	 
	    /**
	     * Returns true if the current node is internal.
	     */
		public boolean isInternal() {
			return getRight() != null || getLeft() != null; // if the node has leaves
		}


	    /**
	     * Returns true if the node is a leaf.
	     */
	    public boolean isLeaf( )                                
	    {    
	    	return parent != null && getRight() == null && getLeft() == null;
	    }                                                                  


	}

    //The root of the tree.                                            
    protected BSTNode<K,V> root;                                

    //Number of nodes in the tree.                                  
    protected int currentSize;                   

    /**
     * Tree Constructor - creates an empty tree.
     */
    public BinarySearchTree()  {
        root = null;
        currentSize = 0;
    }

    /**
     * Returns the number of children of node.
     *                         
     * @param node node to count its children
     * @return the number of children of node 
     */
	protected int numChildren(BSTNode<K,V> node) {
		int nChildren = 0;

		// counts num of children in each immediate child
		if (node.getLeft() != null)
			nChildren += 1;

		if (node.getRight() != null)
			nChildren += 1;

		return nChildren;
	}
	
	@Override
    public boolean isEmpty( )  {    
        return root == null;
    }


    @Override
    public int size( )  {    
        return currentSize;
    }

    /**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.        
     *                         
     * @param node where the search starts 
     * @param key to be found
     * @return the found node, when the search is successful
     */
    protected BSTNode<K,V> findNode( BSTNode<K,V> node, K key )  {                                                                   
        if ( node == null )
            return null;
        else
        {
            int compResult = key.compareTo( node.getKey() );
            if ( compResult == 0 )
                return node;                                         
            else if ( compResult < 0 )
                return this.findNode(node.getLeft(), key);
            else                                                     
                return this.findNode(node.getRight(), key); 
        }                 
    }
    
    @Override
    public V find( K key ) {    
        BSTNode<K,V> node = this.findNode(root, key);
        if ( node == null )                                   
            return null;                                    
        else                                                     
            return node.getValue();
    }

    @Override
    public Entry<K,V> minEntry( ) throws EmptyDictionaryException
    {
		if ( this.isEmpty() )
			throw new EmptyDictionaryException();

		return this.minNode(root).getEntry();
    }

    protected BSTNode<K, V> minNode(BSTNode<K, V> node) {

    	if (node.getLeft() == null)
    		return node;
    	else
    		return this.minNode(node.getLeft());
	}

    @Override
    public Entry<K,V> maxEntry( ) throws EmptyDictionaryException
    {                                                                   
        if ( this.isEmpty() )                              
            throw new EmptyDictionaryException();

        return this.maxNode(root).getEntry();                    
    }


    /**
     * Returns the node with the largest key 
     * in the tree rooted at the specified node.
     * Requires: node != null.
     * @param node that roots the tree
     * @return node with the largest key in the tree
     */
    protected BSTNode<K,V> maxNode( BSTNode<K,V> node )
    {                                                                   
        if ( node.getRight() == null )                            
            return node;                                             
        else                                                     
            return this.maxNode( node.getRight() );                       
    }                               

    public V insert( K key, V value ) {
        if (root == null) { // if the tree is empty
			root = new BSTNode<>(key, value);
			currentSize++;
			return null;
		}

		BSTNode<K,V> parent = findPlaceToInsert(root, key);

		if (parent.getKey().equals(key)) {
			V oldVal = parent.getValue();
			parent.setValue(value);
			return oldVal;

		}
		else {
			BSTNode<K, V> newNode = new BSTNode<>(key, value, parent, null, null);
			if (parent.getKey().compareTo(key) > 0) // if the parent key is greater than the newNode key insert
				parent.setLeft(newNode); // newNode on the left of the parent
			else
				parent.setRight(newNode); // if the newNode key is greater insert on the right
			currentSize++;
			return null;
		}

	}

	private BSTNode<K, V> findPlaceToInsert(BSTNode<K, V> root, K key) {

    	if (root.getKey().compareTo(key) > 0 && root.getLeft() != null) // smaller keys are on the left
    		return findPlaceToInsert(root.getLeft(), key);

    	else if (root.getKey().compareTo(key) < 0 && root.getRight() != null) // greater keys are on the right
    		return findPlaceToInsert(root.getRight(), key);

    	else
    		return root; // key = root.getKey()
	}


	//sugestao: implementar metodo auxiliary replaceParentWithChild(nodeToRemove, child) que poe
    //pai de noteToRemove a apontar para child (filho de nodeToRemove)
    public V remove (K key) {
    	BSTNode<K,V> nodeToRemove = findNode(root, key);
	
    	if (nodeToRemove!=null) {
			V value = nodeToRemove.getValue();
			// pick not null child
			switch (numChildren(nodeToRemove)) {
				// 1 child or leaf - replace nodeToRemove with child (or null)
				case 0, 1 -> {
					// if left is not null choose left if it is null choose right (if leaf right is null)
					boolean hasLeftChild = nodeToRemove.getLeft() != null;
					BSTNode<K, V> child = hasLeftChild ? nodeToRemove.getLeft() : nodeToRemove.getRight();
					BSTNode<K, V> parent = nodeToRemove.getParent();
					if (nodeToRemove == root) {
						root = child; // only child
					}
					else {

						if (parent.getLeft() == nodeToRemove)
							parent.setLeft(child); // parent not null (not root)
						else
							parent.setRight(child);
					}

					if (child != null) // child could be null if nodeToRemove is leaf
						child.setParent(parent); // if root just sets to null like it should be
				}
				// 2 children - find successor node
				case 2 -> {
					BSTNode<K, V> successor = this.minNode(nodeToRemove.getRight()); // find lowest higher node (successor)
					nodeToRemove.setEntry(successor.getEntry()); // copy successor to node to remove
					successor.getParent().setLeft(null); // delete successor
				}
			}

			currentSize--;
			return value;
    	}
    	else
    		return null;
    }
    
  
    /**
     * Returns an iterator of the entries in the dictionary 
     * which preserves the key order relation.
     * @return  key-order iterator of the entries in the dictionary
     */
    public Iterator<Entry<K,V>> iterator( ) 
    {
        return new BSTKeyOrderIterator<>(root);
    }

    static class BSTKeyOrderIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {

    	private BSTNode<K, V> root;
    	private Stack<BSTNode<K, V>> untreatedNodes;

    	public BSTKeyOrderIterator(BSTNode<K, V> root) {
			this.root = root;
			this.rewind();
		}

		@Override
		public boolean hasNext() {
			return !untreatedNodes.isEmpty();
		}

		@Override
		public Entry<K, V> next() throws NoSuchElementException {

    		if (!hasNext())
    			throw new NoSuchElementException();

    		BSTNode<K, V> currNode = untreatedNodes.pop();

    		if (currNode.getRight() != null)
    			addMinorNodes(currNode.getRight());

			return currNode.getEntry();
		}

		@Override
		public void rewind() {
    		untreatedNodes = new StackClass<>();

    		addMinorNodes(root);

		}

		/**
		 * Adds all nodes in the leftest most branch of the tree starting at and including <code>node</code> to the
		 * list of untreated nodes
		 * @param node top node of tree
		 */
		protected void addMinorNodes(BSTNode<K, V> node) {
			for (BSTNode<K, V> aux = node; aux != null; aux = aux.getLeft())
				untreatedNodes.push(aux);
    	}
	}

}

