package bean;

/**

 *code  200成功

 *      -11失败

 *message 返回信息

 *data 传回token

 */

public class Message {

    private int code;

    private String message;

    private String data;//



    public int getCode() {

        return code;

    }



    public void setCode(int code) {

        this.code = code;

    }



    public String getMessage() {

        return message;

    }



    public void setMessage(String message) {

        this.message = message;

    }



    public String getData() {

        return data;

    }



    public void setData(String data) {

        this.data = data;

    }

}