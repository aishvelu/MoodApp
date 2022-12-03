#MoodApp
An app that generates movie suggestions based on a user's current mood.




Backend Requirements
1. Has over 9 working routes, (get, post, & delete).
2. Has 3 tables with relationships between them: Recommend(movies), Mood, & User. 
3. API specification explaining each implemented route (postman collection & app.py files)
4. Implementation of images
5. Backend's API pulls data from themoviedb.org's API to receive movie title, genre, and images
with a private API key. The server runs on docker via google cloud vm.
Example route: http://34.86.132.50/api/moods/2/, returns movies of Romantic mood.
