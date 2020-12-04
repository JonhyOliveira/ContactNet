package dataStructures;


/**
 * Auxiliary class with node rotation methods for implementation of AVLTree
 * @author Joao Oliveira 58001 & Rafel Borralho 58349 & AED Team
 * @version final
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class AdvancedBSTree<K extends Comparable<K>, V>
		extends BinarySearchTree<K,V>{

	/**
	 * Performs a single right rotation rooted at Y node.
	 * Node X was a  left  child  of Y before the  rotation,
	 * then Y becomes the right child of X after the rotation.
	 * @param Y - root of the rotation
	 *    Y				X
	 *   /	turns into:	 \
	 *  X 				  Y
	 */
	protected void rotateRight( BSTNode<K,V> Y){
		BSTNode<K, V> X = Y.getLeft();

		// move Xs right tree to the left of Y so that the sub-tree will remain between X and Y
		Y.setLeft(X.getRight());
		if (X.getRight() != null)
			X.getRight().setParent(Y);
		// X.setRight(null); //X right sub-tree is now free

		// before making the rotation we need to make X a child of Y's parent
		X.setParent(Y.getParent());
		if (Y.getParent() != null) {
			if (Y.getParent().getLeft() == Y)
				Y.getParent().setLeft(X);
			else
				Y.getParent().setRight(X);
		}

		Y.setParent(X);
		X.setRight(Y);
	}

	/**
	 * Performs a single left rotation rooted at Y node.
	 * Node X was a  right  child  of Y before the  rotation,
	 * then Y becomes the left child of X after the rotation.
	 * @param Y - root of the rotation
	 *    Y				      X
	 *     \  turns into:	 /
	 *      X 				Y
	 */
	protected void rotateLeft( BSTNode<K,V> Y){
		BSTNode<K, V> X = Y.getRight();

		// move Xs left tree to the left of Y so that the sub-tree will remain between X and Y
		Y.setRight(X.getLeft());
		if (X.getLeft() != null)
			X.getLeft().setParent(Y);
		// X.setLeft(null); //X left sub-tree is now free

		// before making the rotation we need to make X a child of Y's parent
		X.setParent(Y.getParent());
		if (Y.getParent() != null) {
			if (Y.getParent().getLeft() == Y)
				Y.getParent().setLeft(X);
			else
				Y.getParent().setRight(X);
		}

		Y.setParent(X);
		X.setLeft(Y);
	}

	/**
	 * Performs a tri-node restructuring (a single or double rotation).
	 * Assumes the nodes are in one of following configurations:
	 *
	 * @param X - a node that has both a parent y and a grandparent z
	 * <pre>
	 *          z=c       z=c        z=a         z=a
	 *         /  \      /  \       /  \        /  \
	 *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
	 *      /  \      /  \           /  \         /  \
	 *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
	 *   /  \          /  \       /  \             /  \
	 *  t1  t2        t2  t3     t2  t3           t3  t4
	 * </pre>
	 * @return the new root of the restructured subtree
	 */
	protected BSTNode<K,V> restructure (BSTNode<K,V> X) {
		// the modification of a tree T caused by a trinode restructuring operation
		// can be implemented through case analysis either as a single rotation or as a double rotation.
		// The double rotation arises when position x has the middle of the three relevant keys
		// and is first rotated above its parent y, and then above what was originally its grandparent z.
		// In any of the cases, the trinode restructuring is completed with O(1)running time.
		// [Goodrich et al., 2015]
		BSTNode<K, V> Y = X.getParent(), Z = Y.getParent(); // parent, grandparent

		boolean isXLeftY = Y.getLeft() == X;
		boolean isYLeftZ = Z.getLeft() == Y;

		if (isXLeftY) {
			if (isYLeftZ) { // 1st case - 1 rotation (X < Y < Z) (Y is middle)
				rotateRight(Z); // collapse Z
				return Y;
			}
			else { // 3rd case - 2 rotations (Z < X < Y) (X is middle)
				rotateRight(Y);
				rotateLeft(Z);
				return X;
			}
		}
		else {
			if (isYLeftZ) { // 2nd case - 2 rotations (Y < X < Z) (X is middle)
				rotateLeft(Y);
				rotateRight(Z);
				return X;
			}
			else { // 4th case - 1 rotation (Z < Y < X) (Y is middle)
				rotateLeft(Z);
				return Y;
			}
		}
	}
}

