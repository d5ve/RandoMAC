RandoMAC
========

Android app to change WIFI MAC to random values.

Rationale
---------

Dev environment setup
---------------------

I followed the instructions from the following pages, specifically the ones for
a non-Eclipse setup on OSX.

https://developer.android.com/training/basics/firstapp/creating-project.html
https://developer.android.com/sdk/index.html
https://developer.android.com/sdk/installing/index.html
https://developer.android.com/sdk/installing/adding-packages.html

Building and installing
-----------------------

```
$ cd ~/src/RandoMAC/
# Start emulator
$ android avd & 

# Build the project.
$ ant debug

# Install on emulator (or plugged in phone)
$ adb install -r bin/RandoMAC-debug.apk

```

Status
------

Caveats
-------

License
-------

RandoMAC is free software. It comes without any warranty, to the extent
permitted by applicable law.

RandoMAC is released under the WTFPL Version 2.0 license 

    http://sam.zoy.org/wtfpl/COPYING

0. You just DO WHAT THE FUCK YOU WANT TO.

