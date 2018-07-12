package xyz.amazingxu.core.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/12 20:37
 */
public class SSHConnection {
    static int lport = 3306;//本地端口
    static String rhost = "193.112.72.214";//远程MySQL服务器
    static int rport = 3306;//远程MySQL服务端口

    private final static int LOCAl_PORT = 3307;
    private final static int REMOTE_PORT = 3306;
    private final static int SSH_REMOTE_PORT = 1022;
    private final static String SSH_USER = "ubuntu";
    private final static String SSH_PASSWORD = "jameswang950514";


    private static void go() {
        String user = "ubuntu";//SSH连接用户名
        String password = "jameswang950514";//SSH连接密码
        String host = "193.112.72.214";//SSH服务器
        int port = 22;//SSH访问端口
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SSHConnection(){
        go();
    }
}
