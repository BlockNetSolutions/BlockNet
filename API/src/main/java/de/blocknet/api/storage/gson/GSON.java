package de.blocknet.api.storage.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.blocknet.api.storage.mysql.impl.MasterConfig;
import de.blocknet.api.storage.mysql.impl.MySQLConfig;

import java.util.HashMap;

public class GSON {

    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    public static void main(String[] args) {

        HashMap<String, String> daddy = new HashMap<String, String>();

        System.out.println(toJson(new MasterConfig(new MySQLConfig("187", 3306, "4213", "4", "adsasdasd"))));
    }

}
