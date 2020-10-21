package test.SocketIo;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;


/**
 * @author yinmengqi
 * @date 2020/10/20 3:24 下午
 */
public class Client {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;//失败重连的时间间隔
        options.timeout = 500;//连接超时时间(ms)

//        final Socket socket = IO.socket("http://localhost:9092/?token=123456", options);//错误的token值连接示例
        final Socket socket = IO.socket("http://localhost:9092/?token=87df42a424c48313ef6063e6a5c63297", options);

        Thread.sleep(3);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.send("hello");
            }
        });
        Thread.sleep(3);
        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("连接关闭");
            }
        });
        Thread.sleep(3);
        socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("sessionId:" + socket.id());
                for (Object obj : args) {
                    System.out.println(obj);
                }
                System.out.println("收到服务器应答，将要断开连接...");
                socket.disconnect();
            }
        });
        socket.connect();

        Thread.sleep(10);
    }
}
