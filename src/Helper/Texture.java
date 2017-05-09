package Helper;

import java.awt.image.BufferedImage;

public class Texture {

	public BufferedImage[] player = new BufferedImage[5];
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] coin = new BufferedImage[5];
	public BufferedImage[] goomba = new BufferedImage[3];
	public BufferedImage[] mushroom = new BufferedImage[2];
	public BufferedImage[] mysticBlocks = new BufferedImage[5];
	public BufferedImage[] flag = new BufferedImage[2];
	public BufferedImage[] background = new BufferedImage[9];
	public BufferedImage[] fireball = new BufferedImage[4];
	public BufferedImage[] pipe = new BufferedImage[2];
	public BufferedImage[] playerLeft = new BufferedImage[4];
	public BufferedImage[] boss = new BufferedImage[8];
	public BufferedImage[] bossBullet = new BufferedImage[4];
	
	public Texture(){
		ImageLoader loader = new ImageLoader();
		
		for(int i=0;i<player.length;i++)	player[i] = loader.loadImage("/mario/" + i + ".png");
		for(int i=0;i<block.length;i++)		block[i] = loader.loadImage("/textures/" + i + ".png");
		for(int i=0;i<coin.length;i++)		coin[i] = loader.loadImage("/coin/" + i + ".png");
		for(int i=0;i<goomba.length;i++)	goomba[i] = loader.loadImage("/goomba/"+ i + ".png");
		for(int i=0;i<mushroom.length;i++)	mushroom[i] = loader.loadImage("/mushroom/" + i + ".png");
		for(int i=0;i<mysticBlocks.length;i++)	mysticBlocks[i] = loader.loadImage("/mysticBlocks/" + i + ".png");
		for(int i=0;i<background.length;i++)	background[i] = loader.loadImage("/background/" + i + ".png"); 	
		for(int i=0;i<1;i++)				flag[i] = loader.loadImage("/flag/" + i + ".png");
		for(int i=0;i<fireball.length;i++)	fireball[i] = loader.loadImage("/fireball/" + i + ".png");
		for(int i=0;i<pipe.length;i++)		pipe[i] = loader.loadImage("/pipe/" + i + ".png");
		for(int i=0;i<playerLeft.length;i++)	playerLeft[i] = loader.loadImage("/marioLeft/" + i + ".png");
		for(int i=0;i<boss.length;i++)		boss[i] = loader.loadImage("/boss/" + i + ".png");
		for(int i=0;i<bossBullet.length;i++)	bossBullet[i] = loader.loadImage("/bossBullet/" + i + ".png");
	}	
}
