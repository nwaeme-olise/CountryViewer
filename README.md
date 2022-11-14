# CountryViewer

An app that displays a list of all the world's countries and details about them. The list is searchable, filterable and the app has a dark mode feature.

# App Description

 The application was written using Kotlin and the Android Framework. The UI for the app was designed using XML with the Master/Detail design concept. It was built using the Single-Activity Architecture with multiple Fragments.
 
 # Libraries and Tools used
 
 * Viewbinding - For easy access to individual UI views/components in code.
 * ViewModel - For holding UI related data, separating it from UI code.
 * LiveData - Used in conjunction with ViewModel for exposing UI data to the app's UI component (Activities/Fragments).
 * Navigation Component - For setting up the navigation to and from different screens/destinations in the app.
 * Retrofit - For making network calls to and fetching data from REST APIs.
 * Glide - For loading images from remote sources.
 * Coroutines - For performing long-running tasks (i.e network calls) on a background thread.

Nice-to-haves

* Offline caching
* Different languages