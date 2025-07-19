# Building the server
1) Clone this repository.
2) At the root of the repo, run this command in your terminal `javac -cp DungeonsAndDragons/lib/protobuf-java-3.10.0.jar -d out $(find DungeonsAndDragons/src -name "*.java")`. This will compile the java project.

# Run the server
1) After compiling just run the server by this command `java -cp out:DungeonsAndDragons/lib/protobuf-java-3.10.0.jar ServerHandler.ServerListener `.
2) You should see the your **Server is running**
3) To stop the server, just hit ctr+c.
