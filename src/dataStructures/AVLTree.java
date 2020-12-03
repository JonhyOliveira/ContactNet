package dataStructures;

/**
 * AVLTree implementation
 * @author Joao Oliveira 58001 & Rafael Borralho
 * @version final
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class AVLTree <K extends Comparable<K>,V>
		extends AdvancedBSTree<K,V> {

	static class AVLNode<K,V> extends BSTNode<K,V> {
		// Height of the node
		protected int height;

		public AVLNode(K key, V value) {
			super(key, value);
			height = 1;
		}

		public AVLNode(K key, V value, AVLNode<K,V> parent, AVLNode<K,V> left, AVLNode<K,V> right){
			super(key, value, parent, left, right);
			height = 1 + Math.max(getHeight(left),getHeight(right));
		}

		protected int getHeight(AVLNode<K,V> node) {
			//precisamos deste metodo porque node pode ser null
			if (node==null)
				return 0;
			return node.getHeight();
		}

		public int getHeight() {
			return height;
		}

		public boolean isBalance() {
			int dif= getHeight((AVLNode<K,V>)this.getLeft()) -
					getHeight((AVLNode<K,V>)this.getRight());
			return dif==0 ||dif==-1 ||dif ==1;
		}

		public int setHeight() {
			height= 1 + Math.max(getHeight((AVLNode<K,V>)this.getLeft()),
					getHeight((AVLNode<K,V>)this.getRight()));
			return height;
		}

		/**
		 * Return the child with greater height
		 */
		protected AVLNode<K,V> tallerChild()  {
			AVLNode<K,V> leftChild = (AVLNode<K,V>) this.getLeft();
			AVLNode<K,V> rightChild = (AVLNode<K,V>) this.getRight();
			int leftChildHeight  = getHeight(leftChild);
			int rightChildHeight = getHeight(rightChild);

			if (leftChildHeight > rightChildHeight)
				return leftChild;
			else if (leftChildHeight < rightChildHeight)
				return rightChild;
			return null;
		}
	}


	/**
	 * Rebalance method called by insert and remove.  Traverses the path from
	 * zPos to the root. For each node encountered, we recompute its height
	 * and perform a trinode restructuring if it's unbalanced.
	 * the rebalance is completed with O(log n)running time
	 */
	protected void rebalance(AVLNode<K,V> zPos) {
		if(zPos.isInternal())
			zPos.setHeight();

		while (zPos!=null) {
			zPos.setHeight();
			if (!zPos.isBalance()) {
				//perform a trinode restructuring at zPos's tallest grandchild
				//If yPos (tallerChild(zPos)) denote the child of zPos with greater height.
				//Finally, let xPos be the child of yPos with greater height
				AVLNode<K, V> xPos = zPos.tallerChild().tallerChild();
				zPos = (AVLNode<K, V>) restructure(xPos); // tri-node restructure (from parent class)
				//note that zPos now may be a different node (the new root of the tri-node)

				// recompute heights for these 3 nodes
				((AVLNode<K, V>) zPos.getLeft()).setHeight();
				((AVLNode<K, V>) zPos.getRight()).setHeight();
				zPos.setHeight();
			}
			zPos = (AVLNode<K, V>) zPos.getParent();
		}


	}

	@Override
	public V insert(K key, V value) {

		V valueToReturn = null;
		AVLNode<K, V> newNode = null;

		if (root == null) {
			root = new AVLNode<>(key, value);
			currentSize++;
			return null;
		}

		AVLNode<K, V> parent = (AVLNode<K, V>) findPlaceToInsert(root, key);

		if (!parent.getKey().equals(key)) { // if node does not exist insert
			newNode = new AVLNode<>(key, value, parent, null, null);

			int compResult = parent.getKey().compareTo(key);
			if ( compResult < 0 )
				parent.setLeft(newNode);
			else
				parent.setRight(newNode);

			currentSize++;
		}
		else { // otherwise update
			valueToReturn = parent.getValue();
			parent.setValue(value);
		}


		if (newNode != null) // was inserted a new node
			rebalance(newNode);

		return valueToReturn;
	}


	@Override
	public V remove(K key) {
		AVLNode<K,V> node = (AVLNode<K, V>) findNode(root, key); // father of node where the key was deleted

		if (node != null)
			node = (AVLNode<K, V>) node.getParent();

		V valueToReturn = super.remove(key); // the value of the removed node

		if(node != null) // if node was found and is not root (if it is root the tree should remain balanced)
			rebalance(node); // rebalance up from the node
		return valueToReturn;
	}

}

