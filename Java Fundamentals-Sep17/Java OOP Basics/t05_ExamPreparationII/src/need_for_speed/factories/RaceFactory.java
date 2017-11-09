package need_for_speed.factories;

import need_for_speed.entities.races.*;

public final class RaceFactory {

    private RaceFactory() {}

    public static Race createCasualRace(int length, String route, int prizePool) {
        return new CasualRace(length, route, prizePool);
    }

    public static Race createDriftRace(int length, String route, int prizePool) {
        return new DriftRace(length, route, prizePool);
    }

    public static Race createDragRace(int length, String route, int prizePool) {
        return new DragRace(length, route, prizePool);
    }

    public static Race createTimeLimitRace(int length, String route, int prizePool, int goldTime) {
        return new TimeLimitRace(length, route, prizePool, goldTime);
    }

    public static Race createCirculateRace(int length, String route, int prizePool, int laps) {
        return new CirculateRace(length, route, prizePool, laps);
    }
}
