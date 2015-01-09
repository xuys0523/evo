package com.github.longkerdandy.evo.api.message;

import com.github.longkerdandy.evo.api.protocol.MessageType;
import com.github.longkerdandy.evo.api.protocol.QoS;

import java.util.Map;
import java.util.UUID;

/**
 * Message Factory
 */
public class MessageFactory {

    /**
     * Create a default Message
     */
    protected static <T> Message<T> newMessage() {
        Message<T> msg = new Message<>();
        msg.setMsgId(UUID.randomUUID().toString());     // Random UUID as Message Id
        // msg.setMsgType();
        // msg.setDescription();
        msg.setFrom("Evo Platform");                    // Default from Evolution Platform
        // msg.setTo();
        msg.setQos(QoS.MOST_ONCE);                      // Default QoS 0
        msg.setTimestamp(System.currentTimeMillis());   // Current time as Timestamp
        // msg.setPayload();
        return msg;
    }

    /**
     * Create a new Message<ConnAckMessage>
     *
     * @param to    Device Id which message will be sent to
     * @param msgId ConnectMessage Id
     * @return Message<ConnAckMessage>
     */
    public static Message<ConnAckMessage> newConnAckMessage(String to, String msgId) {
        Message<ConnAckMessage> msg = newMessage();
        ConnAckMessage connAck = new ConnAckMessage();
        connAck.setConnMsg(msgId);              // ConnectMessage Id
        msg.setMsgType(MessageType.CONNACK);    // Message Type: ConnAck
        msg.setTo(to);                          // To
        msg.setPayload(connAck);                // Payload
        return msg;
    }

    /**
     * Create a new Message<OnlineMessage>
     *
     * @param from Device Id which is online
     * @param to   Device Id which follows online device
     * @return Message<OnlineMessage>
     */
    public static Message<OnlineMessage> newOnlineMessage(String from, String to, String pv, String desc, Map<String, Object> attr) {
        Message<OnlineMessage> msg = newMessage();
        OnlineMessage online = new OnlineMessage();
        online.setProtocolVersion(pv);          // Protocol Version
        online.setDescription(desc);            // Description
        online.setAttributes(attr);             // Attributes
        msg.setMsgType(MessageType.ONLINE);     // Message Type: Online
        msg.setFrom(from);                      // From
        msg.setTo(to);                          // To
        msg.setPayload(online);                 // Payload
        return msg;
    }

    /**
     * Create a new Message<OfflineMessage>
     *
     * @param from Device Id which is online
     * @param to   Device Id which follows offline device
     * @return Message<OfflineMessage>
     */
    public static Message<OfflineMessage> newOfflineMessage(String from, String to, String pv, String desc) {
        Message<OfflineMessage> msg = newMessage();
        OfflineMessage offline = new OfflineMessage();
        offline.setProtocolVersion(pv);         // Protocol Version
        offline.setDescription(desc);           // Description
        msg.setMsgType(MessageType.OFFLINE);    // Message Type: Offline
        msg.setFrom(from);                      // From
        msg.setTo(to);                          // To
        msg.setPayload(offline);                // Payload
        return msg;
    }
}
