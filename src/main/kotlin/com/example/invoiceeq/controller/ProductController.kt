package com.example.invoiceeq.controller


import com.example.invoiceeq.model.Product
import com.example.invoiceeq.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list():List<Product>{
        return productService.list()
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product?{
        return productService.save(product)

    }


    @GetMapping("/products/{stock}")
    fun listStocks(@PathVariable("stock") stock:Long):ResponseEntity<*>{
        return ResponseEntity(productService.findStockMinium(stock), HttpStatus.ACCEPTED)
    }

    @PutMapping
    fun update(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(product), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateTotal(product), HttpStatus.ACCEPTED)
    }
}
