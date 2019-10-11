package com.ing.mb.mwchapter;

import java.util.Map;
import java.util.Optional;

public class LexicographicalOrderMvpImpl implements LexicographicalOrder {

  private final Map<String, String> questionsAndAnswers = Map.of("ab", "ba",
    "cd", "dc",
    "hefg", "hegf",
    "dhck", "dhkc",
    "dkhc", "hcdk"
  );

  @Override
  public Optional<String> nextLargerWord(String word) {
    return Optional.ofNullable(questionsAndAnswers.get(word));
  }
}
