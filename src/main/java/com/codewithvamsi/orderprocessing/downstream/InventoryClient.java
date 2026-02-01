package com.codewithvamsi.orderprocessing.downstream;

import com.codewithvamsi.orderprocessing.model.response.Product;
import reactor.core.publisher.Mono;

public interface InventoryClient {
    Product getProduct(Long id);

    Mono<Product> getProductAsync(Long id);
}
