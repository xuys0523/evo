package com.github.longkerdandy.evo.tcp.mq;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.longkerdandy.evo.api.message.Message;
import com.github.longkerdandy.evo.api.mq.LegacyConsumerWorker;
import com.github.longkerdandy.evo.tcp.repo.ChannelRepository;
import kafka.consumer.KafkaStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.github.longkerdandy.evo.api.util.JsonUtils.ObjectMapper;

/**
 * Message Queue Legacy Consumer Worker for TCP Output Topic
 */
public class TCPConsumerWorker extends LegacyConsumerWorker {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(TCPConsumerWorker.class);

    private final ChannelRepository repository;

    public TCPConsumerWorker(KafkaStream stream, ChannelRepository repository) {
        super(stream);
        this.repository = repository;
    }

    @Override
    public void handleMessage(String message) {
        try {
            // parse json
            JavaType type = ObjectMapper.getTypeFactory().constructParametrizedType(Message.class, Message.class, JsonNode.class);
            Message<JsonNode> msg = ObjectMapper.readValue(message, type);
            // send message
            String deviceId = msg.getTo();
            if (StringUtils.isNotBlank(deviceId)) {
                this.repository.sendMessage(deviceId, msg);
            }
        } catch (IOException e) {
            logger.warn("Error when parsing message: {}", ExceptionUtils.getMessage(e));
        }
    }
}