
package com.hogo.rahul.retrofitusingjson.Activity;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostGsonModel {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("userdata")
    @Expose
    private List<Userdatum> userdata = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Userdatum> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<Userdatum> userdata) {
        this.userdata = userdata;
    }

}
