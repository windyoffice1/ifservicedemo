package com.windyoffice.ifservice.common;

public enum ResponseCode {

    SUCCESS(0,"success"),

    ERROR(1,"error");

    private final int code;
    private final String des;

    ResponseCode(int code,String des){
        this.code=code;
        this.des=des;
    }

    public int getCode(){
       return this.code;
    }

    public  String getDes(){
        return this.des;
    }
}
