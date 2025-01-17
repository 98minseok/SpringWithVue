package backend.util;

public class ApiResponse {
	public static String SUCCESS = "SUCCESS";
	public static String FAILED = "FAILED";
	public static String ERROR = "ERROR";
	
	private String status;
	private Object data;
	private String msg;
	
	public ApiResponse(String status) {
		this.status = status;
	}
	public ApiResponse(String status,String msg) {
		this.status = status;
		this.msg = msg;
	}
	public ApiResponse(String status,String msg,Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}	
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
