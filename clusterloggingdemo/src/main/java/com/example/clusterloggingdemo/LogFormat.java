package com.example.clusterloggingdemo;

public class LogFormat {
	
	private String jobId;
	private String environment;
	private String jobStream;
	private String workstation;
	private String elapsedTime;
	private String jobStatus;
	private Integer memoryConsumption;
	private Integer cpuUtilisation;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getJobStream() {
		return jobStream;
	}
	public void setJobStream(String jobStream) {
		this.jobStream = jobStream;
	}
	public String getWorkstation() {
		return workstation;
	}
	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Integer getMemoryConsumption() {
		return memoryConsumption;
	}
	public void setMemoryConsumption(Integer memoryConsumption) {
		this.memoryConsumption = memoryConsumption;
	}
	public Integer getCpuUtilisation() {
		return cpuUtilisation;
	}
	public void setCpuUtilisation(Integer cpuUtilisation) {
		this.cpuUtilisation = cpuUtilisation;
	}
	
	@Override
	public String toString()
	{
		return "[ "+ "Job_ID: "+jobId
				+", Env: "+environment
				+", Job_Stream:"+ jobStream
				+", Workstation:"+workstation
				+", Elapsed_Time:"+elapsedTime
				+", Job_Status:"+ jobStatus
				+", Memory_Consumption: "+memoryConsumption
				+", CPU_Utilisation: "+cpuUtilisation
				+ " ]";
	}
	

}
