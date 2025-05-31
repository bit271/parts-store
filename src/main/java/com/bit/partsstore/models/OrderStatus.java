package com.bit.partsstore.models;

public enum OrderStatus {
    PENDING,    // Ожидает обработки
    PROCESSING, // В процессе
    SHIPPED,    // Отправлен
    DELIVERED,  // Доставлен
    CANCELLED   // Отменен
}
