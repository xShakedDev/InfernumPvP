package net.apartium.servers.infernumpvp.utils;

public class utilTime {
 
    public static enum TimeUnit {
        BEST,
        DAYS,
        HOURS,
        MINUTES,
        SECONDS,
}
 
    public static double convert(long time, TimeUnit seconds, int decPoint) {
        if(seconds == TimeUnit.BEST) {
            if(time < 60000L) seconds = TimeUnit.SECONDS;
            else if(time < 3600000L) seconds = TimeUnit.MINUTES;
            else if(time < 86400000L) seconds = TimeUnit.HOURS;
            else seconds = TimeUnit.DAYS;
        }
        if(seconds == TimeUnit.SECONDS) return utilMath.trim(time / 1000.0D, decPoint);
        if(seconds == TimeUnit.MINUTES) return utilMath.trim(time / 60000.0D, decPoint);
        if(seconds == TimeUnit.HOURS) return utilMath.trim(time / 3600000.0D, decPoint);
        if(seconds == TimeUnit.DAYS) return utilMath.trim(time / 86400000.0D, decPoint);
        return utilMath.trim(time, decPoint);
    }
 
}