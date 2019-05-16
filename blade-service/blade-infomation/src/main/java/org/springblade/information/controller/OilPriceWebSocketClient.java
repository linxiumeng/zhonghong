package org.springblade.information.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hanbin
 */
public class OilPriceWebSocketClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(OilPriceWebSocketClient.class);

    public OilPriceWebSocketClient(String url, Map<String, String> headers) throws URISyntaxException {
        super(new URI(url), new Draft_6455(), headers, 10000);
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        logger.info("尝试握手连接中");
        for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
            String key = it.next();
            logger.info("key:value 为 "+key + ":" + shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String paramString) {
        //todo 入库
        logger.info("接收到 www.cnoil.com 的websocket的数据为 ：{} ",paramString);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        logger.info("webclient onClose 关闭");
    }

    @Override
    public void onError(Exception e) {
        logger.error("websocket on error : {}",e.toString());

    }

    public static void main(String[] args) {
        try {
            Map<String, String> headers = generateHeaders();
            OilPriceWebSocketClient client = new OilPriceWebSocketClient("ws://47.96.146.130:12345/quote?codes=oilf_oilh_oilz_oilg_oilm_oiln_oilq_oilu_oilv_oilx",headers);
            client.connect();
            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                logger.info("还没有打开");
            }
            logger.info("建立websocket连接");
            String a = "";
            client.onMessage(a);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> generateHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "47.96.146.130:12345");
        headers.put("Origin", "http://www.cnoil.com");
        headers.put("Sec-WebSocket-Extensions", "permessage-deflate; client_max_window_bits");
        headers.put("Sec-WebSocket-Key", "g1BF2tyAP/1ViqPuyktkQQ==");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
        return headers;
    }

}