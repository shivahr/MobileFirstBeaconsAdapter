/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015, 2016. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
*/

package com.mobilefirst;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.MFPJAXRSApplication;

public class MobileFirstBeaconsAdapterApplication extends MFPJAXRSApplication{

	static Logger logger = Logger.getLogger(MobileFirstBeaconsAdapterApplication.class.getName());
	
	@Context
	ConfigurationAPI configurationAPI;

	public Database beaconsDB = null;
	public Database triggersDB = null;
	public Database associationsDB = null;

	protected void init() throws Exception {
		logger.info("Adapter initialized!");

		String cloudantAccount = configurationAPI.getPropertyValue("account");
		String cloudantKey = configurationAPI.getPropertyValue("key");
		String cloudantPassword = configurationAPI.getPropertyValue("password");

		String beaconsDBName = configurationAPI.getPropertyValue("beaconsDBName");
		String triggersDBName = configurationAPI.getPropertyValue("triggersDBName");
		String associationsDBName = configurationAPI.getPropertyValue("associationsDBName");

		if (!cloudantAccount.isEmpty() && !cloudantKey.isEmpty() && !cloudantPassword.isEmpty()){
			try {
				CloudantClient cloudantClient = ClientBuilder.account(cloudantAccount).username(cloudantKey)
						.password(cloudantPassword).disableSSLAuthentication().build();
				if (!beaconsDBName.isEmpty()) {
					beaconsDB = cloudantClient.database(beaconsDBName, false);
				}
				if (!triggersDBName.isEmpty()) {
					triggersDB = cloudantClient.database(triggersDBName, false);
				}
				if (!associationsDBName.isEmpty()) {
					associationsDB = cloudantClient.database(associationsDBName, false);
				}
			} catch (CouchDbException e){
				throw new Exception("Unable to connect to Cloudant DB, check the configuration.");
			}
		}
	}

	protected void destroy() throws Exception {
		logger.info("Adapter destroyed!");
	}
	

	protected String getPackageToScan() {
		//The package of this class will be scanned (recursively) to find JAX-RS resources. 
		//It is also possible to override "getPackagesToScan" method in order to return more than one package for scanning
		return getClass().getPackage().getName();
	}
}
