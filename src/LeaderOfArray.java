
public class LeaderOfArray {

	public static void main(String[] args) {
		int length = args.length;
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(args[i]);
		}

		System.out.println(leader(arr));

	}

	private static int leader(int[] arr) {
		int leader = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			if (leader < arr[i])
				leader = arr[i];
		}
		return leader;
	}

}
