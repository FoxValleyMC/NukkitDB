[![HitCount](http://hits.dwyl.io/TimelessMC/NukkitDB.svg)](http://hits.dwyl.io/TimelessMC/NukkitDB)

<img src="https://i.imgur.com/RIWMp43.png" alt="icon" width="100px" />

# Important!
This plugin requires MongoDB java drivers which conveniently has been provided within the `/libs` directory ***OF THIS REPO***

# Installation
<img src="https://imgur.com/QmG58Yz.png" alt="image"/>

- Create a `libs` directory in your server root. (i.e) `C://users/yourname/desktop/nukkitX-server/libs`... It should be alongside your `run.bat` file  

- place `bson-3.11.0.jar`, `mongodb-driver-3.11.0.jar`, and `mongodb-driver-core-3.11.0.jar` inside the `/libs` you just created

- Edit your `run.bat` and write:
```batch
java -cp YOUR_NUKKIT_JAR.jar;libs/* cn.nukkit.Nukkit
```
This command will load the mongoDB java drivers into the servers main package

# Api Usage
```text
Api usage and examples coming soon
```

# credits
Icon made by <a href="https://www.flaticon.com/authors/good-ware" title="Good Ware">Good Ware</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>