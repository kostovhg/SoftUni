package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private final List<Car> participants;

    Race(int length, String route, int prizePool) {
        this.setLength(length);
        this.setRoute(route);
        this.setPrizePool(prizePool);
        this.participants = new ArrayList<>();
    }

    protected int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    protected String getRoute() {
        return route;
    }

    private void setRoute(String route) {
        this.route = route;
    }

    protected int getPrizePool() {
        return prizePool;
    }

    private void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public void addParticipant(Car car) {
        this.participants.add(car);
    }

    public List<Car> getParticipants() {
        return Collections.unmodifiableList(this.participants);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d", this.getRoute(), this.getLength()));
        final int[] position = {0};
        this.getParticipants().stream()
                .sorted((c1, c2) -> Integer.compare(this.getPerformancePoints(c2), this.getPerformancePoints(c1)))
                .limit(3)
                .forEach((Car c) -> {
                    sb.append(System.lineSeparator());
                    sb.append(String.format("%d. %s %s %dPP - $%d",
                            ++position[0], c.getBrand(), c.getModel(),
                            getPerformancePoints(c), this.getPrize(position[0])));
                });
        return sb.toString();
    }

    protected abstract int getPerformancePoints(Car car);

    private int getPrize(int i) {
        switch (i) {
            case 1:
                return this.getPrizePool() / 2;
            case 2:
                return (this.getPrizePool() * 30) / 100;
            case 3:
                return (this.getPrizePool() * 20) / 100;
            default:
                return 0;
        }
    }
}


