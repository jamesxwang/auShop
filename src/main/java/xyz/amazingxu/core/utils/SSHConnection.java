package xyz.amazingxu.core.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


/**
 * @author xuwang <121894598@qq.com>
 * @date 2018/7/12 20:37
 */
public class SSHConnection {
    static int LOCAL_PORT = 3306;//本地端口
    static String REMOTE_HOST = "193.112.72.214";//远程MySQL服务器
    static int REMOTE_PORT = 3306;//远程MySQL服务端口

    private static void go() {
        String SSH_USER = "ubuntu";//SSH连接用户名
        String SSH_PASSWORD = "jameswang950514";//SSH连接密码
        String SSH_HOST = "193.112.72.214";//SSH服务器
        int SSH_PORT = 22;//SSH访问端口
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(SSH_USER, SSH_HOST, SSH_PORT);
            session.setPassword(SSH_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(LOCAL_PORT, REMOTE_HOST, REMOTE_PORT);
            System.out.println("localhost:" + assinged_port + " -> " + REMOTE_HOST + ":" + REMOTE_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SSHConnection(){
        go();
    }
}
