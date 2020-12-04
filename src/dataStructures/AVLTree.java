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

	static class AVLNode<K, V> extends BSTNode<K, V> {
		// Height of the node
		protected int height;

		public AVLNode(K key, V value) {
			super(key, value);
			height = 1;
		}

		public AVLNode(K key, V value, AVLNode<K, V> parent, AVLNode<K, V> left, AVLNode<K, V> right) {
			super(key, value, parent, left, right);
			height = 1 + Math.max(getHeight(left), getHeight(right));
		}

		protected int getHeight(AVLNode<K, V> node) {
			if (node == null)
				return 0;
			return node.getHeight();
		}

		public int getHeight() {
			return height;
		}

		public boolean isBalance() {
			int dif = getHeight((AVLNode<K, V>) this.getLeft()) -
					getHeight((AVLNode<K, V>) this.getRight());
			return dif == 0 || dif == -1 || dif == 1;
		}

		public int setHeight() {
			height = 1 + Math.max(getHeight((AVLNode<K, V>) this.getLeft()),
					getHeight((AVLNode<K, V>) this.getRight()));
			return height;
		}

		/**
		 * Return the child with greater height
		 */
		protected AVLNode<K, V> tallerChild() {
			AVLNode<K, V> leftChild = (AVLNode<K, V>) this.getLeft();
			AVLNode<K, V> rightChild = (AVLNode<K, V>) this.getRight();
			int leftChildHeight = getHeight(leftChild);
			int rightChildHeight = getHeight(rightChild);

			if (leftChildHeight >= rightChildHeight)
				return leftChild;
			else
				return rightChild;
		}
	}


	/**
	 * Rebalance method called by insert and remove.  Traverses the path from
	 * zPos to the root. For each node encountered, we recompute its height
	 * and perform a trinode restructuring if it's unbalanced.
	 * the rebalance is completed with O(log n)running time
	 */
	protected void rebalance(AVLNode<K, V> zPos) {
		if (zPos.isInternal())
			zPos.setHeight();
		// Melhorar se possivel
		while (zPos.getParent() != null) {  // traverse up the tree towards the root
			zPos = (AVLNode<K, V>) zPos.getParent();
			zPos.setHeight();
			if (!zPos.isBalance()) {
				//perform a trinode restructuring at zPos's tallest grandchild
				//If yPos (tallerChild(zPos)) denote the child of zPos with greater height.
				//Finally, let xPos be the child of yPos with greater height
				AVLNode<K, V> xPos = zPos.tallerChild().tallerChild();

				AVLNode<K, V> newZ = (AVLNode<K, V>) restructure(xPos); // balanced (sub)tree from tri-node restructure

				if (zPos == root) // if the root was unbalanced it should have suffered a rotation and is no
					root = newZ; // longer the actual root
				zPos = newZ;

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
		AVLNode<K, V> newNode = null;

		if (root == null) {
			root = new AVLNode<>(key, value);
			currentSize++;
			return null;
		}

		AVLNode<K, V> parent = (AVLNode<K, V>) findPlaceToInsert(root, key);

		if (parent.getKey().equals(key)) { // if node exists update
			valueToReturn = parent.getValue();
			parent.setValue(value);
		}
		else { // otherwise insert new node
			newNode = new AVLNode<>(key, value, parent, null, null);

			if (parent.getKey().compareTo(key) > 0) // if the parent key is greater than the newNode key insert
				parent.setLeft(newNode); // newNode on the left of the parent
			else
				parent.setRight(newNode); // if the newNode key is greater insert on the right
			currentSize++;

		}


		if (newNode != null) // was inserted a new node
			rebalance(newNode);

		return valueToReturn;
	}


	@Override
	public V remove(K key) {

		AVLNode<K, V> node = (AVLNode<K, V>) findNode(root, key), parentNode = null;
		if (node != null)
			parentNode = (AVLNode<K, V>) node.getParent();

		V valueToReturn = super.remove(key);

		if (parentNode != null)
			rebalance(parentNode);

		return valueToReturn;
	}

}

