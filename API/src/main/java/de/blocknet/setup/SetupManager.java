package de.blocknet.setup;

import de.blocknet.api.gson.ModuleManager;
import java.io.File;

public class SetupManager {

    private boolean isMaster;
    private ModuleManager moduleManager;

    public SetupManager(boolean isMaster) {
        this.isMaster = isMaster;
        moduleManager = new ModuleManager();
        if(isMaster) {
            moduleManager.createdictionary("Master/config/wrappers");
            moduleManager.createdictionary("Master/templates/lobbies");
            moduleManager.createdictionary("Master/templates/proxies");
            moduleManager.createdictionary("Master/templates/servers");

        } else {
            File currentDirectory = new File(System.getProperty("user.dir"));
            String number = "";
            for(File file : currentDirectory.listFiles()) {
                if(file.getName().contains("Base")) {
                    number = file.getName().substring(5) + 1;
                } else {
                    number = "1";
                }
            }
            moduleManager.createdictionary("Base-" + number + "/static/lobbies");
            moduleManager.createdictionary("Base-" + number + "/static/servers");
            moduleManager.createdictionary("Base-" + number + "/static/proxies");

            moduleManager.createdictionary("Base-" + number + "/temporary/lobbies");
            moduleManager.createdictionary("Base-" + number + "/temporary/servers");
            moduleManager.createdictionary("Base-" + number + "/temporary/proxies");
        }
    }
}
