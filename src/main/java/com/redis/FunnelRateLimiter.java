package com.redis;

import java.util.HashMap;
import java.util.Map;

public class FunnelRateLimiter {
    static class Funnel {
        /**
         * 漏斗容量
         */
        int capacity;
        /**
         * 漏嘴流水速率
         */
        float leakingRate;
        /**
         * 漏斗剩余空间
         */
        int leftQuota;
        /**
         * 上一次漏水时间
         */
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            int deltaQuota = (int) (deltaTs * leakingRate);
            if (deltaQuota < 0) {
                // 时间间隔太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            if (deltaQuota < 1) {
                // 腾出空间太小，最小单位是1
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, Funnel> funnelMap = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s%s", userId, actionKey);
        Funnel funnel = funnelMap.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnelMap.put(key, funnel);
        }
        // 需要1个quota
        return funnel.watering(1);
    }

    public static void main(String[] args) {
        FunnelRateLimiter funnelRateLimiter = new FunnelRateLimiter();
        for (int i = 0; i < 20; i++) {
            System.out.println(funnelRateLimiter.isActionAllowed("laoqian", "reply", 15, 0.5F));
        }
    }
}
