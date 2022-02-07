package cn.cy.moneysys.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    private HashMap<String,Object> data = new HashMap<String,Object>();
    private String messageCode;

    public static Msg createMsg(String messageCode){
        Msg msg = new Msg();
        msg.setMessageCode(messageCode);
        return msg;
    }


    public Msg addData(String key,Object value){
        this.data.put(key,value);
        return this;
    }


}
