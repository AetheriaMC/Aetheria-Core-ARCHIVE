package net.badbird5907.aetheriacore.spigot.commands;

import lombok.Getter;
import net.badbird5907.aetheriacore.commons.CC;

@Getter
public enum CommandResult {
    SUCCESS(""),
    ERROR(CC.RED + "There was an error while processing that command!"),
    INVALID_ARGS(CC.RED + "Invalid Arguments!"),
    PLAYER_NOT_FOUND(CC.RED + "That player can't be found!"),
    INVALID_PLAYER(PLAYER_NOT_FOUND.getMsg()),
    OTHER(""),
    NO_PERMS(CC.RED + "You don't have permissions!");
    private String msg;
    CommandResult(String s){
        this.msg = s;
    }

    public String getMessage() {
        return msg;
    }
}
