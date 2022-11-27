package handler;

import oshi.SystemInfo;

public class ComputerIdentifierHandler {

    private static final String DELIMITER = "#";

    public static String generateHardware() {
        SystemInfo systemInfo = new SystemInfo();
        String processorID = systemInfo.getHardware().getProcessor().getProcessorIdentifier().getProcessorID();
        String baseboardID = systemInfo.getHardware().getComputerSystem().getBaseboard().getSerialNumber();
        String computerID = systemInfo.getHardware().getComputerSystem().getHardwareUUID();
        return processorID + DELIMITER + baseboardID + DELIMITER + computerID;
    }
}