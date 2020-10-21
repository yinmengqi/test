package test.SocketIo;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import io.socket.client.Socket;

/**
 * @author yinmengqi
 * @date 2020/10/20 3:19 下午
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
        config.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData handshakeData) {
                System.out.println("-----------------------------进入监听"+handshakeData.getHeaders());
                return true;
            }
        });

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                String token = client.getHandshakeData().getUrlParams().get("token").get(0);
                if (!token.equals("87df42a424c48313ef6063e6a5c63297")) {
                    client.disconnect();//校验token示例
                }
                System.out.println("-------------sessionId:" + client.getSessionId() + ",token:" + token);
            }
        });
        server.addDisconnectListener( new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                System.out.println("----------------断开连接");
            }
        });
        server.addEventListener(Socket.EVENT_MESSAGE, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                System.out.println("-------------------client data:" + data);
                server.getBroadcastOperations().sendEvent(Socket.EVENT_MESSAGE, "hi");
            }
        });
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }

}
