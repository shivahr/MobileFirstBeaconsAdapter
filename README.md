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

