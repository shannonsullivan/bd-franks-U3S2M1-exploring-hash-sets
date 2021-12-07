package com.amazon.ata.hashingset.partsmanager;

import java.util.HashSet;
import java.util.Set;

public class PartManager {
    // Instantiate a HashSet of DeviceParts to keep track of what part us currently used
    private Set<DevicePart> deviceParts = new HashSet<>();

    public void addDevicePart(DevicePart devicePart) {

        // Using the HashSet .add() to add the part passes to use to the deviceParts hashSet
        // .add() automatically calls the hashCode()
        // .add() sometimes calls .equals() when there is a collision
        boolean isAdded = deviceParts.add(devicePart);
    }

    public void printDeviceParts() {
        for (DevicePart devicePart: deviceParts) {
            System.out.println(devicePart);
        }
    }
}
