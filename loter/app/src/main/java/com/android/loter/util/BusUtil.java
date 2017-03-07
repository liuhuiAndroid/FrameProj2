package com.android.loter.util;

import com.android.loter.model.MyBus;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by WE-WIN-027 on 2016/9/8.
 *
 * @des ${TODO}
 */
public class BusUtil {

    private static MyBus bus = null;

    public static Bus getBus() {
        if (bus == null) {
            synchronized (BusUtil.class) {
                if (bus == null) {
                    bus = new MyBus(ThreadEnforcer.ANY);
                }
            }
        }
        return bus;
    }

}
