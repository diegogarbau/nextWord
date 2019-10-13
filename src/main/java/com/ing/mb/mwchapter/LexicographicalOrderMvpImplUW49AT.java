package com.ing.mb.mwchapter;

import java.util.Arrays;
import java.util.Optional;

public class LexicographicalOrderMvpImplUW49AT implements LexicographicalOrder {

    @Override
    public Optional<String> nextLargerWord(String word) {
        return calcNextLargerWord(word);
    }


    private Optional<String> calcNextLargerWord(String word) {
        String next = getStringFromASCIIArray(nextPermutation(getArrayFromString(word)));
        return (next.equals(word))? Optional.empty():Optional.of(next);
    }

    public static int[] nextPermutation(int[] array) {
        // Find non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        if (i <= 0)
            return array;
        // Find successor to pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return array;
    }

    private static int[] getArrayFromString(String word) {
        return word.chars()
                .toArray();
    }

    private String getStringFromASCIIArray(int[] arry) {
        return Arrays.stream(arry)
                .boxed()
                .map(k -> (char) k.intValue())
                .map(Object::toString)
                .reduce("", String::concat);
    }

}