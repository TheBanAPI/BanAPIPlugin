package de.curano.banapiplugin.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class BanAPI {

    public static boolean isBanned(String uuid) {
        if (uuid == null) {
            return false;
        }

        String url = "https://ban-api.thecurano.dev/state?uuid=" + uuid;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
            JSONObject json = new JSONObject(result.toString());
            return json.getBoolean("banned");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isBanned(UUID uuid) {
        if (uuid == null) {
            return false;
        }

        return isBanned(uuid.toString());
    }

}
