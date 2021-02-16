package ru.job4j.generics;

/**Следующий пример показывает, как мы можем выводить
 * массив различного типа, используя один общий метод.
 */
public class GenericMethod {
    // Общий метод printArray
    public static <E> void printArray(E[] inputArray) {
        // Отображаем элементы массива
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Создание массивов типа Integer, Double и Character
        Integer[] intArray = {1, 2, 3, 4, 5 };
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = {'П', 'Р', 'И', 'В', 'Е', 'Т'};

        System.out.println("Массив integerArray содержит:");
        printArray(intArray);   // передать массив Integer

        System.out.println("\nМассив doubleArray содержит:");
        printArray(doubleArray);   // передать массив Double

        System.out.println("\nМассив characterArray содержит:");
        printArray(charArray);   // передать массив Character
    }
}
