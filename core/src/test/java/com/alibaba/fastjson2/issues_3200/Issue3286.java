package com.alibaba.fastjson2.issues_3200;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Issue3286 {
    @BeforeEach
    public void setUp() throws Exception {
        JSON.configEnumAsJavaBean(OrderType.class);
    }

    @Test
    public void test_serializeEnumAsJavaBean() throws Exception {
        String text = JSON.toJSONString(OrderType.PayOrder);
        assertEquals("{\"remark\":\"支付订单\",\"value\":1}", text);
    }

    @Test
    public void test_field() throws Exception {
        Model model = new Model();
        model.orderType = OrderType.SettleBill;
        String text = JSON.toJSONString(model);
        assertEquals("{\"orderType\":{\"remark\":\"结算单\",\"value\":2}}", text);
    }

    public enum OrderType {
        PayOrder(1, "支付订单"), //
        SettleBill(2, "结算单");

        public final int value;
        public final String remark;

        OrderType(int value, String remark) {
            this.value = value;
            this.remark = remark;
        }
    }

    public static class Model {
        public OrderType orderType;
    }
}
