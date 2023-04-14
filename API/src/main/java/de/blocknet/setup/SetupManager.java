package de.blocknet.setup;

import de.blocknet.api.gson.ModuleManager;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetupManager {

    private ModuleManager moduleManager;

    public SetupManager(Logger logger, String input) {
        moduleManager = new ModuleManager();
        input = input.toLowerCase();
        switch (input){
            case "master":
                    startSetupMaster();
                    logger.info("§aDer Master wurde nun erstellt, stoppe nun das Setup und erstelle mindestens eine Base.");
                break;
            case "base":
                    startSetupBase();
                    logger.info("§aEs wurde nun eine Base erstellt.");
                break;
        }
    }

    public void startSetupMaster() {

        moduleManager.createdictionary("templates/lobbies");
        moduleManager.createdictionary("templates/proxies");
        moduleManager.createdictionary("templates/servers");
        try (InputStream in = getClass().getResourceAsStream("/start_master");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            File startfile = new File("start.sh");
            BufferedWriter writer = new BufferedWriter(new FileWriter(startfile));
            writer.write(line);
            writer.flush();
            writer.close();
            startfile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void startSetupBase() {
        moduleManager.createdictionary("/static/lobbies");
        moduleManager.createdictionary("/static/servers");
        moduleManager.createdictionary("/static/proxies");
        moduleManager.createdictionary("/temporary/lobbies");
        moduleManager.createdictionary("/temporary/servers");
        moduleManager.createdictionary("/temporary/proxies");
        try (InputStream in = getClass().getResourceAsStream("/start_base");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            File startfile = new File("start.sh");
            BufferedWriter writer = new BufferedWriter(new FileWriter(startfile));
            writer.write(line);
            writer.flush();
            writer.close();
            startfile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
