package com.example.demo.vo;

public class UIResponseBean {
    String operation;
    String status;
    Object result;
    Object error;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "UIResponseBean{" +
                "operation='" + operation + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                ", error=" + error +
                '}';
    }
}
