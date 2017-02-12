# OpenHeosControl

OpenHeosControl is a lightweight desktop application to control HEOS sound systems around your home.

HEOS (or Denon) has not released any controller software for desktop machines; the mobile apps are the only option users have.
Since Denon does not feel obliged to supply such software with their >€250 sound systems, I guess I'll do it myself.

This software makes use of [this](01.2015_HEOS---CLI_PROTOCOL_V01.pdf) protocol. 

# Version 0.2

![Version 0.2](https://github.com/bart-kneepkens/OpenHeosControl/blob/master/02.gif "Version 0.2")

Version 0.2 of OpenHeosControl is quite an improvement compared to the previous version. It can now automatically find HEOS systems on the users's network, connect to them and control all players on this network. Furthermore the UI has been upgraded to be responsive to changes that the HEOS can broadcast; i.e. if you change your volume on your HEOS or Spotify app, it will be visible in OpenHeosController. The UI also shows more information, like song progress, current song and artist.

# Version 0.1 : Barebone
Version 0.1 of OpenHeosControl only contains minimum functionality. Users can play, pause and stop the music on their HEOS system, as well as change the volume from a (not so friendly yet 🤔) UI.

HEOS systems can not be automatically discovered yet. It needs to be connected to by IP address.
