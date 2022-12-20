package org.example;

import javax.xml.crypto.Data;
import java.util.List;

public class DataOfOrder {

    private boolean success;
    private List<Data> data;

    public boolean isSuccess() {
        return success;
    }

    public List<Data> getOrders() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setOrders(List<Data> data) {
        this.data = data;
    }
}
