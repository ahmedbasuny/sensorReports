package com.thirdwayv.common;
/**
 * @author Ahmed Basuny 07_06_2020
 * handle custom error
 */
public class ErrorEntity {
    String error;

    public ErrorEntity(String error) {
        this.error = error;
    }

    public ErrorEntity() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
