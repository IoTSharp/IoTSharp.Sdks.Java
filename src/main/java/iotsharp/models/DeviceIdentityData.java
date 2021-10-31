package iotsharp.models;

import iotsharp.CommonResult;
import iotsharp.models.result.UserResult;
import iotsharp.models.result.entities.DeviceIdentity;

public class DeviceIdentityData extends CommonResult {


    private DeviceIdentity data;

    public DeviceIdentity getData() {
        return data;
    }

    public void setData(DeviceIdentity data) {
        this.data = data;
    }
}
