package chapter;

import java.util.Optional;

@FunctionalInterface
public interface LexicographicalOrder {
  Optional<String> nextLargerWord(String word);
}
