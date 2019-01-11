#!/bin/sh
cd ./products && chmod +x build.sh && ./build.sh
cd ..
cd ./orders   && chmod +x build.sh && ./build.sh