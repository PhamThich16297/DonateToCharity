package bean;

public class DVTN {
	private int maDVTN;
	private String tenDVTN;
	private String thongTin;
	private String urlLogo;
	
	
	
	public DVTN() {
	}

	public DVTN(String tenDVTN, String urlLogo) {
		this.tenDVTN = tenDVTN;
		this.urlLogo = urlLogo;
	}

	public DVTN(int maDVTN, String tenDVTN, String thongTin, String urlLogo) {
		this.maDVTN = maDVTN;
		this.tenDVTN = tenDVTN;
		this.thongTin = thongTin;
		this.urlLogo = urlLogo;
	}

	public int getMaDVTN() {
		return maDVTN;
	}

	public void setMaDVTN(int maDVTN) {
		this.maDVTN = maDVTN;
	}

	public String getTenDVTN() {
		return tenDVTN;
	}

	public void setTenDVTN(String tenDVTN) {
		this.tenDVTN = tenDVTN;
	}

	public String getThongTin() {
		return thongTin;
	}

	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}
	
	
}
