package cn.cy.moneysys.service.impl;

import cn.cy.moneysys.entity.MessageFormat;
import cn.cy.moneysys.mapper.MessageFormatMapper;
import cn.cy.moneysys.service.MessageFormatService;
import cn.cy.moneysys.utils.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageFormatServiceImpl implements MessageFormatService {

    @Autowired
    private MessageFormatMapper messageFormatMapper;

    @Override
    public List<MessageFormat> selectAllMessageFormat() {
        return messageFormatMapper.selectList(new QueryWrapper<MessageFormat>());
    }

    @Override
    public void insertMessageFormat(MessageFormat messageFormat) {
        String id = UUID.getId();
        messageFormat.setCmid(id);
        int insert = messageFormatMapper.insert(messageFormat);
    }

    @Override
    public void deleteMessageFormat(List<String> ids) {
        int i = messageFormatMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateMessageFormat(List<MessageFormat> messageFormatList) {
        for (MessageFormat messageFormat : messageFormatList) {
            messageFormatMapper.updateById(messageFormat);
        }
    }

    @Override
    public void deleteMessageFormat(String id) {
        int i = messageFormatMapper.deleteById(id);
    }
}
