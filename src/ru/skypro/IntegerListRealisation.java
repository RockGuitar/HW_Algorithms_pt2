package ru.skypro;

import ru.skypro.exceptions.ArrayIsFullException;
import ru.skypro.exceptions.IndexOutOfArrayLengthException;
import ru.skypro.exceptions.ItemNotFoundException;
import ru.skypro.exceptions.ParameterIsNullException;

import java.util.Arrays;

public class IntegerListRealisation implements IntegerList {
    public int arraySize = 0;
    public static int numberOfElements = 7;
    public Integer[] arrayInt = new Integer[numberOfElements];

    public void checkItem ( Integer num ) {
        if (num == null) {
            throw new ParameterIsNullException();
        }
    }

    public static void checkIndex ( int index ) {
        if (index >= numberOfElements | index < 0) {
            throw new IndexOutOfArrayLengthException();
        }
    }

    public void checkSize () {
        if (this.arraySize >= numberOfElements) {
            throw new ArrayIsFullException();
        }
    }

    public void checkIndexItem ( Integer index ) {
        if (arrayInt[index] == null) {
            throw new ItemNotFoundException();
        }
    }

    //Метод "add" находит первую попавшуюся ячейку где есть null и ставит в нее добавляемый элемент, таким образом
    //даже если между элементами в массиве будет пусто, сначала метод добавит элементы в пустые места
    public Integer add ( int num ) {
        checkItem(num);
        checkSize();
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (arrayInt[i] == null) {
                arrayInt[i] = num;
                arraySize++;
                break;
            }
        }
        return num;
    }

    public Integer set ( int index, int num ) {
        checkItem(num);
        checkIndex(index);
        if (arrayInt[index] == null) {
            arraySize++;
        }
        arrayInt[index] = num;
        return num;
    }

    public Integer add ( int index, int num ) {
        checkItem(num);
        checkIndex(index);
        checkSize();
        if (arrayInt[index] == null) {
            set(index, num);
        } else {
            for (int i = index; i < numberOfElements - 1; i++) {
                if (arrayInt[i + 1] == null) {
                    for (int j = i + 1; j > index; j--) {
                        arrayInt[j] = arrayInt[j - 1];
                    }
                    arrayInt[index] = num;
                    arraySize++;
                    break;
                }
            }
        }
        return num;
    }

    public Integer removeIndex ( int index ) {
        checkIndex(index);
        checkIndexItem(index);
        Integer deletedNum = arrayInt[index];
        for (int i = index; i < numberOfElements - 1; i++) {
            arrayInt[i] = arrayInt[i + 1];
        }
        arraySize--;
        return deletedNum;
    }

    public Integer remove ( int num ) {
        checkItem(num);
        boolean found = false;
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (num == arrayInt[i]) {
                removeIndex(i);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ItemNotFoundException();
        }
        return num;
    }

    public Integer indexOf ( int num ) {
        checkItem(num);
        int searchIndex = -1;
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (arrayInt[i].equals(num)) {
                searchIndex = i;
            }
        }
        return searchIndex;
    }

    public Integer lastIndexOf ( int num ) {
        checkItem(num);
        int searchIndex = -1;
        for (int i = numberOfElements - 1; i > -1; i--) {
            if (arrayInt[i].equals(num)) {
                searchIndex = i;
            }
        }
        return searchIndex;
    }

    public Integer get ( int index ) {
        checkIndex(index);
        return arrayInt[index];
    }

    public void clear () {
        arrayInt = new Integer[numberOfElements];
        arraySize = 0;
    }

    public int size () {
        return arraySize;
    }

    public boolean isEmpty () {
        return arraySize == 0;
    }

    public int[] toArray () {
        int[] otherArray = new int[this.arraySize];
        for (int i = 0; i < otherArray.length; i++) {
            otherArray[i] = this.get(i);
        }
        return otherArray;
    }

    public boolean equalArrays ( int[] arrayOne, int[] arrayTwo ) {
        return Arrays.equals(arrayOne, arrayTwo);
    }

    @Override
    public boolean equals ( IntegerList otherList ) {
        boolean equality = true;
        if (otherList == null) {
            throw new ParameterIsNullException();
        }
        if (arraySize != otherList.size()) {
            return false;
        } else {
            for (int i = 0; i < numberOfElements - 1; i++) {
                if (!equalArrays(this.toArray(), otherList.toArray())) {
                    equality = false;
                    break;
                }
            }
        }

        return equality;
    }

    @Override
    public String toString () {
        return "Contents of your array: " + Arrays.toString(this.toArray());
    }

    public static int[] generateRandomArray () {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    private static void swapElements ( int[] arr, int indexA, int indexB ) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble ( int[] arr ) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection ( int[] arr ) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion ( int[] arr ) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static long sortTime ( int sortIndex, int[] originalArray, int[] copyOne, int[] copyTwo ) {
        long start = System.currentTimeMillis();
        switch (sortIndex) {
            case 1:
                sortBubble(originalArray);
                System.out.println("Время сортировки пузырьком (индекс 1): " + (System.currentTimeMillis() - start));
                break;
            case 2:
                sortSelection(copyOne);
                System.out.println("Время сортировки выбором (индекс 2): " + (System.currentTimeMillis() - start));
                break;
            case 3:
                sortInsertion(copyTwo);
                System.out.println("Время сортировки вставкой (индекс 3): " + (System.currentTimeMillis() - start));
                break;
        }
        return System.currentTimeMillis() - start;
    }

    public boolean contains( int num) {
        int[] sortedArray = this.toArray();
        sortSelection(sortedArray);
        int min = 0;
        int max = sortedArray.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (num == sortedArray[mid]) {
                return true;
            }

            if (num < sortedArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
