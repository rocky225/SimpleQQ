package iqq.im.bean;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 9:38 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public enum QQStatus {
    /**
     * 10 : "online",
     * 20 : "offline",
     * 30 : "away",
     * 40 : "hidden",
     * 50 : "busy",
     * 60 : "callme",
     * 70 : "silent"
     */
    ONLINE("online", 10),
    OFFLINE("offline", 20),
    AWAY("away", 30),
    HIDDEN("hidden", 40),
    BUSY("busy", 50),
    CALLME("callme", 60),
    SLIENT("silent", 70);

    private String value;
    private int status;

    QQStatus(String value, int status) {
        this.value = value;
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public int getStatus() {
        return status;
    }
}
