/*
 * Author : Ketki Keni
 * Date : 17-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.config;

import org.json.simple.JSONObject;

public class ProductDTO {
    private JSONObject jsonObject;

    public ProductDTO() {
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
