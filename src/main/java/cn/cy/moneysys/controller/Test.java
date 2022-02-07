package cn.cy.moneysys.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class Test {

  @GetMapping("/test")
  Map getTest(){
    HashMap<String, String> map = new HashMap<>();
    map.put("test","this is test");
    return map;
  }
}
