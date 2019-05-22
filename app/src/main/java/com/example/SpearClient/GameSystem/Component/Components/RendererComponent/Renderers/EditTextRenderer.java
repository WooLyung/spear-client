package com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers;

import android.widget.EditText;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class EditTextRenderer extends Component {

    private String text = "";
    private EditText editText;
    private float nowX = 99999;
    private float nowY = 99999;
    private float nowWidth = 99999;
    private float nowHeight = 99999;
    private boolean isExist = false;
    private boolean isCenterWidth = true;
    private boolean isCenterHeight = true;

    @Override
    public void start() {
        setName("editTextRenderer");

        editText = new EditText(Game.instance);
        setText("text");
    }

    @Override
    public void update() {

        if (isExist == false) {
            if (Game.instance.mainView != null) {
                Game.instance.mainView.addView(editText);
                isExist = true;
            }
        }
        else {
            Game.instance.runOnUiThread(new Runnable() {
                public void run() {
                    if (nowX != object.getTransform().position.x || nowWidth != editText.getMeasuredWidth()) {
                        nowX = object.getTransform().position.x;
                        nowWidth = editText.getMeasuredWidth();
                        editText.setX(Game.screenWidth / 2 + Game.screenWidth * object.getTransform().position.x / (float)GLView.defaultWidth / 2 - ((isCenterWidth) ? editText.getMeasuredWidth() / 2 : 0));
                    }
                    if (nowY != object.getTransform().position.y || nowHeight != editText.getMeasuredHeight()) {
                        nowY = object.getTransform().position.y;
                        nowHeight = editText.getMeasuredHeight();
                        editText.setY(Game.screenHeight / 2 - Game.screenHeight * object.getTransform().position.y / (float)GLView.defaultHeight / 2 - ((isCenterHeight) ? editText.getMeasuredHeight() / 2 : 0));
                    }
                }
            });
        }
    }

    @Override
    public void finish() {
        if (isExist == true)
            Game.instance.mainView.removeView(editText);
    }

    public String getText() {
        return text;
    }

    public void setText(String text_) {
        this.text = text_;

        Game.instance.runOnUiThread(new Runnable() {
            public void run() {
            	editText.setText(text);
            }
        });
    }

    public EditText getEditText() {
        return editText;
    }

    public boolean getIsCenterWidth() {
        return isCenterWidth;
    }

    public void setIsCenterWidth(boolean isCenterWidth) {
        this.isCenterWidth = isCenterWidth;
    }

    public boolean getIsCenterHeight() {
        return isCenterHeight;
    }

    public void setIsCenterHeight(boolean isCenterHeight) {
        this.isCenterHeight = isCenterHeight;
    }
}
