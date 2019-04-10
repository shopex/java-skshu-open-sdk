import cn.shopex.open.ShopexOpenClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopexOpenPostTestHttps {

    public static void main(String[] args) {

        String url = "https://xxx.shopex.cn/router";
        String appkey = "";
        String secret = "";
        String method = "skshu.data.import.order";

        ShopexOpenClient shopexOpenClient = new ShopexOpenClient(url, appkey, secret);
        Map<String, String> sysParams = new HashMap<String, String>();
        sysParams.put("method", method);

//        Map<String, String> jsondata = JSONObject.parseObject("{\"receiver_city\":\"杭州\",\"orders_number\":\"2\",\"shipping_type\":\"快递\",\"is_cod\":\"false\",\"tid\":\"709274618384220001\",\"receiver_state\":\"浙江\",\"pay_status\":\"PAY_FINISH\",\"payed_fee\":\"0.00\",\"total_trade_fee\":\"0.00\",\"modified\":\"2018-07-09 17:36:19\",\"receiver_name\":\"林xx了\",\"method\":\"store.trade.add\",\"created\":\"2018-07-09\",\"receiver_district\":\"西湖区\",\"format\":\"json\",\"has_invoice\":\"false\",\"receiver_mobile\":\"1316217193\",\"ship_status\":\"SHIP_NO\",\"shipping_fee\":\"0.00\",\"v\":\"1.0\",\"receiver_address\":\"xx大厦\",\"orders\":\"{\\\"items_num\\\":4,\\\"oid\\\":\\\"70927461838422C0001\\\",\\\"order_items\\\":{\\\"item\\\":[{\\\"bn\\\":\\\"\\\",\\\"item_status\\\":\\\"normal\\\",\\\"item_type\\\":\\\"product\\\",\\\"num\\\":2,\\\"price\\\":\\\"1\\\",\\\"sale_price\\\":\\\"1\\\",\\\"total_item_fee\\\":\\\"3\\\"},{\\\"bn\\\":\\\"\\\",\\\"item_status\\\":\\\"normal\\\",\\\"item_type\\\":\\\"product\\\",\\\"num\\\":2,\\\"price\\\":\\\"1\\\",\\\"sale_price\\\":\\\"1\\\",\\\"total_item_fee\\\":\\\"3\\\"}]},\\\"refund_status\\\":\\\"PAY_FINISH\\\",\\\"total_order_fee\\\":0.0,\\\"type\\\":\\\"goods\\\"}\",\"is_protect\":\"false\",\"node_id\":\"1092156036_1129944230\",\"status\":\"TRADE_FINISHED\"}", Map.class);

        Map<String, String> appParams = new HashMap<String, String>();
        appParams.put("jsondata", "orderorder.....");

        try {
            String apiResult = shopexOpenClient.doPost(sysParams, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
