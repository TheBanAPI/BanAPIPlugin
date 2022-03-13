package de.curano.banapiplugin.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;

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
            reader.close();
            connection.disconnect();
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

    public static void sendServerInfos(String token, String ip, int port, boolean active) {
        try {
            URL url = new URL("https://ban-api.thecurano.dev/serverstate");

            JSONObject json = new JSONObject();
            json.put("token", token);
            json.put("ip", ip);
            json.put("port", port);
            json.put("active", active);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://ban-api.thecurano.dev/serverstate");

            StringEntity entity = new StringEntity(json.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            client.close();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
