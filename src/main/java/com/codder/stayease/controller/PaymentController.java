package com.codder.stayease.controller;

import com.codder.stayease.dto.PaymentRequest;
import com.codder.stayease.entity.Payment;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;


    // ADD PAYMENT
    @PostMapping("/add")
    public ApiResponse addPayment(
            @RequestBody PaymentRequest request) {

        Payment payment = service.addPayment(request);

        return new ApiResponse(
                true,
                "Payment Successfully Added!",
                payment
        );
    }


    // GET ALL PAYMENTS
    @GetMapping("/all")
    public ApiResponse getAllPayment() {

        List<Payment> payments = service.getAllPayment();

        return new ApiResponse(
                true,
                "All Payments Fetched!",
                payments
        );
    }


    // GET PAYMENT BY ID
    @GetMapping("/{id}")
    public ApiResponse getPaymentById(
            @PathVariable long id) {

        Payment payment = service.getPaymentById(id);

        return new ApiResponse(
                true,
                "Payment Successfully Fetched!",
                payment
        );
    }


    // UPDATE PAYMENT
    @PutMapping("/update/{id}")
    public ApiResponse updatePaymentById(
            @PathVariable long id,
            @RequestBody PaymentRequest request) {

        Payment payment =
                service.updatePaymentById(id, request);

        return new ApiResponse(
                true,
                "Payment Successfully Updated!",
                payment
        );
    }


    // DELETE PAYMENT
    @DeleteMapping("/delete/{id}")
    public ApiResponse deletePaymentById(
            @PathVariable long id) {

        service.deletePaymentById(id);

        return new ApiResponse(
                true,
                "Payment Successfully Deleted!",
                null
        );
    }
}