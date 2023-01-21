package com.example.invoiceeq.service

import com.example.invoiceeq.repository.ClientRepository
import com.example.invoiceeq.repository.DetailRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DetailServiceTest {
    @InjectMocks
    lateinit var detailService: DetailService

    @Mock
    lateinit var detailRepository: DetailRepository
}