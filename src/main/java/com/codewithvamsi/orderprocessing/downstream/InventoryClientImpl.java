package com.codewithvamsi.orderprocessing.downstream;

import com.codewithvamsi.orderprocessing.exception.ProductUnavailableException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import com.codewithvamsi.orderprocessing.model.response.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Component
public class InventoryClientImpl implements InventoryClient{

    private final RestTemplate restTemplate;
    private final WebClient webClient;

    private String url;

    public InventoryClientImpl(RestTemplate restTemplate, WebClient.Builder webClient,
                               @Value("${inventory.service.port}") String port,
                               @Value("${inventory.service.baseEndPoint}") String baseEndPoint,
                               @Value("${inventory.service.host}") String host
                               ){
        this.restTemplate=restTemplate;
        this.url=String.format(
                "%s:%s/%s",
                host,
                port,
                baseEndPoint);
        this.webClient= webClient.clone().baseUrl(url).build();

    }

    @Override
    @CircuitBreaker(name="inventoryCB", fallbackMethod = "fallback")
    @Retry(name="inventoryRetry")
    @Bulkhead(name="inventoryBulkhead",type= Bulkhead.Type.SEMAPHORE)
    public Mono<Product> getProductAsync(Long id) {
        return webClient.get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(Product.class)
                .timeout(Duration.ofSeconds(2))
                .onErrorMap(TimeoutException.class, ex->new com.codewithvamsi.orderprocessing.exception.TimeOutException("Inventory " +
                        "service " +
                        "timed out for product " + id));
    }

    private Mono<Product> fallback(Long id, Throwable ex){
        return Mono.error(new ProductUnavailableException("Inventory service unavailable for product: " + id));
    }

    @Override
    public Product getProduct(Long id) {
        return restTemplate.getForObject(url+"/"+id, Product.class);
    }
}
