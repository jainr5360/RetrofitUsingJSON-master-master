package com.hogo.rahul.retrofitusingjson.RerofitModel;

import java.util.List;

/**
 * Created by Rahul jain on 05-11-2017.
 */

public class MyResponse {

    /**
     * response : success
     * userdata : [{"parent_id":"1%22","id":"3","name":"Egg","img":"http://biryanihouseuganda.com/api/assets/images/Bh_Images/","bh":"","description":"","cost":"28000"},{"parent_id":"1%22","id":"4","name":"Chicken","img":"http://biryanihouseuganda.com/api/assets/images/Bh_Images/","bh":"","description":"","cost":"28000"},{"parent_id":"1%22","id":"5","name":"Mutton","img":"http://biryanihouseuganda.com/api/assets/images/Bh_Images/","bh":"","description":"","cost":"28000"},{"parent_id":"1%22","id":"6","name":"Fish","img":"http://biryanihouseuganda.com/api/assets/images/Bh_Images/","bh":"","description":"","cost":"28000"},{"parent_id":"1%22","id":"7","name":"Extra Salan & Raita","img":"http://biryanihouseuganda.com/api/assets/images/Bh_Images/","bh":"","description":"","cost":"2500"}]
     */

    private String response;
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
         * parent_id : 1%22
         * id : 3
         * name : Egg
         * img : http://biryanihouseuganda.com/api/assets/images/Bh_Images/
         * bh :
         * description :
         * cost : 28000
         */

        private String parent_id;
        private String id;
        private String name;
        private String img;
        private String bh;
        private String description;
        private String cost;

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getBh() {
            return bh;
        }

        public void setBh(String bh) {
            this.bh = bh;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }
    }
}
