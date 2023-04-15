package de.blocknet.cloud;


import de.blocknet.cloud.manager.master.Master;
import lombok.Getter;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    @Getter
    private static Logger logger;

    public static void main(String[] args) throws IOException, InterruptedException {

        Master master = new Master();
        logger = master.getLogger();

    }

}

