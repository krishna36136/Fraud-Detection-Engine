package com.krishna.Transaction_Service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.Transaction_Service.dto.CreateTransactionRequest;
import com.krishna.Transaction_Service.dto.CreateTransactionResponse;
import com.krishna.Transaction_Service.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    

    public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}



	@PostMapping
    public CreateTransactionResponse createTransaction(
            @Valid
            @RequestBody
            CreateTransactionRequest request) {

        return transactionService.createTransaction(request);
    }
}