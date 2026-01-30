package com.codewithvamsi.orderprocessing.downstream;

import com.codewithvamsi.orderprocessing.model.response.Product;

public interface InventoryClient {
    Product getProduct(Long id);
}
