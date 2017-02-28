package com.myapplication;

/**
 * Created by Ysnows on 2017/2/27.
 */

public class YsnowsBaseModel {
    public int type = 0;

    public static int TYPE_PLACEHOLDER = 1;//占位符
    public static int TYPE_PASTED = 2;//过期的
    public static int TYPE_CURRENT = 3;//当前的
    public static int TYPE_FUTURE = 4;//还没到的

    public String[] content = new String[2];


    public YsnowsBaseModel(int type, String content_1, String content_2) {
        this.type = type;
        this.content[0] = content_1;
        this.content[1] = content_2;
    }

    public int getType() {
        return type;
    }

    public String[] getContent() {
        return content;
    }
}
