package com.blacksw.bcm.dummy;

import java.util.ArrayList;
import java.util.List;

public class Dummy {

	public static String getInsertSQLBusinessCardList() {
		StringBuffer buffer = new StringBuffer();
		
		// name
		List<String> name = new ArrayList<>();
		for(int i=1; i<=10; i++)
			name.add("�̸�" + i);
		
		// company_name
		List<String> companyName = new ArrayList<>();
		for(int i=1; i<=10; i++)
			companyName.add("ȸ���" + i);
		
		// department
		List<String> department = new ArrayList<>();
		for(int i=1; i<=10; i++)
			department.add("�μ�" + i);
		
		// position
		List<String> position = new ArrayList<>();
		for(int i=1; i<=10; i++)
			position.add("��å" + i);
		
		// email
		List<String> email = new ArrayList<>();
		for(int i=1; i<=100; i++)
			email.add("�̸���" + i + "@gmail.com");
		
		// email
		List<String> tel = new ArrayList<>();
		for(int i=1; i<=100; i++)
			tel.add("010-xxxx-xxxx");
		
		return buffer.toString();
	}
	
}
