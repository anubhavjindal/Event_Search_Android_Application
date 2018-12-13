# Event Search - Android Application

## Technologies used
NodeJS, Android(Java), Volley library, Glide library, XML, Google play services

## Overview
This Android application serves as the Mobile counterpart of the project found at http://csci-hw8-222600.appspot.com

Event search allows users to search for various different sports, music, film etc events based on current location or any other location entered by the user. Users can know more about the artists performing, the venue of the event, the upcoming events at the same venue, the seating map, etc. They can also book tickets using links which lead to Ticketmaster website.

## Features
1. The application makes HTTP requests to the Node server using Volley Library. 
2. Users can search events from current location which is caught using GPS or a custom location which is geocoded using Google api
3. In case of music artists, artist details are obtained from Songkick api and Users can directly access them on Spotify.
4. Artist Images are obtained using custom Google Search and displayed using Glide library.
5. Users can see the location on Google maps with a marker.
6. The upcoming events can be sorted based on several things in ascending or descending order.
7. Users can directly tweet about the event by just a click of a button.
8. Clear button resets the application.
