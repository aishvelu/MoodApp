import json
from db import db
from flask import Flask
from db import Recommend
from db import Mood
from db import User
from flask import request
import requests
import os

APIKey = os.environ.get("APIKEY")

app = Flask(__name__)
db_filename = "mood.db"

app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///%s" % db_filename
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["SQLALCHEMY_ECHO"] = True

db.init_app(app)
with app.app_context():
    db.create_all()

def success_response(data, code=200):
    """
    Helper function to get the sucess a
    """
    return json.dumps(data), code


def failure_response(message, code=404):
    return json.dumps({"error": message}), code
#------recommends Routes------------------------------------------------------------

@app.route("/")
@app.route("/api/recommends/")
def get_recommends():
    """
    Endpoint for getting all recommends.
    """
    recommends = [recommend.serialize() for recommend in Recommend.query.all()]
    return success_response({"recommends":recommends})


@app.route("/api/recommends/<int:mood_id>/", methods=["POST"])
def create_recommends(mood_id):
    """
    Endpoint for creating a new recommend.
    """
    body = json.loads(request.data)
    title = body.get("name")
    httpRequest = "https://api.themoviedb.org/3/search/movie?include_adult=false&page=1&query="+title+"&language=en-US&api_key="+str(APIKey)
    response = requests.get(httpRequest)
    data = response.json()
    name = data["results"][0]["original_title"]
    genre_id = data["results"][0]["genre_ids"][0]
    image = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" +str(data["results"][0]["poster_path"])
    data = requests.get("https://api.themoviedb.org/3/genre/movie/list?api_key="+str(APIKey)+"&language=en-US").json()
    genre = ""

    for i in range(len(data["genres"])):
        if data["genres"][i]["id"]==genre_id:
            genre = data["genres"][i]["name"]

   
    if name is None:
        return failure_response("recommend not found",400)
    new_recommend = Recommend(name = name, genre = genre, image = image, mood = mood_id)
    db.session.add(new_recommend)
    db.session.commit()
    return success_response(new_recommend.serialize(),201)


@app.route("/api/recommends/<int:recommend_id>/")
def get_recommend(recommend_id):
    """
    Endpoint for getting a recommend by id.
    """
    recommend = Recommend.query.filter_by(id= recommend_id).first()
    if recommend is None:
        return failure_response("recommend not found")
    return success_response(recommend.serialize())


@app.route("/api/recommends/<int:recommend_id>/", methods=["DELETE"])
def delete_recommend(recommend_id):
    """
    Endpoint for deleting a recommend by id.
    """
    recommend = Recommend.query.filter_by(id = recommend_id).first()
    if recommend is None:
        return failure_response("recommend not found")
    db.session.delete(recommend)
    db.session.commit()
    return success_response(recommend.serialize())
# -- Create a mood ---------------------------------------------------


@app.route("/api/moods/", methods=["POST"])
def create_mood():
    """
    Endpoint for creating a mood for a recommend by id.
    """

    body = json.loads(request.data)
    mood =  body.get("mood")
    if mood is None:
        return failure_response("Could not create mood", 400)

    new_mood = Mood(
        mood = mood
    )
    db.session.add(new_mood)
    db.session.commit()
    return success_response(new_mood.serialize(), 201)

@app.route("/api/moods/")
def get_moods():
    """
    Endpoint for getting all mood by.
    """
    moods = [mood.serialize() for mood in Mood.query.all()]
    return success_response({"moods":moods})

@app.route("/api/moods/<int:mood_id>/")
def get_mood_by_id(mood_id):
    """
    Endpoint for getting all mood by.
    """
    mood = Mood.query.filter_by(id= mood_id).first()
    if mood is None:
        return failure_response("mood not found")
    return success_response(mood.serialize())

# -- User Routes --------------------------------------------------


@app.route("/api/users/<int:mood_id>/", methods=["POST"])
def create_user(mood_id):
    """
    Endpoint for creating a user.
    """
    body = json.loads(request.data)
    name = body.get('name')

    if name == None:
        return failure_response("User was not created", 400)
    new_user = User(
        name = name,
        currentMood = "Happy",
        mood = mood_id

        )
    db.session.add(new_user)
    db.session.commit()
    return success_response(new_user.serialize(), 201)


@app.route("/api/users/<int:user_id>/")
def get_user(user_id):
    """
    Endpoint for getting a user by id.
    """
    user = User.query.filter_by(id = user_id).first()
    if user is None:
        return failure_response("User not found")
    return success_response(user.serialize())


'''
@app.route("/api/recommends/<int:recommend_id>/add/", methods=["POST"])
def update_user():
    """
    Endpoint for updating a user by id.
    """
    user = User.query.filter_by(id = user_id).first()

    if user is None:
        return failure_response("User not found")
    if type == "student":
        recommend.students.append(user)
        db.session.commit()
        return success_response(recommend.serialize())
    else:
        return failure_response("Did not choose between student or instructor")

'''
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000, debug=True)

