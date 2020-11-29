package dataStructures;

import dataStructures.BinarySearchTree.BSTNode;

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
			int leftChildHeight  = getHeight((AVLNode<K,V>) leftChild);
			int rightChildHeight = getHeight((AVLNode<K,V>) rightChild);

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
		// Melhorar se possivel
		while (zPos!=null) {  // traverse up the tree towards the root

			zPos = (AVLNode<K, V>) zPos.getParent();
			zPos.setHeight();
			if (!zPos.isBalance()) {
				//perform a trinode restructuring at zPos's tallest grandchild
				//If yPos (tallerChild(zPos)) denote the child of zPos with greater height.
				//Finally, let xPos be the child of yPos with greater height
				AVLNode<K,V> xPos = zPos.tallerChild().tallerChild();
				zPos = (AVLNode<K, V>) restructure(xPos); // tri-node restructure (from parent class)
				//note that zPos now may be a different node (the new root of the tri-node)

				// recompute heights for these 3 nodes
				((AVLNode<K, V>) zPos.getLeft()).setHeight();
				((AVLNode<K, V>) zPos.getRight()).setHeight();
				zPos.setHeight();
			}
		}
	}

	@Override
	public V insert(K key, V value) {

		V valueToReturn = null;

		if (root == null) { // if the tree is empty
			root = new AVLNode<>(key, value);
			currentSize++;
		}

		AVLNode<K,V> placeToInsert = (AVLNode<K, V>) findPlaceToInsert(root, key), newNode = null;

		if (placeToInsert.getKey().equals(key)) {
			valueToReturn = placeToInsert.getValue();
			placeToInsert.setValue(value);

		}
		else {
			newNode = new AVLNode<>(key, value, placeToInsert, null, null);
			if (placeToInsert.getKey().compareTo(key) > 0) // if the parent key is greater than the newNode key insert
				placeToInsert.setLeft(newNode); // newNode on the left of the parent
			else
				placeToInsert.setRight(newNode); // if the newNode key is greater insert on the right
			currentSize++;
			return null;
		}

		/*
		while(auxNode != null) {

			if(key < auxNode.getEntry().getKey()) {
				auxNode = (AVLNode<K, V>) auxNode.getLeft();
			}else if(key > auxNode.getEntry().getKey()) {
				auxNode = (AVLNode<K, V>) auxNode.getRight();
			}else {
				auxNode.getParent().setValue(value);
				return value;
			}
		}*/

		// node where the new entry is being inserted (if find(key)==null)
		// insert the new Entry (if find(key)==null)
		// or set the new value (if find(key)!=null)

		if(newNode != null) // a new node was inserted
			rebalance(newNode); // rebalance up from the inserted node
		//rebalance checks if it needs to call restructure
		return valueToReturn;
	}


	@Override
	public V remove(K key) {
		// TODO
		V valueToReturn=null;
		AVLNode<K,V> node=null; // father of node where the key was deleted

		if(node != null) //(if find(key)==null)
			rebalance(node); // rebalance up from the node
		return valueToReturn;
	}

}

