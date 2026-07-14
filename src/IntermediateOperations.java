import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Java Record for Person
record Person(String name, int age) {}

public class IntermediateOperations {

    public static void main(String[] args) {
/*
1. Filter a list of integers and collect only the even numbers
    into a new list using streams
*/
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("1. " + result);

/*
2. how can you transform a list of strings into a list of their lengths
*/
        List<String> words = Arrays.asList("apple", "banana", "cherry");

        List<Integer> wordsLength = words.stream()
                .map(w -> w.length())
                .toList();
        System.out.println("2. " + wordsLength);


/*
3. how can you flatten a list of lists into a single list in upper case using Java Streams
*/
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("cherry", "date"),
                Arrays.asList("elderberry", "fig")
        );

        List<String> flattenedFruits = listOfLists.stream()
                .flatMap(list -> list.stream())
                .map(s -> s.toUpperCase())
                .toList();
        System.out.println("3. flattenedFruits: " + flattenedFruits);

/*
4. how can you remove duplicates from a list of integers using Java Streams
*/
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        List<Integer> distinctNumbers = numbersWithDuplicates.stream()
                .distinct()
                .toList();
        System.out.println("4. distinctNumbers " + distinctNumbers);

/*
5. how can you sort a list of strings in natural order using Java Streams
*/
        List<String> unOrderedWords = Arrays.asList("cherry", "banana", "apple");

        List<String> orderedWords = unOrderedWords.stream()
                .sorted()   //normally this works for natural order
                .toList();

        System.out.println("5. orderedWords " + orderedWords);

/*
6. how can you sort a list of strings in reverse order using Java Streams and a comparator
*/
        List<String> mixedWords = Arrays.asList("cherry", "banana", "apple");

        List<String> reversedWords = mixedWords.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("6. reversedWords " + reversedWords);


/*
7. how can you sort a list of strings by their length
*/
        List<String> wordss = Arrays.asList("cherry", "banana", "appleee");
        List<String> orderedByLength = wordss.stream()
//                .sorted(Comparator.comparingInt(str -> str.length()))
                .sorted(Comparator.comparingInt(String::length))    // both are fine. Remember -> comparingInt() only for int comparison
                .toList();
        System.out.println("7. orderedByLength " + orderedByLength);


/*
8. how can you sort a list of objects first by name and then by age
*/
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Alice", 22)
        );

        List<Person> sortedMFs = people.stream()
                .sorted(
                        Comparator.comparing((Person person) -> person.name().toUpperCase())
                                .thenComparing(person -> person.age())
                )
                .toList();

//        Another important way
//        List<Person> sortedMFss = people.stream()
//                .sorted(
//                        Comparator.comparing(Person::name, String.CASE_INSENSITIVE_ORDER)
//                                .thenComparingInt(Person::age)
//                )
//                .toList();

        System.out.println("8. sortedMFs " + sortedMFs);

/*
9. how can you sort a list of numbers by their absolute values
*/
        List<Integer> mixedNumbers = Arrays.asList(-5, 3, -1, 7, -9);

        List<Integer> absSorting = mixedNumbers.stream()
//                .sorted(Comparator.comparingInt(n -> Math.abs(n)))
                .sorted(Comparator.comparingInt(Math::abs))
                .toList();
        System.out.println("9. absSorting " + absSorting);

/*
10. how can you prioritize a specific word (like "apple") to appear first before sorting the rest alphabetically
*/
        List<String> fruits = Arrays.asList("banana", "apple", "cherry", "mango", "apple");

        List<String> appleFirstfruits = fruits.stream()
                .sorted((a, b) -> {
                    if (a.equals("apple")) return -1;    // a comes first
                    if (b.equals("apple")) return 1;     // b comes first
                    return a.compareTo(b);
                })
                .distinct()
                .toList();
//        (a, b) are the two elements being compared during sorting.
//        Returning -1 means a comes first.
//        Returning 1 means b comes first.
//        Returning a.compareTo(b) applies normal alphabetical order.
        System.out.println("10. appleFirstfruits " + appleFirstfruits);

        //Another varient
//        Comparator<String> customComparator = (a, b) -> {
//            if (a.equals("apple")) return -1;   // "apple" always comes first
//            if (b.equals("apple")) return 1;
//            return a.compareTo(b);             // otherwise sort alphabetically
//        };
//
//        List<String> sortedFruits = fruits.stream()
//                .sorted(customComparator)
//                .collect(Collectors.toList());
//
//        System.out.println(sortedFruits);
        // Output: [apple, banana, cherry, mango]

