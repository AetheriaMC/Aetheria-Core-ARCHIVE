package net.badbird5907.aetheriacore.shared.utils.hash;

public class NoHash implements Hash {

    public String hash(String string) {
        return string;
    }
}
