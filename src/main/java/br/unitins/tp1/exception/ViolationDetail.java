package br.unitins.tp1.exception;

public record ViolationDetail(
        String field,
        String message) {
}