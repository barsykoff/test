import java.util.Arrays;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 3;

        findMax(arr, k);
    }

    private static void findMax(int[] arr, int k) {
        if (arr.length == 0 || k > arr.length || k < 0) {
            System.out.println("Program is failed");
        } else {
            int[] res = findMaxSimple(arr, k);
            System.out.println(Arrays.toString(res));

            int[] res1 = findMaxSmart(arr, k);
            System.out.println(Arrays.toString(res1));
        }
    }

    private static int[] findMaxSimple(int[] arr, int k) {
        int[] result = new int[arr.length - k + 1];

        for (int i = 0; i < arr.length - k + 1; i++) {
            int curr = arr[i];
            result[i] = curr;
            for (int j = 1; j < k; j++) {
                int next = arr[i + j];
                if (next > curr) {
                    curr = next;
                    result[i] = curr;
                }
            }
        }

        return result;
    }

    private static int[] findMaxSmart(int[] arr, int k) {
        LinkedList<Integer> tmpList = new LinkedList<>();
        int[] result = new int[arr.length - k + 1];
        int resultPos = 0;

        int curr = arr[0];

        // Сначала заполняем временный лист макс. значением из первого подмассива
        for (int i = 1; i < k; i++) {
            int next = arr[i];
            if (next > curr) {
                curr = next;
            }
        }
        tmpList.offer(curr);

        // Далее берем следующий после найденного макс. элемента элемент и
        // начинаем сравнивать каждый последующий с предыдущим из листа
        for (int i = k - 1; i < arr.length; i++) {
            while (!tmpList.isEmpty() && tmpList.peekLast() < arr[i]) {
                // Если следующий больше, то предыдущий удаляем из листа (справа)...
                tmpList.pollLast();
            }
            // ... и записываем следующий (справа)
            tmpList.offerLast(arr[i]);

            // В итоговый массив переносим добавленный раньше всех элемент (слева)
            result[resultPos] = tmpList.peekFirst();
            // Как только перенесли, удаляем из листа (слева)
            if (!tmpList.isEmpty() && tmpList.peekFirst() == arr[resultPos]) {
                tmpList.pollFirst();
            }
            resultPos++;
        }

        return result;
    }
}
