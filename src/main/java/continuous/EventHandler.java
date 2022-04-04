package continuous;

import math.Vector3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class EventHandler {
    void Init() {}
    void Created(Object object) {}
    void PositionChanged(Object object, Vector3D position) {}
    void VelocityChanged(Object object, Vector3D velocity) {}
    void Accelerated(Object object, Vector3D acceleration) {}
    void MassChanged(Object object, Double mass) {}
    void Update(double milliSeconds) {}
    void Update(long nanoSeconds) {}
    void Update() {}
    void Destroyed(Object object) {}
    void MovementEnabled(Object object) {}
    void MovementDisabled(Object object) {}
    void RenderHandlerRegistered(Object object, double z, Consumer<Graphics> handler) {}
    void FrameCreated(JFrame frame) {}
    void SizeChanged(Object object, Vector3D size) {}
    void ColorChanged(Object object, Color color) {}
    void Visible(Object object) {}
    void Invisible(Object object) {}
    void RenderRectangleEnabled(Object object) {}
    void RenderRectangleDisabled(Object object) {}
    void WindowVisible() {}
    void WindowSizeChanged(int width, int height) {}
    void KeyPressed(KeyEvent event) {}
    void KeyReleased(KeyEvent event) {}
    void PlayerEnabled(Object object) {}
    void PlayerDisabled(Object object) {}
    void MovedDirection(Object object, Vector3D direction) {}
    void FrictionEnabled(Object object) {}
    void FrictionDisabled(Object object) {}
    void Update16() {}
    void KeyHeld(int key) {}
    void HealthSet(Object object) {}
    void DamageCaused(Object object, int damage) {}
    void HealthEnabled(Object object) {}
    void HealthDisabled(Object object) {}
    void Destructible(Object object) {}
    void Indestructible(Object object) {}
    void WeaponOffsetChanged(Object object, Vector3D offset) {}
    void WeaponNameChanged(Object object, String name) {}
    void WeaponFireMsChanged(Object object, double ms) {}
    void WeaponSpreadChanged(Object object, double spread) {}
    void WeaponAngleChanged(Object object, double angle) {}
    void WeaponProjectileSpeedChanged(Object object, double speed) {}
    void WeaponFired(Object object) {}
    void WeaponEnabled(Object object) {}
    void WeaponDisabled(Object object) {}
    void TimerRegistered(String name, double msInterval) {}
    void TimerTriggered(String name) {}
    void DelayRegistered(String name, double msDelay) {}
    void DelayTriggered(String name) {}
    void ProjectileFired(Object object, double angle) {}
}
