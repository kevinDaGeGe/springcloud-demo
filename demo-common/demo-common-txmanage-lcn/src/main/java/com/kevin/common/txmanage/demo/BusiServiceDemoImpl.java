package com.kevin.common.txmanage.demo;
/**
 * Copyright © 2020 zlpay.
 */

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/4 18:20
 */
public class BusiServiceDemoImpl {
    // 下单扣库存
    @TxTransaction(isStart = true)
    @Transactional
    @GetMapping(value = "/addOrderAndStock")
    public ResponseBase addOrderAndStock(int i) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("demo测试");
        orderEntity.setOrderCreatetime(new Date());
        // 价格是300元
        orderEntity.setOrderMoney(300d);
        // 状态为 未支付
        orderEntity.setOrderState(0);
        Long commodityId = 30l;
        // 商品id
        orderEntity.setCommodityId(commodityId);
        // 1.先下单，创建订单
        int orderResult = orderMapper.addOrder(orderEntity);
        System.out.println("orderResult:" + orderResult);
        // 2.下单成功后,调用库存服务
        ResponseBase inventoryReduction = stockFeign.inventoryReduction(commodityId);
        // if (inventoryReduction.getRtnCode() != 200) {
        // // 手动回滚事务
        // }
        int reuslt = 1 / i;
        return setResultSuccess("下单成功!");
    }

}
