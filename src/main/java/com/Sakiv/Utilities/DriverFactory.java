package com.Sakiv.Utilities;

public class DriverFactory {
	private static String chromeExePath;
	private static String geckoExePath;
	private static String configPropertyPath;
	private static boolean isRemote;
	private static String gridPath;

	public static String getchromeExePath() {
		return chromeExePath;
	}

	public static void setchromeExePath(String chromeExePath) {
		DriverFactory.chromeExePath = chromeExePath;
	}

	public static String getgeckoExePath() {
		return geckoExePath;
	}

	public static void setgeckoExePath(String geckoExePath) {
		DriverFactory.geckoExePath = geckoExePath;
	}

	public static String getconfigPropertyPath() {
		return configPropertyPath;
	}

	public static void setconfigPropertyPath(String configPropertyPath) {
		DriverFactory.configPropertyPath = configPropertyPath;
	}

	public static boolean getisRemote() {
		return isRemote;
	}

	public static void setisRemote(boolean isRemote) {
		DriverFactory.isRemote = isRemote;
	}

	public static String getgridPath() {
		return gridPath;
	}

	public static void setgridPath(String gridPath) {
		DriverFactory.gridPath = gridPath;
	}
}
