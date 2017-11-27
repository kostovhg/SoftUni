package i_TrafficLights;

public enum Signal {
    RED {
        @Override
        Signal switchNext() {
            return GREEN;
        }
    }, GREEN {
        @Override
        Signal switchNext() {
            return YELLOW;
        }
    }, YELLOW {
        @Override
        Signal switchNext() {
            return RED;
        }
    };

    abstract Signal switchNext();

    @Override
    public String toString() {
        return this.name();
    }
}
