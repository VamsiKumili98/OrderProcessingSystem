package com.codewithvamsi.orderprocessing.downstream;

import com.codewithvamsi.orderprocessing.model.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClientImpl implements InventoryClient{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${inventory.service.host}")
    private String host;

    @Value("${inventory.service.port}")
    private String port;

    @Value("${inventory.service.baseEndPoint}")
    private String baseEndPoint;

    @Override
    public Product getProduct(Long id) {
        String url = String.format(
                "%s:%s/%s/%d",
                host,
                port,
                baseEndPoint,
                id
        );
        ResponseEntity<Product> product = restTemplate.getForEntity(url, Product.class);
        return product.getBody();
    }
}
