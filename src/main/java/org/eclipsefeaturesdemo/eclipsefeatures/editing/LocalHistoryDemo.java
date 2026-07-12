package org.eclipsefeaturesdemo.eclipsefeatures.editing;

public class LocalHistoryDemo {

    public static void main(String[] args) {

        Product product = new Product("SKU-1001", "Wireless Mouse", 24.99);

        System.out.println(product.getDisplayText());
    }

    private static class Product {

        private String sku;
        private String name;
        private double price;

        Product(String sku, String name, double price) {
            this.sku = sku;
            this.name = name;
            this.price = price;
        }

        private String getDisplayText() {
            return sku + " - " + name + " ($" + price + ")";
        }
    }
}