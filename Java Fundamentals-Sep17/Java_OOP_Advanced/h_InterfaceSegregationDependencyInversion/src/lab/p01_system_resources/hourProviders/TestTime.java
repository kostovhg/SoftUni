package lab.p01_system_resources.hourProviders;

import lab.p01_system_resources.contracts.TimeProvider;

import java.time.LocalTime;

public class TestTime implements TimeProvider {
    @Override
    public int getHour() {
        return LocalTime.now().getHour();
    }
}
