package net.badbird5907.aetheriacore.utils.spigui.pagination;

import static java.util.Arrays.stream;

public enum SGPaginationButtonType {
	PREV_BUTTON(3),
	CURRENT_BUTTON(4),
	NEXT_BUTTON(5),
	UNASSIGNED(0);
	private final int slot;

	SGPaginationButtonType(int slot) {
		this.slot = slot;
	}

	public static SGPaginationButtonType forSlot(int slot) {
		return stream(values()).filter(buttonType -> buttonType.slot == slot).findFirst().orElse(UNASSIGNED);
	}

	public int getSlot() {
		return slot;
	}

}