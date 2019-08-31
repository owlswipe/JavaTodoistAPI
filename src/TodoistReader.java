import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class TodoistReader {

    private String request;
    private String token;
    private BufferedReader reader;

    public TodoistReader(String token){
        this.token = token;
        request = "https://todoist.com/API/v8/sync?token=" + token + "&sync_token=%27*%27&resource_types=";
    }

    public ArrayList<TodoistProject> readProjects() throws IOException, JSONException {
        URL url = new URL(request + "[\"projects\"]");
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();

        JSONObject jsonObject = new JSONObject(answerFromServer);
        JSONArray jsonArray = (JSONArray) jsonObject.get("projects");

        ArrayList<TodoistProject> projects = new ArrayList();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject currentObject = (JSONObject) jsonArray.get(i);
            projects.add(JSONtoTodoistProject(currentObject));
        }

        return projects;
    }

    public ArrayList<TodoistItem> readItems() throws IOException, JSONException {
        URL url = new URL(request + "[\"items\"]");
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();

        JSONObject jsonObject = new JSONObject(answerFromServer);
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");

        ArrayList<TodoistItem> items = new ArrayList();


        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject currentObject = (JSONObject) jsonArray.get(i);
            items.add(JSONtoTodoistItem(currentObject));
        }

        return items;
    }

    public ArrayList<TodoistItem> readItems(long projectId) throws IOException, JSONException {
        URL url = new URL(request + "[\"items\"]");
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();

        JSONObject jsonObject = new JSONObject(answerFromServer);
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        JSONArray jsonArrayAnswer = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            if (jo.get("project_id").equals(projectId)){
                jsonArrayAnswer.put(jo);
            }
        }

        ArrayList<TodoistItem> items = new ArrayList();


        for (int i = 0; i < jsonArrayAnswer.length(); i++){
            JSONObject currentObject = (JSONObject) jsonArrayAnswer.get(i);
            items.add(JSONtoTodoistItem(currentObject));
        }

        return items;
    }

    public ArrayList<String> readItemsContent(long projectId) throws IOException, JSONException {
        URL url = new URL(request + "[\"items\"]");
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();

        JSONObject jsonObject = new JSONObject(answerFromServer);
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");

        ArrayList<String> array = new ArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            if (jo.get("project_id").equals(projectId)){
                array.add(jo.get("content").toString());
            }
        }

        return array;
    }

    public ArrayList<TodoistItem> readItems(long projectId, long userId) throws IOException, JSONException {
        URL url = new URL(request + "[\"items\"]");
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();

        JSONObject jsonObject = new JSONObject(answerFromServer);
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        JSONArray jsonArrayAnswer = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            if (jo.get("user_id").equals(userId)){
                if (jo.get("project_id").equals(projectId)) {
                    jsonArrayAnswer.put(jo);
                }
            }
        }

        ArrayList<TodoistItem> items = new ArrayList();


        for (int i = 0; i < jsonArrayAnswer.length(); i++){
            JSONObject currentObject = (JSONObject) jsonArrayAnswer.get(i);
            items.add(JSONtoTodoistItem(currentObject));
        }

        return items;
    }

    public ArrayList<String> readItemsContent(long projectId, long userId) throws IOException, JSONException {
        URL url = new URL(request + "[\"items\"]");
        reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String answerFromServer = getServerAnswer(reader);
        reader.close();

        JSONObject jsonObject = new JSONObject(answerFromServer);
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");

        ArrayList<String> array = new ArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            if (jo.get("user_id").equals(userId)) {
                if (jo.get("project_id").equals(projectId)){
                    array.add(jo.get("content").toString());
                }
            }
        }

        return array;
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

    private ArrayList<Integer> JSONtoTodoistLabels(JSONArray jsonArray){
        ArrayList<Integer> array = new ArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                array.add(Integer.valueOf(jsonArray.get(i).toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return array;
    }

    private TodoistItem JSONtoTodoistItem(JSONObject currentObject){
        TodoistItem item = new TodoistItem();
        try {
            item.setDay_order(Integer.valueOf(currentObject.get("day_order").toString()));
            if (currentObject.get("assigned_by_uid") == null) {
                item.setAssigned_by_uid(null);
            } else {
                item.setAssigned_by_uid(Long.valueOf(currentObject.get("assigned_by_uid").toString()));
            }
            if (currentObject.get("due_date_utc") == null) {
                item.setDue_date_utc(null);
            } else {
                item.setDue_date_utc(currentObject.get("due_date_utc").toString());
            }
            item.setIs_archived(Integer.valueOf(currentObject.get("is_archived").toString()));
            item.setLabels(JSONtoTodoistLabels((JSONArray) currentObject.get("labels")));
            if (currentObject.get("sync_id") == null) {
                item.setSync_id(null);
            } else {
                item.setSync_id(Long.valueOf(currentObject.get("sync_id").toString()));
            }
            item.setAll_day(Boolean.valueOf(currentObject.get("all_day").toString()));
            item.setIn_history(Integer.valueOf(currentObject.get("in_history").toString()));
            item.setDate_added(currentObject.get("date_added").toString());
            item.setChecked(Integer.valueOf(currentObject.get("checked").toString()));
            item.setDate_lang(currentObject.get("date_lang").toString());
            item.setId(Long.valueOf(currentObject.get("id").toString()));
            item.setContent(currentObject.get("content").toString());
            item.setIndent(Integer.valueOf(currentObject.get("indent").toString()));
            item.setUser_id(Long.valueOf(currentObject.get("user_id").toString()));
            item.setIs_deleted(Integer.valueOf(currentObject.get("is_deleted").toString()));
            item.setPriority(Integer.valueOf(currentObject.get("priority").toString()));
            if (currentObject.get("parent_id") == null) {
                item.setParent_id(null);
            } else {
                item.setParent_id(Long.valueOf(currentObject.get("parent_id").toString()));
            }
            item.setItem_order(Integer.valueOf(currentObject.get("item_order").toString()));
            if (currentObject.get("responsible_uid") == null) {
                item.setResponsible_uid(null);
            } else {
                item.setResponsible_uid(Long.valueOf(currentObject.get("responsible_uid").toString()));
            }
            item.setProject_id(Long.valueOf(currentObject.get("project_id").toString()));
            item.setCollapsed(Integer.valueOf(currentObject.get("collapsed").toString()));
            if (currentObject.get("date_string") == null) {
                item.setDate_string(null);
            } else {
                item.setDate_string(currentObject.get("date_string").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    private TodoistProject JSONtoTodoistProject(JSONObject currentObject){
        TodoistProject project = new TodoistProject();
        try {
            project.setName(currentObject.get("name").toString());
            project.setColor(Integer.valueOf(currentObject.get("color").toString()));
            project.setIs_deleted(Integer.valueOf(currentObject.get("is_deleted").toString()));
            project.setCollapsed(Integer.valueOf(currentObject.get("collapsed").toString()));
            project.setId(Long.valueOf(currentObject.get("id").toString()));
            project.setParent_id(Long.valueOf(currentObject.get("parent_id").toString()));
            project.setItem_order(Integer.valueOf(currentObject.get("item_order").toString()));
            project.setIndent(Integer.valueOf(currentObject.get("indent").toString()));
            project.setShared(Boolean.valueOf(currentObject.get("shared").toString()));
            project.setIs_archived(Integer.valueOf(currentObject.get("is_archived").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return project;
    }

}
