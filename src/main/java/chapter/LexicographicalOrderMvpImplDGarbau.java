package chapter;

import java.util.Arrays;
import java.util.Optional;

public class LexicographicalOrderMvpImplDGarbau implements LexicographicalOrder {
    @Override
    public Optional<String> nextLargerWord(String word) {
        int[] array = word.chars().toArray();
        int i = array.length - 1;
        int j = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        if (i <= 0) return Optional.empty();

        while (array[j] <= array[i - 1])
            j--;
        swap(array, i - 1, j);

        j = array.length - 1;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
        return Optional.of(getStringFromASCIIArray(array));
    }

    private void swap(int[] array, int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private String getStringFromASCIIArray(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .map(k -> (char) k.intValue())
                .map(Object::toString)
                .reduce("", String::concat);
    }
}