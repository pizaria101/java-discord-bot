package dev.schulte.entities;

public class Bruh {

    private String userId;
    private long bruhMoment;
    private long time;

    public Bruh() {
    }

    public Bruh(String userId, long bruhMoment, long time) {
        this.userId = userId;
        this.bruhMoment = bruhMoment;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getBruhMoment() {
        return bruhMoment;
    }

    public void setBruhMoment(long bruhMoment) {
        this.bruhMoment = bruhMoment;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bruh{" +
                "userId='" + userId + '\'' +
                ", bruhMoment=" + bruhMoment +
                ", time=" + time +
                '}';
    }
}
