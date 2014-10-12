mist
====

Обновленный код для DesktopLauncher.java

```java
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MistGame.getWidth();
		config.height = MistGame.getHeight();
		config.title = MistGame.GAME_TITLE;
		new LwjglApplication(new MistGame(), config);
	}
}
```

<i>prepare9</i> commit preview<br>
[GIF (5.41mb traffic)](gitdata/prepare9-long.gif)

<i>prepare7</i> commit preview
![prepare7](http://ipic.su/img/img7/fs/kiss_219kb.1412890735.png)

<i>prepare5.2</i> commit preview
![prepare5.2](gitdata/prepare5.gif)
