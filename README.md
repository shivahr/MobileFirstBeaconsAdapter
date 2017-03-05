- Log-in to Bluemix

# Create Cloudant service on Bluemix
- Click on *Catalog*
- Search for and select *Cloudant NoSQL DB*
- Click on *Create*
- Click on *Launch* to open Cloudant Dashboard

## Create database for storing beacon data
- Click on *Databases*
- Click on *Create Database*
- Name it **beacons**


- Click on + icon and select *New Doc*
- Add following data corresponding to beacon-1 after *_id*
```
  , "uuid": "F75D6DF0-9B95-9EF1-A1BA-AE2765DE0987",
  "major": 57388,
  "minor": 50057,
  "customData": {
   "branchName": "Indiranagar, Bangalore"
  }
```
- Change UUID, major and minor numbers to correspond to your beacon
- Click on *Create document*


- Click on + icon
- Add following data corresponding to beacon-2 after *_id*
```
  , "uuid": "F75D6DF0-9B95-9EF1-A1BA-AE2765DE0987",
  "major": 56179,
  "minor": 406,
  "customData": {
   "branchName": "Koramangala, Bangalore"
   }
```
- Change UUID, major and minor numbers to correspond to your beacon
- Click on *Create document*


## Create database for storing trigger information
- Click on *Databases*
- Click on *Create Database*
- Name it **triggers**


- Click on + icon and select *New Doc*
- Add following data corresponding to trigger-1 after *_id*
```
  , "triggerName": "entryIntoBranch",
  "triggerType": "Enter",
  "proximityState": "Near",
  "actionPayload": {
   "alert": "Welcome to $branchName branch of IMF Bank"
  }
```
- Change triggerName, triggerType, proximityState and alert to suit your needs
- Click on *Create document*


- Click on + icon
- Add following data corresponding to trigger-2 after *_id*
```
  , "triggerName": "exitFromBranch",
  "triggerType": "Exit",
  "proximityState": "Far",
  "actionPayload": {
   "alert": "Thank you for visiting our $branchName branch. Have a nice day!"
  }
```
- Change triggerName, triggerType, proximityState and alert to suit your needs
- Click on *Create document*

## Create database for storing mapping between beacons and triggers
- Click on *Databases*
- Click on *Create Database*
- Name it **beacon-trigger-associations**


- Click on + icon and select *New Doc*
- Add following data corresponding to mapping between beacon-1 and trigger-1 after *_id*
```
  , "uuid": "F75D6DF0-9B95-9EF1-A1BA-AE2765DE0987",
  "major": 57388,
  "minor": 50057,
  "triggerName": "entryIntoBranch"
```
- Change uuid, major & minor numbers and triggerName to suit your needs
- Click on *Create document*


- Click on + icon
- Add following data corresponding to mapping between beacon-1 and trigger-2 after *_id*
```
  , "uuid": "F75D6DF0-9B95-9EF1-A1BA-AE2765DE0987",
  "major": 57388,
  "minor": 50057,
  "triggerName": "exitFromBranch"
```
- Change uuid, major & minor numbers and triggerName to suit your needs
- Click on *Create document*


- Click on + icon and select *New Doc*
- Add following data corresponding to mapping between beacon-2 and trigger-1 after *_id*
```
  , "uuid": "F75D6DF0-9B95-9EF1-A1BA-AE2765DE0987",
  "major": 56179,
  "minor": 406,
  "triggerName": "entryIntoBranch"
```
- Change uuid, major & minor numbers and triggerName to suit your needs
- Click on *Create document*


- Click on + icon
- Add following data corresponding to mapping between beacon-2 and trigger-2 after *_id*
```
  , "uuid": "F75D6DF0-9B95-9EF1-A1BA-AE2765DE0987",
  "major": 56179,
  "minor": 406,
  "triggerName": "exitFromBranch"
```
- Change uuid, major & minor numbers and triggerName to suit your needs
- Click on *Create document*


