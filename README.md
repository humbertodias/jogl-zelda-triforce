# Zelda Triforce with JOGL

Simple Zelda Triforce animation with Java Binding for the OpenGL

### Prerequires

1. Git 2.6+
2. Maven 3+
3. Java 8+


### How to Play

Clone

```
git clone https://github.com/humbertodias/zelda-triforce-jogl.git
```

Inside the folder

```
cd zelda-triforce-jogl
```

Run

```
mvn compile exec:java -Dexec.mainClass="jogl.zelda.triforce.Main"
```


### Output
![Preview](doc/triforce.gif)


### Git Animated
```
ffmpeg -i triforce.mov -s 800x600 -pix_fmt rgb24 -r 10 -f gif - | gifsicle --optimize=3 --delay=1 > triforce.gif
```

### References

[https://jogamp.org/wiki/index.php/Maven](https://jogamp.org/wiki/index.php/Maven)

[http://www.tutorialspoint.com/jogl/](http://www.tutorialspoint.com/jogl/)