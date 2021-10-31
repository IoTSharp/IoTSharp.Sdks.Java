package iotsharp.models;

import iotsharp.CommonResult;
import iotsharp.models.result.MyInfoResult;


public class MyInfoData extends CommonResult {

    public MyInfoResult getData() {
        return data;
    }

    public void setData(MyInfoResult data) {
        this.data = data;
    }

    MyInfoResult data;
}
