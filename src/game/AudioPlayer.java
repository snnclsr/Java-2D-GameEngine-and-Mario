package game;


import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class AudioPlayer {

	public static Map<String,Music> musicMap = new HashMap<String,Music>();
	
	public static void loadSounds(){
		try {
			musicMap.put("coinMusic",new Music("music/coin.ogg"));
			musicMap.put("jumpMusic",new Music("music/jump-small.ogg"));
			musicMap.put("intro", new Music("music/intro.ogg"));
			musicMap.put("powerUp",new Music("music/smb_powerup.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
}
