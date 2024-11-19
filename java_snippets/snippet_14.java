public class Main {
    public static void main(String args[]) {
        LocalDate date = LocalDate.parse("2011-09-13");
        System.out.println(date);

        // Formatted string for logging
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(String.format("Output is %s", date.format(formatter)));
    }
}
