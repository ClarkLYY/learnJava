package streamLearn;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Parallel {

    public static void main(String[] args) {
        var t = new Random();
        var list = IntStream.range(0,1_000)
                .map(x-> t.nextInt(10_000_000))
                .boxed()
                .collect(Collectors.toList());
        var time0 = System.currentTimeMillis();
        System.out.println(list.stream().max((a,b)->a-b).get());
        System.out.println("time: "+(System.currentTimeMillis()-time0));

//        TreeMap<String,String> map = new TreeMap<>();
//        map.put(null,null);

        //生成串行流

        System.out.println(list.stream().map(x->x+1).filter(x->x<10000).max((a,b)->a-b).get());
        //生成并行流
//        list.parallelStream().forEach(System.out::print);


    }
}
