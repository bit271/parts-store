package com.bit.sparestore.models;

public enum OrderStatus {
    PENDING,    // Ожидает обработки
    PROCESSING, // В процессе
    SHIPPED,    // Отправлен
    DELIVERED,  // Доставлен
    CANCELLED   // Отменен
}
