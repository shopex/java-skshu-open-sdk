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

        Map<String, String> appParams = new HashMap<String, String>();
        appParams.put("jsondata", "{\"platform_id\":\"OMS\",\"unique_id\":\"123123\",\"source\":\"tmall2222\",\"created\":\"2019-03-21 09:12:14\",\"lastmodify\":\"2019-03-23 19:12:14\",\"status\":\"TRADE_ACTIVE1212\",\"payment_status\":\"PAY_FINISH\",\"payment_type\":\"alipay\",\"shipping_type\":\"SF\",\"total_goods_fee\":28800,\"total_trade_fee\":29800,\"seller_uname\":\"张三111\",\"seller_name\":\"张三疯\",\"seller_mobile\":\"13111111111\",\"buyer_uname\":\"李四\",\"buyer_id\":\"lisi123\",\"buyer_name\":\"李四四\",\"buyer_email\":\"lisi@lisi.com\",\"buyer_mobile\":\"13911111111\",\"buyer_phone\":\"\",\"buyer_province\":\"浙江省\",\"buyer_city\":\"杭州市\",\"buyer_district\":\"滨江区\",\"buyer_address\":\"滨湖路388号\",\"buyer_postcode\":\"\",\"orders_num\":3,\"items_num\":8,\"trade_type\":\"fixed\",\"orders\":{\"aa\":\"bb\"},\"items\":{\"xxx\":\"1231312\"},\"payments\":{},\"promotions\":{},\"dealer_id\":\"d001\",\"agent_id\":\"a001\",\"shop_id\":\"s001\",\"referrer_member_id\":\"risss55\",\"referrer_point\":55,\"addon\":\"asdfasf\"}");

        try {
            String apiResult = shopexOpenClient.doPost(sysParams, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
