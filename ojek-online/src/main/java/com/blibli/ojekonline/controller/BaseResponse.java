package com.blibli.ojekonline.controller;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String status;
    private T value;
}
