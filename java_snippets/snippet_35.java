    String[][] matrix = new String[][]{
            {"11111", "why ", "did ", "I ", "lose ", "reputation ", "11111", "11111"},
            {"22222", "points ", "for ", "this ", "question? ", "(dk)", "11111", "22222"}};
    Arrays.stream(matrix, 0, 2)
            .flatMap(s -> Arrays.stream(s, 1, 6))
            .forEach(System.out::print);
}
