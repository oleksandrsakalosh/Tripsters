package main.java.system.services;

public class Room extends Hotel{
    private int id;
    private int capacity;
    private long dayIn;
    private long dayOut;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getDayIn() {
        return dayIn;
    }

    public void setDayIn(long dayIn) {
        this.dayIn = dayIn;
    }

    public long getDayOut() {
        return dayOut;
    }

    public void setDayOut(long dayOut) {
        this.dayOut = dayOut;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
