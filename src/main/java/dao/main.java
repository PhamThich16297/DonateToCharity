package dao;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import bean.DotQuyenGop;

public class main {
	public static void main(String[] args) {
		
		float n = 123456789;
		Locale locale = new Locale("vi", "VN");
		NumberFormat format = NumberFormat.getInstance(locale);
		System.out.println(format.format(n));
		
		
	}
}
