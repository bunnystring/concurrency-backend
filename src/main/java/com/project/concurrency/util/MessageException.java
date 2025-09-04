package com.project.concurrency.util;

/**
 * Clase utilitaria que contiene los mensajes de error para las excepciones de la aplicación.
 */
public class MessageException {

    // Mensajes para UserException
    public static final String USER_NOT_FOUND = "El usuario no fue encontrado. ";
    public static final String USER_ALREADY_EXISTS = "El nombre de usuario ya existe. ";
    public static final String EMAIL_IN_USE = "El correo electrónico ya está registrado. ";

    // Mensajes para ConcurrencyException
    public static final String CONCURRENCY_NOT_FOUND = "Concurrencia no encontrada. ";
    public static final String CONCURRENCY_ERROR = "Error de concurrencia. ";
}
