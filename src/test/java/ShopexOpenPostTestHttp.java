import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.shopex.open.ShopexOpenClient;

public class ShopexOpenPostTestHttp {

    public static void main(String[] args) {

        String url = "http://apigateway.teegon.ex-sandbox.com/router";
        String appkey = "JtDiBwD";
        String secret = "cjN22dCDJzTN4ZAMGac4";
        String method = "shopex.queue.read";

        ShopexOpenClient shopexOpenClient = new ShopexOpenClient(url, appkey, secret);
        Map<String, String> sysParams = new HashMap<String, String>();
        sysParams.put("method", method);

        Map<String, String> appParams = new HashMap<String, String>();
        appParams.put("topic", "user");
        appParams.put("drop", "true");
        appParams.put("num", "1");

        try {
            String apiResult = shopexOpenClient.doPost(sysParams, appParams);
            System.out.println(apiResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
