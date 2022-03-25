package com.xd.entity;

public interface MessageConstant {
    public int TYPE_SYSTEM = 1;
    public int TYPE_OWNER = 2;
    public int TYPE_OTHERS = 4;

    public int COMMAND_SEND = 8;
    public int COMMAND_ENTER = 16;
    public int COMMAND_LEAVE = 32;

    //  bitwise XOR result
    public int RESULT_SYSTEM_SEND = 9;
    public int RESULT_OWNER_SEND = 10;
    public int RESULT_OTHERS_SEND = 12;

    public int RESULT_OWNER_ENTER = 18;
    public int RESULT_OTHERS_ENTER = 20;

    public int RESULT_OWNER_LEAVE = 34;
    public int RESULT_OTHERS_LEAVE = 36;

}
