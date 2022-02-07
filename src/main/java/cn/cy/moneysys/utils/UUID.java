package cn.cy.moneysys.utils;

public class UUID {

    public static String getId(){
        String s = java.util.UUID.randomUUID().toString();
        String replace = s.replace("-", "");
        return replace;
    }
}
