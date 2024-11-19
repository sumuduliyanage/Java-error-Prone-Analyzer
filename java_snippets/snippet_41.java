@Fork(value = 1, warmups = 1)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class MainTest {
    public static final int FIRST_SIZE = 1000;
    private static final int SECOND_SIZE = 100000;
    private int[][] arr;

    @Setup(Level.Trial)
    public void setUp() {
        arr = IntStream.range(0, FIRST_SIZE)
                .mapToObj(x -> new Random().ints(SECOND_SIZE).toArray())
                .toArray(int[][]::new);
    }

    @Benchmark
    public void loop(Blackhole blackhole) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void singleLoop(Blackhole blackhole) {
        int sum = 0;
        for (int i = 0; i < FIRST_SIZE * SECOND_SIZE; i++) {
            sum += arr[i / SECOND_SIZE][i % SECOND_SIZE];
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void enhancedLoop(Blackhole blackhole) {
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        blackhole.consume(sum);
    }

    @Benchmark
    public void stream(Blackhole blackhole) {
        int sum = Arrays.stream(arr)
                .flatMapToInt(a -> Arrays.stream(a))
                .sum();
        blackhole.consume(sum);
    }

    @Benchmark
    public void parallelStream(Blackhole blackhole) {
        int sum = Arrays.stream(arr)
                .parallel()
                .flatMapToInt(a -> Arrays.stream(a))
                .sum();
        blackhole.consume(sum);
    }


    @Benchmark
    public void mapMultiToInt(Blackhole blackhole) {
        int sum = Arrays.stream(arr).mapMultiToInt((row, consumer) -> {
            for (int i = 0; i < row.length; i++) {
                consumer.accept(row[i]);
            }
        }).sum();

        blackhole.consume(sum);
    }

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }
}
