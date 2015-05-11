Extreme Startup Implementation by @CodingFabian
-----------------------------------------------


I tried to defend the bad reputation of Java and ended with noticing that
coding under such extreme pressure is hard. Java has a hard time competing
with untyped languages due to the additional casts. It also has no eval(), which
makes solving math harder (and I did not manage to get Nashorn running in OSGi).

Talking of OSGi, to reduce cycle times, I choose an OSGi bundle, which can be
easily installed and redeployed in an Apache Karaf 4, by doing:
`bundle:watch mvn:codingfabian/extreme-startup/1.0.0-SNAPSHOT`

I had an maven builder which was automatically building after each change, so
only versions which passed the little tests were deployed.

From "save" to "deployed" took only 2 seconds with no downtime.
