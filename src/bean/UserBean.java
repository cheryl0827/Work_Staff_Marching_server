package bean;
public class UserBean {
    private int userID;
    private String userName;
    private String indentificationCard;
    private String phone;
    private String country;
    private String address;
    private String roleName;
    private String password;
    private int registerStatus;
    private String city;
    private String province;
    private String workuserNo;
    private int workStatus;
    private String sex;
    private int maxTaskNumber;
    private int remainTaskNumber;
    
	public int getRemainTaskNumber() {
		return remainTaskNumber;
	}

	public void setRemainTaskNumber(int remainTaskNumber) {
		this.remainTaskNumber = remainTaskNumber;
	}

	public int getMaxTaskNumber() {
		return maxTaskNumber;
	}

	public void setMaxTaskNumber(int maxTaskNumber) {
		this.maxTaskNumber = maxTaskNumber;
	}

	public String getWorkuserNo() {
		return workuserNo;
	}

	public void setWorkuserNo(String workuserNo) {
		this.workuserNo = workuserNo;
	}

	public int getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIndentificationCard() {
        return indentificationCard;
    }

    public void setIndentificationCard(String indentificationCard) {
        this.indentificationCard = indentificationCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(int registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
