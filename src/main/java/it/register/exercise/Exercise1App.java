package it.register.exercise;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.register.exercise.model.RequestRecord;
import it.register.exercise.model.TrafficData;

public class Exercise1App {
	
	private final static int SCALE = 3;
	private final static String HTTP_OK = "200";

	public static void main(String[] args) throws URISyntaxException, IOException {
	    List<RequestRecord> requestRecords = loadFileInList();
	    
	    List<TrafficData> trafficDataList = processRecordAndCreateTrafficData(requestRecords);
	    
	    writeTrafficDataToFile(trafficDataList);
	    	
	}
	
	private static List<RequestRecord> loadFileInList() throws URISyntaxException, IOException {
		Path path = Paths.get(Exercise1App.class.getClassLoader()
	    	      .getResource("logfiles/requests.log").toURI());
	    	         
	    Stream<String> lines = Files.lines(path);
	    List<RequestRecord> requestRecords = lines
	    		.map(record -> new RequestRecord(record))
	    		.filter(record -> !HTTP_OK.equals(record.getHttpStatus()))
	    	.collect(Collectors.toList());
	    lines.close();
		return requestRecords;
	}

	private static List<TrafficData> processRecordAndCreateTrafficData(List<RequestRecord> requestRecords) {
		//get total info
	    long totalRequest = requestRecords.size();
	    long totalBytes =  requestRecords.stream()
	    		.map(RequestRecord::getBytes)
	    		.mapToLong(Long::valueOf)
	    		.sum();
	    
	    //process record and create traffic data
	    Comparator<TrafficData> sortByNumberOfRequest = (h1, h2) -> h1.getNumberOfrequest().compareTo(h2.getNumberOfrequest());
	    return requestRecords.stream()
    		.collect(groupingBy(r -> r.getRemoteAddress())).entrySet().stream()
	    	.map(buildTrafficData(totalRequest, totalBytes))
	    	.sorted(sortByNumberOfRequest.reversed())
	    	.collect(Collectors.toList());
	}

	private static void writeTrafficDataToFile(List<TrafficData> collect) throws URISyntaxException, IOException {
		Path out = Paths.get(Exercise1App.class.getClassLoader()
	    	      .getResource("reports/ipaddr.csv").toURI());
	    
	    List<String> csvRecords = collect.stream()
	    		.map(TrafficData::toCsv)
	    		.peek(csvRecord -> System.out.println(csvRecord))
	    		.collect(Collectors.toList());
		Files.write(out,csvRecords,Charset.defaultCharset());
	}

	private static Function<Entry<String, List<RequestRecord>>, TrafficData> buildTrafficData(
			long totalRequest, long totalBytes) {
		return entrySet -> {
			long numberOfrequest = (long) entrySet.getValue().size();
			BigDecimal percentageOfTotalRequest = divideToBigDecimal(numberOfrequest,totalRequest); 
			Long totalBytes4address = entrySet.getValue().stream()
					.map(RequestRecord::getBytes)
					.mapToLong(Long::valueOf)
					.sum();
			BigDecimal percentageOfTotalBytes = divideToBigDecimal(totalBytes4address,totalBytes);
			return new TrafficData(
				entrySet.getKey(), 
				numberOfrequest, 
				percentageOfTotalRequest,
				totalBytes4address,  
				percentageOfTotalBytes);
		};
	}

	private static BigDecimal divideToBigDecimal(Long dividend, Long divisor) {
		return new BigDecimal(dividend).divide(new BigDecimal(divisor), SCALE, RoundingMode.HALF_UP);
	}

}
