package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.PaymentRequest;
import com.codder.stayease.dto.PaymentResponse;
import com.codder.stayease.entity.Payment;
import com.codder.stayease.entity.Rent;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.entity.User;
import com.codder.stayease.repository.PaymentRepository;
import com.codder.stayease.repository.RentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private RentRepository rentRepo;


    // =====================================================
    // ADD PAYMENT
    // ADMIN / STAFF
    // =====================================================

    public PaymentResponse addPayment(
            PaymentRequest request) {

        Rent rent = rentRepo.findById(request.getRentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));

        Payment payment =
                createPayment(rent, request);

        return convertToResponse(payment);
    }


    // =====================================================
    // ADD PAYMENT FOR LOGGED-IN TENANT
    // TENANT
    // =====================================================

    public PaymentResponse addPaymentForTenant(
            PaymentRequest request,
            long userId) {

        Rent rent =
                rentRepo.findByIdAndTenant_User_Id(
                                request.getRentId(),
                                userId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Rent Not Found for this Tenant!"
                                ));

        Payment payment =
                createPayment(rent, request);

        return convertToResponse(payment);
    }


    // =====================================================
    // COMMON PAYMENT CREATION
    // =====================================================

    private Payment createPayment(
            Rent rent,
            PaymentRequest request) {

        // -------------------------------------------------
        // Calculate late fine
        // -------------------------------------------------

        double lateFine = 0;

        if (request.getPaymentDate() != null
                && rent.getDueDate() != null
                && request.getPaymentDate()
                .isAfter(rent.getDueDate())) {

            long lateDays =
                    ChronoUnit.DAYS.between(
                            rent.getDueDate(),
                            request.getPaymentDate()
                    );

            // ₹100 per late day
            lateFine = lateDays * 100;
        }


        // -------------------------------------------------
        // Update rent late fine
        // -------------------------------------------------

        rent.setLateFine(lateFine);


        // -------------------------------------------------
        // Calculate final amount
        // -------------------------------------------------

        double totalAmount =
                rent.getRoomRent()
                        + rent.getElectricityBill()
                        + rent.getWaterBill()
                        + rent.getMaintenanceCharge()
                        + lateFine;

        rent.setTotalAmount(totalAmount);


        // -------------------------------------------------
        // Mark rent as paid
        // -------------------------------------------------

        rent.setStatus("PAID");

        rentRepo.save(rent);


        // -------------------------------------------------
        // Create payment
        // -------------------------------------------------

        Payment payment = new Payment();

        /*
         * IMPORTANT:
         * Amount is calculated by backend.
         * Frontend cannot modify payment amount.
         */
        payment.setAmount(totalAmount);

        payment.setPaymentDate(
                request.getPaymentDate()
        );

        payment.setPaymentMethod(
                request.getPaymentMethod()
        );

        payment.setTransactionId(
                request.getTransactionId()
        );

        payment.setStatus(
                request.getStatus()
        );

        payment.setRent(rent);


        return paymentRepo.save(payment);
    }


    // =====================================================
    // GET ALL PAYMENTS
    // ADMIN / STAFF
    // =====================================================

    public List<PaymentResponse> getAllPayment() {

        return paymentRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    // =====================================================
    // GET PAYMENT BY ID
    // ADMIN / STAFF
    // =====================================================

    public PaymentResponse getPaymentById(
            long id) {

        Payment payment =
                paymentRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment Not Found!"
                                ));

        return convertToResponse(payment);
    }


    // =====================================================
    // GET LOGGED-IN TENANT PAYMENTS
    // TENANT
    // =====================================================

    public List<PaymentResponse> getMyPayments(
            long userId) {

        return paymentRepo
                .findByRent_Tenant_User_Id(userId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    // =====================================================
    // GET ONE PAYMENT OF LOGGED-IN TENANT
    // TENANT
    // =====================================================

    public PaymentResponse getMyPaymentById(
            long paymentId,
            long userId) {

        Payment payment =
                paymentRepo
                        .findByIdAndRent_Tenant_User_Id(
                                paymentId,
                                userId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment Not Found for this Tenant!"
                                ));

        return convertToResponse(payment);
    }


    // =====================================================
    // UPDATE PAYMENT
    // ADMIN / STAFF
    // =====================================================

    public PaymentResponse updatePaymentById(
            long id,
            PaymentRequest request) {

        Payment payment =
                paymentRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment Not Found!"
                                ));


        Rent rent =
                rentRepo.findById(
                                request.getRentId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Rent Not Found!"
                                ));


        // -------------------------------------------------
        // Recalculate late fine
        // -------------------------------------------------

        double lateFine = 0;

        if (request.getPaymentDate() != null
                && rent.getDueDate() != null
                && request.getPaymentDate()
                .isAfter(rent.getDueDate())) {

            long lateDays =
                    ChronoUnit.DAYS.between(
                            rent.getDueDate(),
                            request.getPaymentDate()
                    );

            lateFine = lateDays * 100;
        }


        // -------------------------------------------------
        // Update Rent
        // -------------------------------------------------

        rent.setLateFine(lateFine);


        double totalAmount =
                rent.getRoomRent()
                        + rent.getElectricityBill()
                        + rent.getWaterBill()
                        + rent.getMaintenanceCharge()
                        + lateFine;

        rent.setTotalAmount(totalAmount);

        rent.setStatus("PAID");

        rentRepo.save(rent);


        // -------------------------------------------------
        // Update Payment
        // -------------------------------------------------

        payment.setAmount(totalAmount);

        payment.setPaymentDate(
                request.getPaymentDate()
        );

        payment.setPaymentMethod(
                request.getPaymentMethod()
        );

        payment.setTransactionId(
                request.getTransactionId()
        );

        payment.setStatus(
                request.getStatus()
        );

        payment.setRent(rent);


        Payment savedPayment =
                paymentRepo.save(payment);

        return convertToResponse(savedPayment);
    }


    // =====================================================
    // DELETE PAYMENT
    // ADMIN / STAFF
    // =====================================================

    @Transactional
    public void deletePaymentById(long id) {

        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment Not Found!"
                        ));

        // Get the rent linked to this payment
        Rent rent = payment.getRent();

        if (rent != null) {

            // Remove the payment reference from Rent
            rent.setPayment(null);

            // Since the payment is deleted,
            // rent should become unpaid again
            rent.setStatus("PENDING");

            // Reset late fine
            rent.setLateFine(0);

            // Recalculate original rent amount
            double totalAmount =
                    rent.getRoomRent()
                            + rent.getElectricityBill()
                            + rent.getWaterBill()
                            + rent.getMaintenanceCharge();

            rent.setTotalAmount(totalAmount);

            // Save Rent first
            rentRepo.save(rent);
        }

        // Now delete Payment
        paymentRepo.delete(payment);
    }


    // =====================================================
    // CONVERT ENTITY → PAYMENT RESPONSE
    // =====================================================

    private PaymentResponse convertToResponse(
            Payment payment) {

        PaymentResponse response =
                new PaymentResponse();


        // -------------------------------------------------
        // Payment information
        // -------------------------------------------------

        response.setId(
                payment.getId()
        );

        response.setAmount(
                payment.getAmount()
        );

        response.setPaymentDate(
                payment.getPaymentDate()
        );

        response.setPaymentMethod(
                payment.getPaymentMethod()
        );

        response.setTransactionId(
                payment.getTransactionId()
        );

        response.setStatus(
                payment.getStatus()
        );


        // -------------------------------------------------
        // Rent information
        // -------------------------------------------------

        Rent rent =
                payment.getRent();

        if (rent != null) {

            response.setRentId(
                    rent.getId()
            );


            // -------------------------------------------------
            // Tenant information
            // -------------------------------------------------

            Tenant tenant =
                    rent.getTenant();

            if (tenant != null) {

                response.setTenantId(
                        tenant.getId()
                );


                // -------------------------------------------------
                // User information
                // -------------------------------------------------

                User user =
                        tenant.getUser();

                if (user != null) {

                    response.setTenantName(
                            user.getName()
                    );

                    response.setTenantEmail(
                            user.getEmail()
                    );
                }
            }
        }


        return response;
    }
}