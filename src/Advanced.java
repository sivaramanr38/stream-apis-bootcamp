import java.util.*;
import java.util.stream.Collectors;

record Product(int id, String name, double price){};
public class Advanced {
    static List<Product> products = Arrays.asList(
            new Product(1, "Laptop A", 25000.0),
            new Product(2, "Dell Laptop B", 18000.0),
            new Product(3, "Laptop C", 22000.0),
            new Product(4, "Laptop D", 15000.0)
    );

    public static void main(String[] args) {

/*
1. join names of laptops with price > 20000 into a single string
*/
        String costlyProductNames = products.stream()
                .filter(product -> product.price() > 20000)
                .map(product -> product.name())
                .collect(Collectors.joining(", "));
        System.out.println("costlyProductNames : " + costlyProductNames);

/*
2. find the maximum price among laptops with price > 20000
*/

        Optional<Double> maxPrice = products.stream()
                .filter(product -> product.price() > 20000)
                .map(Product::price)
                .max(Comparator.naturalOrder());

        System.out.println("maxPrice: " + maxPrice);
        OptionalDouble maxDoublePrice = products.stream()
                .filter(product -> product.price() > 20000)
                .mapToDouble(Product::price)
                .max();
        System.out.println("maxDoublePrice: " + maxDoublePrice);

/*
3. check if any laptop costs more than 20000
*/
        boolean moreThan20K = products.stream()
                .anyMatch(product -> product.price() > 20000);

        System.out.println("moreThan20K : " + moreThan20K);

/*
4. check if all laptops cost more than 20000
*/

        boolean allMoreThan20K = products.stream()
                .allMatch(product -> product.price() > 20000);
        System.out.println("allMoreThan20K : " + allMoreThan20K);

/*
5. find the first laptop with price > 20000
*/
        Optional<Product> firstCostlyProduct = products.stream()
                .filter(product -> product.price() > 20000)
                .findFirst();
        firstCostlyProduct.ifPresent(System.out::println);


/*
6. find the product with the minimum price
*/

        Optional<Double> minPrice = products.stream()
                .map(Product::price)
                .min(Comparator.naturalOrder());

        System.out.println("minPrice : " + minPrice);
        OptionalDouble minPriceDoub = products.stream()
                .mapToDouble(Product::price)
                .min();
        System.out.println("minPriceDoub : " + minPriceDoub);

        Optional<Product> minProduct = products.stream()
                .min(Comparator.comparingDouble(Product::price));
        minProduct.ifPresent(System.out::println);

/*
7. how can you collect products into a map with product id as key and product name as value
*/

        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::id, p -> p));
        System.out.println("productMap : " + productMap);

/*
8. collect products into a map with product name as key and product price as value
*/
        Map<String, Double> productNamePriceMap = products.stream()
                .collect(Collectors.toMap(Product::name, Product::price));

        productNamePriceMap.forEach((k, v) ->
                System.out.println("Name : " + k + " Value : " + v));

/*
9. collect products into a map with product name as key and length of the name as value
*/

        Map<String, Integer> nameWithLengh = products.stream()
                .collect(Collectors.toMap(
                        Product::name,
                        p -> p.name().length()));

        nameWithLengh.forEach((productName, nameLength) ->
                System.out.println("Name is : " + productName + ", name length : " + nameLength));

/*
12. collect expensive laptops into a map with name as key and price as value
*/
        Map<String, Double> nameWithProductPrice = products.stream()
                .filter(product -> product.price() > 20000)
                .collect(Collectors.toMap(
                        p -> p.name(),
                        p -> p.price()
                ));

        nameWithProductPrice.forEach((productName, price) ->
                System.out.println("Name is : " + productName + ", price : " + price));


/*
15. collect prices of expensive laptops as Double objects
*/
        List<Double> priceAsDouble = products.stream()
                .filter(product -> product.price() > 20000)
                .map(Product::price)
                .toList();


        List<Double> totalPriceList = products.stream()
                .filter(p -> p.price() > 20000)
                .mapToDouble(Product::price)
                .boxed()
                .toList();
        System.out.println(totalPriceList);
// Output: [25000.0, 22000.0]

/*
16. collect prices of expensive laptops as integers
*/
        List<Integer> priceAsInteger = products.stream()
                .filter(product -> product.price() > 20000)
                .map(p -> Integer.valueOf((int) p.price()))
                .toList();

        System.out.println("priceAsInteger : " + priceAsInteger);

/*
17. how can you print prices of expensive laptops as int objects and add 1 to each price
*/

        List<Integer> expPriceWihMoi = products.stream()
                .filter(product -> product.price() > 20000)
                .map(p -> (int) p.price() + 1)
                .collect(Collectors.toList());
        System.out.println("expPriceWihMoi : " + expPriceWihMoi);


/*
18. how can you take a list of products and group them into categories based on whether their price is above or below 20000
*/

        Map<String, List<Product>> catogories = products.stream()
                .collect(Collectors.groupingBy(product -> product.price() > 20000 ? "Expensive" : "Affordable"));

        catogories.forEach((key, value) ->
                System.out.println(key + " " + value));

/*
11. group laptops by the first letter of their name
*/
        Map<Character, List<Product>> catogoriedByName = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.name().charAt(0)

                ));
        catogoriedByName.forEach((k, v) ->
                System.out.println("first char : " + k + ", value : " + v)
        );

/*
12. group laptops by price rounded to the nearest thousand
*/
        Map<Long, List<Product>> catogoriedByRoundOff = products.stream()
                .collect(Collectors.groupingBy(
                        product -> Math.round(product.price() / 1000) * 1000
                ));
        catogoriedByRoundOff.forEach((k, v) ->
                System.out.println("Nearby thousands : " + k + ", value : " + v)
        );

/*
14. how can you group laptops into multiple price buckets (e.g., <15000, 15000–20000, >20000)
*/

        Map<String, List<Product>> variety = products.stream()
                .collect(Collectors.groupingBy(
                        product -> {
                            if(product.price() > 20000) return "20000 above";
                            if(product.price() > 15000) return "15000 above";
                            return "row";
                        }
                ));

        System.out.println(variety);

//        PartitioningBy

/*
1. how can you partition laptops by price > 20000
*/
        Map<Boolean, List<Product>> partitionByExpensive = products.stream()
                .collect(Collectors.partitioningBy(
                        product -> product.price() > 20000
                ));
        System.out.println(partitionByExpensive);
    }
}
