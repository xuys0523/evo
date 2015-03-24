package com.github.longkerdandy.evo.api.message;

import com.github.longkerdandy.evo.api.protocol.Const;
import com.github.longkerdandy.evo.api.protocol.DeviceType;
import com.github.longkerdandy.evo.api.protocol.MessageType;
import com.github.longkerdandy.evo.api.protocol.QoS;
import com.github.longkerdandy.evo.api.util.UuidUtils;

import java.util.Map;

/**
 * Message Factory
 */
@SuppressWarnings("unused")
public class MessageFactory {

    private MessageFactory() {
    }

    /**
     * Create a new message based on a exist message and replace the payload
     *
     * @param msg     Exist Message, fields will be copied
     * @param payload Payload will be replaced
     * @param <T>     Payload Message Class
     * @return Message with replaced Payload
     */
    public static <T> Message<T> newMessage(Message msg, T payload) {
        Message<T> m = new Message<>();
        m.setPv(msg.getPv());
        m.setMsgType(msg.getMsgType());
        m.setQos(msg.getQos());
        m.setDuplicate(msg.isDuplicate());
        m.setDeviceType(msg.getDeviceType());
        m.setMsgId(msg.getMsgId());
        m.setFrom(msg.getFrom());
        m.setTo(msg.getTo());
        m.setDescId(msg.getDescId());
        m.setUserId(msg.getUserId());
        m.setTimestamp(msg.getTimestamp());
        m.setPayload(payload);
        return m;
    }

    /**
     * Create a new message container
     *
     * @param pv         Protocol Version
     * @param msgType    Message Type
     * @param qos        QoS
     * @param deviceType DeviceType
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @param payload    Payload
     * @param <T>        Payload Message Class
     * @return Message with Payload
     */
    protected static <T> Message<T> newMessage(int pv, int msgType, int qos, int deviceType, String from, String to, T payload) {
        Message<T> msg = new Message<>();
        msg.setPv(pv);                                  // Protocol Version
        msg.setMsgType(msgType);                        // Message Type
        msg.setQos(qos);                                // QoS
        msg.setDuplicate(false);                        // Default not duplicated
        msg.setDeviceType(deviceType);                  // Device Type
        msg.setMsgId(UuidUtils.shortUuid());            // Random UUID as Message Id
        msg.setFrom(from);                              // Device ID (who sends this message)
        msg.setTo(to);                                  // Device ID (who receives this message)
        msg.setTimestamp(System.currentTimeMillis());   // Current time as Timestamp
        msg.setPayload(payload);                        // Payload
        return msg;
    }

    /**
     * Create a new Message<Connect>
     *
     * @param pv         Protocol Version
     * @param deviceType Device Type
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @param descId     Device Description Id
     * @param userId     User ID (optional)
     * @param token      Token
     * @param attributes Attributes
     * @return Message<Connect>
     */
    public static Message<Connect> newConnectMessage(int pv, int deviceType, String from, String to, String descId, String userId,
                                                     String token, int policy, Map<String, Object> attributes) {
        Connect connect = new Connect();
        connect.setToken(token);
        connect.setPolicy(policy);
        connect.setAttributes(attributes);
        Message<Connect> msg = newMessage(pv, MessageType.CONNECT, QoS.LEAST_ONCE, deviceType, from, to, connect);
        msg.setDescId(descId);
        msg.setUserId(userId);
        return msg;
    }

    /**
     * Create a new Message<ConnAck>
     *
     * @param pv         Protocol Version
     * @param to         Device ID (who receives this message)
     * @param connMsgId  Connect Message Id
     * @param returnCode Return Code
     * @return Message<ConnAck>
     */
    public static Message<ConnAck> newConnAckMessage(int pv, String to, String connMsgId, int returnCode) {
        ConnAck connAck = new ConnAck();
        connAck.setConnMsgId(connMsgId);
        connAck.setReturnCode(returnCode);
        return newMessage(pv, MessageType.CONNACK, QoS.MOST_ONCE, DeviceType.PLATFORM, Const.PLATFORM_ID, to, connAck);
    }

