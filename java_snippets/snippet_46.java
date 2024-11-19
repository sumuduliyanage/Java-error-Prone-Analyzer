public static void main(String[] args) throws Exception {
    List<Integer> linkedList = new LinkedList<>(fillRandom.IntFill());

    Print printer = new printImpl();
    printer.print(linkedList);
}
