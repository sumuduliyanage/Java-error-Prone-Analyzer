import java.util.function.Predicate;

public class Validator {
    public static <T> void validate(Predicate<T> predicate, String errorMsg, T objectUnderTest) {
        if (predicate.test(objectUnderTest)) {
            System.out.println(errorMsg);
        }
    }
}
