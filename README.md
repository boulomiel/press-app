# Press-app

An Android app developped in Kotlin, using Retrofit, Coroutines,ViewModel, LiveData, Materials and NewsApi


![Image of screen one](https://static.wixstatic.com/media/f98153_1f42917a131a495a93e62141fc4fce2c~mv2.png)

### Pick a country
Here we look at the first screen after the splash one, where we pick the country we want the news from. the id of the country as well as its language is being transfered 
on click to the viewmodel. There are two recyclers view and one searchView. The left recycler view holds pictures of flags representing countries used for their news and languages.
The main one holds CircleViews of flags pictures, from which we can scroll to the right to pick a country. The searchView is updating the viewmodel after each character inserted.
Leading it to scroll the main recycler to display our desired country. By selecting a flag from the side recycler we order to display as well from the main recycler the wanted country.


![Image of screen two](https://static.wixstatic.com/media/f98153_617f0b6825d44e4eba445db16e9d196a~mv2.png)

### Headline Fragment
Here is the headline fragment. The previous screen sent data about the country we want to display news from. We launched a livesScope of the Coroutines, LiveData implementation
from where we get call the retrofit builder and get the callbacks, and update our viewModel.

![Image of screen two](https://static.wixstatic.com/media/f98153_78214655bc1945c1a056633260bbe0e3~mv2.png)
### Category in headlines fragment 
Same as previously in a different language



![Image of screen three](https://static.wixstatic.com/media/f98153_2080b33d17c742f685a487d9ee8cd8ac~mv2.png)
### International fragment
Every user can reach international news, and ask for an accurate research using the searchView. Every time a researched is launched, the recycler is cleaned 
and reinitialized in a background thread.


### Read more Button
The button redirects the user to the source media and open the device's navigator. 

### Design
Views have their own homemade xml file, and and material library has been used.


### Implementations
    def lifecycle_version = "2.2.0"
    ####material
    api 'com.google.android.material:material:1.3.0-alpha01'
     ####coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7'

     ####retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

     ####glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'

     ####circle image view
    implementation 'de.hdodenhof:circleimageview:3.1.0'

     ####recyclerview
    implementation "androidx.recyclerview:recyclerview:1.1.0"

     ####lifecycle
     ##### ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
     ##### LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
