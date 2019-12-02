# Pubs list
Small learning project built on Spring boot + Vue. Made for fun.

## Back end Project setup
### Creates and runs a container with postgres (one time need)
```
docker-compose up -d
```

### Stops the container from running (if not needed now)
```
docker container stop postgres
```
Then simply run built Spring boot jar file or use your favorite IDE to run it.

### Deletes container docker rm postgres (to recreate db and etc)
```
docker rm postgres
```

## Front end Project setup
### /src/main/webapp
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```


## Rest Contract:

### Login
```
path: "/login"
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
path: "/login"
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