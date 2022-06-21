package yammer_test;

public class DemoClass {
	
	public static void main(String[] args) {
		String s = "[[ChromeDriver: chrome on WINDOWS (715f5f868700169bf9ab7c9cd9814349)] -> xpath: //*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/ul/li[2]/div/div/div/div/div/div/div[1]/div[5]/div[2]/div/div/div/div/div[2]/div/div/div/button/span/div]";
		System.out.println(s.length());
		String chromeLine = "[[ChromeDriver: chrome on WINDOWS (715f5f868700169bf9ab7c9cd9814349)] -> xpath: ";
		System.out.println(chromeLine.length());
		String xPathUptoDiv = "\"[[ChromeDriver: chrome on WINDOWS (715f5f868700169bf9ab7c9cd9814349)] -> xpath: //*[@id=\\\"root\\\"]/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/ul/li[2]/div/div/div/div/div/div/div[1]";
		System.out.println(xPathUptoDiv.length());
	}

}
