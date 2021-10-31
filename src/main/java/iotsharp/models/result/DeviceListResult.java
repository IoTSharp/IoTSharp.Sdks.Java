package iotsharp.models.result;

import java.util.List;

public class DeviceListResult {

    private List<DeviceResult> rows;

    private int total;

    public List<DeviceResult> getRows() {
        return rows;
    }

    public void setRows(List<DeviceResult> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
