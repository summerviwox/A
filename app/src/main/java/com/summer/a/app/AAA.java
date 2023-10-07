package com.summer.a.app;

import com.squareup.javapoet.MethodSpec;

public class AAA {

    public static void AAADDD() {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addCode(""
                        + "int total = 0;\n"
                        + "for (int i = 0; i < 10; i++) {\n"
                        + "  total += i;\n"
                        + "}\n")
                .build();
    }
}



