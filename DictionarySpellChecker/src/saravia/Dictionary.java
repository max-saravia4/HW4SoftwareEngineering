package saravia;


public class Dictionary {

	private WordNode root;
	
	// Insert Word
	public void insertWordNode(String word) {
		word = word.toLowerCase();
		root = insertHelper(root, word);
		assert isValidBST(root, null, null);
	}

	// Helper method for insertion
	private WordNode insertHelper(WordNode node, String word) {
		if (node == null) {
			return new WordNode(word);
		}

		if (word.compareTo(node.word) < 0) {
			node.left = insertHelper(node.left, word);
		} else if (word.compareTo(node.word) > 0) {
			node.right = insertHelper(node.right, word);
		}
		// duplicate words ignored
		return node;
	}

	// Spell Check
	public boolean checkWord(String word) {
		return searchHelper(root, word.toLowerCase());
	}
	
	// Helper method for searching
	private boolean searchHelper(WordNode node, String word) {
		if (node == null)
			return false;

		if (word.equals(node.word))
			return true;

		if (word.compareTo(node.word) < 0)
			return searchHelper(node.left, word);
		else
			return searchHelper(node.right, word);
	}

	// Remove Word
	public void removeWord(String word) {
		root = removeHelper(root, word.toLowerCase());
		assert isValidBST(root, null, null);
	}
	
	// Helper method for removal
	private WordNode removeHelper(WordNode node, String word) {
		if (node == null)
			return null;

		if (word.compareTo(node.word) < 0) {
			node.left = removeHelper(node.left, word);
		} else if (word.compareTo(node.word) > 0) {
			node.right = removeHelper(node.right, word);
		} else {
			// Case 1: no children
			if (node.left == null && node.right == null) {
				return null;
			}
			// Case 2: one child
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;

			// Case 3: two children
			WordNode successor = findMin(node.right);
			node.word = successor.word;
			node.right = removeHelper(node.right, successor.word);
		}
		return node;
	}
	
	// Helper method to find the minimum node in a subtree
	private WordNode findMin(WordNode node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	// Assertions
	private boolean isValidBST(WordNode node, String min, String max) {
		if (node == null)
			return true;

		if (min != null && node.word.compareTo(min) <= 0)
			return false;
		if (max != null && node.word.compareTo(max) >= 0)
			return false;

		return isValidBST(node.left, min, node.word) && isValidBST(node.right, node.word, max);
	}
}
