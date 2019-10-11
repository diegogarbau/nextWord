package com.ing.mb.mwchapter;

import java.util.Optional;

@FunctionalInterface
public interface LexicographicalOrder {
  Optional<String> nextLargerWord(String word);
}
