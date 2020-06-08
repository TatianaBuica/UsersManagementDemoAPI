package main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectToJson {

    public static String objectToJson (Object object) {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";

        try {
            jsonStr = Obj.writeValueAsString(object);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return jsonStr;
    }
}
