package org.eclipsefeaturesdemo.retail.debugging.streams;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.eclipsefeaturesdemo.retail.config.RetailProperties;
import org.eclipsefeaturesdemo.retail.dto.InventoryReportRow;
import org.eclipsefeaturesdemo.retail.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class StreamInventoryService {

    private final ProductRepository productRepository;
    private final RetailProperties retailProperties;

    public StreamInventoryService(
            ProductRepository productRepository,
            RetailProperties retailProperties) {
        this.productRepository = productRepository;
        this.retailProperties = retailProperties;
    }

    public List<String> findActiveProductNames() {
        return productRepository.findAll().stream().filter(product -> product.isActive()).map(product -> product.getName()).sorted((left, right) -> left.compareToIgnoreCase(right)).toList();
    }

    public List<InventoryReportRow> findTopInventory(int limit) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must be at least 1");
        }

        return productRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(product -> {
                    BigDecimal inventoryValue = product.getPrice()
                            .multiply(BigDecimal.valueOf(
                                    product.getAvailableQuantity()));

                    boolean lowStock = product.getAvailableQuantity()
                            <= retailProperties.lowStockThreshold();

                    return new InventoryReportRow(
                            product.getId(),
                            product.getName(),
                            product.getCategory(),
                            product.getPrice(),
                            product.getAvailableQuantity(),
                            inventoryValue,
                            lowStock);
                })
                .limit(limit)
                .sorted(Comparator.comparing(
                        InventoryReportRow::inventoryValue).reversed())
                .toList();
    }
}
