package sortLearn;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortTests {

    @Test
    public void InsertionSortTest(){
        sortTest(InsertionSort.class, 100000);
    }


    public void sortTest(Class cls, int N){

        try{
            var constructor = cls.getConstructor();
            var rawInst = constructor.newInstance();
            var start = System.currentTimeMillis();
            if (rawInst instanceof IMutableSorter){
                var arr = gen(N).stream().mapToInt(x->x).toArray();
                var inst = (IMutableSorter)rawInst;
                inst.sort(arr);
                System.out.println("time usage: " + (System.currentTimeMillis() - start));
                assertSorted(arr);
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void assertSorted(int[] arr) {
        Integer min = Integer.MIN_VALUE;
        for (var i: arr){
            if (min > i){
                System.out.println(arr.toString());
                Assert.fail("Array not in sorted order");
            }
            min = i;
        }
    }

    static List<Integer> gen(int n) {
        var t = new Random();
        var list = IntStream.range(0,n)
                .map(x->t.nextInt(10000000))
                .boxed()
                .collect(Collectors.toList());
//        list.stream().forEach(System.out::println);
        return list;
    }
}
