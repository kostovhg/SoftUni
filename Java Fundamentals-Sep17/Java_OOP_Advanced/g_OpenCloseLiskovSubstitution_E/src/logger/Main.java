package logger;


import logger.contracts.Appender;
import logger.contracts.InputReader;
import logger.contracts.Logger;
import logger.controllers.Controller;
import logger.io.ConsoleInputReader;
import logger.models.loggers.MessageLogger;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        InputReader reader = new ConsoleInputReader();
        Controller controller = new Controller();
        List<Appender> appenderList = new ArrayList<>();

        int numberOfAppenders = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfAppenders; i++) {
            appenderList.add(controller.readAppender(reader.readLine().split("\\s+")));
        }

        Logger logger = new MessageLogger(appenderList);

        String[] input;
        while (true){
            input = reader.readLine().split("\\|");
            if(input[0].equalsIgnoreCase("END")){
                break;
            }
            logger.log(input[1], input[0], input[2]);
        }

        System.out.println(logger);

    }
/*
    private static void runTester() {
        SimpleCodeManualTester.sampleInput();
        SimpleCodeManualTester.extensibility();
        SimpleCodeManualTester.implementations();
        SimpleCodeManualTester.ReportThreshold();
    }
    */
}
