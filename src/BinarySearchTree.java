
public class BinarySearchTree {

	public static void main(String[] args) {
		int length = args.length;
		Node tree = null;
		for (int i = 0; i < length; i++) {
			tree = insert(tree, Integer.parseInt(args[i]));
		}
		BinaryTreeLinked.inOrder(tree);
		System.out.println();
		BinaryTreeLinked.bfs(tree);
		
		Node node100 = search(tree, 100);
		if (null != node100) {
			System.out.println("found 100");
		}

		Node node139 = search(tree, 139);
		if (null != node139) {
			System.out.println("found 10");
		}
	}

	static Node insert(Node node, int data) {
		if (null == node) {
			node = new Node();
			node.val = data;
		}
		if (node.val < data) {
			if (node.right != null) {
				insert(node.right, data);
			} else {
				node.right = new Node();
				node.right.val = data;
			}
		} else if (node.val > data) {
			if (node.left != null) {
				insert(node.left, data);
			} else {
				node.left = new Node();
				node.left.val = data;
			}
		} else {
			System.out.println("discarding duplicate");
		}
		return node;
	}

	static Node search(Node node, int data) {
		if (null != node) {
			System.out.println("search " + node.val);
		}
		if (null == node || node.val == data) {
			return node;
		} else if (node.val < data) {
			return search(node.right, data);
		} else {
			return search(node.left, data);
		}

	}
}
