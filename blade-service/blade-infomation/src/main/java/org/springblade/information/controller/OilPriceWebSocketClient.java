package org.springblade.information.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.information.protocol.Draft_6456;

/**
 * @author hanbin
 */
public class OilPriceWebSocketClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(OilPriceWebSocketClient.class);

    public OilPriceWebSocketClient(String url, Map<String, String> headers) throws URISyntaxException {
        super(new URI(url), new Draft_6456(), headers, 10000);
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
        logger.info("接收到 www.cnoil.com 的websocket的数据为 ："+paramString);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        logger.info("webclient onClose 关闭");
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        logger.error("websocket on error" + e);

    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        super.onMessage(bytes);
        System.out.println(bytes);
    }

    public static void main(String[] args) {
        try {
            Map<String, String> headers = generateHeaders2();
      //      OilPriceWebSocketClient client = new OilPriceWebSocketClient("ws://47.96.146.130:12345/quote?codes=oilf_oilh_oilz_oilg_oilm_oiln_oilq_oilu_oilv_oilx",headers);
            OilPriceWebSocketClient client = new OilPriceWebSocketClient("wss://stream183.forexpros.com/echo/937/g4s39659/websocket",headers);
            client.connect();
            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                logger.info("还没有打开");
            }
            logger.info("建立websocket连接");
            String a = "";
          //  ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            client.onMessage(a);
            client.send("[\"{\\\"_event\\\":\\\"bulk-subscribe\\\",\\\"tzID\\\":28,\\\"message\\\":\\\"pid-8849:%%pid-44794:%%pid-14208:%%pid-14218:%%pid-1043091:%%pid-1075589:%%pid-44793:%%pid-1014132:%%pid-38233:%%pid-40820:%%pid-28930:%%pid-179:%%pid-178:%%pid-8873:%%pid-8839:%%pid-8827:%%pid-8984:%%pid-1:%%pid-2:%%pid-3:%%pid-5:%%pid-7:%%pid-9:%%pid-10:%%pidTechSumm-1:%%pidTechSumm-2:%%pidTechSumm-3:%%pidTechSumm-5:%%pidTechSumm-7:%%pidTechSumm-9:%%pidTechSumm-10:%%pidExt-8849:%%isOpenExch-1:%%isOpenExch-95:%%isOpenExch-54:%%isOpenExch-21:%%isOpenExch-20:%%isOpenPair-8849:%%isOpenPair-8873:%%isOpenPair-8839:%%isOpenPair-8827:%%isOpenPair-8984:%%cmt-6-5-8849:%%domain-6:\\\"}\"]");
            client.send("[\"{\\\"_event\\\":\\\"UID\\\",\\\"UID\\\":0}\"]");
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

    private static Map<String, String> generateHeaders2() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "stream195.forexpros.com");
        headers.put("Origin", "https://cn.investing.com");
        headers.put("Sec-WebSocket-Extensions", "permessage-deflate; client_max_window_bits");
        headers.put("Sec-WebSocket-Key", "g1BF2tyAP/1ViqPuyktkQQ==");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
        return headers;
    }



}