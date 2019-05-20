package com.yugabyte.app.yugastore.rest.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yugabyte.app.yugastore.domain.ProductMetadata;

@FeignClient("products-microservice")
@RequestMapping("/products-microservice")
public interface ProductCatalogRestClient {
  
  @RequestMapping("/product/{asin}")
  ProductMetadata getProductDetails(@PathVariable("asin") String asin);
  
  @RequestMapping("/products")
  List<ProductMetadata> getProducts(@RequestParam("limit") int limit, @RequestParam("offset") int offset);
}
