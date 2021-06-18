package iotsharp.models;

import iotsharp.CommonResult;

public class UserInfoResult extends CommonResult {

    public UserInfoResult() {
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    UserInfo data;
}
