package net.badbird5907.aetheriacore.spigot.util;

import org.jetbrains.annotations.NotNull;

public final class Byte extends Number implements Comparable<Byte> {

	/*
	@since 1.8
	*/
	public static int toUnsignedInt(byte x) {
		return ((int) x) & 0xff;
	}

	@Override
	public int compareTo(@NotNull Byte o) {
		return 0;
	}

	@Override
	public int intValue() {
		return 0;
	}

	@Override
	public long longValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public double doubleValue() {
		return 0;
	}
}