package it.register.exercise.model;

public class RequestRecord {
	
	private static final int TIMESTAMP=0;
	private static final int BYTES=1;
	private static final int STATUS=2;
	private static final int REMOTE_ADDR=3;
	
	private String timestamp;
	private Long bytes;
	private String httpStatus;
	private String remoteAddress;
	
	public RequestRecord(String record) {
		if (record != null) {
			String[] fields = record.split(";");
			this.timestamp=fields[TIMESTAMP];
			this.bytes=Long.valueOf(fields[BYTES]);
			this.httpStatus=fields[STATUS];
			this.remoteAddress=fields[REMOTE_ADDR];
		}
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Long getBytes() {
		return bytes;
	}
	public void setBytes(Long bytes) {
		this.bytes = bytes;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

}
