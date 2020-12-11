package panel_glowny;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Audio
{
	private Clip clip; 
	
	public Audio(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
		AudioFormat baseFormat = ais.getFormat();
		AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
				baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
		
		AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
		clip = AudioSystem.getClip();
		clip.open(dais);
	}
	
	
	
	public void play(float volume, int framePos)
	{
		playClip(volume, framePos);
		clip.start();
	}
	

	public void stop()
	{
		if(clip.isRunning())
			clip.stop();
	}
	

	public void close()
	{
		stop();
		clip.close();
	}
	
	public void playAfterPause(int frame)
	{
		clip.setFramePosition(frame);
		clip.start();
	}
	
	public int getFramePosition()
	{
		return clip.getFramePosition();
	}
	
	public boolean getIsRunning()
	{
		return clip.isRunning();
	}
	
	
	public void changeVolume(float volume)
	{
		volumeControl(volume);
	}
	
	private void playClip(float volume, int framePos)
	{
		if(clip == null)
			return;
		
		stop();
		volumeControl(volume);
		
		clip.setFramePosition(framePos);
	}
	
	public void loop()
	{    
	        clip.setFramePosition(0);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public String clipLength()
	{
		long frames = clip.getFrameLength();
		double lengthInSeconds = (frames+0.0) / clip.getFormat().getFrameRate();  
		//long length = TimeUnit.SECONDS.toMinutes((long)lengthInSeconds);
		double lengthFormated = Math.floor(lengthInSeconds/60) + (lengthInSeconds%60)/100;
		String length = String.format("%.2f", lengthFormated);
		//length = Math.round(length * 100.0) / 100.0;
		return length;
	}
	
	
	
	private void volumeControl(float volume)
	{
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		try
		{
			gainControl.setValue(volume);
			System.out.println(volume);
		}
		catch(IllegalArgumentException e)
		{
			//e.printStackTrace();
			if(volume > gainControl.getMaximum())
				volume = gainControl.getMaximum();
			else if(volume < gainControl.getMinimum())
				volume = gainControl.getMinimum();
		}	
	}
}
