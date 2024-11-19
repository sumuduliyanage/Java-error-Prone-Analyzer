public class Test {
    public static void main(String[] args) {
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            if(test(i) != i + 0x123)
                throw new AssertionError();
        }
    }
  
    static int test(int someArg) {
        int[] arr = new int[1000];
        arr[42] = someArg;
        return arr[42] + 0x123;
    }
}