# Create Mobile Foundation service on Bluemix
**Mobile Foundation** is the mobile backend for your mobile app
- Click on *Catalog*
- Search for and select *Mobile Foundation*
- Click on *Create*
- Click on *Settings*
- Click on *Security* tab
- Specify the *Console Login Password* (user-id would be admin)
- Click on *Next*
- Click on *Start Advanced Server* to create Mobile backend server

Click on Launch Console
 - Login with username as *admin* and the password you have specified before
 - Note down the server URL (just the domain - like mobilefoundation-4w-server.eu-gb.mybluemix.net)

## Install mfpdev cli
While the server is being started (normally it takes around 10mins to start the server), install mfpdev cli (command line for working with Mobile Foundation (a.k.a. Mobile First Platform) on your local machine as follows:
```
npm install -g mfpdev-cli
```

Verify installation 
```
mfpdev --version
```
It should be `8.0.0-*`

## Add server information for mfpdev-cli
```
$ mfpdev server add
? Enter the name of the new server profile: ATMMobileBackend
? Enter the fully qualified URL of this server: https://mobilefoundation-4w-server.eu-gb.mybluemix.net:443
? Enter the MobileFirst Server administrator login ID: admin
? Enter the MobileFirst Server administrator password: ********
? Save the administrator password for this server?: Yes
? Enter the context root of the MobileFirst administration services: mfpadmin
? Enter the MobileFirst Server connection timeout in seconds: 30
Verifying server configuration...
The following runtimes are currently installed on this server: mfp
Server profile 'ATMMobileBackend' added successfully.
```
Note:
  - Specify an appropriate name for new server profile.
  - Specify port (like :443) at the end of server domain URL
  - Leave default for administrator login ID as admin
  - Specify the password you created before
  - Leave default for context root
  - Leave default for connection timeout

# Deploy MobileFoundation adapter
Adpater is code running on server that fetches data from Cloudant database and makes it available for mobile applications

Download adapter source code and change directory:
```
$ git clone https://github.com/shivahr/MobileFirstBeaconsAdapter.git
$ cd MobileFirstBeaconsAdapter
```

### Edit adapter configuration and specify connection details to your Cloudant database
- Open pom.xml and change values for mfpfUrl, mfpUser and mfpPassword
- Open src/main/adapter-resources/adapter.xml and specify correct values for account, key & password
  - Open Bluemix console and go the list of all services
  - Click on *Cloudant NoSQL DB-* service
  - Click on *Service Credentials* tab and click on *View Credentials*
  - Copy value for *username* from above to property name *account* in adapter.xml under defaultValue
  - Copy value for *username* from above to property name *key* in adapter.xml under defaultValue
  - Copy value for *password* from above to property name *password* in adapter.xml under defaultValue

## Build adapter
Adapter build uses maven & Java for compiling. If not already installed, install Java and Maven before proceeding further.
```
mfpdev adapter build
```

## Deploy adapter to mobile foundation server
```
$ mfpdev adapter deploy
```

## Test Adapter REST APIs
- Go to MobileFirst dashboard
- Under *Adapters*, you should see the *MobileFirstBeaconsAdpater* that you just deployed
- Create a temporary username and password to test Adapter REST APIs
  - Click on *Runtime Settings*
  - Click on *Confidential Clients*
  - Click on *New*
  - Specify *Display Name* of choice say *Test*
  - Specify an id say *testuser*
  - Specify secret (password)
  - Specify *Allowed Scope* as \*\*
  - Click on *Add* and then click *Save*
 - Under *Adapters* click on *MobileFirstBeaconsAdpater*
 - Go to *Resources* tab
 - Click on *View Swagger Docs*
 - In the Swagger documentation page, click on *List Operations*
 - Click on *Off* button, select *DEFAULT_SCOPE* and click *Authorize*
 - Specify the temporary username and password that you just created under *Confidential Clients*
 - Test GET APIs for beacons, triggers, associations and getBeaconsTriggersAndAssociations by clicking on *Try it out!*
