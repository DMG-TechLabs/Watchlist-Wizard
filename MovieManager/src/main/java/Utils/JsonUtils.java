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

    public static JSONArray JsonToJsonArray(String json, ArrayList<String> list) throws Exception {
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

        return jsonArray;
    }

    public static String GetJsonValue(JSONArray json, ArrayList<String> list) throws Exception {
        JSONObject jsonObjectdecode = null;
        JSONArray jsonArray = null;

        jsonObjectdecode = JsonToJsonObject(json.get(Integer.parseInt(list.get(0))).toString());

        if (list.size() == 0)
            throw new Exception("Empty list");

        if (list.size() > 1) {
            String key;
            int i = 1;
            while (i < list.size() - 1) {
                key = list.get(i);
                jsonObjectdecode = JsonToJsonObject(jsonObjectdecode.get(key).toString());
                i = i + 1;
            }
        }
        return jsonObjectdecode.get(list.get(list.size() - 1)).toString();
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
        ArrayList<String> get_array = new ArrayList<String>();
        ArrayList<String> results = new ArrayList<String>();
        String value = "";

        for (int i=0;i<=pos_element;i++) {
            get_array.add(list.get(i));
        }
        
        JSONArray array = JsonToJsonArray(json, get_array);
        int size = array.size();

        get_array = null;
        System.gc();
        
        // After a have get the array which is before a work with array
        ArrayList<String> based_value_list = new ArrayList<String>();
        for (int i=pos_element;i<=list.size()-2;i++) {
            based_value_list.add(list.get(i));
        }
        based_value_list.add(based_on);

        ArrayList<String> array_value_list = new ArrayList<String>();
        for (int i=pos_element;i<=list.size()-1;i++) {
            array_value_list.add(list.get(i));
        }
        
        for (int i = 0; i < size; i++) {
            based_value_list.set(0, String.valueOf(i));
            if(GetJsonValue(array, based_value_list).equals(equals_based_on)) {
                array_value_list.set(0, String.valueOf(i));
                value = GetJsonValue(array, array_value_list);
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
