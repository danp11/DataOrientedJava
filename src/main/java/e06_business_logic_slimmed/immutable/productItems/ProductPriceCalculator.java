package e06_business_logic_slimmed.immutable.productItems;


import e06_business_logic_slimmed.immutable.productItems.ProductItems.ProductItem;

public interface ProductPriceCalculator {
  ProductItems.PricedProductItem calculate(ProductItem productItems);
}
