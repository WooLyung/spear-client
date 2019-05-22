package com.example.SpearClient.Main;

public class MainLoop implements Runnable {

    @Override
    public void run() {
        while(true) {
            try {
                Game.deltaTime = (System.currentTimeMillis() - Game.preTime) / 1000f; // 델타 타임을 구함
                Game.preTime = System.currentTimeMillis();

                Thread.sleep(5); // 최대 프레임 제한
                Game.engine.update(); // 엔진을 업데이트
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
