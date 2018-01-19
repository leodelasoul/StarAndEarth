#!/bin/bash

repopath= pwd

java -Djava.library.path=Libs/ -classpath $repopath/StarAndEarth/out/production/StarAndEarth:$repopath/StarAndEarth/Libs/lwjgl-2.9.3/jar/lwjgl.jar:/$repopath/StarAndEarth/Libs/lwjgl-2.9.3/jar/lwjgl_util.jar edu.berlin.htw.ds.cg.Main

