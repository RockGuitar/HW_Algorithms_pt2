package ru.skypro;

import ru.skypro.exceptions.ArrayIsFullException;
import ru.skypro.exceptions.IndexOutOfArrayLengthException;
import ru.skypro.exceptions.ItemNotFoundException;
import ru.skypro.exceptions.ParameterIsNullException;

import java.util.Arrays;

public class StringListRealisation implements StringList {
    public int arraySize = 0;
    public static int numberOfElements = 7;
    public String[] arrayStr = new String[numberOfElements];

    public void checkItem ( String item ) {
        if (item == null) {
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

    public void checkIndexItem ( int index ) {
        if (arrayStr[index] == null) {
            throw new ItemNotFoundException();
        }
    }

    //Метод "add" находит первую попавшуюся ячейку где есть null и ставит в нее добавляемый элемент, таким образом
    //даже если между элементами в массиве будет пусто, сначала метод добавит элементы в пустые места
    public String add ( String item ) {
        checkItem(item);
        checkSize();
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (arrayStr[i] == null) {
                arrayStr[i] = item;
                arraySize++;
                break;
            }
        }
        return item;
    }

    public String set ( int index, String item ) {
        checkItem(item);
        checkIndex(index);
        if (arrayStr[index] == null) {
            arraySize++;
        }
        arrayStr[index] = item;
        return item;
    }

    public String add ( int index, String item ) {
        checkItem(item);
        checkIndex(index);
        checkSize();
        if (arrayStr[index] == null) {
            set(index, item);
        } else {
            for (int i = index; i < numberOfElements - 1; i++) {
                if (arrayStr[i + 1] == null) {
                    for (int j = i + 1; j > index; j--) {
                        arrayStr[j] = arrayStr[j - 1];
                    }
                    arrayStr[index] = item;
                    arraySize++;
                    break;
                }
            }
        }
        return item;
    }

    public String remove ( int index ) {
        checkIndex(index);
        checkIndexItem(index);
        String deletedString = arrayStr[index];
        for (int i = index; i < numberOfElements - 1; i++) {
            arrayStr[i] = arrayStr[i + 1];
        }
        arraySize--;
        return deletedString;
    }

    public String remove ( String item ) {
        checkItem(item);
        boolean found = false;
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (item.equals(arrayStr[i])) {
                remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ItemNotFoundException();
        }
        return item;
    }

    public boolean contains ( String item ) {
        checkItem(item);
        for (String string : arrayStr) {
            if (string.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf ( String item ) {
        checkItem(item);
        int searchIndex = -1;
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (arrayStr[i].equals(item)) {
                searchIndex = i;
            }
        }
        return searchIndex;
    }

    public int lastIndexOf ( String item ) {
        checkItem(item);
        int searchIndex = -1;
        for (int i = numberOfElements - 1; i > -1; i--) {
            if (arrayStr[i].equals(item)) {
                searchIndex = i;
            }
        }
        return searchIndex;
    }

    public String get ( int index ) {
        checkIndex(index);
        return arrayStr[index];
    }

    public void clear () {
        arrayStr = new String[numberOfElements];
        arraySize = 0;
    }

    public int size () {
        return arraySize;
    }

    public boolean isEmpty () {
        return arraySize == 0;
    }

    public String[] toArray () {
        String[] otherArray = new String[this.arraySize];
        for (int i = 0; i < otherArray.length; i++) {
            otherArray[i] = this.get(i);
        }
        return otherArray;
    }

    public boolean equalArrays ( String[] arrayOne, String[] arrayTwo ) {
        return Arrays.equals(arrayOne, arrayTwo);
    }

    @Override
    public boolean equals ( StringList otherList ) {
        boolean equality = true;
        if (otherList == null) {
            throw new ParameterIsNullException();
        }
        if (arraySize != otherList.size()) {
            return false;
        } else {
            for (int i = 0; i < numberOfElements - 1; i++) {
                if (!equalArrays(arrayStr, otherList.toArray())) {
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

}
