package com.dly;

import dly.id.IDGenerator;

public class TempMainTest {
    public static void main(String args[]) {
        System.out.println(java.util.UUID.randomUUID().toString());
        System.out.println(IDGenerator.UUID.generate());
        String a = "12";
        String b = new String(a);
        System.out.println(a + "-" + b);
        System.out.println(a == b);
    }
}
