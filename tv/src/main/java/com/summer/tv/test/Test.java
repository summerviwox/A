package com.summer.tv.test;

import com.blankj.utilcode.util.LogUtils;

public class Test {

    static Boolean b;

    public static void aaa(){
        bbb(b);
    }
    public static void bbb(boolean bbb){
        LogUtils.e(bbb);
    }
}
