package com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers;

import android.util.Log;
import android.widget.TextView;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class TextRenderer extends Component {

    private String text = "";
    private TextView textView;
    private float nowX = 99999;
    private float nowY = 99999;
    private float nowWidth = 99999;
    private float nowHeight = 99999;
    private boolean isExist = false;
    private int horizontal = 1;
    private int vertical = 1;

    @Override
    public void start() {
        setName("textRenderer");

        textView = new TextView(Game.instance);
    }

    @Override
    public void update() {
        if (isExist == false) {
            if (Game.instance.mainView != null) {
                Game.instance.runOnUiThread(new Runnable() {
                    public void run() {
                        Game.instance.mainView.addView(textView);
                    }
                });
                isExist = true;
            }
        }
        else {
            Game.instance.runOnUiThread(new Runnable() {
                public void run() {
                textView.setText(textView.getText());

                if (nowX != object.getTransform().position.x || nowWidth != textView.getMeasuredWidth()) {
                    nowX = object.getTransform().position.x;
                    nowWidth = textView.getMeasuredWidth();
                    textView.setX(Game.screenWidth / 2 + Game.screenWidth * object.getTransform().position.x / (float)GLView.defaultWidth / 2 - horizontal * textView.getMeasuredWidth() / 2);
                }
                if (nowY != object.getTransform().position.y || nowHeight != textView.getMeasuredHeight()) {
                    nowY = object.getTransform().position.y;
                    nowHeight = textView.getMeasuredHeight();
                    textView.setY(Game.screenHeight / 2 - Game.screenHeight * object.getTransform().position.y / (float)GLView.defaultHeight / 2 - vertical * textView.getMeasuredHeight() / 2);
                }
                }
            });
        }
    }

    @Override
    public void finish() {
        if (isExist == true) {
            Game.instance.runOnUiThread(new Runnable() {
                public void run() {
                    Game.instance.mainView.removeView(textView);
                }
            });
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text_) {
        this.text = text_;

        Game.instance.runOnUiThread(new Runnable() {
            public void run() {
            	textView.setText(text);
            }
        });
    }

    public TextView getTextView() {
        return textView;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
}
