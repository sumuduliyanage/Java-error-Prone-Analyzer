class MyTests {
    @ParameterizedTest
    @MethodSource("parameterSource")
    void doit(String s, int i){
        assertEquals(s, Integer.toString(i));
    }

    public static Stream<Arguments> parameterSource() {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("2", 2)
        );
    }
}
