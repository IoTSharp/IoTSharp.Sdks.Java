package iotsharp.models;

import iotsharp.CommonResult;
import iotsharp.models.result.UserResult;

public class LoginData extends CommonResult {

    private UserResult data;

    public UserResult getData() {
        return data;
    }

    public void setData(UserResult data) {
        this.data = data;
    }
}
