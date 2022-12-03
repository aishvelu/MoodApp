## Hack Challenge 2022 Submission - Aishwarya Velu, Joanna Andrews, Minhaj Fahad, Lindsey Cheng


# App Name


Mood Movies


# Tagline


Mood Movies generates a list of movies corresponding to the user's desired mood from which he or she can readily select from.


# Video Link

https://drive.google.com/file/d/1BQ6HzwU6VFjN2x4fpJHCHGDfknh2Hrxz/view?usp=sharing


# Screenshots


https://docs.google.com/document/d/1ViDlWDwYcTvJC2GrNaAsRtUXKry_aDTAB8I_Hi8b9KE/edit?usp=sharing


# Description


When the app is launched, the user will see the welcome screen with a text view displaying the app's name as well as a text view prompting the user to choose their preferred mood from 4 big buttons corresponding to 4 different moods: whimsical, adventurous, romantic, and sad. Once a button is selected, the user is redirected to a screen with a recycler view list of movies that correspond to the chosen mood. Then, the user can select one of those movies by clicking on that specific movie cell and will be redirected to a screen with the movie name, genre, and cover image blown up for better readability. Additionally, the user can press the back button on their Android device to select a different movie from the same mood or a different movie from a different mood.


# Backend Requirements (See READMEFRONTEND.md for Frontend requirements)


1. Has over 9 working routes, (get, post, & delete).
2. Has 3 tables with relationships between them: Recommend(movies), Mood, & User. 
3. API specification explaining each implemented route (postman collection & app.py files)
4. Implementation of images
5. Backend's API pulls data from themoviedb.org's API to receive movie title, genre, and images
with a private API key. The server runs on docker via google cloud vm.
Example route: http://34.86.132.50/api/moods/2/, returns movies of Romantic mood.
