package com.mrwang.kotlindemo.test1;

/**
 * @author chengwangyong
 * @date 2018/9/2
 */
public class JavaTest {


    public static void getResult(HttpResult result) {
        if (result != null) {
            result.httpResult(null);
        }
    }

    public interface HttpResult {
        public void httpResult(String result);
        public void error(String result);
    }
}
