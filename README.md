## About

Android example project showcasing retrofit consuming firebase data over REST calls. 

## Step 1 : create Android Studio sample project

I've picked Basic Activity from the available options. Any example project will do. It just provides you with the base structure for an Android App. 

## Step 2 : set up MVP 

The Views are in place already with the Activity (and Fragments) created withing the sample. For a true MVP implementation we need to add Presenters and Models. For maximum compliance with the MVP pattern approach we'll also create View interfaces that our Activities and Fragments will implement.

## Step 3 : create firebase account/project

Next we need to create a firebase project that we will consume in the following steps. Go to https://console.firebase.google.com, register if needed and create a new project. 

For this example I've created a project named "Tasks" that you can reach at https://tasks-e93aa.firebaseio.com/. For now the data will be public. As soon as I've got the authenticated options documented we'll add security. To go public on your own database do:

// These rules give anyone, even people who are not users of your app,
// read and write access to your database
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
from: https://firebase.google.com/docs/database/security/quickstart



## Resources

http://d.android.com
https://console.firebase.google.com
https://www.firebase.com/docs/rest/api/
https://firebase.google.com/docs/database/security/quickstart