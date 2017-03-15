package com.android.loter.util;

/**
 * Created by we-win on 2017/3/14.
 */

public class CommonEvent {

    public class TabSelectedEvent {
        public int position;

        public TabSelectedEvent(int position) {
            this.position = position;
        }
    }

    public class BottombarStatusEvent {
        private int status;

        public BottombarStatusEvent(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
