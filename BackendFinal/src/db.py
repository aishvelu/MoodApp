from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


class Recommend(db.Model):
    """
    Many to one relationship with Mood.
    """
    __tablename__ = "recommend_list"
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    name = db.Column(db.String, nullable = False)
    genre = db.Column(db.String, nullable = False)
    image = db.Column(db.String, nullable = False)
    mood = db.Column(db.Integer, db.ForeignKey("moods.id"), nullable = False)
    

    def __init__(self, **kwargs):
        """
        Initializes recommend object.
        """
        self.name = kwargs.get("name", "")
        self.genre = kwargs.get("genre", "")
        self.image = kwargs.get("image", "")
        self.mood = kwargs.get("mood")


    def serialize(self):
        
        """
        Serializes a recommend object.
        """
        return{
            "id": self.id,
            "name": self.name,
            "genre": self.genre,
            "image": self.image,
            "mood": Mood.query.filter_by(id = self.mood).first().incr_serialize()

        }


    def incr_serialize(self):

        """
        Incr Serializes a recommend object so infinte loop does not occur.
        """

        return{
            "id": self.id,
            "name": self.name,
            "genre": self.genre,
            "image": self.image
        }


class Mood(db.Model):
    """
    Mood model has a one to many relationship with Recommend.
    Mood model has a one to many relationship with User
    """
    __tablename__ = "moods"
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    mood = db.Column(db.String, nullable = False)
    recommend = db.relationship("Recommend", backref = "moods", cascade = "delete")
    users = db.relationship("User", backref = "moods", cascade ="delete")
    

    def __init__(self, **kwargs):
        """
        Initializes mood object.
        """
        self.mood = kwargs.get("mood", "")

    def serialize(self):
        
        """
        Serializes a mood object.
        """

        return{
            "id": self.id,
            "mood": self.mood,
            "recommend": [r.incr_serialize() for r in self.recommend],
            "users": [u.incr_serialize() for u in self.users]
        }


    def incr_serialize(self):
        
        """
        incr Serializes a mood object so infinte loop does not occur.
        """

        return{
            "id": self.id,
            "mood": self.mood,
        }


class User(db.Model):
    """
    User Model has many to one relationship with mood.
    """
    __tablename__ = "users"
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    name = db.Column(db.String, nullable = False)
    currentMood = db.Column(db.String, nullable = False)
    mood = db.Column(db.Integer, db.ForeignKey("moods.id"), nullable = False)
    

    def __init__(self, **kwargs):
        """
        Inititalizes a User object.
        """
        self.name = kwargs.get("name", "")
        self.currentMood = kwargs.get("currentMood", "")
        self.mood = kwargs.get("mood")


    def serialize(self):
        """
        incr serialize User object.
        """

        return{
            "id": self.id,
            "name": self.name,
            "currentMood" :self.currentMood,
            "mood": Mood.query.filter_by(id = self.mood).first().incr_serialize()
        }


    def incr_serialize(self):
        """
        incr Serializes a User object so infinte loop does not occur.
        """ 
        return{
            "id": self.id,
            "name": self.name,
            "currentMood" :self.currentMood
        }
    
