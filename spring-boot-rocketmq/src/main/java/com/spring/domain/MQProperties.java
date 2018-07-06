package com.spring.domain;

import com.spring.bean.MQTopicInfo;
import com.spring.bean.TopicInfo;
import com.spring.enums.MQMessagTypeEnum;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author shichao
 * @date 2018/6/19 下午4:44
 */
@ConfigurationProperties(prefix = "rocketmq")
@Component
@Validated
public class MQProperties {

    /**
     * 消费者地址
     */
    @NotBlank
    private String namesrvAddr;

    /**
     * mq普通、顺序、事务消息，包含启动时休眠时间及topic基本配置
     * key:{@link com.spring.enums.MQMessagTypeEnum},value:MQTopicInfo
     */
    private Map<String, MQTopicInfo> topicMap = new HashMap<>();

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public Map<String, MQTopicInfo> getTopicMap() {
        return topicMap;
    }

    public void setTopicMap(Map<String, MQTopicInfo> topicMap) {
        this.topicMap = topicMap;
    }

    public boolean checkProp(MQMessagTypeEnum topicEnum) {
        if (MapUtils.isEmpty(topicMap)) {
            return Boolean.FALSE;
        }
        MQTopicInfo mqTopicInfo = topicMap.get(topicEnum.getType());
        if (null == mqTopicInfo) {
            return Boolean.FALSE;
        }
        Map<String, TopicInfo> topicInfos = mqTopicInfo.getTopicInfos();
        if (MapUtils.isEmpty(topicInfos)) {
            return Boolean.FALSE;
        }
        AtomicBoolean flag = new AtomicBoolean(Boolean.FALSE);
        topicInfos.forEach((topic, topicInfo) -> {
            if (StringUtils.isBlank(topic)
                    || null == topic
                    || StringUtils.isBlank(topicInfo.getTags())) {
                return;
            }
            flag.set(Boolean.TRUE);
        });
        return flag.get();
    }

}
