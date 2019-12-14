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
### <path_to_project>/src/main/webapp
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