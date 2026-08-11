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


    // ADD PAYMENT
    public Payment addPayment(PaymentRequest request) {

        Rent rent = rentRepo.findById(request.getRentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rent Not Found!"));

        // Calculate late fine
        double lateFine = 0;

        if (request.getPaymentDate().isAfter(rent.getDueDate())) {

            long lateDays = ChronoUnit.DAYS.between(
                    rent.getDueDate(),
                    request.getPaymentDate()
            );

            // ₹100 per late day
            lateFine = lateDays * 100;
        }

        // Update late fine in Rent
        rent.setLateFine(lateFine);

        // Calculate new total rent
        double totalAmount =
                rent.getRoomRent()
                        + rent.getElectricityBill()
                        + rent.getWaterBill()
                        + rent.getMaintenanceCharge()
                        + lateFine;

        rent.setTotalAmount(totalAmount);

        // Mark rent as PAID
        rent.setStatus("PAID");

        rentRepo.save(rent);


        Payment payment = new Payment();

        payment.setAmount(totalAmount);
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(request.getTransactionId());
        payment.setStatus(request.getStatus());
        payment.setRent(rent);

        return paymentRepo.save(payment);
    }


    // GET ALL PAYMENTS
    public List<Payment> getAllPayment() {

        return paymentRepo.findAll();
    }


    // GET PAYMENT BY ID
    public Payment getPaymentById(long id) {

        return paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment Not Found!"));
    }


    // UPDATE PAYMENT
    public Payment updatePaymentById(
            long id,
            PaymentRequest request) {

        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment Not Found!"));

        Rent rent = rentRepo.findById(request.getRentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rent Not Found!"));

        // Recalculate late fine
        double lateFine = 0;

        if (request.getPaymentDate().isAfter(rent.getDueDate())) {

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
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(request.getTransactionId());
        payment.setStatus(request.getStatus());
        payment.setRent(rent);

        return paymentRepo.save(payment);
    }


    // DELETE PAYMENT
    public void deletePaymentById(long id) {

        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment Not Found!"));

        paymentRepo.delete(payment);
    }
}