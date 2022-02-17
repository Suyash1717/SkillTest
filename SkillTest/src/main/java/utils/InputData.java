package utils;

public class InputData {
	
	private String testURL;
	private String user;
	private String password;
	private String sort;
	private String firstName;
	private String lastName;
	private String zip;
	//CommonFunctions cmf = new CommonFunctions();
	public String getTestURL() {
		return testURL;
	}
	public void setTestURL(String testURL) {
		this.testURL = testURL;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/*
	 * public InputData translateXls(Integer iteration) {
		
		if(null!=iteration) {
			
			HashMap<String String> swagLabData = cmf.fetchXLDataNStoreInHashMap(String, String, String, String);
		}
		
		return null;
		
	} */
}
