# Translatify
My app detects and recognizes text by camera and has the ability to translate the text to another language

## Screenshot:
<img src="https://github.com/qchay/Translatify/blob/master/images/Splash.png" align="left" height="300" width="200" >
<img src="https://github.com/qchay/Translatify/blob/master/images/Translate.png" align="left" height="300" width="200" >
<img src="https://github.com/qchay/Translatify/blob/master/images/Languages.png" align="left" height="300" width="200" >
<img src="https://github.com/qchay/Translatify/blob/master/images/OCR.png"  height="300" width="200" >

## API:
- Google translate - Used to translate the texts
- Google Vision - Used to recognize the text from the Camera

## Libraries/Services:
- Google Vision
  - com.google.android.gms:play-services-vision:17.0.2
    - Make use of CameraSource and TextRecognizer
    - Allows my app to use a camera and to recognize text from the camera’s view
- Google Translate
  - Com.google.cloud:google-cloud-translate:1.52.0
    - Make requests to the API to translate the text given with a target language given.
    - Make use of Translate, TranslateOption, Translation
- Android Design Support
  - Com.android.support:design:28.0.0
    - Design of buttons such as Floating action button used for my app to open the CameraActivity.
## UI:
The UI of my app is very simple and clean. I didn’t want to get crazy with the designs but still wanted to make it approachable to the user without clogging the screen. I have created a splash page that animates into the main activity. This wasn’t too difficult of a feature to add and enhances the user experience. I also tried to clean up the edges of my textbox, clipboard button, and translate button. Everything is simple yet easy to use. In the future, I would like to add more customizable styles but for now I am happy with my UI.
## Backend:
Most of my apps difficulty lies in the backend logic. My App uses google visions CameraSource and Text recognizer api to read off and display text by image. My app also has the functionality to translate text to different languages by use of google translate API. My app sends a request through google translate’s API by providing text and language target. Discuss the most important or interesting thing you learned doing your project.
## Challenges/Difficulties:
This project had so many obstacles that I had to overcome. My initial idea of an app was totally different than what I have now. My first idea was an app that would use ESPN’s API for fantasy football and use that data to make calls and schedule actions that would reflect on the ESPN fantasy football league of the user. The problem with this idea was that ESPN recently had made their API private. Having initially working on that idea for a few days and having to abandon ship was frustrating but I ran into another problem. With my current app, I initially was going to utilize firestore/firebase to save the translated text with the picture onto my database in firestore/firebase. I ran into a problem with dependencies involving google translate and firestore. I spent nearly 2 days just debugging and asking the TA/Professor. I had to readjust my app to not have firestore/firebase so my current app has less features than I would hope. My current problem that I have still not resolved is that whenever I am using the camera, it is oriented sideways which is an annoying thing to deal with when trying to read off text from a source. I googled for hours and still have not come up with a solution. It seemed like an easy problem to resolve but it is not as easy as it seems. 

## How to build/run my project:
The only thing that is needed to make my project work if downloaded is to setup a google translate API key and input the api key into the String labeled API_KEY. It is fairly simple to do by following, https://cloud.google.com/translate/docs/quickstart-client-libraries. The api key for Google translate is not free so take note of that.
