import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminateOperations {

    public static void main(String[] args) {

/*
1. how can you take a list of words and print each one on a new line
*/
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        words.stream().forEach(System.out::println);

/*
2. how can you take a list of words with duplicates and collect them into a set
*/
        List<String> wordsWithDuplicates = Arrays.asList("apple", "banana", "cherry", "apple");

        Set<String> uniqueWords = wordsWithDuplicates.stream()
//                .distinct()
                .collect(Collectors.toSet());


/*
3. how can you take a list of numbers and calculate their sum
*/
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
//                .reduce(0, (a,b) -> a+b);
        .reduce(Integer::sum)
                .orElse(0);
        System.out.println("sum: "+sum);

        int summ = numbers.parallelStream()
                .reduce(0, (subtotal, element) -> subtotal + element, Integer::sum);
        System.out.println("summ: "+sum);

/*
25. how can you take a list of words and combine them into a single string
*/
        List<String> fullWords = Arrays.asList("apple", "banana", "cherry");
        String str = fullWords.stream()
                .reduce("", (a,b) -> a+b);
        System.out.println("str : "+str);

/*
26. how can you take a list of numbers and calculate their factorial
*/
        List<Integer> numbersForFac = Arrays.asList(1, 2, 3, 4, 5);
        int fact = numbersForFac.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("fact : "+fact );


/*
        Generate 10 even numbers
*/
        List<Integer> evenNums = Stream.iterate(0, n -> n + 2)
                .limit(10)
                .toList();

        System.out.println("evenNums : "+evenNums);

/*
        Generate first 10 Fibonacci numbers and collect into a List
*/
        List<Integer> fibNums = Stream.iterate(new int[]{0,1}, arr -> new int[]{arr[1], arr[0]+arr[1]})
                .limit(10)
                .map(arr -> arr[0])
                .toList();
        System.out.println("fibNums : "+fibNums);

/*
27. how can you write a method to check if a given year is a leap year
*/
//        int year = 2012;
//        boolean isLeap = Stream.of(year)
//                .anyMatch(y -> (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0));

//        System.out.println(year + " is leap year? " + isLeap);

/*
28. how can you take a list of words and collect them into a new ArrayList
*/
        List<String> wordsList = Arrays.asList("apple", "banana", "cherry");
        List<String> listOfWords = wordsList.parallelStream()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("listOfWords : "+listOfWords);

/*
29. how can you take a list of words and convert it into an array
*/
        List<String> wordssList = Arrays.asList("apple", "banana", "cherry");

        String[] strArr = wordssList.stream()
                .toArray(String[]::new );
        System.out.println("strArr : "+Arrays.toString(strArr));

/*
30. how can you take a list of numbers and find the minimum value
*/
        List<Integer> numberss = Arrays.asList(10, 5, 15, 3, 20);

        Optional<Integer> minValue =  numberss.stream()
                .min(Comparator.naturalOrder());
        System.out.println("Min value : "+(minValue.isPresent()? minValue: 0));

/*
31. how can you take a list of numbers and check if any of them are greater than 3
*/
        List<Integer> numberssss = Arrays.asList(1, 2, 3, 4, 5);

        boolean anyGreatThan3 = numberssss.stream()
                .anyMatch(n -> n > 3);
        System.out.println("anyGreatThan3 : "+anyGreatThan3);

/*
32. how can you take a list of numbers and check if all of them are greater than 0
*/
        List<Integer> numbersRandom = Arrays.asList(1, 2, 3, 4, 5);

        boolean isAllNonZero = numbersRandom.stream()
                .allMatch(n -> n > 0);
        System.out.println(" isAllNonZero : "+isAllNonZero);

/*
33. how can you take a list of numbers and check if none of them are greater than 5
*/
        List<Integer> numberrs = Arrays.asList(1, 2, 3, 4, 5);
        boolean nonegreatThan5 = numberrs.stream()
                .noneMatch(n -> n > 5);
        System.out.println("nonegreatThan5 : "+nonegreatThan5);

/*
34. how can you take a list of words and find the first element
*/
        List<String> worrds = Arrays.asList("apple", "banana", "cherry");
        Optional<String> firstElm = worrds.stream()
                .findFirst();

        firstElm.ifPresent(System.out::print);


/*
35. how can you take a list of numbers and generate summary statistics like count, sum, min, average, and max
*/
        List<Integer> nummbers = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = nummbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("stats  count: "+ stats.getCount());
        System.out.println("stats  sum: "+stats.getSum());
        System.out.println("stats  min: "+stats.getMin());
        System.out.println("stats  max: "+stats.getAverage());
    }
}
