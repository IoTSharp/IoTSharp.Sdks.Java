package iotsharp.models;

import iotsharp.CommonResult;
import iotsharp.models.result.DeviceResult;
import iotsharp.models.result.UserResult;

public class DevcieData extends CommonResult {

    private DeviceResult data;

    public DeviceResult getData() {
        return data;
    }

    public void setData(DeviceResult data) {
        this.data = data;
    }
}
