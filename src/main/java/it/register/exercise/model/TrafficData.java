package it.register.exercise.model;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrafficData {
	
	private String ipAddress;
	private Long numberOfrequest;
	private BigDecimal percentageOfTotalRequest;
	private Long totalBytes;
	private BigDecimal percentageOfTotalBytes;
	
	public TrafficData(String ipAddress, Long numberOfrequest, BigDecimal percentageOfTotalRequest, Long totalBytes,
			BigDecimal percentageOfTotalBytes) {
		super();
		this.ipAddress = ipAddress;
		this.numberOfrequest = numberOfrequest;
		this.percentageOfTotalRequest = percentageOfTotalRequest;
		this.totalBytes = totalBytes;
		this.percentageOfTotalBytes = percentageOfTotalBytes;
	}
	
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Long getNumberOfrequest() {
		return numberOfrequest;
	}
	public void setNumberOfrequest(Long numberOfrequest) {
		this.numberOfrequest = numberOfrequest;
	}
	public BigDecimal getPercentageOfTotalRequest() {
		return percentageOfTotalRequest;
	}
	public void setPercentageOfTotalRequest(BigDecimal percentageOfTotalRequest) {
		this.percentageOfTotalRequest = percentageOfTotalRequest;
	}
	public Long getTotalBytes() {
		return totalBytes;
	}
	public void setTotalBytes(Long totalBytes) {
		this.totalBytes = totalBytes;
	}
	public BigDecimal getPercentageOfTotalBytes() {
		return percentageOfTotalBytes;
	}
	public void setPercentageOfTotalBytes(BigDecimal percentageOfTotalBytes) {
		this.percentageOfTotalBytes = percentageOfTotalBytes;
	}


	@Override
	public String toString() {
		return "TrafficData [ipAddress=" + ipAddress + ", numberOfrequest=" + numberOfrequest
				+ ", percentageOfTotalRequest=" + percentageOfTotalRequest + ", totalBytes=" + totalBytes
				+ ", percentageOfTotalBytes=" + percentageOfTotalBytes + "]";
	}
	public String toCsv() {
	    return Stream.of(ipAddress.toString(),numberOfrequest.toString(),percentageOfTotalRequest.toString(),totalBytes.toString(),percentageOfTotalBytes.toString())
	    	      .collect(Collectors.joining(";"));
	}

}
