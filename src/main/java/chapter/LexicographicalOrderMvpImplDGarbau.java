package chapter;

import java.util.Arrays;
import java.util.Optional;

public class LexicographicalOrderMvpImplDGarbau implements LexicographicalOrder {

    @Override
    public Optional<String> nextLargerWord(String word) {
        int[] array = word.chars().toArray();
        int i = findPivot(array).orElse(0);
        if (i <= 0) {
            return Optional.empty();
        }
        findSuccessor(array, i);
        reverseSuffix(array, i);
        return Optional.of(getStringFromASCIIArray(array));
    }

    private Optional<Integer> findPivot(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        return (i <= 0) ? Optional.empty() : Optional.of(i);
//        ALTERNATIVE STREAM VERSION
//        return IntStream.range(0, array.length - 1)
//            .mapToObj(i -> array.length - 1 - i)
//            .filter(i -> array[i - 1] < array[i])
//            .findFirst();
    }

    private void findSuccessor(int[] array, int i) {
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }
        swap(array, i - 1, j);
//        ALTERNATIVE STREAM VERSION
//        IntStream.range(0, array.length - 1)
//            .mapToObj(j -> array.length - 1 - j)
//            .filter(j -> array[j] > array[i - 1])
//            .limit(1)
//            .forEach(j -> swap(array, i - 1, j));
    }

    private void reverseSuffix(int[] array, int i) {
        int j = array.length - 1;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
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