package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.PaymentRequest;
import com.codder.stayease.entity.Payment;
import com.codder.stayease.entity.Rent;
import com.codder.stayease.repository.PaymentRepository;
import com.codder.stayease.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

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

    public Payment addPayment(PaymentRequest request) {

        Rent rent = rentRepo.findById(request.getRentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));

        return createPayment(rent, request);
    }


    // =====================================================
    // ADD PAYMENT FOR LOGGED-IN TENANT
    // =====================================================

    public Payment addPaymentForTenant(
            PaymentRequest request,
            long userId) {

        Rent rent = rentRepo
                .findByIdAndTenant_User_Id(
                        request.getRentId(),
                        userId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found for this Tenant!"
                        ));

        return createPayment(rent, request);
    }


    // =====================================================
    // COMMON PAYMENT CREATION
    // =====================================================

    private Payment createPayment(
            Rent rent,
            PaymentRequest request) {

        // Calculate late fine
        double lateFine = 0;

        if (request.getPaymentDate()
                .isAfter(rent.getDueDate())) {

            long lateDays = ChronoUnit.DAYS.between(
                    rent.getDueDate(),
                    request.getPaymentDate()
            );

            // ₹100 per late day
            lateFine = lateDays * 100;
        }


        // Update late fine
        rent.setLateFine(lateFine);


        // Calculate final amount
        double totalAmount =
                rent.getRoomRent()
                        + rent.getElectricityBill()
                        + rent.getWaterBill()
                        + rent.getMaintenanceCharge()
                        + lateFine;


        rent.setTotalAmount(totalAmount);

        // Mark rent as paid
        rent.setStatus("PAID");

        rentRepo.save(rent);


        // Create Payment
        Payment payment = new Payment();

        // IMPORTANT:
        // Amount comes from backend calculation
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

    public List<Payment> getAllPayment() {

        return paymentRepo.findAll();
    }


    // =====================================================
    // GET PAYMENT BY ID
    // ADMIN / STAFF
    // =====================================================

    public Payment getPaymentById(long id) {

        return paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment Not Found!"
                        ));
    }


    // =====================================================
    // GET LOGGED-IN TENANT PAYMENTS
    // =====================================================

    public List<Payment> getMyPayments(long userId) {

        return paymentRepo
                .findByRent_Tenant_User_Id(userId);
    }


    // =====================================================
    // GET ONE PAYMENT OF LOGGED-IN TENANT
    // =====================================================

    public Payment getMyPaymentById(
            long paymentId,
            long userId) {

        return paymentRepo
                .findByIdAndRent_Tenant_User_Id(
                        paymentId,
                        userId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment Not Found for this Tenant!"
                        ));
    }


    // =====================================================
    // UPDATE PAYMENT
    // ADMIN / STAFF
    // =====================================================

    public Payment updatePaymentById(
            long id,
            PaymentRequest request) {

        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment Not Found!"
                        ));

        Rent rent = rentRepo.findById(
                        request.getRentId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));


        // Recalculate late fine
        double lateFine = 0;

        if (request.getPaymentDate()
                .isAfter(rent.getDueDate())) {

            long lateDays = ChronoUnit.DAYS.between(
                    rent.getDueDate(),
                    request.getPaymentDate()
            );

            lateFine = lateDays * 100;
        }


        // Update Rent
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


        // Update Payment
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
    // DELETE PAYMENT
    // ADMIN / STAFF
    // =====================================================

    public void deletePaymentById(long id) {

        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment Not Found!"
                        ));

        paymentRepo.delete(payment);
    }
}