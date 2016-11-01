import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeArray {
	private static final int PLACE_HOLDER = -1;

	public static void main(String[] args) {
		int length = args.length;
		int[] tree = new int[length];
		for (int i = 0; i < length; i++) {
			tree[i] = Integer.parseInt(args[i]);
		}
		System.out.println("preorder");
		preOrder(tree, 0);
		System.out.println("\ninorder");
		inOrder(tree, 0);
		System.out.println("\npostorder");
		postorder(tree, 0);
		System.out.println("\nbfs");
		bfs(tree, 0);
		System.out.println("\ndfs");
		dfs(tree, 0);
		System.out.println("\nzigzag");
		zigzag(tree);
	}

	private static void zigzag(int[] tree) {
		Queue<Integer> mainQue = new LinkedList<Integer>();
		Map<Integer, Integer> indexToLevelMap = new HashMap<>();
		mainQue.add(0);
		indexToLevelMap.put(0, 0);
		boolean reverse = false;
		int currIndex = 0;
		int level = 0;
		Stack<Integer> stack = new Stack<>();
		boolean printStack = false;

		do {
			currIndex = mainQue.poll();
			level = indexToLevelMap.get(currIndex);
			indexToLevelMap.remove(currIndex);

			if (!reverse) {
				if (level % 2 == 1) {
					reverse = true;
				}
			} else {
				if (level % 2 == 0) {
					reverse = false;
					printStack = true;
				}
			}

			if (!reverse) {
				if (printStack) {
					while (!stack.isEmpty()) {
						print(tree, stack.pop());
					}
					printStack = false;
				}
				print(tree, currIndex);
			} else {
				stack.push(currIndex);
			}
			if (mainQue.isEmpty() && !stack.isEmpty()) {
				while (!stack.isEmpty()) {
					print(tree, stack.pop());
				}
				printStack = false;
			}

			int lChildIndex = 2 * currIndex + 1;
			if (lChildIndex < tree.length) {
				mainQue.add(lChildIndex);
				indexToLevelMap.put(lChildIndex, level + 1);
			}

			int rChildIndex = 2 * currIndex + 2;
			if (rChildIndex < tree.length) {
				mainQue.add(rChildIndex);
				indexToLevelMap.put(rChildIndex, level + 1);
			}

			if (mainQue.isEmpty()) {
				break;
			}
		} while (true);

	}

	private static void bfs(int[] arr, int nodeIndex) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(nodeIndex);

		do {
			int currIndex = que.poll();
			print(arr, currIndex);

			int lChildIndex = 2 * currIndex + 1;
			if (lChildIndex < arr.length)
				que.add(lChildIndex);

			int rChildIndex = 2 * currIndex + 2;
			if (rChildIndex < arr.length)
				que.add(rChildIndex);

		} while (!que.isEmpty());

	}

	private static void dfs(int[] arr, int nodeIndex) {
		print(arr, nodeIndex);
		int lChildIndex = 2 * nodeIndex + 1;

		if (lChildIndex < arr.length)
			dfs(arr, lChildIndex);

		int rChildIndex = 2 * nodeIndex + 2;
		if (rChildIndex < arr.length)
			dfs(arr, rChildIndex);

	}

	private static void postorder(int[] arr, int nodeIndex) {

		int lChildIndex = 2 * nodeIndex + 1;
		if (lChildIndex < arr.length)
			postorder(arr, lChildIndex);

		int rChildIndex = 2 * nodeIndex + 2;
		if (rChildIndex < arr.length)
			postorder(arr, rChildIndex);

		print(arr, nodeIndex);

	}

	private static void inOrder(int[] arr, int nodeIndex) {

		int lChildIndex = 2 * nodeIndex + 1;
		if (lChildIndex < arr.length)
			inOrder(arr, lChildIndex);
		print(arr, nodeIndex);
		int rChildIndex = 2 * nodeIndex + 2;
		if (rChildIndex < arr.length)
			inOrder(arr, rChildIndex);

	}

	public static void print(int[] arr, int nodeIndex) {
		if (PLACE_HOLDER != arr[nodeIndex]) {
			System.out.print(arr[nodeIndex] + " ");
		}
	}

	private static void preOrder(int[] arr, int nodeIndex) {
		print(arr, nodeIndex);
		int lChildIndex = 2 * nodeIndex + 1;
		if (lChildIndex < arr.length)
			preOrder(arr, lChildIndex);

		int rChildIndex = 2 * nodeIndex + 2;
		if (rChildIndex < arr.length)
			preOrder(arr, rChildIndex);

	}

}
