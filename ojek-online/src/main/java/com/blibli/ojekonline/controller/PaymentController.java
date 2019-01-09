package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Payment;
import com.blibli.ojekonline.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/api/payment/all")
    public BaseResponse<List<Payment>> getAllPayment() {
        BaseResponse response = new BaseResponse();
        response.setValue(paymentService.getPaymentList());
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }

    @GetMapping("/api/payment/{id}")
    public BaseResponse<Payment> getPayment(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            Payment payment = paymentService.getPaymentById(id);
            response.setValue(payment);
            response.setStatus(HttpStatus.OK.toString());
        } catch (RuntimeException e) {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @PostMapping("/api/payment")
    public BaseResponse<String> insertPayment(@Valid @RequestBody Payment payment) {
        Payment returned = paymentService.savePayment(payment);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setValue("Save success!");
            response.setStatus(HttpStatus.OK.toString());
        } else {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @PutMapping("/api/payment")
    public BaseResponse<String> updatePayment(@Valid @RequestBody Payment payment) {
        Payment returned = paymentService.savePayment(payment);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setValue("Update success!");
            response.setStatus(HttpStatus.OK.toString());
        } else {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }
}
