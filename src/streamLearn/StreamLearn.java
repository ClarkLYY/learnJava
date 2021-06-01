package streamLearn;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamLearn {
    public static void main(String[] args) {

        var result = Stream.of("My","Mine")
                .flatMap(str -> str.chars().mapToObj(i -> (char)i))
                .collect(Collectors.toSet());

        Iterator it = result.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        result.stream().forEach(System.out::print);

    }
}
