package small_game;
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
public class Game extends Applet implements Runnable
{
	Thread gameThread;
	int width=400,height=400,MAX=1;
	int currentX[]=new int[MAX];
	int currentY[]=new int [MAX];
    BufferedImage bufferdImg;	
    Graphics2D bufferdImgSurface;
	public void start()
	{
		Thread gameThread=new Thread(this);
		gameThread.start();
	}
	public void init()
	{
	    bufferdImg = (BufferedImage)createImage(width, height);
	    bufferdImgSurface = bufferdImg.createGraphics();
		currentX[0]=0;
		currentY[0]=0;
		directionX[0]=1;
		directionY[0]=0;
	}
	public void run()
	{
		while(true) {
			start=System.currentTimeMillis();
			for(int i=0;i<MAX;i++)
			{
				if(directionX[i]==1)
					currentX[i]+=speed;
				if(directionX[i]==0)
					currentX[i]-=speed;
				if(currentX[i]<=0)
					directionX[i]=1;
				if(currentX[i]+20>=width)
					directionX[i]=0;
				if(directionY[i]==1)
					currentY[i]+=speed;
				if(directionY[i]==0)
					currentY[i]-=speed;
				if(currentY[i]<=0)
					directionY[i]=1;
				if(currentY[i]+20>=height)
					directionY[i]=0;
			}
			repaint();
			tick_end_time=System.currentTimeMillis();
            tick_duration = tick_end_time - start;
            sleep_duration = MAX_MS_PER_FRAME - tick_duration;
            if(sleep_duration<MIN_SLEEP_TIME)
            {
            	sleep_duration=MIN_SLEEP_TIME;
            }
            fps=1000/(sleep_duration+tick_duration);
            try {
            	Thread.sleep(sleep_duration);
            	
            }catch(InterruptedException e) {}
		}
	}
	public void paint(Graphics g)
	{
		update(g);
	}
	public void update(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		bufferdImgSurface.setBackground(Color.black);
		bufferdImgSurface.clearRect(0, 0, width, height);
		bufferdImgSurface.setColor(Color.green);
		bufferdImgSurface.fillOval(currentX[0], currentY[0], 20, 20);
		g2.drawImage(BufferImg,0,0,this);
//		g2.setBackground(Color.black);
//		g2.clearRect(0, 0, width, height);
//		g2.setColor(Color.green);
//		g2.fillOval(currentX[0], currentY[0], 20, 20);
	}
//	CODE ="Game.class"
//	NAME ="Game Tutorial"
//	WIDTH =400
//	HEIGHT=400
//	ALIGN =MIDDLE
	int directionX[]=new int[MAX];
	int directionY[]=new int[MAX];
	long start=0;
	long tick_end_time;
	long tick_duration;
	long sleep_duration;
	static final int MIN_SLEEP_TIME=10;
	static final int MAX_FPS=20;
	static final int MAX_MS_PER_FRAME=1000/MAX_FPS;
	float fps=0;

}
