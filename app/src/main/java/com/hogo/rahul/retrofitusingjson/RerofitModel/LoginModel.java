package com.hogo.rahul.retrofitusingjson.RerofitModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rahul on 8/11/17.
 */

public class LoginModel {

    /**
     * response : success
     * userdata : [{"msg":"login successfully","id":"26","name":"Rahul Mahaveer Jain","email":"jainr5360@gmail.com"}]
     */

    @SerializedName("response")
    private String response;
    @SerializedName("userdata")
    private List<UserdataBean> userdata;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<UserdataBean> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<UserdataBean> userdata) {
        this.userdata = userdata;
    }

    public static class UserdataBean {
        /**
         * msg : login successfully
         * id : 26
         * name : Rahul Mahaveer Jain
         * email : jainr5360@gmail.com
         */

        @SerializedName("msg")
        private String msg;
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("email")
        private String email;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
