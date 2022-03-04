package cn.cy.moneysys.service;

import cn.cy.moneysys.entity.MessageFormat;

import java.util.List;

public interface MessageFormatService {
    List<MessageFormat> selectAllMessageFormat();

    void insertMessageFormat(MessageFormat messageFormat);

    void deleteMessageFormat(List<String> ids);

    void updateMessageFormat(List<MessageFormat> messageFormatList);

    void deleteMessageFormat(String id);
}
