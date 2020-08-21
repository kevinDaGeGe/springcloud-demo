package com.kevin.plugin.fileupload.constans;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/18 14:50
 */
public enum FileType {
    IMG("img"),TEXT("text");


    private String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
