package com.midgardabc.day14.shop.domain;

import com.midgardabc.day14.shop.PurchaseType;

/**
 * Created by user on 23.10.2015.
 */
public class Payment {

    private String typePayment;
    private PurchaseType purchaseType;
    private double numberPayment;

    public Payment() {

    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public double getNumberPayment() {
        return numberPayment;
    }

    public void setNumberPayment(double numberPayment) {
        this.numberPayment = numberPayment;
    }

}
