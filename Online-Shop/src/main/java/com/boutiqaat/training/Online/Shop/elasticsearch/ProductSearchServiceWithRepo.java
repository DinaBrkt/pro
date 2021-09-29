package com.boutiqaat.training.Online.Shop.elasticsearch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boutiqaat.training.Online.Shop.Repositories.ProductRepository;

@Service
public class ProductSearchServiceWithRepo {

  private ProductRepository productRepository;

  public void createProductIndexBulk(final List<Product> products) {
    productRepository.saveAll(products);
  }

  public void createProductIndex(final Product product) {
    productRepository.save(product);
  }
}