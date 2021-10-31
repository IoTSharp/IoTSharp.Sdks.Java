package iotsharp.models;

import iotsharp.CommonResult;
import iotsharp.models.result.entities.UserInfo;

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
