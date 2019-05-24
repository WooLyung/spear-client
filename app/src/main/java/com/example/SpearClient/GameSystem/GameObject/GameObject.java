package com.example.SpearClient.GameSystem.GameObject;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.TransformComponent;
import com.example.SpearClient.Main.Game;

import org.json.JSONException;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

abstract public class GameObject {

    protected RendererComponent renderer;
    protected TransformComponent transform;
    private ArrayList<Component> components = new ArrayList<Component>();
    private String tag = "";
    private String name = "";
    private GameObject parent = null;
    private ArrayList<GameObject> childs = new ArrayList<GameObject>();
    private boolean isActive = true;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameObject() {
        start();
    }

    public RendererComponent getRenderer() {
        return renderer;
    }

    public TransformComponent getTransform() {
        return transform;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void appendChild(GameObject child) {
        childs.add(child);
        child.parent = this;
    }

    public void setParent(GameObject parent) {
        parent.appendChild(this);
    }

    public void removeParent() {
        if (parent != null) {
            parent.childs.remove(this);
            parent = null;
        }
    }

    public void removeChild(GameObject child) {
        child.removeParent();
    }

    public GameObject findOfTag(String tag) {
        if (getName().equals(tag)) {
            return this;
        }

        for (GameObject child : childs) {
            GameObject foundObj = child.findOfTag(tag);
            if (foundObj != null)
                return foundObj;
        }

        return null;
    }

    public GameObject findOfName(String name) {
        if (getName().equals(name)) {
            return this;
        }

        for (GameObject child : childs) {
            GameObject foundObj = child.findOfName(name);
            if (foundObj != null)
                return foundObj;
        }

        return null;
    }

    public void attachComponent(Component newComponent) {
        components.add(newComponent);
        newComponent.object = this;

        if (newComponent.getName() == "spriteRenderer"
            || newComponent.getName() == "animationRenderer") {
            renderer = (RendererComponent) newComponent;
        }

        if (newComponent.getName() == "transform"
            || newComponent.getName() == "GUITransform") {
            transform = (TransformComponent) newComponent;
        }
    }

    public ArrayList<GameObject> getChilds() {
        return childs;
    }

    public GameObject getParent() {
        return parent;
    }

    public Component getComponent(String componentName) {
        for (Component component : components) {
            if (component.getName().equals(componentName)) {
                return component;
            }
        }

        return null;
    }

    abstract public void start();

    public void update() {
        for (Component component : components) {
            component.update();
        }

        for (GameObject child : childs) {
            child.update();
        }
    }

    public void render(GL10 gl) {
        if (renderer != null)
            renderer.render(gl);
    }

    public void finish() {
        for (Component component : components) {
            component.finish();
        }

        for (GameObject child : childs) {
            child.finish();
        }
    }

    public void destroy() {
        removeParent();
        Game.engine.nowScene.objs.remove(this);
        finish();
    }
}