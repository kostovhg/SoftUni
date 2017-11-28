package n_InfernoInfinityRefactoring;

import n_InfernoInfinityRefactoring.engine.Engine;
import n_InfernoInfinityRefactoring.io.ConsoleInputReader;
import n_InfernoInfinityRefactoring.io.ConsoleOutputWriter;
import n_InfernoInfinityRefactoring.io.InputReader;
import n_InfernoInfinityRefactoring.io.OutputWriter;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;
import n_InfernoInfinityRefactoring.repositories.Repository;
import n_InfernoInfinityRefactoring.repositories.WeaponRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        InputReader reader = new ConsoleInputReader(bufferedReader);
        OutputWriter writer = new ConsoleOutputWriter();
        Repository<WeaponInterface> weaponRepository = new WeaponRepository<>();
        Runnable engine = new Engine(weaponRepository, reader, writer);

        engine.run();

    }
}