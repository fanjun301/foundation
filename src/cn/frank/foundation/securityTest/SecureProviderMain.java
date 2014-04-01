package cn.frank.foundation.securityTest;

import java.security.Provider;
import java.security.Security;

public class SecureProviderMain {

	public static void main(String[] args) {

		Provider[] providers = Security.getProviders();
		for (Provider provider : providers) {
			System.out.println(String.format("Name : %s | Info : %s | Version : %s", provider.getName(),provider.getInfo(),provider.getVersion()));
			
		}
		
		//add new provider api
//		Security.addProvider(provider);
	}

}
