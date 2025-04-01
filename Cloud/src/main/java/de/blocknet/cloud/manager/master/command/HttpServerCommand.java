package de.blocknet.cloud.manager.master.command;

import de.blocknet.cloud.Main;
import de.blocknet.cloud.http.server.monitor.Status;
import de.blocknet.cloud.manager.command.Command;
import de.blocknet.cloud.manager.master.Master;
import de.blocknet.cloud.terminal.Extra;
import de.blocknet.cloud.utils.MessageStyler;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.HashMap;

public class HttpServerCommand implements Command {

    @Override
    public String getName() {
        return "http-server";
    }


    @Override
    public String[] getAliases() {
        return new String[]{
                "web",
                "webserver",
                "rest",
                "rest-api"
        };
    }

    @Override
    public HashMap<Integer, ArrayList<String>> getArguments() {
        HashMap<Integer, ArrayList<String>> args = new HashMap<Integer, ArrayList<String>>();
        args.put(0, new ArrayList<String>());


        args.get(0).add("restart");
        args.get(0).add("stop");
        args.get(0).add("start");
        args.get(0).add("status");
        return args;
    }


    @Override
    public void onCommand(Terminal terminal, String[] args) {
        if(args.length > 0){
            switch (args[0].toLowerCase()){
                case "start":
                    Master.getInstance().getFrontendServer().start();
                    //Main.getLogger().info(MessageStyler.getFormattedString("The http server has been started successfully."));
                    break;
                case "restart":
                    Master.getInstance().getFrontendServer().start();
                    //Main.getLogger().info("The http server has been restarted successfully.");
                    break;
                case "stop":
                    Master.getInstance().getFrontendServer().stop();
                    //Main.getLogger().info("The http server has been stopped successfully.");
                    break;
                case "status":
                    Status status = Master.getInstance().getFrontendServer().getStatus();
                    String isRunningStr = status.getIsRunning() ? "§2running" : "§1stopped";
                    Main.getLogger().info(MessageStyler.getFormattedString("§7HTTP server status: " + isRunningStr));
                    if(status.getIsRunning()) {
                        Main.getLogger().info(MessageStyler.getFormattedString("§7HTTP server address: §6http://" + status.getHost() + "§7:§3" + status.getPort()));
                    }
                    break;
                default:
                    Main.getLogger().info(MessageStyler.getFormattedString("§1Wrong usage! use §3" + getName() + "§1 <§3start§1/§3stop§1/§3restart§1/§3status§1>"));
                    break;
            }
        }else{
            Main.getLogger().info(MessageStyler.getFormattedString("§1No arguments given! use §3" + getName() + "§1 <§3start§1/§3stop§1/§3restart§1/§3status§1>"));
        }
    }

    @Override
    public String getHelpInfo() {
        return "manages the http-server";
    }


}
