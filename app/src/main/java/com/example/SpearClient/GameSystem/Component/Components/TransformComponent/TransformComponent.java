package com.example.SpearClient.GameSystem.Component.Components.TransformComponent;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.Types.Vector;

abstract public class TransformComponent extends Component {

    public Vector anchor = new Vector(0.5f, 0.5f);
    public Vector position = new Vector();
    public Vector scale = new Vector(1, 1);
    public float angle = 0;
}
