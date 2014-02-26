// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
<<<<<<< HEAD
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.1")
=======
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.1")

libraryDependencies += "postgresql" % "postgresql" % "8.4-702.jdbc4"
>>>>>>> 65458ffc5dae5c3b81e1e63c2fca30bff48df767
