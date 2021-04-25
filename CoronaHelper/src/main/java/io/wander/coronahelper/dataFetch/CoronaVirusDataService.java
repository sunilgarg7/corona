package io.wander.coronahelper.dataFetch;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.wander.coronahelper.data.LocationStats;

// charting option we explored and done RnD -https://jsitor.com/CVSqNP__I

@Service
public class CoronaVirusDataService {
	//https://github.com/covid19india/api -- all data api 
	// taken from web-scrapping - https://api.covid19india.org/v4/min/data-2020-08-06.min.json -- just modify the date in last part to get data for that day.
	
	// INDIA states data -- https://api.covid19india.org/csv/latest/states.csv -- we already have a row with INDIA, so we dont need to process it , along with TESTED column

    private static String VIRUS_DATA_URL = "https://api.covid19india.org/csv/latest/states.csv";// "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct      // after 4 Hours 
    @Scheduled(cron = "* * 1 * * *") // (fixedRate= 4 * 60 * 60 * 1000 )  //    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
   //   Spliterator<CSVRecord>   spliterator = Spliterators.spliteratorUnknownSize( records, 0);
   //    StreamSupport.stream(spliterator, false).filter( x -> x.get("State").equalsIgnoreCase("India")).map(System.out::println);
        int count =0;
        for (CSVRecord record : records) {
        	if (record.get("State").equalsIgnoreCase("India")) {
        		System.out.println(record);
        		count++;
        	}
        	
//            LocationStats locationStat = new LocationStats();
//            locationStat.setState(record.get("Province/State"));
//            locationStat.setCountry(record.get("Country/Region"));
//            int latestCases = Integer.parseInt(record.get(record.size() - 1));
//            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
//            locationStat.setLatestTotalCases(latestCases);
//            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
//            newStats.add(locationStat);
        }
        System.out.println(count);
        this.allStats = newStats;
    }
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
		
    	System.out.println("Start");
    	new CoronaVirusDataService().fetchVirusData();
    	
    	
    	System.out.println("Stop");

    	
	}

}
