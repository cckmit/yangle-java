package com.ater.common.utils;

/**
 * 常量
 *
 * @author
 * @create 2017-04-01
 **/
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    public static final int PER_PAGE_TASK_NUM=6;
    public static final long EXPIRE_TIME = 3600*24*1000;    //token生效时长 单位毫秒
    /**
     * 菜单类型
     *
     * @author
     * @create 2017-04-01
     **/
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 定时任务状态
     *
     * @author
     * @email
     * @date
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum TaskStatus {
        /**
         * 开始
         */
        START(0),

        /**
         * 已发送
         */
        SENDED(1),

        /**
         * 已接单
         */
        COMPLETED(2),

        /**
         * 人工处理
         */
        MANUAL(3),

        /**
         * 人工处理完成
         */
        MANUAL_COMPLETED(4),

        /**
         * 订单失败或取消
         */
        FAILD(99);

        private int value;

        private TaskStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
