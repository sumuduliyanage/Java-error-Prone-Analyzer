
public static void main(String[] args) {
    Random r = new Random();

    int[][] matrix = IntStream.range(0, 300_000)
            .mapToObj(i -> r.ints(200, 0, 200).toArray())
            .toArray(int[][]::new);

    int first = 10;
    int last = 150;
    int N = 30;
    int[] r1 = null;
    int[] r2 = null;
    int[] r3 = null;
    System.out.println("Starting");
    {
        long start = System.nanoTime();
        for (int k = 0; k < N; k++) {
            r1 = nestedLoops(matrix, first, last);
        }
        System.out.printf("%-12s -  %.3fs%n", "nestedLoops",
                (System.nanoTime() - start) / 1e9);
    }
    {
        long start = System.nanoTime();
        for (int k = 0; k < N; k++) {
            r2 = oneLoop(matrix, first, last);
        }
        System.out.printf("%-12s -  %.3fs%n", "oneLoop",
                (System.nanoTime() - start) / 1e9);

    }
    {
        long start = System.nanoTime();
        for (int k = 0; k < N; k++) {
            r3 = arrayStream(matrix, first, last);
        }
        System.out.printf("%-12s -  %.3fs%n", "arrayStream",
                (System.nanoTime() - start) / 1e9);

    }

    System.out.println(Arrays.equals(r1, r2) && Arrays.equals(r1, r3));
    System.out.println(r1.length + " " + r2.length + " " + r2.length);
}
// nested loops
static int[] run1(int arr[][], int first, int last) {
    int k = 0;
    int limit = last - first;
    int[] result = new int[arr.length * limit];
    for (int[] ar : arr) {
        for (int i = first; i < last; i++) {
            result[k++] = ar[i];
        }
    }
    return result;
}
// single loop
static int[] run2(int arr[][], int first, int last) {
    int limit = last - first;
    int[] result = new int[arr.length * limit];
    for (int i = 0; i < arr.length * limit; i++) {
        result[i] = arr[i / limit][i % limit + first];
    }
    return result;
}

// stream with Arrays.stream()
static int[] run3(int[][] matrix, int first, int last) {
   return Arrays.stream(matrix).
         flatMapToInt(arr -> Arrays.stream(arr, first, last)).toArray();
}
