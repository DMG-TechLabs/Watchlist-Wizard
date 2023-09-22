package Utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonUtils {
    public static JSONObject JsonToJsonObject(String input) {
        Object file = JSONValue.parse(input);
        return (JSONObject) file;
    }

    public static String GetJsonValue(String json, ArrayList<String> list) throws Exception {
        JSONObject jsonObjectdecode = null;
        JSONArray jsonArray = null;
        jsonObjectdecode = JsonToJsonObject(json);

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

    public static ArrayList<String> getMultipleValues(String json, ArrayList<String> list, int pos_element) throws Exception {
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> results = new ArrayList<String>();
        String value = "";

        for (int i=0;i<list.size();i++) {
            temp.add(list.get(i));
        }
        
        int size = getJsonArraySize(json, temp);
        
        for (int i = 0; i < size; i++) {
            list.set(pos_element, String.valueOf(i));
            value = GetJsonValue(json, list);
            results.add(value);
        }

        return results;
    }

    public static ArrayList<String> getMultipleValues(String json, ArrayList<String> list, String based_on, String equals_based_on, int pos_element) throws Exception {
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> results = new ArrayList<String>();
        String value = "";

        for (int i=0;i<=pos_element;i++) {
            temp.add(list.get(i));
        }
        
        int size = getJsonArraySize(json, temp);

        temp = null;
        System.gc();
        temp = new ArrayList<String>(list);
        temp.set(list.size()-1, based_on);
        
        for (int i = 0; i < size; i++) {
            temp.set(pos_element, String.valueOf(i));
            if(GetJsonValue(json, temp).equals(equals_based_on)) {
                list.set(pos_element, String.valueOf(i));
                value = GetJsonValue(json, list);
                results.add(value);
            }
        }

        return results;
    }

    public static ArrayList<String> getMultipleValues(String json, ArrayList<String> list, ArrayList<String> based_on, String equals_based_on, int pos_element) throws Exception {
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> results = new ArrayList<String>();
        String value = "";

        for (int i=0;i<=pos_element;i++) {
            temp.add(list.get(i));
        }
        
        int size = getJsonArraySize(json, temp);

        temp = null;
        System.gc();
        
        for (int i = 0; i < size; i++) {
            based_on.set(pos_element, String.valueOf(i));
            if(GetJsonValue(json, based_on).equals(equals_based_on)) {
                list.set(pos_element, String.valueOf(i));
                value = GetJsonValue(json, list);
                results.add(value);
            }
        }

        return results;
    }
}
