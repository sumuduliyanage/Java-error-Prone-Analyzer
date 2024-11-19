import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
...
@org.junit.jupiter.api.Test
void testAllowedRemoveForward() {
  Iterator<String> iterator = ...

  String next = iterator.next();
  assertThat(next).isEqualTo("Alpha");
}
