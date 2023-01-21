package com.example.invoiceeq.service

import com.example.invoiceeq.model.Product
import com.example.invoiceeq.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository



    fun list ():List<Product>{
        return productRepository.findAll()
    }

    fun save (product: Product):Product{
        try{
            product.description?.takeIf{validatePlate(it)}
                    ?:throw  Exception("Error placa")
            product.description?.takeIf { it.trim().isNotEmpty()}
                   ?:throw Exception("Error placa")
            product.description?.takeIf {!it.contains("-")}
                    ?:throw Exception("Error placa")
            product.description?.takeIf {it.matches(Regex("\\d{8}"))}
                    ?:throw Exception("Error placa")
            product.description?.takeIf {it.matches(Regex("^[A-Z]{3}-[0-9]{4}$"))}
                    ?:throw Exception("Error placa")
            product.description?.takeIf {it.matches(Regex("^(?i)[^W][A-Z]{3}-[0-9]{4}$"))}
                    ?:throw Exception("Error placa")
            product.description?.takeIf {it.count{c -> c == '-'}==1}
                    ?:throw Exception("Error placa")

            return productRepository.save(product)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(product: Product):Product{
        try {
            productRepository.findById(product.id)
                ?: throw Exception("El id ${product.id} en products no existe")
            return productRepository.save(product)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
    fun findStockMinium(stock:Long):List<Product>?{
        return productRepository.findStockMin(stock)
    }


    fun updateTotal(product: Product):Product{
        try{
            val response = productRepository.findById(product.id)
                ?:throw Exception("El ${product.id} en producto no existe")
            return productRepository.save(product)
            response.apply{
                stock = product.stock
                price = product.price
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }

    fun validatePlate(description: String):Boolean{
            return true
    }

}