/*
11. how can you sort a list of people by their age in descending order
*/
        Comparator<Integer> cusCom = (a, b) -> {
            return b.compareTo(a);
        };

        List<Integer> ages = Arrays.asList(25, 40, 30, 22, 35);
        List<Integer> reversedAge = ages.stream()
//                .sorted((a,b) -> {
//                    return b.compareTo(a);
//                })
                .sorted(cusCom)
                .toList();
        System.out.println("11. reversedAge " + reversedAge);

/*
11. sort a list of numbers so that odd numbers come first followed by even numbers, both in ascending order
*/
        List<Integer> freakyNumbers = Arrays.asList(10, 5, 3, 7, 1);

        Comparator<Integer> freakingComparator = (a, b) -> {
            if (a % 2 == 1) return -1;
            if (b % 2 == 1) return 1;
            return a.compareTo(b);
        };

        List<Integer> freakyResult = freakyNumbers.stream()
                .sorted(freakingComparator)
                .toList();

        System.out.println("12. freakyResult " + freakyResult);


/*
12. find the eldest person in the list
*/
        Comparator<Person> sortByAge = (a,b) -> {
            return Integer.compare(a.age(), b.age());
        };
        Comparator<Person> descByAge = sortByAge.reversed();

//        List<Person> eldest = people.stream()
//                .sorted(descByAge)
//                .limit(1)
//                .toList();
//
                List<Person> eldest = people.stream()
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .limit(1)
                .toList();

        System.out.println("12. eldest "+ eldest);


/*
13. find the eldest person by writing a method and accept 3 persons: interview question
*/
        Comparator<Person> peoples = Comparator.comparingInt(Person::age);

        int oldAge = eldestGuy(peoples, new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Alice", 65));
        System.out.println("13. "+ oldAge);

/*
14. Write a method that accepts a list of words and prints their lengths
    using Java Streams and mapToInt. Interview-style question.
*/
        List<String> wordsTolength = Arrays.asList("apple", "banana", "cherry");

        int[] wordsTolengths = wordsTolength.stream()
                .mapToInt(String::length)
                .peek(len -> System.out.print(len+" "))
                .toArray();

        Integer[] wordsTolengthsIntegerArray = wordsTolength.stream()
                .map(String::length)
                .peek(len -> System.out.print(len+" "))
                .toArray(Integer[]::new);


/*
18. how can you convert a list of numeric strings into doubles and print them
*/
        List<String> numbericStrings = Arrays.asList("1.1", "2.2", "3.3");

        double[] doubleValues = numbericStrings.stream()
                .mapToDouble(Double::valueOf)
                .peek(System.out::println)
                .toArray();

        Double[] doubleValues2 = numbericStrings.stream()
                .map(Double::valueOf)
                .peek(System.out::println)
                .toArray(Double[]::new);

/*
19. how can you take a list of words and print out the ASCII values of all characters
*/
        List<String> wordsList = Arrays.asList("apple", "banana", "cherry");
        int[] ASCIIChars = wordsList.stream()
                .flatMapToInt(String::chars)
//                .peek(System.out::println)
                .toArray();
        System.out.println(Arrays.toString(ASCIIChars));

/*
20. how can you take a list of numeric strings and print out their double values
*/
        List<String> doubleStrings = Arrays.asList("1.1", "2.2", "3.3");
        double[] doubleArrayRes = doubleStrings.stream()
                .mapToDouble(Double::valueOf)
                .toArray();
        System.out.println("doubleArrayRes: "+ Arrays.toString(doubleArrayRes));

/*
21. how can you take a list of numeric strings and print out their long values
*/
        List<String> longStrings = Arrays.asList("123", "456", "789");

        long[] longValues = longStrings.stream()
                .mapToLong(Long::valueOf)
                .toArray();

        System.out.println("longValues : "+Arrays.toString(longValues));


    }

    public static int eldestGuy(Comparator comparator, Person p1, Person p2, Person p3) {

        Person elderst = p1;

        if(Integer.compare(elderst.age(), p2.age()) < 0) elderst = p2;
        if(Integer.compare(elderst.age(), p3.age()) < 0) elderst = p3;
        return elderst.age();
    }
}

