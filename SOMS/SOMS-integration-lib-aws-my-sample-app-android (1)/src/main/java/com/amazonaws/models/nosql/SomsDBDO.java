package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "soms-mobilehub-1615670371-somsDB")

public class SomsDBDO {
    private Double _userSerialNum;
    private String _userName;
    private Double _approveCode;
    private Double _outingState;
    private Double _outingType;
    private Double _remainGrant;
    private Double _remainRegular;
    private Double _remainReward;
    private Double _troopCode;
    private Double _userClass;

    @DynamoDBHashKey(attributeName = "User_serial_num")
    @DynamoDBIndexHashKey(attributeName = "User_serial_num", globalSecondaryIndexName = "serialNum")
    public Double getUserSerialNum() {
        return _userSerialNum;
    }

    public void setUserSerialNum(final Double _userSerialNum) {
        this._userSerialNum = _userSerialNum;
    }
    @DynamoDBRangeKey(attributeName = "User_name")
    @DynamoDBIndexRangeKey(attributeName = "User_name", globalSecondaryIndexName = "serialNum")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }
    @DynamoDBAttribute(attributeName = "Approve_code")
    public Double getApproveCode() {
        return _approveCode;
    }

    public void setApproveCode(final Double _approveCode) {
        this._approveCode = _approveCode;
    }
    @DynamoDBAttribute(attributeName = "Outing_state")
    public Double getOutingState() {
        return _outingState;
    }

    public void setOutingState(final Double _outingState) {
        this._outingState = _outingState;
    }
    @DynamoDBAttribute(attributeName = "Outing_type")
    public Double getOutingType() {
        return _outingType;
    }

    public void setOutingType(final Double _outingType) {
        this._outingType = _outingType;
    }
    @DynamoDBAttribute(attributeName = "Remain_grant")
    public Double getRemainGrant() {
        return _remainGrant;
    }

    public void setRemainGrant(final Double _remainGrant) {
        this._remainGrant = _remainGrant;
    }
    @DynamoDBAttribute(attributeName = "Remain_regular")
    public Double getRemainRegular() {
        return _remainRegular;
    }

    public void setRemainRegular(final Double _remainRegular) {
        this._remainRegular = _remainRegular;
    }
    @DynamoDBAttribute(attributeName = "Remain_reward")
    public Double getRemainReward() {
        return _remainReward;
    }

    public void setRemainReward(final Double _remainReward) {
        this._remainReward = _remainReward;
    }
    @DynamoDBAttribute(attributeName = "Troop_code")
    public Double getTroopCode() {
        return _troopCode;
    }

    public void setTroopCode(final Double _troopCode) {
        this._troopCode = _troopCode;
    }
    @DynamoDBAttribute(attributeName = "User_class")
    public Double getUserClass() {
        return _userClass;
    }

    public void setUserClass(final Double _userClass) {
        this._userClass = _userClass;
    }

}
