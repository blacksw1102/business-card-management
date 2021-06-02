package com.blacksw.bcm.dummy;

import java.util.ArrayList;
import java.util.List;

public class Dummy {

	public static String getInsertSQLBusinessCardList() {
		StringBuffer buffer = new StringBuffer();
		
		// name
		List<String> name = new ArrayList<>();
		for(int i=1; i<=10; i++)
			name.add("이름" + i);
		
		// company_name
		List<String> companyName = new ArrayList<>();
		for(int i=1; i<=10; i++)
			companyName.add("회사명" + i);
		
		// department
		List<String> department = new ArrayList<>();
		for(int i=1; i<=10; i++)
			department.add("부서" + i);
		
		// position
		List<String> position = new ArrayList<>();
		for(int i=1; i<=10; i++)
			position.add("직책" + i);
		
		// email
		List<String> email = new ArrayList<>();
		for(int i=1; i<=100; i++)
			email.add("이메일" + i + "@gmail.com");
		
		// email
		List<String> tel = new ArrayList<>();
		for(int i=1; i<=100; i++)
			tel.add("010-xxxx-xxxx");
		
		return buffer.toString();
	}
	
}
