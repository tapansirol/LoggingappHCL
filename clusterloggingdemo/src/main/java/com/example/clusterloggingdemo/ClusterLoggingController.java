package com.example.clusterloggingdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController
@Log4j2
public class ClusterLoggingController {
	
	@GetMapping("/info")
	public String getInfoMessage()
	{
		///log.in
//		log.info("Info Message from Demo application");
//		System.out.println("Log level :"+log.getLevel());
		log.error("Error Message from Demo application"+"--->");
		System.out.println("Log level err:"+log.getLevel());

		return "Info Message";
	}
	
	@GetMapping("/error1")
	public String getErrorMessage()
	{
		log.error("Error Message from Demo application"+"");
		return "Error Message";
	}
	@GetMapping("/info/JOB1")
	public String getInfoMessageJob()
	{
		log.info("JOB1: JOB1 Running for Info message");
		return "JOB1: Info Message";
	}
	
	@GetMapping("/error1/JOB1")
	public String getErrorMessageJob()
	{
		log.error("JOB1: JOB1 Running for Error message");
		return "JOB1: Error Message";
	}
	@GetMapping("/error1/{id}")
	public String getErrorMessageId(@PathVariable String id)
	{
		log.error("Error Message from Demo application "+id);
		return "Error Message custom "+id;
	}
	@GetMapping("/info/{id}")
	public String getInfoMessage(@PathVariable String id)
	{
		log.info("Info Message from Demo application "+id);
		return "Info Message custom "+id;
	}
	@GetMapping("/createJob/{jobid}/{env}/{jobStream}/{ws}")
	public String createJob(@PathVariable String jobid, @PathVariable String env, @PathVariable String jobStream, @PathVariable String ws)
	{
//		JobId
//		env: {Qa, Prod, Dev}
//		Job_Submission_time:,
//		Job_current_status: {Running, Completed, Error},
//		Error_reason : out_of_memory
//		Plateform: {Windows, Linux, Mac}
//		Memory_comsumption: 900MB
		
		String[] currStatusArr = {"Waiting","Held","Ready","Blocked","Running","Successful","Error","Cancelled"};
		String[] platformArr = {"Windows","Linux","MAC"};
		
		String errReason ="Out of Memory";
		String jid = jobid;
		
		//String env1 =env;
		
//		for (int i = 0; i < 10; i++) {
//			

			
			int memConsumption = (int)Math.ceil(Math.random() * (9999 - 1) + 1);
			int r = (int) (Math.random() * (99 - 1)) + 1;			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			
			int posStatus = new Random().nextInt(8);
			int posPf = new Random().nextInt(3);
			
			String currStatus = currStatusArr[posStatus];
			String pf = platformArr[posPf];
			
			LogFormat lf = new LogFormat();
			lf.setCpuUtilisation(r);
			lf.setElapsedTime(dtf.format(now));
			lf.setEnvironment(env);
			lf.setJobId(jid);
			lf.setJobStatus(currStatus);
			lf.setJobStream(jobStream);
			lf.setMemoryConsumption(memConsumption);
			lf.setWorkstation(ws);
			
			
			System.out.println("Job_ID: "+jid
					+", Env: "+env
					+", Job_Stream:"+ jobStream
					+", Workstation:"+ws
					+", Elapsed_Time:"+dtf.format(now)
					+", Job_Status:"+ currStatus
					+", Memory_Consumption: "+(int)Math.ceil(memConsumption)
					+", CPU_Utilisation: "+r
					+", Exit_Status: "+posStatus
					);
			
		//}
		
		
		return "job created successfully";
	}
	@PostMapping("/createJobStream/{name}")
	public String createJobStream(@PathVariable String name)
	{
		String search = "index=openshift kubernetes.namespace_name=sampleproject message=*JOB1*";
		String command = "curl -k -u admin:admin123 "
				+ "https://172.30.223.252:8089/servicesNS/admin/search/saved/searches "
				+ "-d name="+name+ 
				" --data-urlencode search="+search;
		
//		String[] commands = {"curl -k -u admin:admin123 \"\r\n" + 
//				"				+ \"https://172.30.223.252:8089/servicesNS/admin/search/saved/searches \"\r\n" + 
//				"				+ \"-d name=\"+name+ \r\n" + 
//				"				\" --data-urlencode search=index=openshift", "kubernetes.namespace_name=sampleproject", 
//				"message=*JOB1*"};

		//String command = "curl -k https://google.com";
		try {
			//Process process = Runtime.getRuntime().exec("/home/createjob.sh", name);
			
			
			
			String[] cmd = {"/home/createjob.sh", name};
			Process process = Runtime.getRuntime().exec(cmd);

			
			//System.out.println("Command ==> "+command);
			//System.out.println("Command ==> "+commands);
			BufferedReader reader = new BufferedReader(new 
					InputStreamReader(process.getInputStream()));
					String line;
					StringBuffer response = new StringBuffer();
					while ((line = reader.readLine()) != null) {
					    response.append(line);
					}
		} catch (IOException e) {
			e.printStackTrace();
		}
		    
		    return "JOB1";
	}

}
