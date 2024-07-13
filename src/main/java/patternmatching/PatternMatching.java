package patternmatching;

public class PatternMatching {

    sealed interface SaleItem{}
    record Book(String title, double price) implements SaleItem { }
    record Electronics(String name, double price) implements SaleItem { }
    record Apparel(String type, String size, double price) implements SaleItem { }


    //using record patterns to deconstruct a record to its components of Apparel
    public static double computeDiscount3(SaleItem item) {
        return switch (item) {
            case Apparel (String type, String size, double price)
                    when size.equals("XXS")                         -> 0.6 * price;
            case Apparel apparel                                    -> 0.3 * apparel.price();
            case Book book                                          -> 0.4 * book.price();
            case Electronics electronics                            -> 0.2 * electronics.price();
            case null, default                                      -> 0;
        };
    }


}