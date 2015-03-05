package com.github.longkerdandy.evo.api.message;

/**
 * ConnAck Message
 * Acknowledge for Connect Message
 */
@SuppressWarnings("unused")
public class ConnAck {

    // Return Codes, from Platform to Device
    public static final int RECEIVED = 100;                         // Message received and re-directed to followers
    public static final int TIMESTAMP_NOT_SATISFIED = 105;          // Message received but device status not updated due to policy
    public static final int EMPTY_USER_OR_TOKEN = 110;              // Empty user or token
    public static final int USER_TOKEN_INCORRECT = 111;             // User token incorrect
    public static final int PROTOCOL_VERSION_UNACCEPTABLE = 115;    // Protocol version is not unacceptable
    public static final int DESCRIPTION_NOT_REGISTERED = 117;       // Device description is not registered

    private String connMsgId;       // ConnectMessage's message id
    private int returnCode;         // Return Code

    public String getConnMsgId() {
        return connMsgId;
    }

    public void setConnMsgId(String connMsgId) {
        this.connMsgId = connMsgId;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
