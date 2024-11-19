int[][] matrix = {{1,2,5},{3,4,6}};

int first = 0;
int last = 1;

int[] result = Arrays.stream(matrix).mapMultiToInt((row, consumer)-> {
    for(int i=first; i <= last; i++) {
        consumer.accept(row[i]);
    }
}).toArray();

System.out.println(Arrays.toString(result));
