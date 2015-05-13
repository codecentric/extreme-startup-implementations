# Extreme Startup Coding Dojo

I took the opportunity of the extreme startup to get to know a new framework: Akka-HTTP.
As one might expect, my biggest problem was the missing knowledge of the framework. Another disadvantage 
was the relatively long cycle time (which was the reason I only re-deployed every 10-20 minutes compared 
to Fabian's or Ben's 2-second-cycle-time).

To be fair, I wasn't able to finish the [TermParser]{src/main/scala/TermParser} during the extreme startup (and even starting it 
probably cost me the last two rounds and several hundred points).

## REST-Service with Akka HTTP

The application is based on the [akka-http-microservice]{https://github.com/theiterators/akka-http-microservice}. 

The application is built with sbt, so you have to start your sbt console. You start the application either directly via
```
sbt> run
```

or you can create an executable jar with
```
sbt> assembly
```

and then execute the jar via
```
sh# java -jar target/scala-2.11/extreme-startup-dojo-assembly-1.0.jar
``

