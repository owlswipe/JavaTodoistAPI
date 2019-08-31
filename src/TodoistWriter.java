import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class TodoistWriter {

    private String token;
    private String way;
    private BufferedReader reader;

    public TodoistWriter(String token){
        this.token = token;
        way = "https://todoist.com/API/v8/sync?token=" + this.token + "&commands=";
    }

    public String writeItem(String content, Object dueDateString, int priority, Boolean setReminder, long project_id) throws IOException, JSONException {
        JSONObject item = new JSONObject();
        JSONObject date = new JSONObject();
        date.put("string", dueDateString);
        JSONObject characteristics = new JSONObject();
        characteristics.put("content", content);
        characteristics.put("project_id", project_id);
        characteristics.put("due", date);
        characteristics.put("priority", priority);
        characteristics.put("auto_reminder", String.valueOf(setReminder));
        item.put("args", characteristics);
        item.put("type", "item_add");
        item.put("temp_id", createUUIDorTEMP_ID());
        item.put("uuid", createUUIDorTEMP_ID());
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(item);
        String request = jsonArray.toString();
        request = encoderRequest(request);
        URL url = new URL(way + request);
        Log.d("TodoistProjectLog", "Sending request to " + String.valueOf(url).replaceAll(token, "[redactedToken]"));
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();
        return answerFromServer;
    }

    public String writeProject(String name) throws IOException, JSONException {
        JSONObject project = new JSONObject();
        JSONObject characteristics = new JSONObject();
        characteristics.put("name", name);
        project.put("args", characteristics);
        project.put("type", "project_add");
        project.put("temp_id", createUUIDorTEMP_ID());
        project.put("uuid", createUUIDorTEMP_ID());
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(project);
        String request = jsonArray.toString();
        request = encoderRequest(request);
        URL url = new URL(way + request);
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();
        return answerFromServer;
    }

    private String createUUIDorTEMP_ID(){
        String id = "";
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            int a = random.nextInt(2);
            if (a == 0) {
                id += (char) (random.nextInt(26) + 97);
            } else {
                id += random.nextInt(10);
            }
        }
        return id;
    }

    private String encoderRequest(String request) throws UnsupportedEncodingException {
        request = URLEncoder.encode(request, "UTF8");
        return request;
    }

    private String getServerAnswer(BufferedReader reader) throws IOException {
        String answerFromServer = "";
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            answerFromServer += line;
        }
        return answerFromServer;
    }

}
