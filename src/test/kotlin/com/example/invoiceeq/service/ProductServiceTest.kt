package com.example.invoiceeq.service

import com.example.invoiceeq.model.Client
import com.example.invoiceeq.model.Invoice
import com.example.invoiceeq.model.Product
import com.example.invoiceeq.repository.ProductRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ProductServiceTest {
    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    val jsonString = File("./src/test/resources/product.json").readText(Charsets.UTF_8)
    val productMock = Gson().fromJson(jsonString, Product::class.java)


    @Test
    fun savePlateCorrect() {

        Assertions.assertThrows(Exception::class.java) {
            productMock.apply { description = "ABC-123" }
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }
    @Test
    fun saveProductWhenPlaceIsBlank() {

        Assertions.assertThrows(Exception::class.java) {
            productMock.apply { description = " " }
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }

    @Test
    fun saveProductWhenPlaceIsEight() {
        Assertions.assertThrows(Exception::class.java) {
            productMock.apply { description = "12345678" }
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }
    @Test
    fun saveProductWhenOneScriptPlace() {
        Assertions.assertThrows(Exception::class.java) {
            productMock.apply { description = "ABC123" }
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }
    @Test
    fun saveProductWhenTwoScriptPlace(){
        Assertions.assertThrows(Exception::class.java){
            productMock.apply { description="AB--123" }
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }
    @Test
    fun saveProductWhenPlateIsWithW(){
        Assertions.assertThrows(Exception::class.java){
            productMock.apply { description="WTR-456" }
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }

    /*@Test
    fun saveProductWhenDescriptionIsBlack() {

        Assertions.assertThrows(Exception::class.java) {
            productMock.apply { stock = 0}
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productMock)
            productService.save(productMock)
        }
    }*/


}