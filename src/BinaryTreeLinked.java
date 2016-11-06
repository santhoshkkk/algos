import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Node {
	int val;
	Node left;
	Node right;
}

public class BinaryTreeLinked {
	static Node tree = null;

	public static void main(String[] args) {
		int length = args.length;
		int[] treeArr = new int[length];
		for (int i = 0; i < length; i++) {
			treeArr[i] = Integer.parseInt(args[i]);
		}
		tree = new Node();

		insert(treeArr, tree, 0);
		System.out.println("preorder");
		preOrder(tree);
		System.out.println("\ninorder");
		inOrder(tree);
		System.out.println("\npostorder");
		postorder(tree);
		System.out.println("\nbfs");
		bfs(tree);
		System.out.println("\ndfs");
		dfs(tree);
		System.out.println("\nzigzag");
		zigzag(tree);
	}

	static void insert(int[] items, Node node, int index) {
		node.val = items[index];
		int leftIndex = 2 * index + 1;
		if (leftIndex < items.length) {
			node.left = new Node();
			insert(items, node.left, leftIndex);
		}

		int rightIndex = 2 * index + 2;
		if (rightIndex < items.length) {
			node.right = new Node();
			insert(items, node.right, rightIndex);
		}
	}

	static void zigzag(Node tree) {
		Queue<Node> q = new LinkedList<>();
		boolean backward = true;
		Map<Node, Integer> nodeLevel = new HashMap<>();
		Stack<Node> printStack = new Stack<>();
		q.add(tree);
		nodeLevel.put(tree, 0);

		do {
			Node node = q.poll();
			int level = nodeLevel.get(node);
			if (backward) {
				if (level % 2 == 1) {
					backward = false;
					printStack(printStack);
					System.out.print(node.val + " ");
				} else {
					printStack.push(node);
				}
			} else {
				if (level % 2 == 0) {
					backward = true;
					printStack.push(node);
				} else {
					System.out.print(node.val + " ");
				}
			}
			if (null != node.left) {
				q.add(node.left);
				nodeLevel.put(node.left, level + 1);
			}
			if (null != node.right) {
				q.add(node.right);
				nodeLevel.put(node.right, level + 1);
			}

			if (q.isEmpty()) {
				printStack(printStack);
				break;
			}
		} while (true);
	}

	public static void printStack(Stack<Node> printStack) {
		while (!printStack.isEmpty()) {
			System.out.print(printStack.pop().val + " ");
		}
	}

	static void bfs(Node tree) {
		Queue<Node> q = new LinkedList<>();
		q.add(tree);
		do {
			Node node = q.poll();
			System.out.print(node.val + " ");
			if (null != node.left)
				q.add(node.left);
			if (null != node.right)
				q.add(node.right);

		} while (!q.isEmpty());
	}

	static void dfs(Node tree) {
		if (null == tree) {
			return;
		}
		System.out.print(tree.val + " ");
		dfs(tree.left);
		dfs(tree.right);
	}

	static void postorder(Node tree) {
		if (null == tree) {
			return;
		}
		postorder(tree.left);
		postorder(tree.right);
		System.out.print(tree.val + " ");
	}

	static void inOrder(Node tree) {
		if (null == tree) {
			return;
		}
		inOrder(tree.left);
		System.out.print(tree.val + " ");
		inOrder(tree.right);
	}

	static void preOrder(Node tree) {
		if (null == tree) {
			return;
		}
		System.out.print(tree.val + " ");
		preOrder(tree.left);
		preOrder(tree.right);
	}

}
