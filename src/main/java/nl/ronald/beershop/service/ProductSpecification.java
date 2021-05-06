package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Product;
import org.springframework.data.jpa.domain.Specification;
import java.util.Collection;

public class ProductSpecification {
    public static Specification<Product> withCategories(Collection<Long> categories) {
        if (categories == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.in(root.get("category_id")).value(categories);
        }
    }
    public static Specification<Product> withPrice(Double price) {
        if (price == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("price"), price);
        }
    }

    public static Specification<Product> withTastes(Collection<Long> tastes) {
        if (tastes == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.in(root.get("taste")).value(tastes);
        }
    }
}