    /**
     * Create a new Message<Disconnect>
     *
     * @param pv         Protocol Version
     * @param deviceType Device Type
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @return Message<Disconnect>
     */
    public static Message<Disconnect> newDisconnectMessage(int pv, int deviceType, String from, String to) {
        Disconnect disconnect = new Disconnect();
        return newMessage(pv, MessageType.DISCONNECT, QoS.LEAST_ONCE, deviceType, from, to, disconnect);
    }

    /**
     * Create a new Message<DisconnAck>
     *
     * @param pv           Protocol Version
     * @param to           Device ID (who receives this message)
     * @param disconnMsgId Disconnect Message Id
     * @param returnCode   Return Code
     * @return Message<DisconnAck>
     */
    public static Message<DisconnAck> newDisconnAckMessage(int pv, String to, String disconnMsgId, int returnCode) {
        DisconnAck disconnAck = new DisconnAck();
        disconnAck.setDisconnMsgId(disconnMsgId);
        disconnAck.setReturnCode(returnCode);
        return newMessage(pv, MessageType.DISCONNACK, QoS.MOST_ONCE, DeviceType.PLATFORM, Const.PLATFORM_ID, to, disconnAck);
    }

    /**
     * Create a new Message<Trigger>
     *
     * @param pv         Protocol Version
     * @param deviceType Device Type
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @param triggerId  Trigger Id
     * @param policy     Attributes Override Policy
     * @param attributes Attributes
     * @return Message<Trigger>
     */
    public static Message<Trigger> newTriggerMessage(int pv, int deviceType, String from, String to,
                                                     String triggerId, int policy, Map<String, Object> attributes) {
        Trigger trigger = new Trigger();
        trigger.setTriggerId(triggerId);
        trigger.setPolicy(policy);
        trigger.setAttributes(attributes);
        return newMessage(pv, MessageType.TRIGGER, QoS.MOST_ONCE, deviceType, from, to, trigger);
    }

    /**
     * Create a new Message<TrigAck>
     *
     * @param pv         Protocol Version
     * @param deviceType Device Type
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @param trigMsgId  Trigger Message Id
     * @param returnCode Return Code
     * @return Message<TrigAck>
     */
    public static Message<TrigAck> newTrigAckMessage(int pv, int deviceType, String from, String to,
                                                     String trigMsgId, int returnCode) {
        TrigAck trigAck = new TrigAck();
        trigAck.setTrigMsgId(trigMsgId);
        trigAck.setReturnCode(returnCode);
        return newMessage(pv, MessageType.TRIGACK, QoS.MOST_ONCE, deviceType, from, to, trigAck);
    }

    /**
     * Create a new Message<Action>
     *
     * @param pv         Protocol Version
     * @param deviceType Device Type
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @param userId     User Id
     * @param actionId   Action Id
     * @param lifetime   Action Lifetime, seconds until this action expires
     * @param attributes Attributes
     * @return Message<Action>
     */
    public static Message<Action> newActionMessage(int pv, int deviceType, String from, String to, String userId,
                                                   String actionId, int lifetime, Map<String, Object> attributes) {
        Action action = new Action();
        action.setActionId(actionId);
        action.setLifetime(lifetime);
        action.setAttributes(attributes);
        Message<Action> msg = newMessage(pv, MessageType.ACTION, QoS.MOST_ONCE, deviceType, from, to, action);
        msg.setUserId(userId);
        return msg;
    }

    /**
     * Create a new Message<ActAck>
     *
     * @param pv         Protocol Version
     * @param deviceType Device Type
     * @param from       Device ID (who sends this message)
     * @param to         Device ID (who receives this message)
     * @param actMsgId   ActAck Message Id
     * @param returnCode Return Code
     * @return Message<ActAck>
     */
    public static Message<ActAck> newActAckMessage(int pv, int deviceType, String from, String to,
                                                   String actMsgId, int returnCode) {
        ActAck actAck = new ActAck();
        actAck.setActMsgId(actMsgId);
        actAck.setReturnCode(returnCode);
        return newMessage(pv, MessageType.ACTACK, QoS.MOST_ONCE, deviceType, from, to, actAck);
    }
}
