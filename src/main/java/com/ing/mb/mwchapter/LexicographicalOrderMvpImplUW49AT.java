package com.ing.mb.mwchapter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LexicographicalOrderMvpImplUW49AT implements LexicographicalOrder {

    @Override
    public Optional<String> nextLargerWord(String word) {
        return calcNextLargerWord(word);
    }


    private Optional<String> calcNextLargerWord(String word) {
        return IntStream.rangeClosed(0, word.length() - 1)
            .mapToObj(i -> calcWord(word, i).orElse(word))
            .filter(s -> !s.equals(word))
            .findFirst();
    }

    private Optional<String> calcWord(String word, int i) {
        return IntStream.rangeClosed(0, word.length() - 1)
            .mapToObj(j ->
                swapIfGreater(word, i, lookForMinGreater(word, i)))
            .filter(s -> !s.equals(word))
            .findFirst();
    }

    private int lookForMinGreater(String word, int i) {
        List<Integer> list = getListFromString(word);
        return IntStream.rangeClosed(i, list.size() - 1)
            .filter(j -> list.get(j) > list.get(i))
            .min().orElse(i);
    }

    private String swapIfGreater(String word, int i, int j) {
        List<Integer> list = getListFromString(word);
        int pointerI = word.length() - 1 - i;
        int pointerJ = word.length() - 1 - j;
        if (list.get(pointerI) > list.get(pointerJ)) {
            Collections.swap(list, pointerI, pointerJ);
        }
        return getStringFromASCIIList(list);
    }

    private String getStringFromASCIIList(List<Integer> list) {
        return list.stream()
            .map(k -> (char) k.intValue())
            .map(Object::toString)
            .reduce("", String::concat);
    }

    private List<Integer> getListFromString(String word) {
        return word.chars()
            .boxed()
            .collect(Collectors.toList());
    }
}