
## Rest Contract:

### Login
```
path: "/api/login"
method: "POST"
expected format: "application/json"
body:
required fields: ["username", "password"]
right request example body:

{
"username": "admin",
"password": "admin"
}

possible responses:
1. SUCCESS: 

{
  "timestamp": "2019-12-02 18:00:10.315",
  "message": "Authentication Successful",
  "token": "eyJ0eXAi...tWR7Q"
}

2. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}

3. MISSING_USERNAME_OR_PASSWORD: status - 400

{
  "timestamp": "2019-12-02 17:57:25.242",
  "status": "Error",
  "message": "Username or Password is not provided"
}

4. AUTHENTICATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authentication failed for provided credentials"
}
```

### sign up

```
path: "/api/login"
method: "POST"
expected format: "application/json"
body:
required fields: ["username", "password"]
right request example body:

{
	"username" : "mothefucker",
	"password" : "password",
	"name" : "somename",
	"role": "ROLE_ADMIN"
}

possible responses:
1. SUCCESS: 

{
    "id": 1,
    "username": "admin",
    "name": "somename",
    "surname": null,
    "role": "ROLE_ADMIN"
}

2. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}

3. MISSING_USERNAME_OR_PASSWORD: status - 400

{
  "timestamp": "2019-12-02 17:57:25.242",
  "status": "Error",
  "message": "Username or Password is not provided"
}

4. USERNAME_IS_ALREADY_USED: status - 400
{
    "timestamp": "2019-12-02 23:46:04.291",
    "status": "error",
    "message": "Username is already in use"
}
```

### Get all users 
```
path: "/api/users"
method: "GET"
expected format: "application/json"
body:
required fields: []

possible responses:
1. SUCCESS: 

[
    {
        "id": 16,
        "username": "timur",
        "name": null,
        "surname": null,
        "role": "USER"
    },
    {
        "id": 13,
        "username": "user",
        "name": "user",
        "surname": null,
        "role": "ADMIN"
    }
]

2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. ACCESS DENIED: status - 403

{
    "timestamp": "2019-12-13 23:28:43.66",
    "status": "Error",
    "message": "Access Forbidden"
}
```

### Get current user
```
path: "/api/user"
method: "GET"
expected format: "application/json"
body:
required fields: []

possible responses:
1. SUCCESS: 

[
    {
        "id": 16,
        "username": "timur",
        "name": null,
        "surname": null,
        "role": "USER"
    }
]

2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

```

### Update a user
```
path: "/api/comments/{user_id}"
method: "PATCH"
body:
right request example body:

{
    "name": "Best name"
}

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. MISSING_REQUIRED_FIELD: status - 400

{
    "timestamp": "2019-12-13 23:48:04.206",
    "status": "error",
    "message": "One or several required fields are not provided"
}

4. ACCESS DENIED: status - 403
 
 {
     "timestamp": "2019-12-13 23:28:43.66",
     "status": "Error",
     "message": "Access Forbidden"
 }

5. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}

6. NO_USER_FOUND: status - 406

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "User is not found"
}
```

### Get all places
```
path: "/api/places"
method: "GET"
body:
required fields: []

possible responses:
1. SUCCESS: 

[
    {
        "id": 27,
        "name": "SOme place",
        "address": "adsadas",
        "latitude": null,
        "longitude": null,
        "ownerId": 16,
        "description": null,
        "approved": false,
        "disabled": false,
        "createdOn": "2019-12-11T22:55:36.199016",
        "createdBy": 16,
        "updatedOn": null,
        "updatedBy": null
    }
    {...}
]

2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

```

### Suggest a place
```
path: "/api/places"
method: "POST"
body:
required fields: [name, address]
right request example body:

{
	"name": "Tref",
	"address": "Lufsdorfskaya",
	"description": "Coolest place in the city"
}

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. MISSING_REQUIRED_FIELD: status - 400

{
    "timestamp": "2019-12-13 23:48:04.206",
    "status": "error",
    "message": "One or several required fields are not provided"
}

4. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}
```

### Update a place
```
path: "/api/places/{place_id}"
method: "PATCH"
body:
required fields: [name, address]
right request example body:

{
	"name": "Tref",
	"address": "Lufsdorfskaya",
	"description": "Coolest place in the city"
}

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. MISSING_REQUIRED_FIELD: status - 400

{
    "timestamp": "2019-12-13 23:48:04.206",
    "status": "error",
    "message": "One or several required fields are not provided"
}

4. ACCESS DENIED: status - 403
 
 {
     "timestamp": "2019-12-13 23:28:43.66",
     "status": "Error",
     "message": "Access Forbidden"
 }

5. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}

6. NO_PLACE_FOUND: status - 406

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "No place with following ID found"
}
```

### Delete a place
```
path: "/api/places/{place_id}"
method: "DELETE"
body:
right request example body:

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. ACCESS DENIED: status - 403
 
 {
     "timestamp": "2019-12-13 23:28:43.66",
     "status": "Error",
     "message": "Access Forbidden"
 }

4. NO_PLACE_FOUND: status - 406

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "No place with following ID found"
}
```

### Get all comments for place
```
path: "/api/comments/{place_id}"
method: "GET"
body:
required fields: []

possible responses:
1. SUCCESS: 

[
    {
        "id": 27,
        "name": "SOme place",
        "address": "adsadas",
        "latitude": null,
        "longitude": null,
        "ownerId": 16,
        "description": null,
        "approved": false,
        "disabled": false,
        "createdOn": "2019-12-11T22:55:36.199016",
        "createdBy": 16,
        "updatedOn": null,
        "updatedBy": null
    }
    {...}
]

2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

```

### Add a comment
```
path: "/api/comments"
method: "POST"
body:
required fields: [text, placeId]
right request example body:

{
	"text": "Worst place in the city",
	"placeId": 11
}

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. MISSING_REQUIRED_FIELD: status - 400

{
    "timestamp": "2019-12-13 23:48:04.206",
    "status": "error",
    "message": "One or several required fields are not provided"
}

4. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}
```

### Update a comment
```
path: "/api/comments/{place_id}"
method: "PATCH"
body:
required fields: [text, placeId]
right request example body:

{
    "text": "Best place in the city",
	"placeId": 11
}

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. MISSING_REQUIRED_FIELD: status - 400

{
    "timestamp": "2019-12-13 23:48:04.206",
    "status": "error",
    "message": "One or several required fields are not provided"
}

4. ACCESS DENIED: status - 403
 
 {
     "timestamp": "2019-12-13 23:28:43.66",
     "status": "Error",
     "message": "Access Forbidden"
 }

5. MALFORMED JSON: status - 400

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "Json is malformed"
}

6. NO_COMMENT_FOUND: status - 406

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "No comment with following ID found"
}
```

### Delete a comment
```
path: "/api/comments/{comment_id}"
method: "DELETE"
body:
right request example body:

possible responses:
1. SUCCESS: status - 200


2. AUTHORIZATION_FAILED: status - 401

{
  "timestamp": "2019-12-02 17:58:40.777",
  "status": "Error",
  "message": "Authorization failed"
}

3. ACCESS DENIED: status - 403
 
 {
     "timestamp": "2019-12-13 23:28:43.66",
     "status": "Error",
     "message": "Access Forbidden"
 }

4. NO_COMMENT_FOUND: status - 406

{
  "timestamp": "2019-12-02 17:56:38.146",
  "status": "Error",
  "message": "No comment with following ID found"
}
```