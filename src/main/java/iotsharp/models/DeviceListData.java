package iotsharp.models;

import iotsharp.CommonResult;
import iotsharp.models.result.DeviceListResult;
import iotsharp.models.result.DeviceResult;

import java.util.List;

public class DeviceListData extends CommonResult {
    public DeviceListResult getData() {
        return data;
    }

    public void setData(DeviceListResult data) {
        this.data = data;
    }

    private DeviceListResult data;
}
