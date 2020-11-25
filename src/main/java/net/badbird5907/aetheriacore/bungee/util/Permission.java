package net.badbird5907.aetheriacore.bungee.util;

public enum Permission {
    //super staff role (all staff should have)
    STAFF("staff"),
    //staff roles
    STAFF_TRIAL_BUILDER("staff.trial.builder"),
    STAFF_BUILDER("staff.builder"),
    STAFF_TRIAL_LM("staff.trial.loremaster"),
    STAFF_LM("staff.loremaster"),
    STAFF_TRIAL_DEV("staff.trial.dev"),
    STAFF_DEV("staff.dev"),
    STAFF_HELPER("staff.helper"),
    STAFF_MOD("staff.mod"),
    STAFF_ADMIN("staff.admin"),
    STAFF_OWNER("staff.owner"),

    //Other staff roles
    STAFF_LEAD_BUILDER("staff.lead.builder"),
    STAFF_LEAD_DEV("staff.lead.dev"),
    STAFF_LEAD_LM("staff.lead.lm"),



    STAFF_CHAT("staffchat"),
    ADMIN_CHAT("adminchat"),
    HUSH("hush"),
    STAFF_JOIN("staffjoin"),
    BROADCAST_JOIN("broadcast.join"),
    BROADCAST_LEAVE("broadcast.leave"),
    BROADCAST_SWITCH("broadcast.switch"),
    SEE_JOIN("see.join"),
    SEE_LEAVE("see.leave"),
    SEE_SWITCH("see.switch"),
    COMMAND_SPY("commandspy"),
    COMMAND_SPY_BYPASS("commandspy.bypass"),
    SEE_STAFF_ONLINE("staff.online"),
    GLOBAL_CLEAR_CHAT("global.clearchat"),
    CLEAR_CHAT_BYPASS("clearchat.bypass`"),
    GLOBAL_BROADCAST("global.broadcast")

    ;
    public final String node;

    Permission(final String node) {
        this.node = "aetheriacore." + node;
    }
}
