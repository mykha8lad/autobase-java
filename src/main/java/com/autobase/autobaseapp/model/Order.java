package com.autobase.autobaseapp.model;

public class Order {
    private int id;
    private String destination; // Пункт назначения
    private double weight; // Вес груза
    private String cargoType; // Тип груза
    private String status; // Новый, В пути, Выполнен
    private User assignedDriver; // Водитель

    private String assignedVehicle; // Автомобиль (упрощенно)

    public Order(int id, String destination, double weight, String cargoType) {
        this.id = id;
        this.destination = destination;
        this.weight = weight;
        this.cargoType = cargoType;
        this.status = "Новый"; // По умолчанию
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(User assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public String getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(String assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }
}