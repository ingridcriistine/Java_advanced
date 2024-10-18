import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
    
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < 6; i++)
            list.add(i);
        
        List<Integer> result = list.stream()
            .map(n -> n * n)
            .filter(n -> n % 2 == 1)
            .sorted((n, m) -> m - n)
            .limit(2)
            .collect(Collectors.toList());
            
        for (Integer value : result)
            System.out.println(value); // 25 9
            
        Integer sum = Stream.of(1, 2, 3, 4, 5)
            .map(n -> n * n)
            .filter(n -> n % 2 == 1)
            .sorted()
            .skip(1)
            .reduce(0, (acc, crr) -> acc + crr);
        System.out.println(sum); // 34
        
        boolean allPrimes = Stream.of(2, 3, 5, 7, 11)
            .allMatch(n ->
            {
            Integer a = n / 2;
            Integer r = ((int)Math.pow(a, n - 1) % n);
            return r == 1;
            });
        System.out.println(allPrimes); // true
        
        Stream.of(3, 5, 7, 8, 11)
            .filter(n -> n % 2 == 0)
            .findAny()
            .ifPresent(n -> System.out.println(n)); // 8

        long odds = IntStream.range(1, 300)
            .filter(n -> n % 2 == 1)
            .count();
        System.out.println(odds); // 150
        
        // Lendo de Arquivos
        long lines = Files.lines(Paths.get("Main.java"), Charset.defaultCharset())
            .filter(line -> line.contains("filter"))
            .count();
        System.out.println(lines); // 5
    }
}