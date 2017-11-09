package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

import java.util.*;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> participants;

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

    protected abstract int getPerformancePoints(Car car);

    public Map<Integer, Car> getWinners(int limit){
        Map<Integer, Car> winners = new HashMap<>();
        final int[] position = {0};
        this.getParticipants().stream()
                .sorted((c1, c2) -> Integer.compare(this.getPerformancePoints(c2), this.getPerformancePoints(c1)))
                .limit(limit)
                .forEach(c -> winners.putIfAbsent(position[0]++, c));
        return winners;
    }

    private int getPrize(int i) {
        switch (i) {
            case 1:
                return (this.getPrizePool() * 50) / 100;
            case 2:
                return (this.getPrizePool() * 30) / 100;
            case 3:
                return (this.getPrizePool() * 20) / 100;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d", this.getRoute(), this.getLength()));

        for (Map.Entry<Integer, Car> carEntry : this.getWinners(3).entrySet()) {
            Car car = carEntry.getValue();
            int position = carEntry.getKey();
            int prize = getPrize(position + 1);
            sb.append(String.format("%n%d. %s %s %dPP - $%d",
                    position + 1, car.getBrand(), car.getModel(),
                    getPerformancePoints(car), prize));
        }
        return sb.toString().trim();
    }
}


