package com.example.SpearClient.SocketIO;

import android.util.Log;
import android.widget.Toast;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.Main.Game;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketIOBuilder {

    private Socket mSocket;
    private static final String TAG = "SocketIO";
    private static SocketIOBuilder instance = null;
    public static String id = null;

    private SocketIOBuilder(String serverUri) throws URISyntaxException {
        IO.Options opts = new IO.Options();
        mSocket = IO.socket(new URI(serverUri),opts);
        mSocket.connect();

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(final Object... args) { Log.d(TAG, "Connected"); }});
        mSocket.on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(final Object... args) { Log.d(TAG, "Error: "+args[0].toString()); }});
        mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(final Object... args) { Log.d(TAG, "Connect Error: "+args[0].toString());}});
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(final Object... args) { Log.d(TAG, "Connect Timeout: "+args[0].toString()); }});
        mSocket.on(Socket.EVENT_RECONNECT_FAILED, new Emitter.Listener() {
            @Override
            public void call(final Object... args) { Log.d(TAG, "Reconnect Error: "+args[0].toString()); }});
        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(final Object... args) { Log.d(TAG, "Disconnected"); }});

        mSocket.on("message", new Emitter.Listener() { // 이벤트네임, 콜백
            @Override
            public void call(final Object... args) { //데이터가 담긴 Object형 변수의 배열
                // 메세지가 왔을 때 동작시킬 함수...
                Log.d(TAG, args[0].toString());
            }
        });
    }

    public static SocketIOBuilder getInstance () {
        if (instance == null) {
            try {
                instance = new SocketIOBuilder("http://spear-server.run.goorm.io");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void register (String id, String password, String nickname, Emitter.Listener listener) {
        try {
            mSocket.emit("register", new JSONObject("{username: \""+id+"\", password: \""+password+"\", nickname: \""+nickname+"\"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.on("registerCallback", listener);
    }

    public void login (String id, String password, Emitter.Listener listener) {
        try {
            mSocket.emit("login", new JSONObject("{username: \""+id+"\", password: \""+password+"\"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.on("loginCallback", listener);
    }

    public void enter (Emitter.Listener listener) {
        mSocket.emit("enter");
        mSocket.on("enterCallback", listener);
    }

    public void gamestart (Emitter.Listener listener) {
        mSocket.on("gamestart", listener);
    }

    public void playerUpdate (JSONObject jsonObject) {
        mSocket.emit("playerUpdate", jsonObject);
    }

    public void update_player (Emitter.Listener listener) {
        mSocket.on("update_player", listener);
    }
}