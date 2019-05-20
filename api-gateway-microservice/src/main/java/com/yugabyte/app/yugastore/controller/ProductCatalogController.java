package com.yugabyte.app.yugastore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yugabyte.app.yugastore.domain.ProductMetadata;
import com.yugabyte.app.yugastore.exception.NotEnoughProductsInStockException;
import com.yugabyte.app.yugastore.service.ProductCatalogServiceRest;

/**
 * The controller that handles all calls related to the product catalog.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class ProductCatalogController {

  // The REST service handler that interacts with the product catalog microservice.
  private final ProductCatalogServiceRest productCatalogServiceRest;

  @Autowired
  public ProductCatalogController(ProductCatalogServiceRest productCatalogServiceRest) {
    this.productCatalogServiceRest = productCatalogServiceRest;
  }


  /**
   * Return details of a single product.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/product/{asin}", produces = "application/json")
  public @ResponseBody ResponseEntity<ProductMetadata> getProductDetails(@PathVariable("asin") String asin) {

    ProductMetadata productMetadata = productCatalogServiceRest.getProductDetails(asin);
    return new ResponseEntity<ProductMetadata>(productMetadata, HttpStatus.OK);
  }

}
