package net.badbird5907.aetheriacore.utils.spigui.pagination;

import net.badbird5907.aetheriacore.utils.spigui.SGMenu;
import net.badbird5907.aetheriacore.utils.spigui.buttons.SGButton;

public interface SGPaginationButtonBuilder {
	SGButton buildPaginationButton(SGPaginationButtonType type, SGMenu inventory);
}