package de.blocknet.cloud;

import de.blocknet.setup.SetupManager;

public class Main {

    private static SetupManager setupManager;
    private static boolean isSetup;

    public static void main(String[] args) {
        System.out.println("test");
        setup();
    }

    private static void setup() {
        isSetup = true;
        if(isSetup) {
            setupManager = new SetupManager(true);
        }
    }
}

