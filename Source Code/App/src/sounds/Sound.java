package sounds;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	// method for sound playing
		public static void playSound(String soundName)
		 {
		   try 
		   {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
		    Clip clip = AudioSystem.getClip( );
		    clip.open(audioInputStream);
		    clip.start( );
		   }
		   catch(Exception ex)
		   {
		     System.out.println("Error with playing sound.");
		     ex.printStackTrace( );
		   }
		 }

}
