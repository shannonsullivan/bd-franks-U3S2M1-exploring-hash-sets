package com.amazon.ata.hashingset.partsmanager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartManager {
    // Instantiate a HashSet of DeviceParts to keep track of what part us currently used
    private Set<DevicePart> deviceParts = new HashSet<>();

    // Use the HashCode to determine the index of an element in an array
    // Since hash code collisions might occur we will store the elements in an ArrayList in the array
    // rather than storing the individual elements in the array
    // Determine the maximum size of the array - 10
    // To add an element in the array
    //  1. Find the Hash Code for the object
    //  2. Calculate the index for the array using the Hash Code
    //  3. Store the object in the array for index determined

    private int numParts = 10; // Number of elements in the array as a constant

    // Define an array of ArrayList<DevicePart> - Each element will be an ArrayList of DevicePart objects
    // An array of 10 DevicePart lists
    // interfaceBase = subclass(extends or implements)
    private List<DevicePart>[] partList = new ArrayList[numParts];

    public void addDevicePart(DevicePart devicePart) {
        // ***Store in HashSet and Array for learning purpose - Not production***
        // Using the HashSet .add() to add the part passes to use to the deviceParts hashSet
        // .add() automatically calls the hashCode()
        // .add() sometimes calls .equals() when there is a collision
        boolean isAdded = deviceParts.add(devicePart);

        // To add an element in the array
        //  1. Find the Hash Code for the object
        //  2. Calculate the index for the array using the Hash Code

        // Use the Math.abs() to be sure the index is not negative
        int partIndex = Math.abs(devicePart.hashCode() % numParts);  // Index based on the Hash Code for object to store

        //  3. Store the object in the array for index determined

        if (partList[partIndex] != null) { // Do we already have an object at this index? (Hash Code collision has occurred)
            partList[partIndex].add(devicePart); // Yes - Add the object to the ArrayList at element
        } else { // No - Instantiate an ArrayList in the index and add the object
            partList[partIndex] = new ArrayList<DevicePart>();
            partList[partIndex].add(devicePart);
        }
        return; // Not required for the method - It's added so we can stop the debugger here
    }

    /**
     * Find and return a DevicePart object in the array of object or null if not found
     *
     * @param requestedDevicePart
     */
    public DevicePart findPart(DevicePart requestedDevicePart) {
        // Instantiate an object to be returned
        DevicePart returnedObject = null;

        // Use the Hash Code for the requested object to determine its index in the array
        int partIndex = Math.abs(requestedDevicePart.hashCode() % numParts);

        // Check to see if the element in the array for the index has an ArrayList() - If not, no objects
        if (partList[partIndex] != null) { // If there is an ArrayList in the element, search for the requested object
            if (partList[partIndex].contains(requestedDevicePart)) { // If we find the requested object,
                // .get() of ArrayList to retrieve the object, .get() needs the index of object
                returnedObject = partList[partIndex].get(partList[partIndex].indexOf(requestedDevicePart)); // set the returned object to it
            }
            // If object not found in ArrayList, set returned object to null
        }
        // Return the found object
        return returnedObject;
    }

    public void printDeviceParts() {
        for (DevicePart devicePart: deviceParts) {
            System.out.println(devicePart);
        }
    }
}
