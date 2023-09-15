package Utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonUtils {
    public static JSONObject JsonToJsonObject(String input) {
        Object file = JSONValue.parse(input);
        return (JSONObject) file;
    }

    public static String GetJsonValue(String json, ArrayList<String> list) throws Exception {
        JSONObject jsonObjectdecode = JsonToJsonObject(json);
        JSONArray jsonArray;

        if (list.size() == 0)
            throw new Exception("Empty list");

        if (list.size() > 1) {
            String key;
            int i = 0;
            while (i < list.size() - 1) {
                key = list.get(i);
                try {
                    jsonObjectdecode = JsonToJsonObject(jsonObjectdecode.get(key).toString());
                } catch (ClassCastException e) {
                    jsonArray = (JSONArray) jsonObjectdecode.get(key);
                    key = list.get(i + 1);
                    jsonObjectdecode = JsonToJsonObject(jsonArray.get(Integer.parseInt(key)).toString());
                    i = i + 1;
                }
                i = i + 1;
            }
        }

        return jsonObjectdecode.get(list.get(list.size() - 1)).toString();
    }

    public static int getJsonArraySize(String json, ArrayList<String> list) throws Exception {
        JSONObject jsonObjectdecode = JsonToJsonObject(json);
        JSONArray jsonArray = new JSONArray();

        if (list.size() == 0)
            throw new Exception("Empty list");


        if (list.size() > 1) {
            String key;
            int i = 0;
            while (i < list.size() - 1) {
                key = list.get(i);
                try {
                    jsonObjectdecode = JsonToJsonObject(jsonObjectdecode.get(key).toString());
                } catch (ClassCastException e) {
                    jsonArray = (JSONArray) jsonObjectdecode.get(key);
                    key = list.get(i + 1);
                    jsonObjectdecode = JsonToJsonObject(jsonArray.get(Integer.parseInt(key)).toString());
                    i = i + 1;
                }
                i = i + 1;
            }
        }

        return jsonArray.size();
    }
}
