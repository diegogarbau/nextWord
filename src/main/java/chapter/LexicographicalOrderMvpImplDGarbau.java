package chapter;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class LexicographicalOrderMvpImplDGarbau implements LexicographicalOrder {

    @Override
    public Optional<String> nextLargerWord(String word) {
        return calcNextLargerWord(word);
    }


    private Optional<String> calcNextLargerWord(String word) {
//        Integer pivot = calcPivot(getArrayFromString(word)).orElse(null);
//        if (pivot.equals(null)) return Optional.empty();


        String next = getStringFromASCIIArray(nextPermutation(getArrayFromString(word)));
        return (next.equals(word)) ? Optional.empty() : Optional.of(next);
    }

    public static int[] nextPermutation(int[] array) {


        // Find non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        if (i <= 0)
            return array;

//        Integer i = calcPivotStream(array);

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
            swap(array, i, j);
            i++;
            j--;
        }
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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

    private static Integer calcPivotStream(int[] array) {
        return IntStream.rangeClosed(0, array.length - 1)
                .mapToObj(i -> array.length - 1 - i)
                .takeWhile(i -> i > 0)
                .filter(i -> array[i - 1] >= array[i])
                .map(i -> i-1)
                .reduce((a, b) -> b).orElse(null);
    }

}