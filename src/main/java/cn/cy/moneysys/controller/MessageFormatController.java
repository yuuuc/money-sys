package cn.cy.moneysys.controller;

import cn.cy.moneysys.entity.MessageFormat;
import cn.cy.moneysys.entity.Msg;
import cn.cy.moneysys.service.MessageFormatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/messageFormat")
@RestController
public class MessageFormatController {

    @Autowired
    private MessageFormatService messageFormatService;

    @GetMapping("getMessageFormats")
    public Msg getAll(){
        try {
            List<MessageFormat> messageFormats = messageFormatService.selectAllMessageFormat();
            return Msg.createMsg("ok").addData("messageFormats",messageFormats);
        }catch (Exception e){
            log.info("getMessageFormats: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("insertMessageFormat")
    public Msg insertMessageFormat(@RequestBody @Validated MessageFormat messageFormat){
        try {
            messageFormatService.insertMessageFormat(messageFormat);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("insertMessageFormat: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("deleteMessageFormats")
    public Msg deleteMessageFormat(@RequestBody List<String> ids){
        try {
            messageFormatService.deleteMessageFormat(ids);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteMessageFormat: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("updateMessageFormats")
    public Msg updateMessageFormat(@RequestBody @Validated List<MessageFormat> messageFormatList){
        try {
            messageFormatService.updateMessageFormat(messageFormatList);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("updateMessageFormats: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("deleteMessageFormat")
    public Msg deleteMessageFormat(@RequestBody MessageFormat messageFormat){
        try {
            messageFormatService.deleteMessageFormat(messageFormat.getCmid());
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteMessageFormat: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

}
