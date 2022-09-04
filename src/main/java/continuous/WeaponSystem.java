package continuous;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class WeaponSystem
        extends GameSystem {
    private Set<Object> objects = new HashSet<>();
    private Map<Object, Double> weaponMs = new HashMap<>();
    private Map<Object, Double> weaponDeltaTimes = new HashMap<>();
    private Map<Object, Double> weaponSpreads = new HashMap<>();
    private Map<Object, Double> weaponAngles = new HashMap<>();

    @Override
    void WeaponFireMsChanged(Object object,
                             double ms) {
        weaponMs.put(object, ms);
    }

    @Override
    void WeaponSpreadChanged(Object object,
                             double spread) {
        weaponSpreads.put(object, spread);
    }

    @Override
    void WeaponFired(Object object) {
        if (weaponDeltaTimes.get(object) < weaponMs.get(object)) return;

        double weaponAngle = weaponAngles.get(object);
        double weaponSpread = weaponSpreads.get(object);
        double angle = between(weaponAngle - weaponSpread, weaponAngle + weaponSpread);
        emit(s -> s.ProjectileFired(object, angle));
    }

    private double between(double low, double high) {
        return Math.random() * (high - low) + low;
    }

    @Override
    void Update(double milliSeconds) {
        weaponDeltaTimes.forEach((k, v) -> {
            if (v >= weaponMs.get(k)) return;
            weaponDeltaTimes.put(k, v + milliSeconds);
        });
    }

    @Override
    void WeaponAngleChanged(Object object,
                            double angle) {
        weaponAngles.put(object, angle);
    }

    @Override
    void WeaponEnabled(Object object) {
        objects.add(object);
        weaponDeltaTimes.put(object, 0d);
    }

    @Override
    void WeaponDisabled(Object object) {
        objects.remove(object);
    }

    @Override
    void Destroyed(Object object) {
        objects.remove(object);
        weaponSpreads.remove(object);
        weaponMs.remove(object);
        weaponAngles.remove(object);
        weaponDeltaTimes.remove(object);
    }
}
