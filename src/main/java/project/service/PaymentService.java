package project.service;

import project.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment toPay();

    List<Payment> getAll();

    void setPay();
}
