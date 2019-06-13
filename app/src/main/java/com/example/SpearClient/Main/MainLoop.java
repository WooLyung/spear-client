package com.example.SpearClient.Main;

public class MainLoop implements Runnable {

    @Override
    public void run() {
        while(true) {
            try {
                Game.setDeltaTime((System.currentTimeMillis() - Game.preTime) / 1000f); // 델타 타임을 구함
                Game.preTime = System.currentTimeMillis();

                Thread.sleep(5); // 최대 프레임 제한
                Game.engine.update(); // 엔진을 업데이트
                if (Game.slowTime != 0) { // 슬로우 시간 업데이트
                    Game.slowTime -= Game.getNoneDeltaTime();
                    if (Game.slowTime < 0)
                        Game.slowTime = 0;
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
