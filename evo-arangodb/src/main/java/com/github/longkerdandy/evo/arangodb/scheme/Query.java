package com.github.longkerdandy.evo.arangodb.scheme;

/**
 * Arango AQL
 */
public class Query {

    public static final String GET_USER_TOKEN = "FOR ut IN user_token FILTER ut.user == @user && ut.device == @device RETURN ut";
    public static final String GET_DEVICE_FOLLOWED_USER_ID = "FOR ud IN user_follow_device FILTER ud._to == @to && ud.permission >= @min && ud.permission <= @max RETURN ud._from";
    public static final String GET_USER_RELATED_DEVICE_ID  = "FOR ud IN user_follow_device FILTER ud._from == @from && ud.permission >= @min && ud.permission <= @max RETURN ud._to";
}