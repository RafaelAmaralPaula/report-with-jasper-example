package com.rafaelamaral.demojasper.service;

public class ReportException extends Throwable {
    private static final long serialVersionUID = 1L;

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportException(String message) {
        super(message);
    }

}
