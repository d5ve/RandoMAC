OUT OF DATE
===========

This repo is very out-of-date, and shouldn't be relied on to work on any phones.

It didn't seem to work well even when it worked at all.

RandoMAC
========

Android app to change WIFI MAC to random values - requires root.

Rationale
---------

Android phones with WIFI turned on will constantly poll for networks. This
polling operation exposes the WIFI adapter hardware MAC address, so businesses
can track phones and their users throughout the day. Companies are now buying
this data from stores etc. to build up location profiles of phone users.

This app is an attempt to confuse this tracking slightly by changing the WIFI
MAC address to random, but assigned, values. Initially this randomisation will
happen with a button press of the app, but the intent is to have the app as a
service, changing the MAC automatically throughtout the day.

Download APK
------------

APK built from current source is at
https://github.com/d5ve/RandoMAC/raw/master/apk/RandoMAC-debug.apk

This requires a rooted phone.

Status
------

Very much WIP.

Currently, the app starts, and attempts to change the MAC address. This doesn't
always work, as you can't change the MAC for a running interface, and
restarting the interface resets the MAC back to the hardware value. There is a
small time during the restart process where the value can be changed.

Some WIFI networks don't like it if the MAC changes during a session, as it
looks like the MAX is part of the shared key.

Caveats
-------

This is the first android app I've developed and the first time I've even
looked at Java since university.

My testing is performed on a rooted Nexus 4 running Cyanogenmod 10.1. Different
phones and OS will behave differently.

Dev environment setup
---------------------

I followed the instructions from the following pages, specifically the ones for
a non-Eclipse setup on OSX.

https://developer.android.com/training/basics/firstapp/creating-project.html

https://developer.android.com/sdk/index.html

https://developer.android.com/sdk/installing/index.html

https://developer.android.com/sdk/installing/adding-packages.html

```
$ android create project --target android-17 --name "RandoMAC" --path ~/src/"RandoMAC" --activity RandoMAC --package com.d5ve.randomac
$ cd ~/src/RandoMAC
$ git init

```

I also had to copy android-support-v4.jar from where it was installed into the app's libs dir.

```
$ cd ~/src/RandoMAC/libs
$ cp ~/dev_tools/android-sdk-macosx/extras/android/support/v4/android-support-v4.jar .

```

This app uses *roottools* from https://code.google.com/p/roottools/ to simplify
running system commands as root.

```
$ cd ~/src/RandoMAC/libs
$ curl -O https://roottools.googlecode.com/files/RootTools2.6.jar

```

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

License
-------

RandoMAC is free software. It comes without any warranty, to the extent
permitted by applicable law.

RandoMAC is released under the WTFPL Version 2.0 license 

    http://sam.zoy.org/wtfpl/COPYING

0. You just DO WHAT THE FUCK YOU WANT TO.

