A simple notebook API.

Current version contains API for creating and getting notes for a user. Future versions will contain API to delete and update notes for a user.

To start the server run
./gradlew bootRun

Example requests:

Create a note:
curl -X POST localhost:8080/notes -H 'Content-type:application/json' -d '{"ownerId": "123", "title": "hello", "content":"hello world"}'

Get a note:
curl localhost:8080/notes/{id}

Get notes for a user:
curl localhost:8080/notes?userid={}
