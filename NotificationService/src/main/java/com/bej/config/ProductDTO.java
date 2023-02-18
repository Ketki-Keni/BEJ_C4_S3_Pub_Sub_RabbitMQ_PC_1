package com.bej.config;

import org.json.simple.JSONObject;

public class ProductDTO {

    private JSONObject jsonObject;

   public ProductDTO() {    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
