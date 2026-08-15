package com.codder.stayease.controller;

import com.codder.stayease.dto.PaymentRequest;
import com.codder.stayease.dto.PaymentResponse;
import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;


    // =====================================================
    // ADD PAYMENT
    // ADMIN / STAFF
    // =====================================================

    @PostMapping("/add")
    public ApiResponse addPayment(
            @RequestBody PaymentRequest request) {

        PaymentResponse payment =
                service.addPayment(request);

        return new ApiResponse(
                true,
                "Payment Successfully Added!",
                payment
        );
    }


    // =====================================================
    // PAY OWN RENT
    // TENANT
    // =====================================================

    @PostMapping("/pay")
    public ApiResponse payRent(
            @RequestBody PaymentRequest request,
            Authentication authentication) {

        User user =
                (User) authentication.getPrincipal();

        PaymentResponse payment =
                service.addPaymentForTenant(
                        request,
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Payment Successfully Added!",
                payment
        );
    }


    // =====================================================
    // GET ALL PAYMENTS
    // ADMIN / STAFF
    // =====================================================

    @GetMapping("/all")
    public ApiResponse getAllPayment() {

        List<PaymentResponse> payments =
                service.getAllPayment();

        return new ApiResponse(
                true,
                "All Payments Fetched!",
                payments
        );
    }


    // =====================================================
    // GET PAYMENT BY ID
    // ADMIN / STAFF
    // =====================================================

    @GetMapping("/{id}")
    public ApiResponse getPaymentById(
            @PathVariable long id) {

        PaymentResponse payment =
                service.getPaymentById(id);

        return new ApiResponse(
                true,
                "Payment Successfully Fetched!",
                payment
        );
    }


    // =====================================================
    // GET MY PAYMENTS
    // TENANT
    // =====================================================

    @GetMapping("/my")
    public ApiResponse getMyPayments(
            Authentication authentication) {

        User user =
                (User) authentication.getPrincipal();

        List<PaymentResponse> payments =
                service.getMyPayments(
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Your Payments Fetched!",
                payments
        );
    }


    // =====================================================
    // GET MY PAYMENT BY ID
    // TENANT
    // =====================================================

    @GetMapping("/my/{id}")
    public ApiResponse getMyPaymentById(
            @PathVariable long id,
            Authentication authentication) {

        User user =
                (User) authentication.getPrincipal();

        PaymentResponse payment =
                service.getMyPaymentById(
                        id,
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Your Payment Successfully Fetched!",
                payment
        );
    }


    // =====================================================
    // UPDATE PAYMENT
    // ADMIN / STAFF
    // =====================================================

    @PutMapping("/update/{id}")
    public ApiResponse updatePaymentById(
            @PathVariable long id,
            @RequestBody PaymentRequest request) {

        PaymentResponse payment =
                service.updatePaymentById(
                        id,
                        request
                );

        return new ApiResponse(
                true,
                "Payment Successfully Updated!",
                payment
        );
    }


    // =====================================================
    // DELETE PAYMENT
    // ADMIN / STAFF
    // =====================================================

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