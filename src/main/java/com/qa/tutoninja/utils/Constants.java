package com.qa.tutoninja.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String FORGOT_OPTION="Forgot password?";
	public static final String ACCOUNTS_PAGE_TITLE="My Account";
	public static final String ACCOUNT_PAGE_HEADER="Your Store";
	public static final int ACCOUNT_SECTIONS_COUNT=4;
	
//	public static final String LOGIN_PAGE_TITLE="Login Page";
//	public static final String FORGOT_OPTION="Forgot password?";
//	public static final String ACCOUNTS_PAGE_TITLE="MoBe";
//	public static final String ACCOUNT_PAGE_HEADER="Mobe";
//	public static final int ACCOUNT_SECTIONS_COUNT=4;
//	
	public static List<String> getAccountsSectionsList()
	{
		List<String> accountList=new ArrayList<String>();
		accountList.add("My Account");
		accountList.add("My Orders");
		accountList.add("My Affiliate Account");
		accountList.add("Newsletter");
		return accountList;
	}
	

}
