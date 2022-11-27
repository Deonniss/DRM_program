package handler;

import data.ConsoleData;

public final class ConsoleHandler {

    private static final String REGEX_KEY = "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$";

    private ConsoleHandler() {}

    public static ConsoleData generateRequest(String command) {

        String[] params = command.split("\\s+");

        if (params.length == 5 && params[1].equalsIgnoreCase("-u") && params[3].equalsIgnoreCase("-p")
                && (params[0].equalsIgnoreCase("reg") || params[0].equalsIgnoreCase("log")) && params[2].length() > 2
                && params[2].length() < 10 && params[4].length() > 2 && params[4].length() < 10) {

            ConsoleData data = new ConsoleData();
            data.setCommand(params[0]);
            data.setUserParam(params[1]);
            data.setUsername(params[2]);
            data.setPassParam(params[3]);
            data.setPassword(params[4]);
            return data;
        }
        throw new RuntimeException("Incorrect command!");
    }

    public static String generateUUIDKey(String key) {
        if (key.matches(REGEX_KEY)) {
            return key;
        }
        throw new RuntimeException("Incorrect license key!");
    }

}
