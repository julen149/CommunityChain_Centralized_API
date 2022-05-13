package com.communitychain.outputs;

public abstract class OutputManager {
    private String URL = "http://127.0.0.1:8080/api";
    public String getURL() {
        
        return this.URL;    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
