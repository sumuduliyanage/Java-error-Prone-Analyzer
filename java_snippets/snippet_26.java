final List<String> threeLetters = List.of("abc", "cde", "dea", "dei", "mod", "loc", "bpa");
threeLetters.stream()
    .filter(name -> !name.contains("c"))
    .map(name -> name + ", ")
    .forEach(System.out::print);
System.out.println();
