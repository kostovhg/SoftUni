package org.softuni.ruk.io.impl;

import org.softuni.ruk.io.interfaces.ConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImpl implements ConsoleIO {

    @Override
    public void write(String line) {
        System.out.println(line);
    }
}

