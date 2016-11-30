package com.huashitu.vo;

/**
 * Created by levy on 2016/11/10.
 */
public class ResSendValidateCodeVO {
    private String reason;

    private Result result;

    private int error_code;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return this.result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return this.error_code;
    }

    public class Result {
        private String sid;

        private int fee;

        private int count;

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSid() {
            return this.sid;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getFee() {
            return this.fee;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }
}