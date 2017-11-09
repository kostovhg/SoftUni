package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

import java.util.HashMap;
import java.util.Map;

public class CirculateRace extends Race{

    private int laps;

    public CirculateRace(int length, String route, int prizePool, int laps) {
        super(length, route, prizePool);
        this.laps = laps;
    }

    private Map<Integer, Car> getWinners() {
        Map<Integer, Car> winners = new HashMap<>();
        final int[] position = {1};

        super.getParticipants().stream()
                .sorted((c1, c2) -> Integer.compare(this.getPerformancePoints(c2), this.getPerformancePoints(c1)))
                .limit(4)
                .forEach(c -> winners.putIfAbsent(position[0]++, c));

        this.decreaseDurability();
        return winners;
    }

    private void decreaseDurability() {
        super.getParticipants().forEach(c -> c.decreaseDurability(this.laps, super.getLength()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d", super.getRoute(), super.getLength() * this.laps));
        for (Map.Entry<Integer, Car> carEntry :
                this.getWinners().entrySet()) {
            int position = carEntry.getKey();
            Car car = carEntry.getValue();
            int prize = getPrize(position);
            sb.append(String.format("%n%d. %s %s %dPP - $%d",
                    position, car.getBrand(), car.getModel(),
                    getPerformancePoints(car), this.getPrize(position)));
        }
        return sb.toString();
    }

    @Override
    public int getPerformancePoints(Car car){
        return (car.getHorsePower() / car.getAcceleration()) +
                car.getSuspension() + car.getDurability();
    }

    private int getPrize(int i) {
        switch (i) {
            case 1:
                return (super.getPrizePool() * 40) / 100;
            case 2:
                return (super.getPrizePool() * 30) / 100;
            case 3:
                return (super.getPrizePool() * 20) / 100;
            case 4:
                return (super.getPrizePool() * 10) / 100;
            default:
                return 0;
        }
    }
}
