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

# Step 4 : add retrofit to your project

Add retrofit to project, instructions are at:

http://square.github.io/retrofit/

You just need to add the following dependency in your app's build.gradle file:

dependencies {
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    //... you probably have more
}

Don't forget the gson converter dependency in you build.gradle file (second line)
And the internet permission in the manifest

Now let's create our remote service implementation to consume the firebase endpoints.

For ease of configuration I've add a buildconfig parameter in the build.gradle file so we can change it depending on our flavor (think pilot builds). See the RemoteServiceImpl class for the endpoint configurations. Those annotations and some method signatures is really all what needs to be done. The OverviewPresenter shows how you can consume these points and get the real data. 

## Resources

http://d.android.com
https://console.firebase.google.com
https://www.firebase.com/docs/rest/api/
https://firebase.google.com/docs/database/security/quickstart
http://square.github.io/retrofit/